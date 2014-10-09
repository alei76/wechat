/************************************************************************************
 * @File name   :      WxGroupDaoImpl.java
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
 * 2014-5-15 15:56:59			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxGroupDao;
import com.covisint.wechat.data.mapper.WxGroupMapper;
import com.covisint.wechat.data.model.WxGroup;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.page.Page;

/**
 * 微信分组表数据访问接口
 * 
 */
@Component
public class WxGroupDaoImpl implements WxGroupDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxGroup wxGroup) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupId", wxGroup.getGroupId());
		paramMap.put("groupName", wxGroup.getGroupName());
		paramMap.put("accountId", wxGroup.getAccountId());
		paramMap.put("createBy", wxGroup.getCreateBy());
		paramMap.put("createTime", wxGroup.getCreateTime());
		paramMap.put("type", wxGroup.getType());
		paramMap.put("status", wxGroup.getStatus());
		return dao.insert(WxGroupMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxGroup wxGroup) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupId", wxGroup.getGroupId());
		paramMap.put("groupName", wxGroup.getGroupName());
		paramMap.put("accountId", wxGroup.getAccountId());
		paramMap.put("createBy", wxGroup.getCreateBy());
		paramMap.put("createTime", wxGroup.getCreateTime());
		paramMap.put("type", wxGroup.getType());
		paramMap.put("status", wxGroup.getStatus());
		return dao.update(WxGroupMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxGroup get(java.lang.String groupId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupId", groupId);
		return dao.get(WxGroupMapper.getFindOneSql(paramMap), paramMap, new WxGroup());
	}

	@Override
	public List<WxGroup> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxGroupMapper.getFindAllSql(paramMap), paramMap, new WxGroup());
	}

	@Override
	public Page<WxGroup> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxGroupMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxGroup());
	}

	/**
	 * overridden:
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-15
	 * @see com.covisint.wechat.data.dao.WxGroupDao#getSystemGroup(java.util.Map)
	 **/
	@Override
	public WxGroup getSystemGroup(Map<String, Object> paramMap) {
		return dao.get(WxGroupMapper.getFindOneSql(paramMap), paramMap, new WxGroup());
	}

	@Override
	public List<ZtreeMenu> listGroup(Map<String, Object> paramMap) {
		return dao.find(WxGroupMapper.getListSql(paramMap), paramMap, new ZtreeMenu());
	}

	@Override
	public int checkExists(Map<String, Object> paramMap) {
		return dao.getJdbcTemplate().queryForObject(WxGroupMapper.getCheckExistsSql(paramMap), paramMap, Integer.class);
	}
}