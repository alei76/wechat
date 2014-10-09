/************************************************************************************
 * @File name   :      IdmRemotingService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-27
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
 * 2014-5-27 下午12:59:34			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.webservice.remoting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.covisint.wechat.utils.AccessTokenData;
import com.covisint.wechat.utils.HttpClientUtil;
import com.covisint.wechat.utils.JacksonJsonMapper;

/**
 * IDM接口服务
 */
@Component
public class IdmRemotingService {
	private static final Logger logger = LoggerFactory.getLogger(IdmRemotingService.class);
	@Value("${idm.webservice}")
	private String idmservice;

	@Value("${idm.username}")
	private String _username;
	@Value("${idm.password}")
	private String _password;
	@Value("${idm.client_id}")
	private String _clientId;
	@Value("${idm.client_secret}")
	private String _clientSecret;

	/**
	 * 获取AccessToken
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-28
	 * @param username
	 * @param password
	 * @param clientId
	 * @param clientSecret
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getOauthToken(String username, String password, String clientId, String clientSecret) {
		boolean validate = AccessTokenData.getInstance().validateToken(username);
		if (!validate) {
			String url = idmservice + "/oauth2.token";
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("username", username));
			nvps.add(new BasicNameValuePair("password", password));
			nvps.add(new BasicNameValuePair("client_id", clientId));
			nvps.add(new BasicNameValuePair("client_secret", clientSecret));
			nvps.add(new BasicNameValuePair("grant_type", "password"));
			try {
				String response = HttpClientUtil.postForString(url, nvps, null);
				Map<String, String> resultMap = JacksonJsonMapper.jsonToObject(response, Map.class);
				String token = resultMap.get("access_token");
				AccessTokenData.getInstance().putToken(username, token);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return AccessTokenData.getInstance().getToken(username);
	}

	/**
	 * 绑定激活会员
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-30
	 * @param userName
	 * @param openId
	 * @param brand
	 * @param password
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String bindMember(String usermobile, String openId, String brand, String password) {
		String accessToken = this.getOauthToken(_username, _password, _clientId, _clientSecret);// 获取idm的access_token
		String url = idmservice + "/WeChat.Active.Member";// 接口地址
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();// 接口需要的body参数
		nvps.add(new BasicNameValuePair("idpUserID", _username));
		nvps.add(new BasicNameValuePair("userName", usermobile));
		nvps.add(new BasicNameValuePair("openId", openId));
		nvps.add(new BasicNameValuePair("brand", brand));
		nvps.add(new BasicNameValuePair("password", password));
		nvps.add(new BasicNameValuePair("nonce", this.getRandomNonce()));
		nvps.add(new BasicNameValuePair("timestamp", Calendar.getInstance().getTimeInMillis() + ""));
		Map<String, String> header = new HashMap<String, String>();// 接口需要的header参数
		header.put("client_id", _clientId);
		header.put("access_token", accessToken);
		try {
			String response = HttpClientUtil.postForString(url, nvps, header);// idm接口返回值
			logger.info("IDM Return : " + response);
			Map<String, Object> resultMap = JacksonJsonMapper.jsonToObject(response, Map.class);// 解析返回值，判断是否绑定激活成功
			Map<String, Object> data = (Map<String, Object>) resultMap.get("data");
			String statusCode = data.get("statusCode").toString();
			if (statusCode.equals("200")) {
				Map<String, Object> user = (Map<String, Object>) data.get("user");
				if (user != null) {
					String member_info_str = (String) user.get("member_info");
					List<Map<String, Object>> member_info_list = JacksonJsonMapper.jsonToObject(member_info_str, List.class);
					if (member_info_list != null) {
						String memberId = null;
						for (Map<String, Object> member : member_info_list) {
							String _openId = (String) member.get("OPEN_ID");
							String _brand = (String) member.get("BRAND");
							String _status = (String) member.get("STATUS_CD");
							if (openId.equals(_openId) && brand.equals(_brand) && _status.equals("已激活")) {
								memberId = (String) member.get("MEMBER_ID");
								break;
							}
						}
						return memberId;
					}
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		return null;

	}

	/**
	 * 获取64位随机值
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-30
	 */
	private String getRandomNonce() {
		Random ran = new Random();
		StringBuffer strb = new StringBuffer();
		int num = 0;
		for (int i = 0; i < 8; i++) {
			while (true) {
				num = ran.nextInt(99999999);
				if (num > 10000000) {
					strb.append(num);
					break;
				}
			}
		}
		return strb.toString();
	}
}
