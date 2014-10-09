/************************************************************************************
 * @File name   :      OauthServiceImpl.java
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
 * 2014-8-6 下午02:17:25			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.weixin.service.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.weixin.service.BasicWeChatService;
import com.covisint.wechat.weixin.service.OauthService;
import com.covisint.wechat.weixin.util.AccessTokenUtils;
import com.covisint.wechat.weixin.util.HttpUtil;

/**
 *
 */
@Service
public class OauthServiceImpl implements OauthService {

	@Override
	public String getOauthToken(String code, String accountId) throws WeChatException {
		Map<String, String> accountInfo = AccessTokenUtils.getAccountInfo(accountId);
		String appid = accountInfo.get(BasicWeChatService.KEY_APPID);
		String secret = accountInfo.get(BasicWeChatService.KEY_APPSECRET);
		return this.getOauthToken(code, appid, secret);
	}

	@Override
	public String getOauthToken(String code, String appId, String appSecert) throws WeChatException {
		try {
			return HttpUtil.get(OAUTH_ACCESS_TOKEN_URL.concat("&appid=") + appId + "&secret=" + appSecert + "&code=" + code);
		} catch (ClientProtocolException e) {
			throw new WeChatException(e);
		} catch (IOException e) {
			throw new WeChatException(e);
		}
	}

	@Override
	public String getOauthUserInfo(String token, String openId) throws WeChatException {
		try {
			return HttpUtil.get(OAUTH_USERINFO_URL.concat("&access_token=") + token + "&openid=" + openId);
		} catch (ClientProtocolException e) {
			throw new WeChatException(e);
		} catch (IOException e) {
			throw new WeChatException(e);
		}
	}

}
