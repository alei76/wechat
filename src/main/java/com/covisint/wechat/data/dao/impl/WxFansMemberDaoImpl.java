/************************************************************************************
 * @File name   :      WxFansMemberDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-21
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
 * 2014-5-21 16:46:59			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxFansMemberDao;
import com.covisint.wechat.data.mapper.WxFansMemberMapper;
import com.covisint.wechat.data.model.WxFansMember;
import com.covisint.wechat.page.Page;

/**
 * 粉丝会员关系表数据访问接口
 * 
 */
@Component
public class WxFansMemberDaoImpl implements WxFansMemberDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxFansMember wxFansMember) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fansMemberId", wxFansMember.getFansMemberId());
		paramMap.put("fansId", wxFansMember.getFansId());
		paramMap.put("memberId", wxFansMember.getMemberId());
		paramMap.put("fullName", wxFansMember.getFullName());
		paramMap.put("mobile", wxFansMember.getMobile());
		paramMap.put("createTime", wxFansMember.getCreateTime());
		return dao.insert(WxFansMemberMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxFansMember wxFansMember) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fansMemberId", wxFansMember.getFansMemberId());
		paramMap.put("fansId", wxFansMember.getFansId());
		paramMap.put("memberId", wxFansMember.getMemberId());
		paramMap.put("fullName", wxFansMember.getFullName());
		paramMap.put("mobile", wxFansMember.getMobile());
		paramMap.put("createTime", wxFansMember.getCreateTime());
		return dao.update(WxFansMemberMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxFansMember get(java.lang.String fansMemberId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fansMemberId", fansMemberId);
		return dao.get(WxFansMemberMapper.getFindOneSql(paramMap), paramMap, new WxFansMember());
	}

	@Override
	public List<WxFansMember> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxFansMemberMapper.getFindAllSql(paramMap), paramMap, new WxFansMember());
	}

	@Override
	public Page<WxFansMember> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxFansMemberMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxFansMember());
	}

	@Override
	public Long checkExists(Map<String, Object> paramMap) {
		return dao.getJdbcTemplate().queryForObject(WxFansMemberMapper.getCheckExistsSql(paramMap), paramMap, Long.class);
	}

	@Override
	public int bindMember(Map<String, Object> paramMap) {
		return dao.insert(WxFansMemberMapper.getBindMemberSql(paramMap), paramMap);
	}
}