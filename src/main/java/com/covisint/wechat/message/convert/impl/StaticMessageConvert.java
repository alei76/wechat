/************************************************************************************
 * @File name   :      StaticMessageConvert.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-30
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2014-5-30 上午10:10:22			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.message.convert.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.covisint.wechat.content.service.AttachmentService;
import com.covisint.wechat.content.service.TemplateService;
import com.covisint.wechat.data.model.WxMediaAtta;
import com.covisint.wechat.data.model.WxMessageHis;
import com.covisint.wechat.data.model.WxNewsMessage;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.exception.MessageException;
import com.covisint.wechat.message.convert.MessageConvertService;
import com.covisint.wechat.message.freemarker.StringTemplateProcess;
import com.covisint.wechat.weixin.model.ArticleItem;
import com.covisint.wechat.weixin.model.Articles;
import com.covisint.wechat.weixin.model.Music;
import com.covisint.wechat.weixin.model.MusicOutMessage;
import com.covisint.wechat.weixin.model.NewsOutMessage;
import com.covisint.wechat.weixin.model.TextOutMessage;
import com.covisint.wechat.weixin.util.XStreamFactory;

import freemarker.template.TemplateException;

/**
 * 静态消息处理
 */
@Service
@Repository("staticMessageConvert")
public class StaticMessageConvert implements MessageConvertService {
	private static final Logger logger = LoggerFactory.getLogger(StaticMessageConvert.class);
	@Value("${weixin.hyperlink}")
	private String hyperlink;
	@Value("${weixin.platform}")
	private String platform;
	@Autowired
	private StringTemplateProcess templateProcess;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private AttachmentService attachmentService;

	@Override
	public String getMessage(WxReplymsgTemplate template, String openId, String accountNo, String accountId) throws MessageException {
		if (logger.isInfoEnabled()) {
			logger.info("开始获取静态消息模板内容");
		}
		String type = template.getType();
		if (WxReplymsgTemplate.TYPE_TEXT.equals(type)) {
			return this.textMessage(template, openId, accountNo, accountId);
		} else if (WxReplymsgTemplate.TYPE_IMAGE.equals(type)) {
			return this.imageMessage(template, openId, accountNo, accountId);
		} else if (WxReplymsgTemplate.TYPE_VOICE.equals(type)) {
			return this.audioMessage(template, openId, accountNo, accountId);
		} else if (WxReplymsgTemplate.TYPE_NEWS.equals(type)) {
			return this.newsMessage(template, openId, accountNo, accountId);
		} else if (WxReplymsgTemplate.TYPE_VIDEO.equals(type)) {
			return this.videoMessage(template, openId, accountNo, accountId);
		}
		return null;
	}

