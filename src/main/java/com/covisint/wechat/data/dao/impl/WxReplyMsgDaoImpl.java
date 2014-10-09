/************************************************************************************
 * @File name   :      WxReplyMsgDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-09
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
 * 2014-5-9 17:34:25			马恩伟			1.0				Initial Version
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
import com.covisint.wechat.data.dao.WxReplyMsgDao;
import com.covisint.wechat.data.mapper.WxReplyMsgMapper;
import com.covisint.wechat.data.model.WxReplyMsg;
import com.covisint.wechat.page.Page;

/**
 * 自动回复关系表数据访问接口
 * 
 */
@Component
public class WxReplyMsgDaoImpl implements WxReplyMsgDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxReplyMsg wxReplyMsg) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("replyId", wxReplyMsg.getReplyId());
		paramMap.put("accountId", wxReplyMsg.getAccountId());
		paramMap.put("keyword", wxReplyMsg.getKeyword());
		paramMap.put("templateId", wxReplyMsg.getTemplateId());
		paramMap.put("tiggerType", wxReplyMsg.getTiggerType());
		paramMap.put("status", wxReplyMsg.getStatus());
		paramMap.put("createBy", wxReplyMsg.getCreateBy());
		paramMap.put("createTime", wxReplyMsg.getCreateTime());
		paramMap.put("checkBind", wxReplyMsg.getCheckBind());
		paramMap.put("anonTemplateId", wxReplyMsg.getAnonTemplateId());
		return dao.insert(WxReplyMsgMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxReplyMsg wxReplyMsg) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("replyId", wxReplyMsg.getReplyId());
		paramMap.put("accountId", wxReplyMsg.getAccountId());
		paramMap.put("keyword", wxReplyMsg.getKeyword());
		paramMap.put("templateId", wxReplyMsg.getTemplateId());
		paramMap.put("tiggerType", wxReplyMsg.getTiggerType());
		paramMap.put("status", wxReplyMsg.getStatus());
		paramMap.put("createBy", wxReplyMsg.getCreateBy());
		paramMap.put("createTime", wxReplyMsg.getCreateTime());
		paramMap.put("checkBind", wxReplyMsg.getCheckBind());
		paramMap.put("anonTemplateId", wxReplyMsg.getAnonTemplateId());
		return dao.update(WxReplyMsgMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxReplyMsg get(java.lang.String replyId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("replyId", replyId);
		return dao.get(WxReplyMsgMapper.getFindOneSql(paramMap), paramMap, new WxReplyMsg());
	}

	@Override
	public List<WxReplyMsg> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxReplyMsgMapper.getFindAllSql(paramMap), paramMap, new WxReplyMsg());
	}

	@Override
	public Page<WxReplyMsg> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxReplyMsgMapper.getPageSql(paramMap), paramMap, current, pagesize, new RowMapper<WxReplyMsg>() {

			@Override
			public WxReplyMsg mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxReplyMsg domain = new WxReplyMsg();
				domain.setReplyId(rs.getString("REPLY_ID"));
				domain.setAccountId(rs.getString("ACCOUNT_ID"));
				domain.setKeyword(rs.getString("KEYWORD"));
				domain.setTemplateId(rs.getString("TEMPLATE_ID"));
				domain.setTiggerType(rs.getString("TIGGER_TYPE"));
				domain.setStatus(rs.getString("STATUS"));
				domain.setCreateBy(rs.getString("CREATE_BY"));
				domain.setCreateTime(rs.getString("CREATE_TIME"));
				domain.setCheckBind(rs.getString("CHECK_BIND"));
				domain.setAnonTemplateId(rs.getString("ANON_TEMPLATE_ID"));
				domain.setStatusCn(rs.getString("STAUTS_CN"));
				domain.setCheckBindCn(rs.getString("CHECK_BIND_CN"));
				domain.setTiggerTypeCn(rs.getString("TIGGER_TYPE_CN"));
				domain.setTemplateCn(rs.getString("TEMPLATE_CN"));
				domain.setAnonTemplateCn(rs.getString("ANON_TEMPLATE_CN"));
				return domain;
			}
		});
	}

	@Override
	public WxReplyMsg getBehaviorMsg(String accountId, String tiggerType) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("tiggerType", tiggerType);
		return dao.get(WxReplyMsgMapper.getFindOneSql(paramMap), paramMap, new WxReplyMsg());
	}

	@Override
	public int changeStatus(String replyId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("replyId", replyId);
		paramMap.put("enable", WxReplyMsg.STATUS_ENABLE);
		paramMap.put("disable", WxReplyMsg.STATUS_DISABLE);
		return dao.update(WxReplyMsgMapper.getChangeStatusSql(paramMap), paramMap);
	}

	@Override
	public int checkExists(Map<String, Object> paramMap) {
		return dao.getJdbcTemplate().queryForObject(WxReplyMsgMapper.getCheckExistsSql(paramMap), paramMap, Integer.class);
	}
}