/************************************************************************************
 * @File name   :      RoleService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-25
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
 * 2014-4-25 上午08:55:24			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.admin.service;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxRoleResource;
import com.covisint.wechat.data.model.WxUserRoles;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;

/**
 * 角色管理Service
 */
public interface RoleService {
	/**
	 * 分页查询
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public Page<WxUserRoles> pageRole(Map<String, Object> paramMap, Integer current, Integer pagesize);

	/**
	 * 创建角色
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 * @return
	 */
	public boolean saveRole(WxUserRoles wxUserRoles, String userId) throws AjaxException;

	/**
	 * 保存角色所拥有的功能
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public boolean saveResource(String roleId, List<String> resourceIds);

	/**
	 * 查询角色下所有的功能
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public List<WxRoleResource> listResource(String roleId);

	/**
	 * 查询所有角色
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-28
	 */
	public List<WxUserRoles> listRole(Map<String, Object> paramMap);

	/**
	 * 查询详情
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-14
	 */
	public WxUserRoles info(String roleId);

	/**
	 * 更改角色状态
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-14
	 */
	public boolean changeStatus(String roleId) throws AjaxException;

}
