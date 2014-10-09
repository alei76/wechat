/************************************************************************************
 * @File name   :      WxWebsiteHisDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-06-17
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
 * 2014-6-17 16:36:15			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxWebsiteHisDao;
import com.covisint.wechat.data.mapper.WxWebsiteHisMapper;
import com.covisint.wechat.data.model.WxWebsiteHis;
import com.covisint.wechat.page.Page;

/**
 * 网址访问记录表数据访问接口
 * 
 */
@Component
public class WxWebsiteHisDaoImpl implements WxWebsiteHisDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxWebsiteHis wxWebsiteHis) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("visitHisId", wxWebsiteHis.getVisitHisId());
		paramMap.put("visitDate", wxWebsiteHis.getVisitDate());
		paramMap.put("visitTime", wxWebsiteHis.getVisitTime());
		paramMap.put("accountId", wxWebsiteHis.getAccountId());
		paramMap.put("resourceId", wxWebsiteHis.getResourceId());
		paramMap.put("viewHref", wxWebsiteHis.getViewHref());
		paramMap.put("openId", wxWebsiteHis.getOpenId());
		paramMap.put("viewSource", wxWebsiteHis.getViewSource());
		return dao.insert(WxWebsiteHisMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxWebsiteHis wxWebsiteHis) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("visitHisId", wxWebsiteHis.getVisitHisId());
		paramMap.put("visitDate", wxWebsiteHis.getVisitDate());
		paramMap.put("visitTime", wxWebsiteHis.getVisitTime());
		paramMap.put("accountId", wxWebsiteHis.getAccountId());
		paramMap.put("resourceId", wxWebsiteHis.getResourceId());
		paramMap.put("viewHref", wxWebsiteHis.getViewHref());
		paramMap.put("openId", wxWebsiteHis.getOpenId());
		paramMap.put("viewSource", wxWebsiteHis.getViewSource());
		return dao.update(WxWebsiteHisMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxWebsiteHis get(java.lang.String visitHisId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("visitHisId", visitHisId);
		return dao.get(WxWebsiteHisMapper.getFindOneSql(paramMap), paramMap, new WxWebsiteHis());
	}

	@Override
	public List<WxWebsiteHis> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxWebsiteHisMapper.getFindAllSql(paramMap), paramMap, new WxWebsiteHis());
	}

	@Override
	public Page<WxWebsiteHis> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxWebsiteHisMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxWebsiteHis());
	}

	@Override
	public Page<Map<String, Object>> pageWebsiteHis(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		return dao.page(WxWebsiteHisMapper.getPageWebsiteHisSql(paramMap), paramMap, current, pagesize, new ColumnMapRowMapper());
	}

	@Override
	public Page<Map<String, Object>> pageDaySummary(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		return dao.page(WxWebsiteHisMapper.getPageDaySummarySql(paramMap), paramMap, current, pagesize, new ColumnMapRowMapper());
	}

	@Override
	public List<Map<String, Object>> daysummary(Map<String, Object> paramMap) {
		return dao.find(WxWebsiteHisMapper.getDaySummarySql(paramMap), paramMap);
	}

	@Override
	public Page<Map<String, Object>> pageTimeSummary(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		return dao.page(WxWebsiteHisMapper.getPageTimeSummarySql(paramMap), paramMap, current, pagesize, new ColumnMapRowMapper());
	}

	@Override
	public List<Map<String, Object>> timesummary(Map<String, Object> paramMap) {
		return dao.find(WxWebsiteHisMapper.getTimeSummarySql(paramMap), paramMap);
	}

	@Override
	public Page<Map<String, Object>> pageAreaSummary(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		return dao.page(WxWebsiteHisMapper.getPageAreaSummarySql(paramMap), paramMap, current, pagesize, new ColumnMapRowMapper());
	}
}