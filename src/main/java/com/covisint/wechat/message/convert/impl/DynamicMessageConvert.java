/************************************************************************************
 * @File name   :      DynamicMessageConvert.java
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
 * 2014-5-30 上午10:11:25			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.message.convert.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.covisint.wechat.content.service.LinkResourceService;
import com.covisint.wechat.data.model.WxLinkResource;
import com.covisint.wechat.data.model.WxMessageHis;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.exception.MessageException;
import com.covisint.wechat.member.service.impl.MessageHisServiceImpl;
import com.covisint.wechat.message.convert.MessageConvertService;
import com.covisint.wechat.weixin.model.ImageOutMessage;
import com.covisint.wechat.weixin.model.MusicOutMessage;
import com.covisint.wechat.weixin.model.NewsOutMessage;
import com.covisint.wechat.weixin.model.OutMessage;
import com.covisint.wechat.weixin.model.TextOutMessage;
import com.covisint.wechat.weixin.model.VideoOutMessage;
import com.covisint.wechat.weixin.model.VoiceOutMessage;
import com.covisint.wechat.weixin.util.HttpUtil;
import com.covisint.wechat.weixin.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;

/**
 * 动态消息处理
 */
@Service
@Repository("dynamicMessageConvert")
public class DynamicMessageConvert implements MessageConvertService {
	private static final Logger logger = LoggerFactory.getLogger(DynamicMessageConvert.class);
	@Autowired
	private LinkResourceService linkResourceService;

	@Override
	public String getMessage(WxReplymsgTemplate template, String openId, String accountNo, String accountId) throws MessageException {
		String resourceId = template.getResourceId();
		WxLinkResource resource = linkResourceService.info(resourceId);
		String url = resource.getResourceHref();
		String originalXml = template.getOriginalXml();
		Map<String, String> signature = template.getSignature();
		if (logger.isInfoEnabled()) {
			logger.info("开始请求动态消息模板内容，参数为：" + originalXml + "，请求路径为：" + url + "，签名验证：" + signature);
		}
		if (!StringUtils.isEmpty(originalXml)) {
			try {
				String outMsgXml = HttpUtil.post(url, originalXml, signature);// post消息至该路径
				if (logger.isInfoEnabled()) {
					logger.info("远程服务器返回数据为：" + outMsgXml);
				}
				XStream xstream = XStreamFactory.getStream();
				xstream.alias("xml", OutMessage.class);
				xstream.ignoreUnknownElements();
				xstream.processAnnotations(OutMessage.class);
				OutMessage out = (OutMessage) xstream.fromXML(outMsgXml);// 转换验证返回的xml是否符合格式要求，不符合将抛异常
				String msgType = out.getMsgType();
				String type = MessageHisServiceImpl.getMsgType(msgType);
				if (WxReplymsgTemplate.TYPE_TEXT.equals(type)) {
					return this.textMessage(outMsgXml);
				} else if (WxReplymsgTemplate.TYPE_IMAGE.equals(type)) {
					return this.imageMessage(outMsgXml);
				} else if (WxReplymsgTemplate.TYPE_VOICE.equals(type)) {
					return this.audioMessage(outMsgXml);
				} else if (WxReplymsgTemplate.TYPE_VIDEO.equals(type)) {
					return this.videoMessage(outMsgXml);
				} else if (WxReplymsgTemplate.TYPE_MUSIC.equals(type)) {
					return this.musicMessage(outMsgXml);
				} else if (WxReplymsgTemplate.TYPE_NEWS.equals(type)) {
					return this.newsMessage(outMsgXml);
				}
				return null;
			} catch (ClientProtocolException e) {
				throw new MessageException(e);
			} catch (IOException e) {
				throw new MessageException(e);
			} catch (StreamException e) {
				throw new MessageException(e);
			}
		}
		return null;
	}

	private String newsMessage(String outMsgXml) {
		XStream xstream = XStreamFactory.getStream();
		xstream.alias("xml", NewsOutMessage.class);
		xstream.ignoreUnknownElements();
		xstream.processAnnotations(NewsOutMessage.class);
		NewsOutMessage out = (NewsOutMessage) xstream.fromXML(outMsgXml);
		out.setMsgSource(WxMessageHis.SOURCE_WEBSERVICE);
		return XStreamFactory.toXml(out);
	}

	private String musicMessage(String outMsgXml) {
		XStream xstream = XStreamFactory.getStream();
		xstream.alias("xml", MusicOutMessage.class);
		xstream.ignoreUnknownElements();
		xstream.processAnnotations(MusicOutMessage.class);
		MusicOutMessage out = (MusicOutMessage) xstream.fromXML(outMsgXml);
		out.setMsgSource(WxMessageHis.SOURCE_WEBSERVICE);
		return XStreamFactory.toXml(out);
	}

	private String videoMessage(String outMsgXml) {
		XStream xstream = XStreamFactory.getStream();
		xstream.alias("xml", VideoOutMessage.class);
		xstream.ignoreUnknownElements();
		xstream.processAnnotations(VideoOutMessage.class);
		VideoOutMessage out = (VideoOutMessage) xstream.fromXML(outMsgXml);
		out.setMsgSource(WxMessageHis.SOURCE_WEBSERVICE);
		return XStreamFactory.toXml(out);
	}

	private String audioMessage(String outMsgXml) {
		XStream xstream = XStreamFactory.getStream();
		xstream.alias("xml", VoiceOutMessage.class);
		xstream.ignoreUnknownElements();
		xstream.processAnnotations(VoiceOutMessage.class);
		VoiceOutMessage out = (VoiceOutMessage) xstream.fromXML(outMsgXml);
		out.setMsgSource(WxMessageHis.SOURCE_WEBSERVICE);
		return XStreamFactory.toXml(out);
	}

	private String imageMessage(String outMsgXml) {
		XStream xstream = XStreamFactory.getStream();
		xstream.alias("xml", ImageOutMessage.class);
		xstream.ignoreUnknownElements();
		xstream.processAnnotations(ImageOutMessage.class);
		ImageOutMessage out = (ImageOutMessage) xstream.fromXML(outMsgXml);
		out.setMsgSource(WxMessageHis.SOURCE_WEBSERVICE);
		return XStreamFactory.toXml(out);
	}

	private String textMessage(String outMsgXml) {
		XStream xstream = XStreamFactory.getStream();
		xstream.alias("xml", TextOutMessage.class);
		xstream.ignoreUnknownElements();
		xstream.processAnnotations(TextOutMessage.class);
		TextOutMessage out = (TextOutMessage) xstream.fromXML(outMsgXml);
		out.setMsgSource(WxMessageHis.SOURCE_WEBSERVICE);
		return XStreamFactory.toXml(out);
	}
}
