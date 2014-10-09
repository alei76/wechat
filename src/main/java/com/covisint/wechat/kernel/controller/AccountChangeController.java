/************************************************************************************
 * @File name   :      AccountChangeController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-7-21
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
 * 2014-7-21 下午02:21:04			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.kernel.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 微信号控制器
 */
@Controller
@RequestMapping("/kernel/account")
public class AccountChangeController {
	/**
	 * 切换当前登录微信号
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 */
	@ResponseBody
	@RequestMapping(value = "/changeaccount", method = RequestMethod.POST)
	public Map<String, Object> changeaccount(@RequestParam("accountId") String accountId) {
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser current = SessionUtils.getCurrentUser();
		current.setCurrentAccount(accountId);
		result.put("success", true);
		return result;
	}
}
