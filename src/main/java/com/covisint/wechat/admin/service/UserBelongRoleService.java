/************************************************************************************
 * @File name   :      UserBelongRoleService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-28
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
 * 2014-4-28 下午02:07:31			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.admin.service;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxUserBelongRoles;

/**
 *
 */
public interface UserBelongRoleService {

	/**
	 * 列表显示会员可使用的微信号
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-28
	 */
	public List<WxUserBelongRoles> listUserRoles(String userId);

	/**
	 * 保存用户的权限
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-28
	 */
	public boolean save(List<Map<String, Object>> userRoleList, String userId);
}
