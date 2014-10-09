/************************************************************************************
 * @File name   :      IDMMemberService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-21
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
 * 2014-5-21 下午03:30:08			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.webservice.service;

/**
 * IDM会员Service
 */
public interface IdmMemberService {

	/**
	 * IDM绑定、激活会员
	 */
	public boolean bindIdmMember(String fullname, String password, String usermobile, String openId);

	/**
	 * 验证粉丝是否绑定
	 */
	public boolean validateMember(String openId);

}
