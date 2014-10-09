/************************************************************************************
 * @File name   :      RoleServiceImpl.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.admin.service.RoleService;
import com.covisint.wechat.data.dao.WxRoleResourceDao;
import com.covisint.wechat.data.dao.WxUserRolesDao;
import com.covisint.wechat.data.model.WxRoleResource;
import com.covisint.wechat.data.model.WxUserRoles;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.IdentifierUtils;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private WxUserRolesDao wxUserRolesDao;
	@Autowired
	private WxRoleResourceDao wxRoleResourceDao;

	@Override
	public Page<WxUserRoles> pageRole(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		paramMap.put("roleType", WxUserRoles.ROLE_TYPE_NORMAL);
		String keyword = (String) paramMap.get("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			paramMap.put("likeRoleName", "%" + keyword + "%");
		}
		paramMap.put("status", WxUserRoles.STATUS_ENABLE);
		return wxUserRolesDao.pageDomain(paramMap, current, pagesize);
	}

	@Override
	public boolean saveRole(WxUserRoles wxUserRoles, String userId) throws AjaxException {
		if (wxUserRoles != null) {
			String roleId = wxUserRoles.getRoleId();
			int row = 0;
			if (StringUtils.isEmpty(roleId)) {
				wxUserRoles.setRoleId(IdentifierUtils.getId().generate().toString());
				wxUserRoles.setCreateBy(userId);
				wxUserRoles.setRoleType(WxUserRoles.ROLE_TYPE_NORMAL);
				wxUserRoles.setStatus(WxUserRoles.STATUS_ENABLE);
				if (this.validate_update(wxUserRoles)) {
					row = wxUserRolesDao.insert(wxUserRoles);
				}
			} else {
				if (this.validate_update(wxUserRoles)) {
					row = wxUserRolesDao.update(wxUserRoles);
				}
			}
			return row > 0;
		}
		return false;
	}

	@Override
	public boolean saveResource(String roleId, List<String> resourceIds) {
		wxRoleResourceDao.delete(roleId);
		List<WxRoleResource> wxRoleResources = new ArrayList<WxRoleResource>();
		int count = resourceIds.size();
		for (int i = 0; i < count; i++) {
			String resourceId = resourceIds.get(i);
			WxRoleResource wxRoleResource = new WxRoleResource();
			wxRoleResource.setResourceId(resourceId);
			wxRoleResource.setRoleId(roleId);
			wxRoleResources.add(wxRoleResource);
		}
		wxRoleResourceDao.batchInsert(wxRoleResources);
		return true;
	}

	@Override
	public List<WxRoleResource> listResource(String roleId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		return wxRoleResourceDao.findDomain(paramMap);
	}

	@Override
	public List<WxUserRoles> listRole(Map<String, Object> paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		paramMap.put("roleType", WxUserRoles.ROLE_TYPE_NORMAL);
		paramMap.put("status", WxUserRoles.STATUS_ENABLE);
		return wxUserRolesDao.findDomain(paramMap);
	}

	@Override
	public WxUserRoles info(String roleId) {
		return wxUserRolesDao.get(roleId);
	}

	@Override
	public boolean changeStatus(String roleId) throws AjaxException {
		WxUserRoles wxUserRoles = this.info(roleId);
		String status = wxUserRoles.getStatus();
		if (WxUserRoles.STATUS_ENABLE.equals(status)) {
			return wxUserRolesDao.changeStatus(roleId) > 0;
		} else {
			if (this.validate_update(wxUserRoles)) {
				return wxUserRolesDao.changeStatus(roleId) > 0;
			}
			return false;
		}
	}

	/**
	 * 验证数据合法性——验证该角色名是否存在
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_update(WxUserRoles wxUserRoles) throws AjaxException {
		String roleId = wxUserRoles.getRoleId();
		String roleName = wxUserRoles.getRoleName();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(roleId)) {
			paramMap.put("except", roleId);
		}
		paramMap.put("roleName", roleName);
		paramMap.put("status", WxUserRoles.STATUS_ENABLE);
		paramMap.put("roleType", WxUserRoles.ROLE_TYPE_NORMAL);
		int row = wxUserRolesDao.checkExists(paramMap);
		if (row > 0) {
			throw new AjaxException("该角色已存在");
		}
		return row == 0;
	}
}
