/************************************************************************************
 * @File name   :      WxMediaAttaDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-07
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
 * 2014-5-7 15:19:25			马恩伟			1.0				Initial Version
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
import com.covisint.wechat.data.dao.WxMediaAttaDao;
import com.covisint.wechat.data.mapper.WxMediaAttaMapper;
import com.covisint.wechat.data.model.WxMediaAtta;
import com.covisint.wechat.page.Page;

/**
 * 富媒体文件表数据访问接口
 * 
 */
@Component
public class WxMediaAttaDaoImpl implements WxMediaAttaDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxMediaAtta wxMediaAtta) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("attaId", wxMediaAtta.getAttaId());
		paramMap.put("name", wxMediaAtta.getName());
		paramMap.put("type", wxMediaAtta.getType());
		paramMap.put("accountId", wxMediaAtta.getAccountId());
		paramMap.put("createBy", wxMediaAtta.getCreateBy());
		paramMap.put("createTime", wxMediaAtta.getCreateTime());
		paramMap.put("status", wxMediaAtta.getStatus());
		return dao.insert(WxMediaAttaMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxMediaAtta wxMediaAtta) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("attaId", wxMediaAtta.getAttaId());
		paramMap.put("name", wxMediaAtta.getName());
		paramMap.put("type", wxMediaAtta.getType());
		paramMap.put("accountId", wxMediaAtta.getAccountId());
		paramMap.put("createBy", wxMediaAtta.getCreateBy());
		paramMap.put("createTime", wxMediaAtta.getCreateTime());
		paramMap.put("status", wxMediaAtta.getStatus());
		return dao.update(WxMediaAttaMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxMediaAtta get(java.lang.String attaId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("attaId", attaId);
		return dao.get(WxMediaAttaMapper.getFindOneSql(paramMap), paramMap, new WxMediaAtta());
	}

	@Override
	public List<WxMediaAtta> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxMediaAttaMapper.getFindAllSql(paramMap), paramMap, new WxMediaAtta());
	}

	@Override
	public Page<WxMediaAtta> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxMediaAttaMapper.getPageSql(paramMap), paramMap, current, pagesize, new RowMapper<WxMediaAtta>() {

			@Override
			public WxMediaAtta mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxMediaAtta domain = new WxMediaAtta();
				domain.setAttaId(rs.getString("ATTA_ID"));
				domain.setName(rs.getString("NAME"));
				domain.setType(rs.getString("TYPE"));
				domain.setAccountId(rs.getString("ACCOUNT_ID"));
				domain.setCreateBy(rs.getString("CREATE_BY"));
				domain.setCreateTime(rs.getString("CREATE_TIME"));
				domain.setStatus(rs.getString("STATUS"));
				domain.setStatusCn(rs.getString("STATUS_CN"));
				domain.setTypeCn(rs.getString("TYPE_CN"));
				return domain;
			}
		});
	}
}