package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.page.Page;

public interface WxSystemUserDao {
	public int insert(WxSystemUser wxSystemUser);

	public int update(WxSystemUser wxSystemUser);

	public WxSystemUser get(java.lang.String userId);

	public Map<String, Object> findOne(java.lang.String userId);

	public List<Map<String, Object>> findList(Map<String, Object> paramMap);

	public List<WxSystemUser> findDomain(Map<String, Object> paramMap);

	public Page<WxSystemUser> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public WxSystemUser getCurrentUser(String userName, String password);

	public int changeStatus(String userId);

	public WxSystemUser getUserAdmin(String userName);

}