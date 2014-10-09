package com.covisint.wechat.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxUserRolesDao;
import com.covisint.wechat.data.mapper.WxUserRolesMapper;
import com.covisint.wechat.data.model.WxUserRoles;
import com.covisint.wechat.page.Page;

@Component
public class WxUserRolesDaoImpl implements WxUserRolesDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxUserRoles wxUserRoles) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", wxUserRoles.getRoleId());
		paramMap.put("roleName", wxUserRoles.getRoleName());
		paramMap.put("roleDesc", wxUserRoles.getRoleDesc());
		paramMap.put("createBy", wxUserRoles.getCreateBy());
		paramMap.put("createTime", wxUserRoles.getCreateTime());
		paramMap.put("status", wxUserRoles.getStatus());
		paramMap.put("roleType", wxUserRoles.getRoleType());
		return dao.insert(WxUserRolesMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxUserRoles wxUserRoles) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", wxUserRoles.getRoleId());
		paramMap.put("roleName", wxUserRoles.getRoleName());
		paramMap.put("roleDesc", wxUserRoles.getRoleDesc());
		paramMap.put("createBy", wxUserRoles.getCreateBy());
		paramMap.put("createTime", wxUserRoles.getCreateTime());
		paramMap.put("status", wxUserRoles.getStatus());
		paramMap.put("roleType", wxUserRoles.getRoleType());
		return dao.update(WxUserRolesMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxUserRoles get(java.lang.String roleId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		return dao.get(WxUserRolesMapper.getFindOneSql(paramMap), paramMap, new WxUserRoles());
	}

	@Override
	public Map<String, Object> findOne(java.lang.String roleId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		return dao.findOne(WxUserRolesMapper.getFindOneSql(paramMap), paramMap);
	}

	@Override
	public List<Map<String, Object>> findList(Map<String, Object> paramMap) {
		return dao.find(WxUserRolesMapper.getFindAllSql(paramMap), paramMap);
	}

	@Override
	public List<WxUserRoles> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxUserRolesMapper.getFindAllSql(paramMap), paramMap, new WxUserRoles());
	}

	@Override
	public Page<WxUserRoles> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxUserRolesMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxUserRoles());
	}

	@Override
	public int changeStatus(String roleId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		paramMap.put("enable", WxUserRoles.STATUS_ENABLE);
		paramMap.put("disable", WxUserRoles.STATUS_DISABLE);
		return dao.update(WxUserRolesMapper.getChangeStatusSql(paramMap), paramMap);
	}

	@Override
	public int checkExists(Map<String, Object> paramMap) {
		return dao.getJdbcTemplate().queryForObject(WxUserRolesMapper.getCheckExistsSql(paramMap), paramMap, Integer.class);
	}
}