/************************************************************************************
 * @File name   :      EventMessageReplyServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-20
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
 * 2014-5-20 上午11:07:01			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.message.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.WxMenuInfoDao;
import com.covisint.wechat.data.dao.WxReplyMsgDao;
import com.covisint.wechat.data.dao.WxReplymsgTemplateDao;
import com.covisint.wechat.data.model.WxMenuInfo;
import com.covisint.wechat.data.model.WxReplyMsg;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.exception.MessageException;
import com.covisint.wechat.message.factory.MessageConvertServiceFactory;
import com.covisint.wechat.message.service.EventMessageReplyService;
import com.covisint.wechat.webservice.service.IdmMemberService;

/**
 *
 */
@Service
public class EventMessageReplyServiceImpl implements EventMessageReplyService {
	private static final Logger logger = LoggerFactory.getLogger(EventMessageReplyServiceImpl.class);
	@Autowired
	private WxReplymsgTemplateDao wxReplymsgTemplateDao;
	@Autowired
	private WxMenuInfoDao wxMenuInfoDao;
	@Autowired
	private MessageConvertServiceFactory converService;
	@Autowired
	private WxReplyMsgDao wxReplyMsgDao;
	@Autowired
	private IdmMemberService idmMemberService;

	@Override
	public String getMenuMessage(String eventKey, String accountId, String openId, String accountNo, String originalXml, Map<String, String> signature) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("菜单消息回复,点击的菜单为：" + eventKey);
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("eventKey", eventKey);
			paramMap.put("accountId", accountId);
			paramMap.put("type", WxMenuInfo.MENU_TYPE_EVENT);
			paramMap.put("status", WxMenuInfo.STATUS_ENABLE);
			WxMenuInfo menuInfo = wxMenuInfoDao.getMessageMenu(paramMap);// 获取该菜单绑定的消息回复
			if (menuInfo != null) {
				String templateId = this.getTemplateId(menuInfo, openId);
				String keyword = null;
				if (templateId.equals(menuInfo.getTarget())) {
					keyword = menuInfo.getRobotKeyword();
				} else {
					keyword = menuInfo.getAnonRobotKeyword();
				}
				WxReplymsgTemplate template = wxReplymsgTemplateDao.get(templateId);
				if (template != null) {
					template.setKeyword(keyword);
					template.setOriginalXml(originalXml);
					template.setSignature(signature);
				}
				return converService.convertTemplate(template, openId, accountNo, accountId);
			}
		} catch (MessageException e) {
			return null;
		}
		return null;
	}

	@Override
	public String getSubscribeMessage(String accountId, String openId, String accountNo, String originalXml, Map<String, String> signature) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("粉丝关注，粉丝openId为：" + openId);
			}
			WxReplyMsg domainMsg = wxReplyMsgDao.getBehaviorMsg(accountId, WxReplyMsg.TIGGER_TYPE_SUBSCRIBE);
			if (domainMsg != null) {
				String status = domainMsg.getStatus();
				if (WxReplyMsg.STATUS_ENABLE.equals(status)) {
					String templateId = this.getTemplateId(domainMsg, openId);
					WxReplymsgTemplate template = wxReplymsgTemplateDao.get(templateId);
					if (template != null) {
						// TODO 设置关注时 默认的小i机器人关键字
						template.setOriginalXml(originalXml);
						template.setSignature(signature);
					}
					return converService.convertTemplate(template, openId, accountNo, accountId);
				}
			}
		} catch (MessageException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public String getLocationMessage(String accountId, String latitude, String longitude, String openId, String accountNo, String originalXml, Map<String, String> signature) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("经纬度消息回复，纬度：" + latitude + "经度：" + longitude);
			}
			WxReplyMsg domainMsg = wxReplyMsgDao.getBehaviorMsg(accountId, WxReplyMsg.TIGGER_TYPE_LOCATION);
			if (domainMsg != null) {
				String status = domainMsg.getStatus();
				if (WxReplyMsg.STATUS_ENABLE.equals(status)) {
					String templateId = this.getTemplateId(domainMsg, openId);
					WxReplymsgTemplate template = wxReplymsgTemplateDao.get(templateId);
					if (template != null) {
						String keyword = latitude + "," + longitude;
						template.setKeyword(keyword);
						template.setOriginalXml(originalXml);
						template.setSignature(signature);
					}
					return converService.convertTemplate(template, openId, accountNo, accountId);
				}
			}
		} catch (MessageException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public String getUnSubscribeMessage(String accountId, String openId, String accountNo, String originalXml, Map<String, String> signature) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("粉丝取消关注，粉丝openId为：" + openId);
			}
			WxReplyMsg domainMsg = wxReplyMsgDao.getBehaviorMsg(accountId, WxReplyMsg.TIGGER_TYPE_UNSUBSCRIBE);
			if (domainMsg != null) {
				String status = domainMsg.getStatus();
				if (WxReplyMsg.STATUS_ENABLE.equals(status)) {
					String templateId = this.getTemplateId(domainMsg, openId);
					WxReplymsgTemplate template = wxReplymsgTemplateDao.get(templateId);
					if (template != null) {
						// TODO 设置取消关注时 默认的小i机器人关键字
						template.setOriginalXml(originalXml);
						template.setSignature(signature);
					}
					return converService.convertTemplate(template, openId, accountNo, accountId);
				}
			}
		} catch (MessageException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * 获取模板ID
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private String getTemplateId(WxMenuInfo menuInfo, String openId) {
		String checkBind = menuInfo.getCheckBind();
		if (checkBind.equals(WxMenuInfo.CHECK_BIND_ENABLE)) {// 验证会员绑定
			if (!idmMemberService.validateMember(openId)) {
				return menuInfo.getAnonTarget();
			}
		}
		return menuInfo.getTarget();
	}

	/**
	 * 获取模板ID
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private String getTemplateId(WxReplyMsg domainMsg, String openId) {
		String checkBind = domainMsg.getCheckBind();
		if (checkBind.equals(WxReplyMsg.CHECK_BIND_ENABLE)) {// 验证会员绑定
			if (!idmMemberService.validateMember(openId)) {
				return domainMsg.getAnonTemplateId();
			}
		}
		return domainMsg.getTemplateId();
	}

}
