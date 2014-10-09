package com.covisint.wechat.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxSystemUserDao;
import com.covisint.wechat.data.mapper.WxSystemUserMapper;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.page.Page;

@Component
public class WxSystemUserDaoImpl implements WxSystemUserDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxSystemUser wxSystemUser) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", wxSystemUser.getUserId());
		paramMap.put("userName", wxSystemUser.getUserName());
		paramMap.put("password", wxSystemUser.getPassword());
		paramMap.put("fullName", wxSystemUser.getFullName());
		paramMap.put("status", wxSystemUser.getStatus());
		paramMap.put("userType", wxSystemUser.getUserType());
		paramMap.put("createBy", wxSystemUser.getCreateBy());
		paramMap.put("createTime", wxSystemUser.getCreateTime());
		return dao.insert(WxSystemUserMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxSystemUser wxSystemUser) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", wxSystemUser.getUserId());
		paramMap.put("userName", wxSystemUser.getUserName());
		paramMap.put("password", wxSystemUser.getPassword());
		paramMap.put("fullName", wxSystemUser.getFullName());
		paramMap.put("status", wxSystemUser.getStatus());
		paramMap.put("userType", wxSystemUser.getUserType());
		paramMap.put("createBy", wxSystemUser.getCreateBy());
		paramMap.put("createTime", wxSystemUser.getCreateTime());
		return dao.update(WxSystemUserMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxSystemUser get(java.lang.String userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return dao.get(WxSystemUserMapper.getFindOneSql(paramMap), paramMap, new WxSystemUser());
	}

	@Override
	public Map<String, Object> findOne(java.lang.String userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return dao.findOne(WxSystemUserMapper.getFindOneSql(paramMap), paramMap);
	}

	@Override
	public List<Map<String, Object>> findList(Map<String, Object> paramMap) {
		return dao.find(WxSystemUserMapper.getFindAllSql(paramMap), paramMap);
	}

	@Override
	public List<WxSystemUser> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxSystemUserMapper.getFindAllSql(paramMap), paramMap, new WxSystemUser());
	}

	@Override
	public Page<WxSystemUser> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxSystemUserMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxSystemUser());
	}

	@Override
	public WxSystemUser getCurrentUser(String userName, String password) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userName);
		paramMap.put("password", password);
		return dao.get(WxSystemUserMapper.getFindOneSql(paramMap), paramMap, new WxSystemUser());
	}

	@Override
	public int changeStatus(String userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("enable", WxSystemUser.ADMIN_STATUS_ENABLE);
		paramMap.put("disable", WxSystemUser.ADMIN_STATUS_DISABLE);
		return dao.update(WxSystemUserMapper.getChangeStatusSql(paramMap), paramMap);
	}

	@Override
	public WxSystemUser getUserAdmin(String userName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userName);
		paramMap.put("status", WxSystemUser.ADMIN_STATUS_ENABLE);
		return dao.get(WxSystemUserMapper.getFindOneSql(paramMap), paramMap, new WxSystemUser());
	}
}