/************************************************************************************
 * @File name   :      MemberController.java
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
 * 2014-5-21 上午10:06:46			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.webservice.controller;

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

import com.covisint.wechat.webservice.service.IdmMemberService;
import com.covisint.wechat.webservice.util.ParamUrlDecoder;

/**
 * IDM会员jsonp WebService
 */
@Controller
@RequestMapping("/api-wechat/ws/member")
public class MemberController {
	@Autowired
	private IdmMemberService idmMemberService;

	/**
	 * idm会员绑定
	 * 
	 * @author 马恩伟
	 * @date 2014-9-1
	 */
	@RequestMapping(value = "/bindMember.jsonp", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public HttpEntity<String> bindMember(@RequestParam Map<String, String> param, @RequestParam("callback") String callback) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/html;charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> data = ParamUrlDecoder.decoderMap(param);// 前台的值需要urlencode，这边转码
		String fullname = data.get("username");// 用户名
		String password = data.get("password");// 密码
		String usermobile = data.get("usermobile");// 手机
		String openId = data.get("openId");// 微信openId
		boolean success = idmMemberService.bindIdmMember(fullname, password, usermobile, openId);
		result.put("success", success);
		return new HttpEntity<String>(ParamUrlDecoder.getJsonpReturn(result, callback), headers);
	}
}
