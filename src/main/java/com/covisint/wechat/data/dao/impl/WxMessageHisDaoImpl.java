/************************************************************************************
 * @File name   :      WxMessageHisDaoImpl.java
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
 * 2014-6-17 9:39:26			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxMessageHisDao;
import com.covisint.wechat.data.mapper.WxMessageHisMapper;
import com.covisint.wechat.data.model.WxMessageHis;
import com.covisint.wechat.page.Page;

/**
 * 微信消息记录表数据访问接口
 * 
 */
@Component
public class WxMessageHisDaoImpl implements WxMessageHisDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxMessageHis wxMessageHis) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("messageHisId", wxMessageHis.getMessageHisId());
		paramMap.put("openId", wxMessageHis.getOpenId());
		paramMap.put("receiver", wxMessageHis.getReceiver());
		paramMap.put("accountId", wxMessageHis.getAccountId());
		paramMap.put("createTime", wxMessageHis.getCreateTime());
		paramMap.put("msgType", wxMessageHis.getMsgType());
		paramMap.put("content", wxMessageHis.getContent());
		paramMap.put("attaId", wxMessageHis.getAttaId());
		paramMap.put("localTime", wxMessageHis.getLocalTime());
		paramMap.put("msgSource", wxMessageHis.getMsgSource());
		paramMap.put("msgId", wxMessageHis.getMsgId());
		paramMap.put("replyMsgId", wxMessageHis.getReplyMsgId());
		return dao.insert(WxMessageHisMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxMessageHis wxMessageHis) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("messageHisId", wxMessageHis.getMessageHisId());
		paramMap.put("openId", wxMessageHis.getOpenId());
		paramMap.put("receiver", wxMessageHis.getReceiver());
		paramMap.put("accountId", wxMessageHis.getAccountId());
		paramMap.put("createTime", wxMessageHis.getCreateTime());
		paramMap.put("msgType", wxMessageHis.getMsgType());
		paramMap.put("content", wxMessageHis.getContent());
		paramMap.put("attaId", wxMessageHis.getAttaId());
		paramMap.put("localTime", wxMessageHis.getLocalTime());
		paramMap.put("msgSource", wxMessageHis.getMsgSource());
		paramMap.put("msgId", wxMessageHis.getMsgId());
		paramMap.put("replyMsgId", wxMessageHis.getReplyMsgId());
		return dao.update(WxMessageHisMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxMessageHis get(java.lang.String messageHisId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("messageHisId", messageHisId);
		return dao.get(WxMessageHisMapper.getFindOneSql(paramMap), paramMap, new WxMessageHis());
	}

	@Override
	public List<WxMessageHis> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxMessageHisMapper.getFindAllSql(paramMap), paramMap, new WxMessageHis());
	}

	@Override
	public Page<WxMessageHis> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxMessageHisMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxMessageHis());
	}

	@Override
	public WxMessageHis getMsgHis(Map<String, Object> paramMap) {
		return dao.get(WxMessageHisMapper.getFindOneSql(paramMap), paramMap, new WxMessageHis());
	}

	@Override
	public Page<Map<String, Object>> pageMsgHis(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		return dao.page(WxMessageHisMapper.getPageMsgHisSql(paramMap), paramMap, current, pagesize, new ColumnMapRowMapper());
	}

	@Override
	public Page<Map<String, Object>> pageMsgCollect(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		return dao.page(WxMessageHisMapper.getPageMsgCollectSql(paramMap), paramMap, current, pagesize, new ColumnMapRowMapper());
	}
}