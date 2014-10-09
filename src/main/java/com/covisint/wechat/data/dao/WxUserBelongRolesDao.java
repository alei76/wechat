/************************************************************************************
 * @File name   :      WxUserBelongRolesDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-28
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
 * 2014-4-28 14:02:13			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxUserBelongRoles;
import com.covisint.wechat.page.Page;

/**
 * 用户所属角色数据访问接口
 * 
 */
public interface WxUserBelongRolesDao {
	public int insert(WxUserBelongRoles wxUserBelongRoles);

	public int[] batchInsert(List<WxUserBelongRoles> wxUserBelongRolesList);

	public int update(WxUserBelongRoles wxUserBelongRoles);

	public WxUserBelongRoles get(java.lang.String accountId, java.lang.String roleId, java.lang.String userId);

	public Map<String, Object> findOne(java.lang.String accountId, java.lang.String roleId, java.lang.String userId);

	public List<Map<String, Object>> findList(Map<String, Object> paramMap);

	public List<WxUserBelongRoles> findDomain(Map<String, Object> paramMap);

	public Page<WxUserBelongRoles> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public int delete(String userId);

}