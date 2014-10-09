/************************************************************************************
 * @File name   :      UserBelongRoleServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-28
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
 * 2014-4-28 下午02:07:53			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.admin.service.UserBelongRoleService;
import com.covisint.wechat.data.dao.WxUserBelongRolesDao;
import com.covisint.wechat.data.model.WxUserBelongRoles;

/**
 *
 */
@Service
public class UserBelongRoleServiceImpl implements UserBelongRoleService {
	@Autowired
	private WxUserBelongRolesDao wxUserBelongRolesDao;

	@Override
	public List<WxUserBelongRoles> listUserRoles(String userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		List<WxUserBelongRoles> roleList = wxUserBelongRolesDao.findDomain(paramMap);
		return this.groupUserRoles(roleList);
	}

	@Override
	public boolean save(List<Map<String, Object>> userRoleList, String userId) {
		wxUserBelongRolesDao.delete(userId);
		List<WxUserBelongRoles> data = this.getRolesList(userRoleList, userId);
		if (data != null) {
			wxUserBelongRolesDao.batchInsert(data);
		}
		return true;
	}

	private List<WxUserBelongRoles> groupUserRoles(List<WxUserBelongRoles> roleList) {
		int count = roleList.size();
		List<WxUserBelongRoles> result = new ArrayList<WxUserBelongRoles>();
		String groupString = null;
		List<String> roles = null;
		WxUserBelongRoles userBelongRole = null;
		for (int i = 0; i < count; i++) {
			WxUserBelongRoles current = roleList.get(i);
			if (!current.getAccountId().equals(groupString)) {
				groupString = current.getAccountId();
				if (userBelongRole != null) {
					userBelongRole.setRoleList(roles);
					result.add(userBelongRole);
				}
				userBelongRole = new WxUserBelongRoles();
				roles = new ArrayList<String>();
			}
			roles.add(current.getRoleId());
			userBelongRole.setAccountId(current.getAccountId());
			userBelongRole.setUserId(current.getUserId());
			if (i == count - 1) {
				userBelongRole.setRoleList(roles);
				result.add(userBelongRole);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private List<WxUserBelongRoles> getRolesList(List<Map<String, Object>> userRoleList, String userId) {
		if (userRoleList != null) {
			List<WxUserBelongRoles> result = new ArrayList<WxUserBelongRoles>();
			for (Map<String, Object> rowData : userRoleList) {
				String accountId = (String) rowData.get("accountId");
				List<String> roleArray = (List<String>) rowData.get("roleList");
				for (String roleId : roleArray) {
					WxUserBelongRoles wxUserBelongRoles = new WxUserBelongRoles();
					wxUserBelongRoles.setAccountId(accountId);
					wxUserBelongRoles.setRoleId(roleId);
					wxUserBelongRoles.setUserId(userId);
					result.add(wxUserBelongRoles);
				}
			}
			return result;
		}
		return null;
	}
}
