/************************************************************************************
 * @File name   :      AutoReplyServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-9
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
 * 2014-5-9 下午04:36:30			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.constant.DictCode;
import com.covisint.wechat.content.service.AutoReplyService;
import com.covisint.wechat.data.dao.WxReplyMsgDao;
import com.covisint.wechat.data.model.WxReplyMsg;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.IdentifierUtils;

/**
 *
 */
@Service
public class AutoReplyServiceImpl implements AutoReplyService {
	@Autowired
	private WxReplyMsgDao wxReplyMsgDao;

	@Override
	public Page<WxReplyMsg> pageAutoReply(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		String search = (String) paramMap.get("search");
		if (!StringUtils.isEmpty(search)) {
			paramMap.put("likeKeyword", "%" + search + "%");
		}
		paramMap.put("autoreplyMessageStatus", DictCode.AUTOREPLY_MESSAGE_STATUS);
		paramMap.put("replyCheckBind", DictCode.REPLY_CHECK_BIND);
		paramMap.put("messageTiggerType", DictCode.MESSAGE_TIGGER_TYPE);
		paramMap.put("status", WxReplyMsg.STATUS_ENABLE);
		return wxReplyMsgDao.pageDomain(paramMap, current, pagesize);
	}

	@Override
	public boolean saveBehaviorMsg(WxReplyMsg wxReplyMsg, String userId, String accountId) throws AjaxException {
		wxReplyMsg.setAccountId(accountId);
		wxReplyMsg.setCreateBy(userId);
		String tiggerType = wxReplyMsg.getTiggerType();
		WxReplyMsg domainMsg = wxReplyMsgDao.getBehaviorMsg(accountId, tiggerType);
		int row = 0;
		if (this.validate_behavior(wxReplyMsg)) {
			if (null == domainMsg) {
				wxReplyMsg.setReplyId(IdentifierUtils.getId().generate().toString());
				row = wxReplyMsgDao.insert(wxReplyMsg);
			} else {
				wxReplyMsg.setReplyId(domainMsg.getReplyId());
				row = wxReplyMsgDao.update(wxReplyMsg);
			}
		}
		return row > 0;
	}

	@Override
	public WxReplyMsg getBehaviorMsg(String tiggerType, String accountId) {
		return wxReplyMsgDao.getBehaviorMsg(accountId, tiggerType);
	}

	@Override
	public boolean saveMsgreply(WxReplyMsg wxReplyMsg, String userId, String accountId) throws AjaxException {
		String replyId = wxReplyMsg.getReplyId();
		int row = 0;
		if (this.validate_reply(wxReplyMsg)) {
			if (StringUtils.isEmpty(replyId)) {
				wxReplyMsg.setAccountId(accountId);
				wxReplyMsg.setCreateBy(userId);
				wxReplyMsg.setReplyId(IdentifierUtils.getId().generate().toString());
				wxReplyMsg.setStatus(WxReplyMsg.STATUS_ENABLE);
				wxReplyMsg.setTiggerType(WxReplyMsg.TIGGER_TYPE_KEYWORD);
				if (this.checkKeyWordExists(accountId, wxReplyMsg.getKeyword())) {
					row = wxReplyMsgDao.insert(wxReplyMsg);
				} else {
					throw new AjaxException("该关键词消息回复已设置，请更换关键词。");
				}
			} else {
				if (this.updateValidate(accountId, wxReplyMsg.getKeyword(), replyId)) {
					row = wxReplyMsgDao.update(wxReplyMsg);
				} else {
					throw new AjaxException("该关键词消息回复已设置，请更换关键词。");
				}
			}
		}
		return row > 0;
	}

	@Override
	public WxReplyMsg info(String replyId) {
		return wxReplyMsgDao.get(replyId);
	}

	@Override
	public boolean changeStatus(String replyId) throws AjaxException {
		WxReplyMsg replyMsg = this.info(replyId);
		replyMsg.setStatus(WxReplyMsg.STATUS_DISABLE);
		return wxReplyMsgDao.update(replyMsg) > 0;
	}

	@Override
	public boolean checkKeyWordExists(String accountId, String keyword) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("status", WxReplyMsg.STATUS_ENABLE);
		paramMap.put("tiggerType", WxReplyMsg.TIGGER_TYPE_KEYWORD);
		paramMap.put("keyword", keyword);
		int row = wxReplyMsgDao.checkExists(paramMap);
		return row == 0;
	}

	/**
	 * 后台验证数据合法性，事件触发类型
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_behavior(WxReplyMsg wxReplyMsg) throws AjaxException {
		String status = wxReplyMsg.getStatus();
		if (WxReplyMsg.STATUS_ENABLE.equals(status)) {
			String templateId = wxReplyMsg.getTemplateId();
			if (StringUtils.isEmpty(templateId)) {
				throw new AjaxException("请选择回复消息模板");
			}
			String check = wxReplyMsg.getCheckBind();
			if (WxReplyMsg.CHECK_BIND_ENABLE.equals(check)) {
				String anonTemplateId = wxReplyMsg.getAnonTemplateId();
				if (StringUtils.isEmpty(anonTemplateId)) {
					throw new AjaxException("请选择未绑定回复消息模板");
				}
			}
		}
		return true;
	}

	/**
	 * 后台验证数据合法性——关键字
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_reply(WxReplyMsg wxReplyMsg) throws AjaxException {
		String keyword = wxReplyMsg.getKeyword();
		if (StringUtils.isEmpty(keyword)) {
			throw new AjaxException("请输入消息触发关键字");
		}
		String templateId = wxReplyMsg.getTemplateId();
		if (StringUtils.isEmpty(templateId)) {
			throw new AjaxException("请选择回复消息模板");
		}
		String check = wxReplyMsg.getCheckBind();
		if (WxReplyMsg.CHECK_BIND_ENABLE.equals(check)) {
			String anonTemplateId = wxReplyMsg.getAnonTemplateId();
			if (StringUtils.isEmpty(anonTemplateId)) {
				throw new AjaxException("请选择未绑定回复消息模板");
			}
		}
		return true;
	}

	/**
	 * 查询是否存在该关键字回复
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean updateValidate(String accountId, String keyword, String except) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("status", WxReplyMsg.STATUS_ENABLE);
		paramMap.put("tiggerType", WxReplyMsg.TIGGER_TYPE_KEYWORD);
		paramMap.put("keyword", keyword);
		paramMap.put("except", except);
		int row = wxReplyMsgDao.checkExists(paramMap);
		return row == 0;
	}
}
