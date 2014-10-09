/************************************************************************************
 * @File name   :      MessageController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-7-24
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
 * 2014-7-24 下午03:59:14			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.api.service.CgiBinService;
import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.utils.PropertiesUtils;

/**
 * 腾讯微信官方平台发送消息部分API支持
 */
@Controller
@RequestMapping("/api-wechat/cgi-bin/message")
public class MessageController {
	@Autowired
	private CgiBinService cgiBinService;

	/**
	 * get 形式返回错误
	 * 
	 * @author 马恩伟
	 * @date 2014-9-9
	 */
	@RequestMapping(value = "/custom/send", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<String> e_send(@RequestParam Map<String, String> param) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf-8");
		Map<String, String> error = new HashMap<String, String>();
		error.put("errcode", "43002");
		error.put("errmsg", PropertiesUtils.getValue("43002"));
		String response = JacksonJsonMapper.objectToJson(error, false);
		return new HttpEntity<String>(response, headers);
	}

	/**
	 * 发送客服消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/custom/send", method = RequestMethod.POST, consumes = "text/xml;charset=utf-8")
	@ResponseBody
	public HttpEntity<String> send(@RequestBody String body, @RequestParam Map<String, String> param) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf-8");
		String response;
		try {
			response = cgiBinService.sendMessage(body, param);
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
