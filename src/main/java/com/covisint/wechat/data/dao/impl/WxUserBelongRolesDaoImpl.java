/************************************************************************************
 * @File name   :      WxUserBelongRolesDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-28
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
 * 2014-4-28 14:02:13			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxUserBelongRolesDao;
import com.covisint.wechat.data.mapper.WxUserBelongRolesMapper;
import com.covisint.wechat.data.model.WxUserBelongRoles;
import com.covisint.wechat.page.Page;

/**
 * 用户所属角色数据访问接口
 * 
 */
@Component
public class WxUserBelongRolesDaoImpl implements WxUserBelongRolesDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxUserBelongRoles wxUserBelongRoles) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", wxUserBelongRoles.getRoleId());
		paramMap.put("userId", wxUserBelongRoles.getUserId());
		paramMap.put("accountId", wxUserBelongRoles.getAccountId());
		return dao.insert(WxUserBelongRolesMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxUserBelongRoles wxUserBelongRoles) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", wxUserBelongRoles.getRoleId());
		paramMap.put("userId", wxUserBelongRoles.getUserId());
		paramMap.put("accountId", wxUserBelongRoles.getAccountId());
		return dao.update(WxUserBelongRolesMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxUserBelongRoles get(java.lang.String accountId, java.lang.String roleId, java.lang.String userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("roleId", roleId);
		paramMap.put("userId", userId);
		return dao.get(WxUserBelongRolesMapper.getFindOneSql(paramMap), paramMap, new WxUserBelongRoles());
	}

	@Override
	public Map<String, Object> findOne(java.lang.String accountId, java.lang.String roleId, java.lang.String userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("roleId", roleId);
		paramMap.put("userId", userId);
		return dao.findOne(WxUserBelongRolesMapper.getFindOneSql(paramMap), paramMap);
	}

	@Override
	public List<Map<String, Object>> findList(Map<String, Object> paramMap) {
		return dao.find(WxUserBelongRolesMapper.getFindAllSql(paramMap), paramMap);
	}

	@Override
	public List<WxUserBelongRoles> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxUserBelongRolesMapper.getFindAllSql(paramMap), paramMap, new WxUserBelongRoles());
	}

	@Override
	public Page<WxUserBelongRoles> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxUserBelongRolesMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxUserBelongRoles());
	}

	@Override
	public int[] batchInsert(List<WxUserBelongRoles> wxUserBelongRolesList) {
		int count = wxUserBelongRolesList.size();
		SqlParameterSource[] batchArgs = new SqlParameterSource[count];
		for (int i = 0; i < count; i++) {
			WxUserBelongRoles wxUserBelongRoles = wxUserBelongRolesList.get(i);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("roleId", wxUserBelongRoles.getRoleId());
			paramMap.put("userId", wxUserBelongRoles.getUserId());
			paramMap.put("accountId", wxUserBelongRoles.getAccountId());
			batchArgs[i] = new MapSqlParameterSource(paramMap);
		}
		return dao.getJdbcTemplate().batchUpdate(WxUserBelongRolesMapper.getBatchInsertSql(), batchArgs);
	}

	@Override
	public int delete(String userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return dao.delete(WxUserBelongRolesMapper.getDeleteSql(), paramMap);
	}
}