/************************************************************************************
 * @File name   :      WxRoleResourceDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-25
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
 * 2014-4-25 16:08:31			马恩伟			1.0				Initial Version
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
import com.covisint.wechat.data.dao.WxRoleResourceDao;
import com.covisint.wechat.data.mapper.WxRoleResourceMapper;
import com.covisint.wechat.data.model.WxRoleResource;
import com.covisint.wechat.page.Page;

/**
 * 角色资源数据访问接口
 * 
 */
@Component
public class WxRoleResourceDaoImpl implements WxRoleResourceDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxRoleResource wxRoleResource) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resourceId", wxRoleResource.getResourceId());
		paramMap.put("roleId", wxRoleResource.getRoleId());
		return dao.insert(WxRoleResourceMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxRoleResource wxRoleResource) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resourceId", wxRoleResource.getResourceId());
		paramMap.put("roleId", wxRoleResource.getRoleId());
		return dao.update(WxRoleResourceMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxRoleResource get(java.lang.String resourceId, java.lang.String roleId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resourceId", resourceId);
		paramMap.put("roleId", roleId);
		return dao.get(WxRoleResourceMapper.getFindOneSql(paramMap), paramMap, new WxRoleResource());
	}

	@Override
	public Map<String, Object> findOne(java.lang.String resourceId, java.lang.String roleId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resourceId", resourceId);
		paramMap.put("roleId", roleId);
		return dao.findOne(WxRoleResourceMapper.getFindOneSql(paramMap), paramMap);
	}

	@Override
	public List<Map<String, Object>> findList(Map<String, Object> paramMap) {
		return dao.find(WxRoleResourceMapper.getFindAllSql(paramMap), paramMap);
	}

	@Override
	public List<WxRoleResource> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxRoleResourceMapper.getFindAllSql(paramMap), paramMap, new WxRoleResource());
	}

	@Override
	public Page<WxRoleResource> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxRoleResourceMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxRoleResource());
	}

	@Override
	public int[] batchInsert(List<WxRoleResource> wxRoleResources) {
		int count = wxRoleResources.size();
		SqlParameterSource[] batchArgs = new SqlParameterSource[count];
		for (int i = 0; i < count; i++) {
			WxRoleResource wxRoleResource = wxRoleResources.get(i);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("resourceId", wxRoleResource.getResourceId());
			paramMap.put("roleId", wxRoleResource.getRoleId());
			batchArgs[i] = new MapSqlParameterSource(paramMap);
		}
		return dao.getJdbcTemplate().batchUpdate(WxRoleResourceMapper.getBatchInsertSql(), batchArgs);
	}

	@Override
	public int delete(String roleId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		return dao.delete(WxRoleResourceMapper.getDeleteSql(), paramMap);
	}
}