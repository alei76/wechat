/************************************************************************************
 * @File name   :      CgiBinServiceImpl.java
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
 * 2014-8-6 下午01:56:14			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.api.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.covisint.wechat.api.service.CgiBinService;
import com.covisint.wechat.utils.AccessTokenData;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.weixin.service.BasicWeChatService;
import com.covisint.wechat.weixin.service.MemberService;
import com.covisint.wechat.weixin.service.OauthService;
import com.covisint.wechat.weixin.service.SendMessageService;

/**
 *
 */
@Service
public class CgiBinServiceImpl implements CgiBinService {
	@Autowired
	private BasicWeChatService basicWeChatService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private OauthService oauthService;
	@Autowired
	private SendMessageService sendMessageService;

	@SuppressWarnings("unchecked")
	@Override
	public String getToken(Map<String, String> param) {
		String result = null;
		String appid = param.get("appid");
		String secret = param.get("secret");
		String accountId = param.get("accountId");
		if (StringUtils.isNotEmpty(accountId)) {// 如果存在accountId，以accountId为准
			result = basicWeChatService.getAccessToken(accountId);
		} else {
			result = basicWeChatService.getAccessToken(appid, secret);
		}
		if (StringUtils.isNotEmpty(result)) {
			if (StringUtils.isNotEmpty(accountId)) {// 如果accountId不为空
				Map<String, Object> map = JacksonJsonMapper.jsonToObject(result, Map.class);
				if (null != map.get("access_token")) {
					String token = map.get("access_token").toString();
					Long expiresIn = Long.valueOf(map.get("expires_in").toString());
					AccessTokenData.getInstance().putToken(accountId, token, expiresIn);// 平台记录该accountId目前可用的access_token
				}
			}
		}
		return result;
	}

	@Override
	public String getUserInfo(Map<String, String> param) {
		String accountId = param.get("accountId");
		String accessToken = param.get("access_token");
		String openid = param.get("openid");
		if (StringUtils.isNotEmpty(accountId)) {
			return memberService.getInfo(openid, accountId);
		} else {
			return memberService.getUserInfo(openid, accessToken);
		}
	}

	@Override
	public String getSnsUserInfo(Map<String, String> param) {
		String accessToken = param.get("access_token");
		String openid = param.get("openid");
		return oauthService.getOauthUserInfo(accessToken, openid);
	}

	@Override
	public String getAccessToken(Map<String, String> param) {
		String appid = param.get("appid");
		String secret = param.get("secret");
		String accountId = param.get("accountId");
		String code = param.get("code");
		if (StringUtils.isNotEmpty(accountId)) {
			return oauthService.getOauthToken(code, accountId);
		} else {
			return oauthService.getOauthToken(code, appid, secret);
		}
	}

	@Override
	public String uploadMedia(MultipartFile file, Map<String, String> param) {
		String type = param.get("type");
		String accountId = param.get("accountId");
		String accessToken = param.get("access_token");
		if (StringUtils.isNotEmpty(accountId)) {
			return basicWeChatService.uploadMediaByAccount(type, file, accountId);
		} else {
			return basicWeChatService.uploadMediaByToken(type, file, accessToken);
		}

	}

	@Override
	public void downloadMedia(Map<String, String> param, HttpServletResponse response) {
		String accountId = param.get("accountId");
		String accessToken = param.get("access_token");
		String mediaId = param.get("media_id");
		if (StringUtils.isNotEmpty(accountId)) {
			basicWeChatService.downloadMediaByAccount(mediaId, accountId, response);
		} else {
			basicWeChatService.downloadMediaByToken(mediaId, accessToken, response);
		}
	}

	@Override
	public String sendMessage(String body, Map<String, String> param) {
		String accountId = param.get("accountId");
		String accessToken = param.get("access_token");
		if (StringUtils.isNotEmpty(accountId)) {
			return sendMessageService.sendByAccount(body, accountId);
		} else {
			return sendMessageService.sendByToken(body, accessToken);
		}
	}
}
