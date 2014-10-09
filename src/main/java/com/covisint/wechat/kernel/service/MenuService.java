/************************************************************************************
 * @File name   :      MenuService.java
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
package com.covisint.wechat.kernel.service;

import java.util.List;

import com.covisint.wechat.data.model.ZtreeMenu;

/**
 * 用户菜单Service
 */
public interface MenuService {
	/**
	 * 获取用户菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public List<ZtreeMenu> getUserMenu(String userId, String userType, String accountId);

	/**
	 * 获取所有菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public List<ZtreeMenu> getResource(String userId, String userType, String accountId);

}
