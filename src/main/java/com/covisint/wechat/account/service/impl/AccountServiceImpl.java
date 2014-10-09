/************************************************************************************
 * @File name   :      AccountServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-25
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
 * 2014-4-25 上午08:55:24			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.covisint.wechat.account.service.AccountService;
import com.covisint.wechat.constant.DictCode;
import com.covisint.wechat.content.service.TemplateService;
import com.covisint.wechat.data.dao.WxWechatAccountDao;
import com.covisint.wechat.data.model.WxWechatAccount;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.member.service.GroupService;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.IdentifierUtils;
import com.covisint.wechat.utils.ShortTokenUtils;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private WxWechatAccountDao wxWechatAccountDao;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private GroupService groupService;
	@Value("${weixin.platform}")
	private String platform;

	@Override
	public Page<WxWechatAccount> pageAccount(Map<String, Object> paramMap, int current, int pagesize) {
		String keyword = (String) paramMap.get("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			paramMap.put("likeName", "%" + keyword + "%");
		}
		paramMap.put("accountType", DictCode.ACCOUNT_TYPE);
		paramMap.put("hiddenStatus", WxWechatAccount.ACCOUNT_STATUS_DELETE);
		return wxWechatAccountDao.pageDomain(paramMap, current, pagesize);
	}

	@Override
	public boolean saveAccount(WxWechatAccount wxWechatAccount, String userId) throws AjaxException {
		if (wxWechatAccount != null) {
			String accountId = wxWechatAccount.getAccountId();
			int row = 0;
			if (this.validaAccount(wxWechatAccount)) {
				if (StringUtils.isEmpty(accountId)) {
					accountId = IdentifierUtils.getId().generate().toString();
					wxWechatAccount.setAccountId(accountId);
					wxWechatAccount.setCreateBy(userId);
					wxWechatAccount.setStatus(WxWechatAccount.ACCOUNT_STATUS_ENABLE);
					String token = ShortTokenUtils.getToken(accountId);
					wxWechatAccount.setToken(token);
					if (this.validate_update(wxWechatAccount)) {
						row = wxWechatAccountDao.insert(wxWechatAccount);
						templateService.createRobotTemplate(accountId, userId);// 创建小I机器人消息模板
						groupService.createSystemGroup(accountId, userId);// 创建'未分组'粉丝分组
					}
				} else {
					if (this.validate_update(wxWechatAccount)) {
						row = wxWechatAccountDao.update(wxWechatAccount);
					}
				}
			}
			return row > 0;
		}
		return false;
	}

	@Override
	public List<WxWechatAccount> listAccount(Map<String, Object> paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		paramMap.put("status", WxWechatAccount.ACCOUNT_STATUS_ENABLE);
		return wxWechatAccountDao.findDomain(paramMap);
	}

	@Override
	public WxWechatAccount info(String accountId) {
		WxWechatAccount info = wxWechatAccountDao.get(accountId);
		if (info != null) {
			info.setNotifyPath(platform + "/api-wechat/notify/" + info.getAccountId());
		}
		return info;
	}

	@Override
	public boolean changeStatus(String accountId) throws AjaxException {
		WxWechatAccount wxWechatAccount = this.info(accountId);
		String status = wxWechatAccount.getStatus();
		if (WxWechatAccount.ACCOUNT_STATUS_ENABLE.equals(status)) {
			return wxWechatAccountDao.changeStatus(accountId) > 0;
		} else {
			if (this.validate_update(wxWechatAccount)) {
				return wxWechatAccountDao.changeStatus(accountId) > 0;
			}
			return false;
		}
	}

	@Override
	public boolean deleteAccount(String accountId) throws AjaxException {
		WxWechatAccount wxWechatAccount = this.info(accountId);
		wxWechatAccount.setStatus(WxWechatAccount.ACCOUNT_STATUS_DELETE);
		int row = wxWechatAccountDao.update(wxWechatAccount);
		return row > 0;
	}

	/**
	 * 后台验证数据合法性
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validaAccount(WxWechatAccount wxWechatAccount) throws AjaxException {
		String name = wxWechatAccount.getName();
		if (StringUtils.isEmpty(name)) {
			throw new AjaxException("请填写微信名称");
		}
		String accountNo = wxWechatAccount.getAccountNo();
		if (StringUtils.isEmpty(accountNo)) {
			throw new AjaxException("请填写微信号");
		}
		String type = wxWechatAccount.getType();
		if (StringUtils.isEmpty(type)) {
			throw new AjaxException("请选择微信公众号的类型");
		}
		if (WxWechatAccount.ACCOUNT_TYPE_SERVICE.equals(type)) {// 如果是服务号的必填字段
			String appId = wxWechatAccount.getAppId();
			if (StringUtils.isEmpty(appId)) {
				throw new AjaxException("请填写接口凭证");
			}
			String appSecret = wxWechatAccount.getAppSecret();
			if (StringUtils.isEmpty(appSecret)) {
				throw new AjaxException("请填写凭证密钥");
			}
		}
		return true;
	}

	/**
	 * 后台验证数据合法性——验证该微信号名称是否重复
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_update(WxWechatAccount wxWechatAccount) throws AjaxException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String name = wxWechatAccount.getName();
		String accountId = wxWechatAccount.getAccountId();
		if (StringUtils.isNotEmpty(accountId)) {
			paramMap.put("except", accountId);
		}
		paramMap.put("name", name);
		paramMap.put("status", WxWechatAccount.ACCOUNT_STATUS_ENABLE);
		int row = wxWechatAccountDao.checkExists(paramMap);
		if (row > 0) {
			throw new AjaxException("该微信号已存在");
		}
		return row == 0;
	}
}
