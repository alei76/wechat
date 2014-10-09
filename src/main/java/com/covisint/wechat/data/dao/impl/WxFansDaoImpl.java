/************************************************************************************
 * @File name   :      WxFansDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-15
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
 * 2014-5-15 15:09:37			马恩伟			1.0				Initial Version
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
import com.covisint.wechat.data.dao.WxFansDao;
import com.covisint.wechat.data.mapper.WxFansMapper;
import com.covisint.wechat.data.model.WxFans;
import com.covisint.wechat.data.model.WxFansMember;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.IdentifierUtils;

/**
 * 微信粉丝表数据访问接口
 * 
 */
@Component
public class WxFansDaoImpl implements WxFansDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxFans wxFans) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fansId", wxFans.getFansId());
		paramMap.put("openId", wxFans.getOpenId());
		paramMap.put("nickName", wxFans.getNickName());
		paramMap.put("sex", wxFans.getSex());
		paramMap.put("city", wxFans.getCity());
		paramMap.put("country", wxFans.getCountry());
		paramMap.put("province", wxFans.getProvince());
		paramMap.put("language", wxFans.getLanguage());
		paramMap.put("headimgUrl", wxFans.getHeadimgUrl());
		paramMap.put("accountId", wxFans.getAccountId());
		paramMap.put("groupId", wxFans.getGroupId());
		paramMap.put("status", wxFans.getStatus());
		paramMap.put("subTime", wxFans.getSubTime());
		paramMap.put("unsubTime", wxFans.getUnsubTime());
		return dao.insert(WxFansMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxFans wxFans) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fansId", wxFans.getFansId());
		paramMap.put("openId", wxFans.getOpenId());
		paramMap.put("nickName", wxFans.getNickName());
		paramMap.put("sex", wxFans.getSex());
		paramMap.put("city", wxFans.getCity());
		paramMap.put("country", wxFans.getCountry());
		paramMap.put("province", wxFans.getProvince());
		paramMap.put("language", wxFans.getLanguage());
		paramMap.put("headimgUrl", wxFans.getHeadimgUrl());
		paramMap.put("accountId", wxFans.getAccountId());
		paramMap.put("groupId", wxFans.getGroupId());
		paramMap.put("status", wxFans.getStatus());
		paramMap.put("subTime", wxFans.getSubTime());
		paramMap.put("unsubTime", wxFans.getUnsubTime());
		return dao.update(WxFansMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxFans get(java.lang.String fansId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fansId", fansId);
		return dao.get(WxFansMapper.getFindOneSql(paramMap), paramMap, new WxFans());
	}

	@Override
	public List<WxFans> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxFansMapper.getFindAllSql(paramMap), paramMap, new WxFans());
	}

	@Override
	public Page<WxFans> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxFansMapper.getPageSql(paramMap), paramMap, current, pagesize, new RowMapper<WxFans>() {

			@Override
			public WxFans mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxFans domain = new WxFans();
				domain.setFansId(rs.getString("FANS_ID"));
				domain.setOpenId(rs.getString("OPEN_ID"));
				domain.setNickName(rs.getString("NICK_NAME"));
				domain.setSex(rs.getString("SEX"));
				domain.setCity(rs.getString("CITY"));
				domain.setCountry(rs.getString("COUNTRY"));
				domain.setProvince(rs.getString("PROVINCE"));
				domain.setLanguage(rs.getString("LANGUAGE"));
				domain.setHeadimgUrl(rs.getString("HEADIMG_URL"));
				domain.setAccountId(rs.getString("ACCOUNT_ID"));
				domain.setGroupId(rs.getString("GROUP_ID"));
				domain.setStatus(rs.getString("STATUS"));
				domain.setSubTime(rs.getString("SUB_TIME"));
				domain.setGroupCn(rs.getString("GROUP_NAME"));

				WxFansMember wxFansMember = new WxFansMember();
				wxFansMember.setFansMemberId(rs.getString("FANS_MEMBER_ID"));
				wxFansMember.setFansId(rs.getString("FANS_ID"));
				wxFansMember.setMemberId(rs.getString("MEMBER_ID"));
				wxFansMember.setFullName(rs.getString("FULL_NAME"));
				wxFansMember.setMobile(rs.getString("MOBILE"));

				domain.setWxFansMember(wxFansMember);
				return domain;
			}
		});
	}

	@Override
	public int[] batchInsert(List<String> openList, String accountId, String groupId) {
		int count = openList.size();
		SqlParameterSource[] batchArgs = new SqlParameterSource[count];
		for (int i = 0; i < count; i++) {
			String openId = openList.get(i);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("fansId", IdentifierUtils.getId().generate().toString());
			paramMap.put("openId", openId);
			paramMap.put("accountId", accountId);
			paramMap.put("groupId", groupId);
			paramMap.put("status", WxFans.STATUS_ENABLE);
			batchArgs[i] = new MapSqlParameterSource(paramMap);
		}
		return dao.getJdbcTemplate().batchUpdate(WxFansMapper.getBatchInsertSql(), batchArgs);
	}

	@Override
	public int changeFansGroup(Map<String, Object> paramMap) {
		return dao.update(WxFansMapper.getGroupChangeSql(paramMap), paramMap);
	}

	@Override
	public int[] changeGroup(String groupId, List<String> fansIdList) {
		int count = fansIdList.size();
		SqlParameterSource[] batchArgs = new SqlParameterSource[count];
		for (int i = 0; i < count; i++) {
			String fansId = fansIdList.get(i);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("fansId", fansId);
			paramMap.put("groupId", groupId);
			batchArgs[i] = new MapSqlParameterSource(paramMap);
		}
		return dao.getJdbcTemplate().batchUpdate(WxFansMapper.getBatchUpdate(), batchArgs);
	}

	@Override
	public WxFans get(String openId, String accountId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openId", openId);
		paramMap.put("accountId", accountId);
		return dao.get(WxFansMapper.getFindOneSql(paramMap), paramMap, new WxFans());
	}

	@Override
	public int unsubscribe(WxFans wxFans) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openId", wxFans.getOpenId());
		paramMap.put("accountId", wxFans.getAccountId());
		paramMap.put("status", wxFans.getStatus());
		return dao.update(WxFansMapper.getUnSubSql(paramMap), paramMap);
	}

	@Override
	public WxFans info(String fansId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fansId", fansId);
		return dao.get(WxFansMapper.getPageSql(paramMap), paramMap, new RowMapper<WxFans>() {

			@Override
			public WxFans mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxFans domain = new WxFans();
				domain.setFansId(rs.getString("FANS_ID"));
				domain.setOpenId(rs.getString("OPEN_ID"));
				domain.setNickName(rs.getString("NICK_NAME"));
				domain.setSex(rs.getString("SEX"));
				domain.setCity(rs.getString("CITY"));
				domain.setCountry(rs.getString("COUNTRY"));
				domain.setProvince(rs.getString("PROVINCE"));
				domain.setLanguage(rs.getString("LANGUAGE"));
				domain.setHeadimgUrl(rs.getString("HEADIMG_URL"));
				domain.setAccountId(rs.getString("ACCOUNT_ID"));
				domain.setGroupId(rs.getString("GROUP_ID"));
				domain.setStatus(rs.getString("STATUS"));
				domain.setSubTime(rs.getString("SUB_TIME"));
				domain.setGroupCn(rs.getString("GROUP_NAME"));

				WxFansMember wxFansMember = new WxFansMember();
				wxFansMember.setFansMemberId(rs.getString("FANS_MEMBER_ID"));
				wxFansMember.setFansId(rs.getString("FANS_ID"));
				wxFansMember.setMemberId(rs.getString("MEMBER_ID"));
				wxFansMember.setFullName(rs.getString("FULL_NAME"));
				wxFansMember.setMobile(rs.getString("MOBILE"));
				wxFansMember.setCreateTime(rs.getString("CREATE_TIME"));
				domain.setWxFansMember(wxFansMember);
				return domain;
			}
		});
	}

	@Override
	public List<String> getNewOpenIds(List<String> openIds, String accountId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openIds", openIds);
		paramMap.put("accountId", accountId);
		return dao.find(WxFansMapper.getNewOpenIdsSql(paramMap), paramMap, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("OPEN_ID");
			}
		});
	}

	@Override
	public List<String> listProv(Map<String, Object> paramMap) {
		return dao.find(WxFansMapper.getListProvSql(paramMap), paramMap, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("province");
			}
		});
	}
}