/************************************************************************************
 * @File name   :      MenuServiceImpl.java
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
package com.covisint.wechat.kernel.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.WxMenusDao;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.data.model.WxUserRoles;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.kernel.service.MenuService;

@Service
@Repository("userMenu")
public class MenuServiceImpl implements MenuService {
	@Autowired
	private WxMenusDao wxMenusDao;

	@Override
	public List<ZtreeMenu> getUserMenu(String userId, String userType, String accountId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("enable", "01");
		paramMap.put("status", WxUserRoles.STATUS_ENABLE);
		if (userType.equals(WxSystemUser.ADMIN_TYPE_SYSTEM)) {// 是内置用户
			paramMap.put("roleType", WxUserRoles.ROLE_TYPE_SYSTEM);
			return wxMenusDao.findSysMenu(paramMap);// 获取内置角色对应的菜单
		} else {
			paramMap.put("roleType", WxUserRoles.ROLE_TYPE_NORMAL);
			paramMap.put("userId", userId);
			paramMap.put("accountId", accountId);
			return wxMenusDao.findUsrMenu(paramMap);// 获取一般用户的菜单
		}
	}

	@Override
	public List<ZtreeMenu> getResource(String userId, String userType, String accountId) {
		if (userType.equals(WxSystemUser.ADMIN_TYPE_SYSTEM)) {// 是内置用户
			return wxMenusDao.findAllResource();// 获取所有菜单
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("enable", "01");
			paramMap.put("status", WxUserRoles.STATUS_ENABLE);
			paramMap.put("roleType", WxUserRoles.ROLE_TYPE_NORMAL);
			paramMap.put("userId", userId);
			paramMap.put("accountId", accountId);
			return wxMenusDao.findUsrMenu(paramMap);// 获取一般用户的菜单
		}
	}
}
