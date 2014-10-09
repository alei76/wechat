package com.covisint.wechat.weixin.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.exception.MessageException;
import com.covisint.wechat.member.service.FansService;
import com.covisint.wechat.message.service.EventMessageReplyService;
import com.covisint.wechat.message.service.KeyWordReplyService;
import com.covisint.wechat.utils.ThreadPoolUtils;
import com.covisint.wechat.weixin.model.InMessage;
import com.covisint.wechat.weixin.service.EventViewService;
import com.covisint.wechat.weixin.service.MessageProcessingHandler;
import com.covisint.wechat.weixin.thread.EventViewThread;

/**
 * 自定义实现消息处理
 * 
 */
@Service
public class MessageProcessingHandlerImpl implements MessageProcessingHandler {
	@Autowired
	private EventMessageReplyService eventMessageReplyService;
	@Autowired
	private KeyWordReplyService keyWordReplyService;
	@Autowired
	private FansService fansService;
	@Autowired
	private EventViewService eventViewService;

	@Override
	public String textTypeMsg(InMessage msg, String originalXml, Map<String, String> signature) throws MessageException {
		String keyword = msg.getContent();
		String accountId = msg.getAccountId();
		String openId = msg.getFromUserName();// 用户ID
		String accountNo = msg.getToUserName();// 开发者微信号
		return keyWordReplyService.getTextMessage(keyword, accountId, openId, accountNo, originalXml, signature);
	}

	@Override
	public String locationTypeMsg(InMessage msg, String originalXml, Map<String, String> signature) throws MessageException {
		String latitude = msg.getLocationX();
		String longitude = msg.getLocationY();
		String accountId = msg.getAccountId();
		String openId = msg.getFromUserName();// 用户ID
		String accountNo = msg.getToUserName();// 开发者微信号
		return eventMessageReplyService.getLocationMessage(accountId, latitude, longitude, openId, accountNo, originalXml, signature);
	}

	@Override
	public String imageTypeMsg(InMessage msg, String originalXml, Map<String, String> signature) throws MessageException {
		return null;
	}

	@Override
	public String linkTypeMsg(InMessage msg, String originalXml, Map<String, String> signature) throws MessageException {
		return null;
	}

	@Override
	public String eventTypeMsg(InMessage msg, String originalXml, Map<String, String> signature) throws MessageException {
		String event = msg.getEvent();// 事件
		String accountId = msg.getAccountId();// 账户主键
		String openId = msg.getFromUserName();// 用户ID
		String accountNo = msg.getToUserName();// 开发者微信号
		if ("subscribe".equals(event)) {// 关注
			fansService.subscribeFans(openId, accountId);
			return eventMessageReplyService.getSubscribeMessage(accountId, openId, accountNo, originalXml, signature);
		} else if ("CLICK".equals(event)) {// 菜单消息
			String key = msg.getEventKey();
			return eventMessageReplyService.getMenuMessage(key, accountId, openId, accountNo, originalXml, signature);
		} else if ("unsubscribe".equals(event)) {// 取消关注
			fansService.unsubscribe(openId, accountId);
			return eventMessageReplyService.getUnSubscribeMessage(accountId, openId, accountNo, originalXml, signature);
		} /*else if ("LOCATION".equals(event)) {// 地址位置
			String latitude = msg.getLatitude();
			String longitude = msg.getLongitude();
			return eventMessageReplyService.getLocationMessage(accountId, latitude, longitude, openId, accountNo, originalXml, signature);
		} */
		else if ("VIEW".equals(event)) {// 菜单链接
			EventViewThread task = new EventViewThread(eventViewService, msg);
			ThreadPoolUtils.execute(task);
			return null;
		}
		return null;
	}

	@Override
	public String voiceTypeMsg(InMessage msg, String originalXml, Map<String, String> signature) throws MessageException {
		return null;
	}
}
