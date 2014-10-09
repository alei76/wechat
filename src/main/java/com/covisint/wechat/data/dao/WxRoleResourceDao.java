/************************************************************************************
 * @File name   :      WxRoleResourceDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-25
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
 * 2014-4-25 16:08:31			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxRoleResource;
import com.covisint.wechat.page.Page;

/**
 * 角色资源数据访问接口
 * 
 */
public interface WxRoleResourceDao {
	public int insert(WxRoleResource wxRoleResource);

	public int[] batchInsert(List<WxRoleResource> wxRoleResources);

	public int update(WxRoleResource wxRoleResource);

	public WxRoleResource get(java.lang.String resourceId, java.lang.String roleId);

	public Map<String, Object> findOne(java.lang.String resourceId, java.lang.String roleId);

	public List<Map<String, Object>> findList(Map<String, Object> paramMap);

	public List<WxRoleResource> findDomain(Map<String, Object> paramMap);

	public Page<WxRoleResource> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public int delete(String roleId);

}