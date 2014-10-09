/************************************************************************************
 * @File name   :      WxMenuInfoDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-04
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
 * 2014-5-4 14:40:02			马恩伟			1.0				Initial Version
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
import com.covisint.wechat.data.dao.WxMenuInfoDao;
import com.covisint.wechat.data.mapper.WxMenuInfoMapper;
import com.covisint.wechat.data.model.WxMenuInfo;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.page.Page;

/**
 * 微信菜单表数据访问接口
 * 
 */
@Component
public class WxMenuInfoDaoImpl implements WxMenuInfoDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxMenuInfo wxMenuInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", wxMenuInfo.getMenuId());
		paramMap.put("type", wxMenuInfo.getType());
		paramMap.put("name", wxMenuInfo.getName());
		paramMap.put("eventKey", wxMenuInfo.getEventKey());
		paramMap.put("target", wxMenuInfo.getTarget());
		paramMap.put("accountId", wxMenuInfo.getAccountId());
		paramMap.put("parentId", wxMenuInfo.getParentId());
		paramMap.put("orderBy", wxMenuInfo.getOrderBy());
		paramMap.put("createTime", wxMenuInfo.getCreateTime());
		paramMap.put("createBy", wxMenuInfo.getCreateBy());
		paramMap.put("status", wxMenuInfo.getStatus());
		paramMap.put("checkBind", wxMenuInfo.getCheckBind());
		paramMap.put("anonTarget", wxMenuInfo.getAnonTarget());
		paramMap.put("robotKeyword", wxMenuInfo.getRobotKeyword());
		paramMap.put("anonRobotKeyword", wxMenuInfo.getAnonRobotKeyword());
		paramMap.put("oauthScope", wxMenuInfo.getOauthScope());
		return dao.insert(WxMenuInfoMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxMenuInfo wxMenuInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", wxMenuInfo.getMenuId());
		paramMap.put("type", wxMenuInfo.getType());
		paramMap.put("name", wxMenuInfo.getName());
		paramMap.put("eventKey", wxMenuInfo.getEventKey());
		paramMap.put("target", wxMenuInfo.getTarget());
		paramMap.put("accountId", wxMenuInfo.getAccountId());
		paramMap.put("parentId", wxMenuInfo.getParentId());
		paramMap.put("orderBy", wxMenuInfo.getOrderBy());
		paramMap.put("createTime", wxMenuInfo.getCreateTime());
		paramMap.put("createBy", wxMenuInfo.getCreateBy());
		paramMap.put("status", wxMenuInfo.getStatus());
		paramMap.put("checkBind", wxMenuInfo.getCheckBind());
		paramMap.put("anonTarget", wxMenuInfo.getAnonTarget());
		paramMap.put("robotKeyword", wxMenuInfo.getRobotKeyword());
		paramMap.put("anonRobotKeyword", wxMenuInfo.getAnonRobotKeyword());
		paramMap.put("oauthScope", wxMenuInfo.getOauthScope());
		return dao.update(WxMenuInfoMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxMenuInfo get(java.lang.String menuId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		return dao.get(WxMenuInfoMapper.getFindOneSql(paramMap), paramMap, new WxMenuInfo());
	}

	@Override
	public Map<String, Object> findOne(java.lang.String menuId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		return dao.findOne(WxMenuInfoMapper.getFindOneSql(paramMap), paramMap);
	}

	@Override
	public List<Map<String, Object>> findList(Map<String, Object> paramMap) {
		return dao.find(WxMenuInfoMapper.getFindAllSql(paramMap), paramMap);
	}

	@Override
	public List<WxMenuInfo> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxMenuInfoMapper.getFindAllSql(paramMap), paramMap, new WxMenuInfo());
	}

	@Override
	public Page<WxMenuInfo> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxMenuInfoMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxMenuInfo());
	}

	@Override
	public int createMenu(WxMenuInfo wxMenuInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", wxMenuInfo.getMenuId());
		paramMap.put("type", wxMenuInfo.getType());
		paramMap.put("name", wxMenuInfo.getName());
		paramMap.put("eventKey", wxMenuInfo.getEventKey());
		paramMap.put("target", wxMenuInfo.getTarget());
		paramMap.put("accountId", wxMenuInfo.getAccountId());
		paramMap.put("parentId", wxMenuInfo.getParentId());
		paramMap.put("orderBy", wxMenuInfo.getOrderBy());
		paramMap.put("createTime", wxMenuInfo.getCreateTime());
		paramMap.put("createBy", wxMenuInfo.getCreateBy());
		paramMap.put("status", wxMenuInfo.getStatus());
		paramMap.put("checkBind", wxMenuInfo.getCheckBind());
		paramMap.put("anonTarget", wxMenuInfo.getAnonTarget());
		paramMap.put("robotKeyword", wxMenuInfo.getRobotKeyword());
		paramMap.put("anonRobotKeyword", wxMenuInfo.getAnonRobotKeyword());
		paramMap.put("oauthScope", wxMenuInfo.getOauthScope());
		return dao.insert(WxMenuInfoMapper.getCreateSql(paramMap), paramMap);
	}

	@Override
	public List<ZtreeMenu> getWxMenu(Map<String, Object> paramMap) {
		return dao.find(WxMenuInfoMapper.getWxMenu(paramMap), paramMap, new ZtreeMenu());
	}

	@Override
	public WxMenuInfo getMenuInfo(String menuId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		paramMap.put("enable", WxMenuInfo.STATUS_ENABLE);
		return dao.get(WxMenuInfoMapper.getMenuInfo(paramMap), paramMap, new RowMapper<WxMenuInfo>() {

			@Override
			public WxMenuInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxMenuInfo domain = new WxMenuInfo();
				domain.setMenuId(rs.getString("MENU_ID"));
				domain.setType(rs.getString("TYPE"));
				domain.setName(rs.getString("NAME"));
				domain.setEventKey(rs.getString("EVENT_KEY"));
				domain.setTarget(rs.getString("TARGET"));
				domain.setAccountId(rs.getString("ACCOUNT_ID"));
				domain.setParentId(rs.getString("PARENT_ID"));
				domain.setOrderBy(rs.getString("ORDER_BY"));
				domain.setCreateTime(rs.getString("CREATE_TIME"));
				domain.setCreateBy(rs.getString("CREATE_BY"));
				domain.setStatus(rs.getString("STATUS"));
				domain.setCheckBind(rs.getString("CHECK_BIND"));
				domain.setAnonTarget(rs.getString("ANON_TARGET"));
				domain.setSubCount(rs.getInt("SUB_COUNT"));
				domain.setRobotKeyword(rs.getString("ROBOT_KEYWORD"));
				domain.setAnonRobotKeyword(rs.getString("ANON_ROBOT_KEYWORD"));
				domain.setOauthScope(rs.getString("OAUTH_SCOPE"));
				return domain;
			}
		});
	}

	@Override
	public int deleteMenu(Map<String, Object> paramMap) {
		return dao.update(WxMenuInfoMapper.getDeleteSql(paramMap), paramMap);
	}

	@Override
	public int updateParentMenu(String menuId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		paramMap.put("parentId", WxMenuInfo.ROOT_PARNET_MENUID);
		paramMap.put("checkBind", WxMenuInfo.CHECK_BIND_DISABLE);
		return dao.update(WxMenuInfoMapper.getInitParentMenu(paramMap), paramMap);
	}

	@Override
	public int moveMenuUp(Map<String, Object> paramMap) {
		dao.update(WxMenuInfoMapper.getMoveOtherUpSql(paramMap), paramMap);
		return dao.update(WxMenuInfoMapper.getMoveSelfUpSql(paramMap), paramMap);
	}

	@Override
	public int moveMenuDown(Map<String, Object> paramMap) {
		dao.update(WxMenuInfoMapper.getMoveOtherDownSql(paramMap), paramMap);
		return dao.update(WxMenuInfoMapper.getMoveSelfDownSql(paramMap), paramMap);
	}

	@Override
	public Long validateToMoveUp(Map<String, Object> paramMap) {
		return dao.getJdbcTemplate().queryForObject(WxMenuInfoMapper.getValidateMoveUpSql(paramMap), paramMap, Long.class);
	}

	@Override
	public List<WxMenuInfo> findMenuDomain(Map<String, Object> paramMap) {
		return dao.find(WxMenuInfoMapper.getMenuSql(paramMap), paramMap, new RowMapper<WxMenuInfo>() {
			@Override
			public WxMenuInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxMenuInfo domain = new WxMenuInfo();
				domain.setMenuId(rs.getString("MENU_ID"));
				domain.setType(rs.getString("TYPE"));
				domain.setName(rs.getString("NAME"));
				domain.setEventKey(rs.getString("EVENT_KEY"));
				domain.setTarget(rs.getString("TARGET"));
				domain.setAccountId(rs.getString("ACCOUNT_ID"));
				domain.setOauthScope(rs.getString("OAUTH_SCOPE"));
				return domain;
			}
		});
	}

	@Override
	public WxMenuInfo getMessageMenu(Map<String, Object> paramMap) {
		return dao.get(WxMenuInfoMapper.getFindOneSql(paramMap), paramMap, new WxMenuInfo());
	}

	@Override
	public int checkExists(Map<String, Object> paramMap) {
		return dao.getJdbcTemplate().queryForObject(WxMenuInfoMapper.getCheckExistsSql(paramMap), paramMap, Integer.class);
	}
}