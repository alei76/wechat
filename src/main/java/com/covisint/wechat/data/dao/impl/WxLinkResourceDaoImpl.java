/************************************************************************************
 * @File name   :      WxLinkResourceDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-29
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
 * 2014-4-29 11:13:42			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxLinkResourceDao;
import com.covisint.wechat.data.mapper.WxLinkResourceMapper;
import com.covisint.wechat.data.model.WxLinkResource;
import com.covisint.wechat.page.Page;

/**
 * 外部资源表数据访问接口
 * 
 */
@Component
public class WxLinkResourceDaoImpl implements WxLinkResourceDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxLinkResource wxLinkResource) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resourceId", wxLinkResource.getResourceId());
		paramMap.put("name", wxLinkResource.getName());
		paramMap.put("accountId", wxLinkResource.getAccountId());
		paramMap.put("resourceHref", wxLinkResource.getResourceHref());
		paramMap.put("type", wxLinkResource.getType());
		paramMap.put("createBy", wxLinkResource.getCreateBy());
		paramMap.put("createTime", wxLinkResource.getCreateTime());
		paramMap.put("status", wxLinkResource.getStatus());
		return dao.insert(WxLinkResourceMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxLinkResource wxLinkResource) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resourceId", wxLinkResource.getResourceId());
		paramMap.put("name", wxLinkResource.getName());
		paramMap.put("accountId", wxLinkResource.getAccountId());
		paramMap.put("resourceHref", wxLinkResource.getResourceHref());
		paramMap.put("type", wxLinkResource.getType());
		paramMap.put("createBy", wxLinkResource.getCreateBy());
		paramMap.put("createTime", wxLinkResource.getCreateTime());
		paramMap.put("status", wxLinkResource.getStatus());
		return dao.update(WxLinkResourceMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxLinkResource get(java.lang.String resourceId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resourceId", resourceId);
		return dao.get(WxLinkResourceMapper.getFindOneSql(paramMap), paramMap, new WxLinkResource());
	}

	@Override
	public Map<String, Object> findOne(java.lang.String resourceId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resourceId", resourceId);
		return dao.findOne(WxLinkResourceMapper.getFindOneSql(paramMap), paramMap);
	}

	@Override
	public List<Map<String, Object>> findList(Map<String, Object> paramMap) {
		return dao.find(WxLinkResourceMapper.getFindAllSql(paramMap), paramMap);
	}

	@Override
	public List<WxLinkResource> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxLinkResourceMapper.getFindAllSql(paramMap), paramMap, new WxLinkResource());
	}

	@Override
	public Page<WxLinkResource> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxLinkResourceMapper.getPageSql(paramMap), paramMap, current, pagesize, new RowMapper<WxLinkResource>() {

			@Override
			public WxLinkResource mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxLinkResource domain = new WxLinkResource();
				domain.setResourceId(rs.getString("RESOURCE_ID"));
				domain.setName(rs.getString("NAME"));
				domain.setAccountId(rs.getString("ACCOUNT_ID"));
				domain.setResourceHref(rs.getString("RESOURCE_HREF"));
				domain.setType(rs.getString("TYPE"));
				domain.setCreateBy(rs.getString("CREATE_BY"));
				domain.setCreateTime(rs.getString("CREATE_TIME"));
				domain.setStatus(rs.getString("STATUS"));
				domain.setStatusCn(rs.getString("status_cn"));
				return domain;
			}
		});
	}

	@Override
	public int changestatus(String resourceId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resourceId", resourceId);
		paramMap.put("enable", WxLinkResource.LINK_STATUS_ENABLE);
		paramMap.put("disable", WxLinkResource.LINK_STATUS_DISABLE);
		return dao.update(WxLinkResourceMapper.getChangeStatusSql(paramMap), paramMap);
	}

	@Override
	public int checkExists(Map<String, Object> paramMap) {
		return dao.getJdbcTemplate().queryForObject(WxLinkResourceMapper.getCheckExistsSql(paramMap), paramMap, Integer.class);
	}

	@Override
	public int[] updateBatch(String status, List<String> resourceIds) {
		int count = resourceIds.size();
		SqlParameterSource[] batchArgs = new SqlParameterSource[count];
		for (int i = 0; i < count; i++) {
			String resourceId = resourceIds.get(i);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("resourceId", resourceId);
			paramMap.put("status", status);
			batchArgs[i] = new MapSqlParameterSource(paramMap);
		}
		return dao.getJdbcTemplate().batchUpdate(WxLinkResourceMapper.getUpdateStatusSql(), batchArgs);
	}
}