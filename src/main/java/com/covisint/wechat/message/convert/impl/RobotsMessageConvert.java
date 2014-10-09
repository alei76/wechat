/************************************************************************************
 * @File name   :      RobotsMessageConvert.java
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
 * 2014-5-30 上午10:12:04			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.message.convert.impl;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.covisint.wechat.data.model.WxMessageHis;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.exception.MessageException;
import com.covisint.wechat.exception.WebServiceException;
import com.covisint.wechat.irobot.service.IRobotService;
import com.covisint.wechat.message.convert.MessageConvertService;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.weixin.model.ArticleItem;
import com.covisint.wechat.weixin.model.Articles;
import com.covisint.wechat.weixin.model.NewsOutMessage;
import com.covisint.wechat.weixin.model.TextOutMessage;
import com.covisint.wechat.weixin.util.XStreamFactory;

/**
 * 机器人消息处理
 */
@Service
@Repository("robotsMessageConvert")
public class RobotsMessageConvert implements MessageConvertService {
	private static final Logger logger = LoggerFactory.getLogger(RobotsMessageConvert.class);
	@Autowired
	private IRobotService iRobotService;
	@Value("${weixin.hyperlink}")
	private String hyperlink;
	@Value("${weixin.platform}")
	private String platform;

	@Override
	public String getMessage(WxReplymsgTemplate template, String openId, String accountNo, String accountId) throws MessageException {
		try {
			if (logger.isInfoEnabled()) {
				logger.info("开始请求小i机器人消息模板内容");
			}
			String keyword = template.getKeyword();
			String brand = "雪佛兰";// 目前默认品牌为雪佛兰，动态可在微信账号管理中，增加字段来配置。
			Map<String, String> answer = iRobotService.getAnswer(keyword, openId, brand);
			if (answer != null) {
				String type = answer.get(IRobotService.MESSAGE_TYPE);
				String content = answer.get(IRobotService.MESSAGE_CONTENT);
				if (WxReplymsgTemplate.TYPE_NEWS.equals(type)) {// 如果是图文消息
					return this.newsMessage(content, openId, accountNo, accountId);
				} else if (WxReplymsgTemplate.TYPE_TEXT.equals(type)) {// 如果是文本消息
					return this.textMessage(content, openId, accountNo, accountId);
				}
			}
			return null;
		} catch (WebServiceException e) {
			throw new MessageException(e);
		}
	}

	/**
	 * 拼装文本消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private String textMessage(String content, String openId, String accountNo, String accountId) throws MessageException {
		content = this.replaceLink(content, openId, accountId);// 替换文本消息的内容，支持A标签，拦截A标签的路径
		TextOutMessage textMessage = new TextOutMessage();
		textMessage.setMsgType("text");
		textMessage.setContent(content);
		textMessage.setCreateTime(Calendar.getInstance().getTimeInMillis() / 1000);
		textMessage.setFromUserName(accountNo);
		textMessage.setToUserName(openId);
		textMessage.setMsgSource(WxMessageHis.SOURCE_IROBOT);
		return XStreamFactory.toXml(textMessage);
	}

	/**
	 * 拼装图文消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@SuppressWarnings("unchecked")
	private String newsMessage(String content, String openId, String accountNo, String accountId) throws MessageException {
		NewsOutMessage newsMessage = new NewsOutMessage();
		List<Map<String, Object>> dataList = JacksonJsonMapper.jsonToObject(content, List.class);
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		for (Map<String, Object> news : dataList) {
			ArticleItem item = new ArticleItem();
			String description = (String) news.get("description");
			String title = (String) news.get("title");
			String image = (String) news.get("image");
			String url = (String) news.get("url");// TODO：可增加拦截路径
			item.setDescription(description);
			item.setPicurl(image);
			item.setTitle(title);
			item.setUrl(url);
			items.add(item);
		}
		Articles articles = new Articles();
		articles.setItems(items);
		newsMessage.setArticles(articles);
		newsMessage.setMsgType("news");
		newsMessage.setCreateTime(Calendar.getInstance().getTimeInMillis() / 1000);
		newsMessage.setToUserName(openId);
		newsMessage.setFromUserName(accountNo);
		newsMessage.setMsgSource(WxMessageHis.SOURCE_IROBOT);
		return XStreamFactory.toXml(newsMessage);
	}

	/**
	 * 替换[link]为<a>
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-6
	 * @param content
	 * @return
	 */
	public String replaceLink(String content, String openId, String accountId) throws MessageException {
		String str = content;
		Pattern p = Pattern.compile("\\[link\\s.*?url\\s*=\\s*\'?\"?([^(\\s\")]+)\\s*\'?\"?[^]]*](.*?)\\[/link]");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String source = m.group(0);
			String url = m.group(1);
			String text = m.group(2);
			String replace = platform + hyperlink;
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(replace);
			uriBuilder.queryParam("r", url);
			uriBuilder.queryParam("o", openId);
			uriBuilder.queryParam("a", accountId);
			UriComponents redirectUri = uriBuilder.build().encode();
			replace = redirectUri.toUriString();
			String target = "<a href=\"" + replace + "\">" + text + "</a>";
			str = str.replace(source, target);
		}
		return str;
	}

}
