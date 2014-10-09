/************************************************************************************
 * @File name   :      SnsOauthController.java
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
 * 2014-8-6 下午02:13:50			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.api.service.CgiBinService;

/**
 * 腾讯微信官方平台OAuth2.0部分API支持
 */
@Controller
@RequestMapping("/api-wechat/sns")
public class SnsOauthController {
	@Autowired
	private CgiBinService cgiBinService;

	/**
	 * 获取用户基本信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<String> userinfo(@RequestParam Map<String, String> param) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf-8");
		String response = cgiBinService.getSnsUserInfo(param);
		return new HttpEntity<String>(response, headers);
	}

	/**
	 * 获取access_token
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/oauth2/access_token", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<String> token(@RequestParam Map<String, String> param) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf-8");
		String response = cgiBinService.getAccessToken(param);
		return new HttpEntity<String>(response, headers);
	}
}
