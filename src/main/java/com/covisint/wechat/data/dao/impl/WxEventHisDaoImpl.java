/************************************************************************************
 * @File name   :      WxEventHisDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-06-04
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
 * 2014-6-4 10:05:47			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxEventHisDao;
import com.covisint.wechat.data.mapper.WxEventHisMapper;
import com.covisint.wechat.data.model.WxEventHis;
import com.covisint.wechat.page.Page;

/**
 * 消息事件记录表数据访问接口
 * 
 */
@Component
public class WxEventHisDaoImpl implements WxEventHisDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxEventHis wxEventHis) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("eventHisId", wxEventHis.getEventHisId());
		paramMap.put("eventType", wxEventHis.getEventType());
		paramMap.put("createTime", wxEventHis.getCreateTime());
		paramMap.put("openId", wxEventHis.getOpenId());
		paramMap.put("accountId", wxEventHis.getAccountId());
		paramMap.put("eventKey", wxEventHis.getEventKey());
		paramMap.put("msgContent", wxEventHis.getMsgContent());
		return dao.insert(WxEventHisMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxEventHis wxEventHis) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("eventHisId", wxEventHis.getEventHisId());
		paramMap.put("eventType", wxEventHis.getEventType());
		paramMap.put("createTime", wxEventHis.getCreateTime());
		paramMap.put("openId", wxEventHis.getOpenId());
		paramMap.put("accountId", wxEventHis.getAccountId());
		paramMap.put("eventKey", wxEventHis.getEventKey());
		paramMap.put("msgContent", wxEventHis.getMsgContent());
		return dao.update(WxEventHisMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxEventHis get(java.lang.String eventHisId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("eventHisId", eventHisId);
		return dao.get(WxEventHisMapper.getFindOneSql(paramMap), paramMap, new WxEventHis());
	}

	@Override
	public List<WxEventHis> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxEventHisMapper.getFindAllSql(paramMap), paramMap, new WxEventHis());
	}

	@Override
	public Page<WxEventHis> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxEventHisMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxEventHis());
	}

	@Override
	public WxEventHis getEventHis(Map<String, Object> paramMap) {
		return dao.get(WxEventHisMapper.getFindOneSql(paramMap), paramMap, new WxEventHis());
	}
}