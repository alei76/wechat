/************************************************************************************
 * @File name   :      UserAdminServiceImpl.java
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
package com.covisint.wechat.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.admin.service.UserAdminService;
import com.covisint.wechat.data.dao.WxSystemUserDao;
import com.covisint.wechat.data.dao.WxWechatAccountDao;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.data.model.WxWechatAccount;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.IdentifierUtils;
import com.covisint.wechat.utils.MD5;
import com.covisint.wechat.utils.SessionUtils;

@Service
public class UserAdminServiceImpl implements UserAdminService {
	@Autowired
	private WxSystemUserDao wxSystemUserDao;
	@Autowired
	private WxWechatAccountDao wxWechatAccountDao;

	@Override
	public Page<WxSystemUser> pageUser(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		paramMap.put("userType", WxSystemUser.ADMIN_TYPE_NORMAL);
		String keyword = (String) paramMap.get("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			paramMap.put("likeFullName", "%" + keyword + "%");
		}
		return wxSystemUserDao.pageDomain(paramMap, current, pagesize);
	}

	@Override
	public boolean changeStatus(String userId) {
		return wxSystemUserDao.changeStatus(userId) > 0;
	}

	@Override
	public WxSystemUser getSystemUser(String userName) {
		if (StringUtils.isEmpty(userName)) {
			return null;
		} else {
			WxSystemUser user = SessionUtils.getCurrentUser();// 从session中获取当前登录用户
			if (user != null) {// 如果不为空
				String currentName = user.getUserName();
				if (currentName.equals(userName)) {// 当前登录用户的用户名与参数中的用户名一致
					return user;
				}
			}
			return this.getUserFromDb(userName);// 从数据库中获取
		}
	}

	/**
	 * 从数据库中获取该用户
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private WxSystemUser getUserFromDb(String userName) {
		if (StringUtils.isEmpty(userName)) {
			return null;
		} else {
			WxSystemUser user = this.getUserAdmin(userName);// 从数据库中获取用户
			if (user != null) {
				return user;
			} else {// 数据库中不存在该用户
				return this.saveUserAdmin(userName);// 自动创建该用户
			}
		}
	}

	@Override
	public WxSystemUser getUserAdmin(String userName) {
		WxSystemUser user = wxSystemUserDao.getUserAdmin(userName);
		if (user != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", user.getUserId());
			paramMap.put("status", WxWechatAccount.ACCOUNT_STATUS_ENABLE);
			List<WxWechatAccount> accountList = wxWechatAccountDao.findUserAccount(paramMap);// 用户所拥有的微信号
			if (accountList != null && accountList.size() > 0) {
				WxWechatAccount defaultAccount = accountList.get(0);// 从中取出一个作为用户登录的默认微信账户
				user.setCurrentAccount(defaultAccount.getAccountId());
			}
			user.setAccountList(accountList);
		}
		return user;
	}

	@Override
	public WxSystemUser saveUserAdmin(String userName) {
		WxSystemUser wxSystemUser = new WxSystemUser();
		wxSystemUser.setUserId(IdentifierUtils.getId().generate().toString());
		wxSystemUser.setUserName(userName);
		wxSystemUser.setUserType(WxSystemUser.ADMIN_TYPE_NORMAL);
		wxSystemUser.setStatus(WxSystemUser.ADMIN_STATUS_ENABLE);
		wxSystemUser.setPassword(MD5.md5(DEFAULT_PASSWORD));
		wxSystemUser.setFullName(userName);
		int row = wxSystemUserDao.insert(wxSystemUser);
		if (row > 0) {
			return wxSystemUser;
		} else {
			return null;
		}
	}
}
