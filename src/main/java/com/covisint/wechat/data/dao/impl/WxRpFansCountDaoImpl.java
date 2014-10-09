/************************************************************************************
 * @File name   :      WxRpFansCountDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-06-18
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
 * 2014-6-18 11:37:48			马恩伟			1.0				Initial Version
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
import com.covisint.wechat.data.dao.WxRpFansCountDao;
import com.covisint.wechat.data.mapper.WxRpFansCountMapper;
import com.covisint.wechat.data.model.StatMapData;
import com.covisint.wechat.data.model.WxRpFansCount;
import com.covisint.wechat.page.Page;

/**
 * 粉丝量统计表(日)数据访问接口
 * 
 */
@Component
public class WxRpFansCountDaoImpl implements WxRpFansCountDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxRpFansCount wxRpFansCount) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("recordId", wxRpFansCount.getRecordId());
		paramMap.put("recordDay", wxRpFansCount.getRecordDay());
		paramMap.put("accountId", wxRpFansCount.getAccountId());
		paramMap.put("fansCount", wxRpFansCount.getFansCount());
		return dao.insert(WxRpFansCountMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxRpFansCount wxRpFansCount) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("recordId", wxRpFansCount.getRecordId());
		paramMap.put("recordDay", wxRpFansCount.getRecordDay());
		paramMap.put("accountId", wxRpFansCount.getAccountId());
		paramMap.put("fansCount", wxRpFansCount.getFansCount());
		return dao.update(WxRpFansCountMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxRpFansCount get(java.lang.String recordId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("recordId", recordId);
		return dao.get(WxRpFansCountMapper.getFindOneSql(paramMap), paramMap, new WxRpFansCount());
	}

	@Override
	public List<WxRpFansCount> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxRpFansCountMapper.getFindAllSql(paramMap), paramMap, new WxRpFansCount());
	}

	@Override
	public Page<WxRpFansCount> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxRpFansCountMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxRpFansCount());
	}

	@Override
	public long checkReport(Long recordDay) {
		String totalSQL = "SELECT COUNT(1) FROM WX_RP_FANS_COUNT WHERE RECORD_DAY = :recordDay";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("recordDay", recordDay);
		return dao.getJdbcTemplate().queryForObject(totalSQL.toString(), paramMap, Long.class);
	}

	@Override
	public int[] insertBatch(List<WxRpFansCount> insertData) {
		int count = insertData.size();
		SqlParameterSource[] batchArgs = new SqlParameterSource[count];
		for (int i = 0; i < count; i++) {
			WxRpFansCount wxRpFansCount = insertData.get(i);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("recordId", wxRpFansCount.getRecordId());
			paramMap.put("recordDay", wxRpFansCount.getRecordDay());
			paramMap.put("accountId", wxRpFansCount.getAccountId());
			paramMap.put("fansCount", wxRpFansCount.getFansCount());
			batchArgs[i] = new MapSqlParameterSource(paramMap);
		}
		return dao.getJdbcTemplate().batchUpdate(WxRpFansCountMapper.getInsertSql(null), batchArgs);
	}

	@Override
	public Map<String, Integer> statPureSummary(Map<String, Object> paramMap) {
		String sqlString = "SELECT FANS_COUNT AS COUNT, TO_CHAR(TO_DATE('1970-01-01', 'yyyy-mm-dd') + RECORD_DAY / 86400 + 8 / 24, 'yyyy-mm-dd') DATE_TIME FROM WX_RP_FANS_COUNT WHERE ACCOUNT_ID = :accountId AND RECORD_DAY >= :startTime AND RECORD_DAY <= :endTime";
		StatMapData md = new StatMapData();
		dao.getJdbcTemplate().query(sqlString, paramMap, md);
		return md.getRowData();
	}

	@Override
	public Map<String, Object> lastTotal(Map<String, Object> paramMap) {
		String sqlString = "SELECT FANS_COUNT AS COUNT FROM WX_RP_FANS_COUNT WHERE TO_CHAR(TO_DATE('1970-01-01', 'yyyy-mm-dd') + RECORD_DAY / 86400 + 8 / 24, 'yyyy-mm-dd') = TO_CHAR(TRUNC(SYSDATE - 1), 'yyyy-mm-dd') AND ACCOUNT_ID = :accountId ";
		return dao.findOne(sqlString, paramMap);
	}
}