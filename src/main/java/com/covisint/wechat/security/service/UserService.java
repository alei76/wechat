/************************************************************************************
 * @File name   :      UserService.java
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
package com.covisint.wechat.security.service;

import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.exception.AuthException;
import com.covisint.wechat.security.model.Credentials;

/**
 * 用户权限Service
 */
public interface UserService {
	/**
	 * 用户登陆认证
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public WxSystemUser authUser(Credentials credentials) throws AuthException;
}
