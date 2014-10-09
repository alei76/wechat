/************************************************************************************
 * @File name   :      WxNewsMessageDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-07-07
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
 * 2014-7-7 17:34:20			马恩伟			1.0				Initial Version
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
import com.covisint.wechat.data.dao.WxNewsMessageDao;
import com.covisint.wechat.data.mapper.WxNewsMessageMapper;
import com.covisint.wechat.data.model.WxNewsMessage;
import com.covisint.wechat.page.Page;

/**
 * 图文消息表数据访问接口
 * 
 */
@Component
public class WxNewsMessageDaoImpl implements WxNewsMessageDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxNewsMessage wxNewsMessage) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("newsMessageId", wxNewsMessage.getNewsMessageId());
		paramMap.put("templateId", wxNewsMessage.getTemplateId());
		paramMap.put("title", wxNewsMessage.getTitle());
		paramMap.put("description", wxNewsMessage.getDescription());
		paramMap.put("attaId", wxNewsMessage.getAttaId());
		paramMap.put("targetHref", wxNewsMessage.getTargetHref());
		paramMap.put("msgIndex", wxNewsMessage.getMsgIndex());
		return dao.insert(WxNewsMessageMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxNewsMessage wxNewsMessage) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("newsMessageId", wxNewsMessage.getNewsMessageId());
		paramMap.put("templateId", wxNewsMessage.getTemplateId());
		paramMap.put("title", wxNewsMessage.getTitle());
		paramMap.put("description", wxNewsMessage.getDescription());
		paramMap.put("attaId", wxNewsMessage.getAttaId());
		paramMap.put("targetHref", wxNewsMessage.getTargetHref());
		paramMap.put("msgIndex", wxNewsMessage.getMsgIndex());
		return dao.update(WxNewsMessageMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxNewsMessage get(java.lang.String newsMessageId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("newsMessageId", newsMessageId);
		return dao.get(WxNewsMessageMapper.getFindOneSql(paramMap), paramMap, new WxNewsMessage());
	}

	@Override
	public List<WxNewsMessage> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxNewsMessageMapper.getFindAllSql(paramMap), paramMap, new WxNewsMessage());
	}

	@Override
	public Page<WxNewsMessage> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxNewsMessageMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxNewsMessage());
	}

	@Override
	public int[] batchInsert(List<WxNewsMessage> newsMessageItems) {
		int count = newsMessageItems.size();
		SqlParameterSource[] batchArgs = new SqlParameterSource[count];
		for (int i = 0; i < count; i++) {
			WxNewsMessage wxNewsMessage = newsMessageItems.get(i);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("newsMessageId", wxNewsMessage.getNewsMessageId());
			paramMap.put("templateId", wxNewsMessage.getTemplateId());
			paramMap.put("title", wxNewsMessage.getTitle());
			paramMap.put("description", wxNewsMessage.getDescription());
			paramMap.put("attaId", wxNewsMessage.getAttaId());
			paramMap.put("targetHref", wxNewsMessage.getTargetHref());
			paramMap.put("msgIndex", wxNewsMessage.getMsgIndex());
			batchArgs[i] = new MapSqlParameterSource(paramMap);
		}
		return dao.getJdbcTemplate().batchUpdate(WxNewsMessageMapper.getInsertSql(null), batchArgs);
	}

	@Override
	public int[] batchUpdate(List<WxNewsMessage> update) {
		int count = update.size();
		SqlParameterSource[] batchArgs = new SqlParameterSource[count];
		for (int i = 0; i < count; i++) {
			WxNewsMessage wxNewsMessage = update.get(i);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("newsMessageId", wxNewsMessage.getNewsMessageId());
			paramMap.put("templateId", wxNewsMessage.getTemplateId());
			paramMap.put("title", wxNewsMessage.getTitle());
			paramMap.put("description", wxNewsMessage.getDescription());
			paramMap.put("attaId", wxNewsMessage.getAttaId());
			paramMap.put("targetHref", wxNewsMessage.getTargetHref());
			paramMap.put("msgIndex", wxNewsMessage.getMsgIndex());
			batchArgs[i] = new MapSqlParameterSource(paramMap);
		}
		return dao.getJdbcTemplate().batchUpdate(WxNewsMessageMapper.getUpdateSql(), batchArgs);
	}

	@Override
	public int[] batchDelete(String[] delItemsArray) {
		int count = delItemsArray.length;
		SqlParameterSource[] batchArgs = new SqlParameterSource[count];
		for (int i = 0; i < count; i++) {
			String newsMessageId = delItemsArray[i];
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("newsMessageId", newsMessageId);
			batchArgs[i] = new MapSqlParameterSource(paramMap);
		}
		return dao.getJdbcTemplate().batchUpdate(WxNewsMessageMapper.getDeleteSql(), batchArgs);
	}
}