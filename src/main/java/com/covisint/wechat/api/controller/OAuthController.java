/************************************************************************************
 * @File name   :      BasicController.java
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
 * 2014-8-6 下午01:51:59			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.covisint.wechat.weixin.service.BasicWeChatService;

/**
 * OAuth2.0路径跳转
 */
@Controller
@RequestMapping("/api-wechat/connect/oauth2")
public class OAuthController {
	@Autowired
	private BasicWeChatService basicWeChatService;

	/**
	 * 获取access_token
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/authorize", method = RequestMethod.GET)
	public String token(@RequestParam Map<String, String> param, Model model) {
		String path = null;
		String accountId = param.get("accountId");
		String redirect_uri = param.get("redirect_uri");
		String scope = param.get("scope");
		String state = param.get("state");

		// TODO:处理 redirect_uri + state

		if ("snsapi_base".equals(scope)) {
			path = basicWeChatService.getOauthBaseUrl(path, accountId);
		} else if ("snsapi_userinfo".equals(scope)) {
			path = basicWeChatService.getOauthInfoUrl(path, accountId);
		}
		model.addAttribute("path", path);
		return "redirect_view";
	}
}
