/************************************************************************************
 * @File name   :      MessageHisServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-17
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
 * 2014-6-17 上午09:49:12			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.member.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.constant.DictCode;
import com.covisint.wechat.data.dao.WxMessageHisDao;
import com.covisint.wechat.data.model.WxMessageHis;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.member.service.MessageHisService;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.DateUtils;
import com.covisint.wechat.utils.IdentifierUtils;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.weixin.model.ImageOutMessage;
import com.covisint.wechat.weixin.model.InMessage;
import com.covisint.wechat.weixin.model.MusicOutMessage;
import com.covisint.wechat.weixin.model.NewsOutMessage;
import com.covisint.wechat.weixin.model.OutMessage;
import com.covisint.wechat.weixin.model.TextOutMessage;
import com.covisint.wechat.weixin.model.VideoOutMessage;
import com.covisint.wechat.weixin.model.VoiceOutMessage;
import com.covisint.wechat.weixin.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;

/**
 *
 */
@Service
public class MessageHisServiceImpl implements MessageHisService {
	@Autowired
	private WxMessageHisDao wxMessageHisDao;

	@Override
	public boolean checkExists(String msgId) {
		WxMessageHis messageHis = this.getMsgByMsgId(msgId);
		return messageHis == null;
	}

	@Override
	public WxMessageHis getMsgByMsgId(String msgId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("msgId", msgId);
		return wxMessageHisDao.getMsgHis(paramMap);
	}

	@Override
	public void saveMsgReply(InMessage inMessage, String outMessage) {
		String accountId = inMessage.getAccountId();
		String msgId = String.valueOf(inMessage.getMsgId());
		String toUser = inMessage.getToUserName();
		String fromUser = inMessage.getFromUserName();
		WxMessageHis userMsg = new WxMessageHis();
		userMsg.setAccountId(accountId);
		userMsg.setContent(inMessage.getContent());
		userMsg.setCreateTime(String.valueOf(inMessage.getCreateTime()));
		userMsg.setMessageHisId(IdentifierUtils.getId().generate().toString());
		userMsg.setMsgId(msgId);
		userMsg.setMsgSource(WxMessageHis.SOURCE_USER);
		userMsg.setMsgType(WxReplymsgTemplate.TYPE_TEXT);
		userMsg.setOpenId(fromUser);
		userMsg.setReceiver(toUser);
		wxMessageHisDao.insert(userMsg);
		if (!StringUtils.isEmpty(outMessage)) {// 没有回复消息
			WxMessageHis replyMsg = this.getOutMsgHis(outMessage, accountId, toUser, fromUser, msgId);
			wxMessageHisDao.insert(replyMsg);
		}
	}

	@Override
	public WxMessageHis getOutMsgHis(String outMsgXml, String accountId, String toUser, String fromUser, String msgId) {
		XStream xstream = XStreamFactory.getStream();
		xstream.alias("xml", OutMessage.class);
		xstream.ignoreUnknownElements();
		xstream.processAnnotations(OutMessage.class);
		OutMessage out = (OutMessage) xstream.fromXML(outMsgXml);
		WxMessageHis replyMsg = new WxMessageHis();
		replyMsg.setAccountId(accountId);
		replyMsg.setCreateTime(String.valueOf(out.getCreateTime()));
		replyMsg.setMessageHisId(IdentifierUtils.getId().generate().toString());
		replyMsg.setMsgSource(out.getMsgSource());
		String msgType = getMsgType(out.getMsgType());
		String content = getMsgContent(msgType, outMsgXml);
		replyMsg.setContent(content);
		replyMsg.setMsgType(msgType);
		replyMsg.setOpenId(toUser);
		replyMsg.setReceiver(fromUser);
		replyMsg.setReplyMsgId(msgId);
		return replyMsg;
	}

	@Override
	public Page<Map<String, Object>> pageMsgHis(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		paramMap.put("catalogCode", DictCode.MESSAGE_SOURCE);
		return wxMessageHisDao.pageMsgHis(paramMap, current, pagesize);
	}

