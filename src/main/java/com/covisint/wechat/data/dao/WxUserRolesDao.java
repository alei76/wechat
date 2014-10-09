package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxUserRoles;
import com.covisint.wechat.page.Page;

public interface WxUserRolesDao {
	public int insert(WxUserRoles wxUserRoles);

	public int update(WxUserRoles wxUserRoles);

	public WxUserRoles get(java.lang.String roleId);

	public Map<String, Object> findOne(java.lang.String roleId);

	public List<Map<String, Object>> findList(Map<String, Object> paramMap);

	public List<WxUserRoles> findDomain(Map<String, Object> paramMap);

	public Page<WxUserRoles> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-14
	 * @param roleId
	 * @return
	 */
	public int changeStatus(String roleId);

	public int checkExists(Map<String, Object> paramMap);

}