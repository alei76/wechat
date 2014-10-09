/************************************************************************************
 * @File name   :      UserAdminService.java
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

import java.util.Map;

import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.page.Page;

/**
 * 用户管理Service
 */
public interface UserAdminService {
	public static final String DEFAULT_PASSWORD = "123456";

	/**
	 * 分页查询
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public Page<WxSystemUser> pageUser(Map<String, Object> paramMap, Integer current, Integer pagesize);

	/**
	 * 更改会员状态
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-14
	 */
	public boolean changeStatus(String userId);

	/**
	 * 根据userName获取用户信息
	 */
	public WxSystemUser getSystemUser(String userName);

	/**
	 * 根据userName获取用户信息
	 */
	public WxSystemUser getUserAdmin(String userName);

	/**
	 * 根据userName创建用户
	 */
	public WxSystemUser saveUserAdmin(String userName);
}
