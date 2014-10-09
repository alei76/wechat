/************************************************************************************
 * @File name   :      WxReplymsgTemplateDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-30
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
 * 2014-4-30 14:56:08			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxReplymsgTemplateDao;
import com.covisint.wechat.data.mapper.WxReplymsgTemplateMapper;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.page.Page;

/**
 * 回复消息模板表数据访问接口
 * 
 */
@Component
public class WxReplymsgTemplateDaoImpl implements WxReplymsgTemplateDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxReplymsgTemplate wxReplymsgTemplate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("templateId", wxReplymsgTemplate.getTemplateId());
		paramMap.put("name", wxReplymsgTemplate.getName());
		paramMap.put("resourceType", wxReplymsgTemplate.getResourceType());
		paramMap.put("resourceId", wxReplymsgTemplate.getResourceId());
		paramMap.put("type", wxReplymsgTemplate.getType());
		paramMap.put("createBy", wxReplymsgTemplate.getCreateBy());
		paramMap.put("createTime", wxReplymsgTemplate.getCreateTime());
		paramMap.put("status", wxReplymsgTemplate.getStatus());
		paramMap.put("accountId", wxReplymsgTemplate.getAccountId());
		paramMap.put("content", wxReplymsgTemplate.getContent());
		paramMap.put("attaId", wxReplymsgTemplate.getAttaId());
		return dao.insert(WxReplymsgTemplateMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxReplymsgTemplate wxReplymsgTemplate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("templateId", wxReplymsgTemplate.getTemplateId());
		paramMap.put("name", wxReplymsgTemplate.getName());
		paramMap.put("resourceType", wxReplymsgTemplate.getResourceType());
		paramMap.put("resourceId", wxReplymsgTemplate.getResourceId());
		paramMap.put("type", wxReplymsgTemplate.getType());
		paramMap.put("createBy", wxReplymsgTemplate.getCreateBy());
		paramMap.put("createTime", wxReplymsgTemplate.getCreateTime());
		paramMap.put("status", wxReplymsgTemplate.getStatus());
		paramMap.put("accountId", wxReplymsgTemplate.getAccountId());
		paramMap.put("content", wxReplymsgTemplate.getContent());
		paramMap.put("attaId", wxReplymsgTemplate.getAttaId());
		return dao.update(WxReplymsgTemplateMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxReplymsgTemplate get(java.lang.String templateId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("templateId", templateId);
		return dao.get(WxReplymsgTemplateMapper.getFindOneSql(paramMap), paramMap, new WxReplymsgTemplate());
	}

	@Override
	public Map<String, Object> findOne(java.lang.String templateId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("templateId", templateId);
		return dao.findOne(WxReplymsgTemplateMapper.getFindOneSql(paramMap), paramMap);
	}

	@Override
	public List<Map<String, Object>> findList(Map<String, Object> paramMap) {
		return dao.find(WxReplymsgTemplateMapper.getFindAllSql(paramMap), paramMap);
	}

	@Override
	public List<WxReplymsgTemplate> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxReplymsgTemplateMapper.getFindAllSql(paramMap), paramMap, new WxReplymsgTemplate());
	}

	@Override
	public Page<WxReplymsgTemplate> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxReplymsgTemplateMapper.getPageSql(paramMap), paramMap, current, pagesize, new RowMapper<WxReplymsgTemplate>() {

			@Override
			public WxReplymsgTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxReplymsgTemplate domain = new WxReplymsgTemplate();
				domain.setTemplateId(rs.getString("TEMPLATE_ID"));
				domain.setName(rs.getString("NAME"));
				domain.setResourceType(rs.getString("RESOURCE_TYPE"));
				domain.setResourceId(rs.getString("RESOURCE_ID"));
				domain.setType(rs.getString("TYPE"));
				domain.setCreateBy(rs.getString("CREATE_BY"));
				domain.setCreateTime(rs.getString("CREATE_TIME"));
				domain.setStatus(rs.getString("STATUS"));
				domain.setAccountId(rs.getString("ACCOUNT_ID"));
				domain.setContent(rs.getString("CONTENT"));
				domain.setAttaId(rs.getString("ATTA_ID"));
				domain.setStatusCn(rs.getString("STATUS_CN"));
				domain.setResourceTypeCn(rs.getString("RESOURCE_TYPE_CN"));
				domain.setTypeCn(rs.getString("TYPE_CN"));
				domain.setResourceName(rs.getString("RESOURCE_NAME"));
				domain.setResourceStatus(rs.getString("RESOURCE_STATUS"));
				return domain;
			}

		});
	}

	@Override
	public WxReplymsgTemplate info(String templateId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("templateId", templateId);
		return dao.get(WxReplymsgTemplateMapper.getInfoSql(paramMap), paramMap, new RowMapper<WxReplymsgTemplate>() {

			@Override
			public WxReplymsgTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxReplymsgTemplate domain = new WxReplymsgTemplate();
				domain.setTemplateId(rs.getString("TEMPLATE_ID"));
				domain.setName(rs.getString("NAME"));
				domain.setResourceType(rs.getString("RESOURCE_TYPE"));
				domain.setResourceId(rs.getString("RESOURCE_ID"));
				domain.setType(rs.getString("TYPE"));
				domain.setCreateBy(rs.getString("CREATE_BY"));
				domain.setCreateTime(rs.getString("CREATE_TIME"));
				domain.setStatus(rs.getString("STATUS"));
				domain.setAccountId(rs.getString("ACCOUNT_ID"));
				domain.setContent(rs.getString("CONTENT"));
				domain.setAttaId(rs.getString("ATTA_ID"));
				domain.setItemsCount(rs.getInt("ITEMS_COUNT"));
				return domain;
			}
		});
	}

	@Override
	public int checkExists(Map<String, Object> paramMap) {
		return dao.getJdbcTemplate().queryForObject(WxReplymsgTemplateMapper.getCheckExistsSql(paramMap), paramMap, Integer.class);
	}
}