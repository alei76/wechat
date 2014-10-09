/************************************************************************************
 * @File name   :      KeyWordReplyServiceImpl.java
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
 * 2014-5-20 上午11:06:41			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.message.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.WxReplyMsgDao;
import com.covisint.wechat.data.dao.WxReplymsgTemplateDao;
import com.covisint.wechat.data.model.WxReplyMsg;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.exception.MessageException;
import com.covisint.wechat.message.factory.MessageConvertServiceFactory;
import com.covisint.wechat.message.service.KeyWordReplyService;
import com.covisint.wechat.webservice.service.IdmMemberService;

/**
 *
 */
@Service
public class KeyWordReplyServiceImpl implements KeyWordReplyService {
	private static final Logger logger = LoggerFactory.getLogger(KeyWordReplyServiceImpl.class);
	@Autowired
	private WxReplymsgTemplateDao wxReplymsgTemplateDao;
	@Autowired
	private MessageConvertServiceFactory converService;
	@Autowired
	private WxReplyMsgDao wxReplyMsgDao;
	@Autowired
	private IdmMemberService idmMemberService;

	@Override
	public String getTextMessage(String keyword, String accountId, String openId, String accountNo, String originalXml, Map<String, String> signature) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("根据关键字获取消息回复内容，关键字为：" + keyword);
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("likeKeyword", "%" + keyword + "%");
			paramMap.put("status", WxReplyMsg.STATUS_ENABLE);
			paramMap.put("tiggerType", WxReplyMsg.TIGGER_TYPE_KEYWORD);
			paramMap.put("accountId", accountId);
			List<WxReplyMsg> replyList = wxReplyMsgDao.findDomain(paramMap);
			if (replyList != null && replyList.size() > 0) {
				WxReplyMsg domainMsg = replyList.get(0);
				if (domainMsg != null) {
					String status = domainMsg.getStatus();
					if (WxReplyMsg.STATUS_ENABLE.equals(status)) {
						String templateId = this.getTemplateId(domainMsg, openId);
						WxReplymsgTemplate template = wxReplymsgTemplateDao.get(templateId);
						if (template != null) {
							template.setKeyword(keyword);
							template.setOriginalXml(originalXml);
							template.setSignature(signature);
						}
						return converService.convertTemplate(template, openId, accountNo, accountId);
					}
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("没有该关键字对应的消息模板");
			}
		} catch (MessageException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return this.getDefaultMessage(keyword, accountId, openId, accountNo, originalXml, signature);// 查询不到关键字,获取默认消息
	}

	@Override
	public String getDefaultMessage(String keyword, String accountId, String openId, String accountNo, String originalXml, Map<String, String> signature) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("使用默认消息模板回复，关键字：" + keyword);
			}
			WxReplyMsg domainMsg = wxReplyMsgDao.getBehaviorMsg(accountId, WxReplyMsg.TIGGER_TYPE_DEFAULT);
			if (domainMsg != null) {
				String status = domainMsg.getStatus();
				if (WxReplyMsg.STATUS_ENABLE.equals(status)) {
					String templateId = this.getTemplateId(domainMsg, openId);
					WxReplymsgTemplate template = wxReplymsgTemplateDao.get(templateId);
					if (template != null) {
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