	@Override
	public Page<Map<String, Object>> pageMsgCollect(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		String startDay = (String) paramMap.get("startDay");
		if (!StringUtils.isEmpty(startDay)) {
			Date start = DateUtils.parse(startDay);
			long startTime = start.getTime() / 1000;
			paramMap.put("startTime", startTime);
		}
		String endDay = (String) paramMap.get("endDay");
		if (!StringUtils.isEmpty(endDay)) {
			Date end = DateUtils.parse(endDay);
			end = DateUtils.add(Calendar.DAY_OF_MONTH, end, 1);
			long endTime = end.getTime() / 1000;
			paramMap.put("endTime", endTime);
		}
		paramMap.put("catalogCode", DictCode.MESSAGE_SOURCE);
		return wxMessageHisDao.pageMsgCollect(paramMap, current, pagesize);
	}

	/**
	 * 获取消息类型
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public static String getMsgType(String msgType) {
		if ("text".equals(msgType)) {// 文本
			return WxReplymsgTemplate.TYPE_TEXT;
		} else if ("news".equals(msgType)) {// 图文
			return WxReplymsgTemplate.TYPE_NEWS;
		} else if ("music".equals(msgType)) {// 音乐
			return WxReplymsgTemplate.TYPE_MUSIC;
		} else if ("image".equals(msgType)) {// 图片
			return WxReplymsgTemplate.TYPE_IMAGE;
		} else if ("voice".equals(msgType)) {// 语音
			return WxReplymsgTemplate.TYPE_VOICE;
		} else if ("video".equals(msgType)) {// 视频
			return WxReplymsgTemplate.TYPE_VIDEO;
		} else {
			return null;
		}
	}

	/**
	 * 获取消息内容
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public static String getMsgContent(String msgType, String outXml) {
		XStream xstream = XStreamFactory.getStream();
		if (WxReplymsgTemplate.TYPE_TEXT.equals(msgType)) {// 文本消息
			xstream.alias("xml", TextOutMessage.class);
			xstream.ignoreUnknownElements();
			xstream.processAnnotations(TextOutMessage.class);
			TextOutMessage out = (TextOutMessage) xstream.fromXML(outXml);
			return out.getContent();// 获取文本内容
		} else if (WxReplymsgTemplate.TYPE_NEWS.equals(msgType)) {// 图文消息
			xstream.alias("xml", NewsOutMessage.class);
			xstream.ignoreUnknownElements();
			xstream.processAnnotations(NewsOutMessage.class);
			NewsOutMessage out = (NewsOutMessage) xstream.fromXML(outXml);
			return JacksonJsonMapper.objectToJson(out.getArticles().getItems(), false);// 获取图文项，用json格式
		} else if (WxReplymsgTemplate.TYPE_MUSIC.equals(msgType)) {// 音乐消息
			xstream.alias("xml", MusicOutMessage.class);
			xstream.ignoreUnknownElements();
			xstream.processAnnotations(MusicOutMessage.class);
			MusicOutMessage out = (MusicOutMessage) xstream.fromXML(outXml);
			return JacksonJsonMapper.objectToJson(out.getMusic(), false);// 获取音乐对象，用json格式
		} else if (WxReplymsgTemplate.TYPE_IMAGE.equals(msgType)) {// 图片消息
			xstream.alias("xml", ImageOutMessage.class);
			xstream.ignoreUnknownElements();
			xstream.processAnnotations(ImageOutMessage.class);
			ImageOutMessage out = (ImageOutMessage) xstream.fromXML(outXml);
			return JacksonJsonMapper.objectToJson(out.getImage(), false);// 获取图片对象，用json格式
		} else if (WxReplymsgTemplate.TYPE_VOICE.equals(msgType)) {// 语音消息
			xstream.alias("xml", VoiceOutMessage.class);
			xstream.ignoreUnknownElements();
			xstream.processAnnotations(VoiceOutMessage.class);
			VoiceOutMessage out = (VoiceOutMessage) xstream.fromXML(outXml);
			return JacksonJsonMapper.objectToJson(out.getVoice(), false);// 获取语音对象，用json格式
		} else if (WxReplymsgTemplate.TYPE_VIDEO.equals(msgType)) {// 视频消息
			xstream.alias("xml", VideoOutMessage.class);
			xstream.ignoreUnknownElements();
			xstream.processAnnotations(VideoOutMessage.class);
			VideoOutMessage out = (VideoOutMessage) xstream.fromXML(outXml);
			return JacksonJsonMapper.objectToJson(out.getVideo(), false);// 获取视频对象，用json格式
		}
		return null;
	}
}