	/**
	 * 拼装图文消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private String newsMessage(WxReplymsgTemplate template, String openId, String accountNo, String accountId) throws MessageException {
		NewsOutMessage newOutMessage = new NewsOutMessage();
		newOutMessage.setCreateTime(Calendar.getInstance().getTimeInMillis() / 1000);
		newOutMessage.setToUserName(openId);
		newOutMessage.setFromUserName(accountNo);
		newOutMessage.setMsgType("news");
		newOutMessage.setMsgSource(WxMessageHis.SOURCE_TEMPLATE);
		Articles articles = new Articles();
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		List<WxNewsMessage> newsItems = templateService.listNewsMsgItems(template.getTemplateId());
		for (WxNewsMessage message : newsItems) {
			ArticleItem item = new ArticleItem();
			item.setDescription(message.getDescription());
			item.setPicurl(platform + "/anon/image/prev/" + message.getAttaId());
			item.setTitle(message.getTitle());
			item.setUrl(message.getTargetHref());// TODO：增加该url的拦截
			items.add(item);
		}
		articles.setItems(items);
		newOutMessage.setArticles(articles);
		String result = XStreamFactory.toXml(newOutMessage);
		if (logger.isInfoEnabled()) {
			logger.info("多图文静态消息：" + result);
		}
		return result;
	}

	/**
	 * 拼装文本消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private String textMessage(WxReplymsgTemplate template, String openId, String accountNo, String accountId) throws MessageException {
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("openId", openId);
			data.put("keyword", template.getKeyword());
			String content = templateProcess.getContent(template.getTemplateId(), template.getContent(), data);
			TextOutMessage textMessage = new TextOutMessage();
			content = this.replaceHyperlink(content, openId, accountId);// 正则判断，如果有A标签，则添加路径拦截
			textMessage.setContent(content);
			textMessage.setCreateTime(Calendar.getInstance().getTimeInMillis() / 1000);
			textMessage.setToUserName(openId);
			textMessage.setFromUserName(accountNo);
			textMessage.setMsgType("text");
			textMessage.setMsgSource(WxMessageHis.SOURCE_TEMPLATE);
			String result = XStreamFactory.toXml(textMessage);
			if (logger.isInfoEnabled()) {
				logger.info("文本静态消息：" + result);
			}
			return result;
		} catch (IOException e) {
			throw new MessageException(e);
		} catch (TemplateException e) {
			throw new MessageException(e);
		}
	}

	/**
	 * 拼装图片消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private String imageMessage(WxReplymsgTemplate template, String openId, String accountNo, String accountId) throws MessageException {
		System.out.println(template);
		return null;
	}

	/**
	 * 拼装音频消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private String audioMessage(WxReplymsgTemplate template, String openId, String accountNo, String accountId) throws MessageException {
		// 图片，预览图路径 /anon/image/prev/{attaId}
		// 视频，音频路径 /anon/media/prev/{attaId}
		String attaId = template.getAttaId();
		WxMediaAtta mediaAtta = attachmentService.getAtta(attaId);
		if (null != mediaAtta) {
			MusicOutMessage musicOutMessage = new MusicOutMessage();
			musicOutMessage.setCreateTime(Calendar.getInstance().getTimeInMillis() / 1000);
			musicOutMessage.setToUserName(openId);
			musicOutMessage.setFromUserName(accountNo);
			musicOutMessage.setMsgType("music");
			musicOutMessage.setMsgSource(WxMessageHis.SOURCE_TEMPLATE);
			Music music = new Music();
			String musicUrl = platform + "/anon/media/prev/" + attaId;
			music.sethQMusicUrl(musicUrl);
			music.setMusicUrl(musicUrl);
			music.setTitle(mediaAtta.getName());
			music.setDescription(mediaAtta.getName());
			musicOutMessage.setMusic(music);
			String result = XStreamFactory.toXml(musicOutMessage);
			if (logger.isInfoEnabled()) {
				logger.info("音频消息：" + result);
			}
			return result;
		}
		return null;
	}

	/**
	 * 拼装视频消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private String videoMessage(WxReplymsgTemplate template, String openId, String accountNo, String accountId) throws MessageException {
		// 图片，预览图路径 /anon/image/prev/{attaId}
		// 视频，音频路径 /anon/media/prev/{attaId}
		String attaId = template.getAttaId();
		WxMediaAtta mediaAtta = attachmentService.getAtta(attaId);
		if (null != mediaAtta) {
			NewsOutMessage newOutMessage = new NewsOutMessage();
			newOutMessage.setCreateTime(Calendar.getInstance().getTimeInMillis() / 1000);
			newOutMessage.setToUserName(openId);
			newOutMessage.setFromUserName(accountNo);
			newOutMessage.setMsgType("news");
			newOutMessage.setMsgSource(WxMessageHis.SOURCE_TEMPLATE);
			Articles articles = new Articles();
			List<ArticleItem> items = new ArrayList<ArticleItem>();
			ArticleItem item = new ArticleItem();
			item.setDescription("点击播放");// 默认提示语
			item.setPicurl(platform + "/anon/image/prev/" + attaId);
			item.setTitle(mediaAtta.getName());
			String redirectUrl = platform + "/api-wechat/mediaplay/video/" + attaId;// 视频播放路径

			// String replace = platform + hyperlink;
			// UriComponentsBuilder uriBuilder =
			// UriComponentsBuilder.fromHttpUrl(replace);
			// uriBuilder.queryParam("r", redirectUrl);
			// uriBuilder.queryParam("o", openId);
			// uriBuilder.queryParam("a", accountId);
			// UriComponents redirectUri = uriBuilder.build().encode();
			// replace = redirectUri.toUriString();// 增加路径拦截

			// item.setUrl(replace);
			item.setUrl(redirectUrl);
			items.add(item);
			articles.setItems(items);
			newOutMessage.setArticles(articles);
			String result = XStreamFactory.toXml(newOutMessage);
			if (logger.isInfoEnabled()) {
				logger.info("视频消息：" + result);
			}
			return result;
		}
		return null;
	}

	/**
	 * 替换A标签
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-20
	 */
	public String replaceHyperlink(String content, String openId, String accountId) throws MessageException {
		String str = content;
		Pattern p = Pattern.compile("<a\\s.*?href\\s*=\\s*\'?\"?([^(\\s\")]+)\\s*\'?\"?[^>]*>(.*?)</a>");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String redirectUrl = m.group(1);
			String replace = platform + hyperlink;
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(replace);
			uriBuilder.queryParam("r", redirectUrl);
			uriBuilder.queryParam("o", openId);
			uriBuilder.queryParam("a", accountId);
			UriComponents redirectUri = uriBuilder.build().encode();
			replace = redirectUri.toUriString();
			str = str.replace(redirectUrl, replace);
		}
		return str;
	}
}
