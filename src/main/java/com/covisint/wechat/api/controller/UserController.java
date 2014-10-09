/************************************************************************************
 * @File name   :      UserController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-7-25
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
 * 2014-7-25 上午10:10:32			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.api.controller;

import java.util.HashMap;
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
import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.utils.PropertiesUtils;

/**
 * 腾讯微信官方平台用户部分API支持
 */
@Controller
@RequestMapping("/api-wechat/cgi-bin/user")
public class UserController {
	@Autowired
	private CgiBinService cgiBinService;

	/**
	 * 获取用户信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<String> info(@RequestParam Map<String, String> param) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf-8");
		String response;
		try {
			response = cgiBinService.getUserInfo(param);
		} catch (WeChatException e) {
			String errcode = e.getMessage();
			Map<String, String> error = new HashMap<String, String>();
			error.put("errcode", errcode);
			error.put("errmsg", PropertiesUtils.getValue(errcode));
			response = JacksonJsonMapper.objectToJson(error, false);
		}
		return new HttpEntity<String>(response, headers);
	}
}
