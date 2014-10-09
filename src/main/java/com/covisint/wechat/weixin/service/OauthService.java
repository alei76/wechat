/************************************************************************************
 * @File name   :      OauthService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-8-6
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
 * 2014-8-6 下午02:16:52			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.weixin.service;

import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.PropertiesUtils;

/**
 *
 */
public interface OauthService {
	public static final String OAUTH_ACCESS_TOKEN_URL = PropertiesUtils.getValue("weixin.api_address") + "/sns/oauth2/access_token?grant_type=authorization_code";
	public static final String OAUTH_USERINFO_URL = PropertiesUtils.getValue("weixin.api_address") + "/sns/userinfo?lang=zh_CN";

	/**
	 * 获取OAuth2.0的access_token
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getOauthToken(String code, String accountId) throws WeChatException;

	/**
	 * 获取OAuth2.0的access_token
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getOauthToken(String code, String appId, String appSecert) throws WeChatException;

	/**
	 * 获取OAuth2.0的获取粉丝基本信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getOauthUserInfo(String token, String openId) throws WeChatException;
}
