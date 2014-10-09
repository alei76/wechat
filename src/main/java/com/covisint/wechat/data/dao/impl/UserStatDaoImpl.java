/************************************************************************************
 * @File name   :      UserStatDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-4
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
 * 2014-6-4 下午03:58:54			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.UserStatDao;
import com.covisint.wechat.data.model.StatMapData;

/**
 *
 */
@Component
public class UserStatDaoImpl implements UserStatDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public List<Map<String, Object>> statSexSummary(Map<String, Object> paramMap) {
		String sqlString = "SELECT COUNT(1) AS COUNT, CASE SEX WHEN '1' THEN '男性' WHEN '2' THEN '女性' ELSE '未知' END AS SEX_CN FROM WX_FANS WHERE ACCOUNT_ID = :accountId AND status = :status GROUP BY SEX";
		return dao.find(sqlString, paramMap, new RowMapper<Map<String, Object>>() {

			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("name", rs.getString("SEX_CN"));
				List<Integer> data = new ArrayList<Integer>();
				data.add(rs.getInt("COUNT"));
				row.put("data", data);
				return row;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.covisint.wechat.data.dao.UserStatDao#statLangSummary(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> statLangSummary(Map<String, Object> paramMap) {
		String sqlString = "SELECT COUNT(1) AS COUNT, CASE LANGUAGE WHEN 'zh_CN' THEN '简体中文' WHEN 'zh_TW' THEN '繁体中文' WHEN 'en' THEN '英文' WHEN 'ja' THEN '日语' ELSE '其他' END AS LANGUAGE_CN FROM WX_FANS WHERE ACCOUNT_ID = :accountId AND status = :status GROUP BY LANGUAGE";
		return dao.find(sqlString, paramMap, new RowMapper<Map<String, Object>>() {

			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("name", rs.getString("LANGUAGE_CN"));
				List<Integer> data = new ArrayList<Integer>();
				data.add(rs.getInt("COUNT"));
				row.put("data", data);
				return row;
			}
		});
	}

	@Override
	public Map<String, Integer> statTrendSummary(Map<String, Object> paramMap) {
		String sqlString = "SELECT COUNT(1) AS COUNT, T1.SUB_DAY AS DATE_TIME FROM (SELECT DISTINCT OPEN_ID, TO_CHAR(TO_DATE('1970-01-01', 'yyyy-mm-dd') + CREATE_TIME / 86400 + 8 / 24, 'yyyy-mm-dd') SUB_DAY FROM WX_EVENT_HIS WHERE ACCOUNT_ID = :accountId AND EVENT_TYPE = :eventType AND create_time >= :startTime AND create_time <= :endTime) T1 GROUP BY T1.OPEN_ID, T1.SUB_DAY ORDER BY T1.SUB_DAY";
		StatMapData md = new StatMapData();
		dao.getJdbcTemplate().query(sqlString, paramMap, md);
		return md.getRowData();
	}

	@Override
	public Map<String, Integer> statPureSummary(Map<String, Object> paramMap) {
		String sqlString = "SELECT COUNT(1) AS COUNT, T1.SUB_DAY AS DATE_TIME FROM (SELECT DISTINCT OPEN_ID, MIN(CREATE_TIME) AS CREATE_TIME, TO_CHAR(TO_DATE('1970-01-01', 'yyyy-mm-dd') + MIN(CREATE_TIME) / 86400 + 8 / 24, 'yyyy-mm-dd') SUB_DAY FROM WX_EVENT_HIS WHERE ACCOUNT_ID = :accountId AND EVENT_TYPE = :eventType GROUP BY OPEN_ID, ACCOUNT_ID) T1 WHERE T1.CREATE_TIME >= :startTime AND T1.CREATE_TIME <= :endTime GROUP BY T1.OPEN_ID, T1.SUB_DAY ORDER BY T1.SUB_DAY";
		StatMapData md = new StatMapData();
		dao.getJdbcTemplate().query(sqlString, paramMap, md);
		return md.getRowData();
	}

	@Override
	public Map<String, Integer> statFansSummary(Map<String, Object> paramMap) {
		String sqlString = "SELECT ACCOUNT_ID AS DATE_TIME, COUNT(1) AS COUNT FROM WX_FANS WHERE STATUS = :status GROUP BY ACCOUNT_ID ";
		StatMapData md = new StatMapData();
		dao.getJdbcTemplate().query(sqlString, paramMap, md);
		return md.getRowData();
	}

	@Override
	public Map<String, Object> lastSub(Map<String, Object> paramMap) {
		String sqlString = "SELECT COUNT(DISTINCT OPEN_ID) AS COUNT FROM WX_EVENT_HIS WHERE TO_CHAR(TO_DATE('1970-01-01', 'yyyy-mm-dd') + CREATE_TIME / 86400 + 8 / 24, 'yyyy-mm-dd') = TO_CHAR(TRUNC(SYSDATE - 1), 'yyyy-mm-dd') AND EVENT_TYPE = :eventType AND ACCOUNT_ID = :accountId";
		return dao.findOne(sqlString, paramMap);
	}

	@Override
	public Map<String, Object> lastUnSub(Map<String, Object> paramMap) {
		String sqlString = "SELECT COUNT(DISTINCT OPEN_ID) AS COUNT FROM WX_EVENT_HIS WHERE TO_CHAR(TO_DATE('1970-01-01', 'yyyy-mm-dd') + CREATE_TIME / 86400 + 8 / 24, 'yyyy-mm-dd') = TO_CHAR(TRUNC(SYSDATE - 1), 'yyyy-mm-dd') AND EVENT_TYPE = :eventType AND ACCOUNT_ID = :accountId";
		return dao.findOne(sqlString, paramMap);
	}

	@Override
	public Map<String, Object> lastPure(Map<String, Object> paramMap) {
		String sqlString = "SELECT COUNT(1) AS COUNT FROM (SELECT DISTINCT OPEN_ID, TO_CHAR(TO_DATE('1970-01-01', 'yyyy-mm-dd') + MIN(CREATE_TIME) / 86400 + 8 / 24, 'yyyy-mm-dd') SUB_DAY FROM WX_EVENT_HIS WHERE EVENT_TYPE = 'subscribe' AND ACCOUNT_ID = :accountId GROUP BY OPEN_ID, ACCOUNT_ID) T1 WHERE T1.SUB_DAY = TO_CHAR(TRUNC(SYSDATE - 1), 'yyyy-mm-dd') ";
		return dao.findOne(sqlString, paramMap);
	}
}
