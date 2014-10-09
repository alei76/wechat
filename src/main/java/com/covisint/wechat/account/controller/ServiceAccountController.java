/************************************************************************************
 * @File name   :      ServiceAccountController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-25
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
 * 2014-4-25 上午08:55:24			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.account.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.account.service.AccountService;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.data.model.WxWechatAccount;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 微信账号控制器
 */
@Controller
@RequestMapping("/account/service")
public class ServiceAccountController {

	@Autowired
	private AccountService accountService;

	/**
	 * 跳转至页面
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "account/service/list";
	}

	/**
	 * 分页列表显示
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/grid", method = RequestMethod.POST)
	@ResponseBody
	public Page<WxWechatAccount> grid(@RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap) {
		return accountService.pageAccount(paramMap, current, pagesize);
	}

	/**
	 * 保存微信账号
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(WxWechatAccount wxWechatAccount) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		try {
			success = accountService.saveAccount(wxWechatAccount, currentUser.getUserId());
			message = success ? "保存成功" : "保存失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 状态更改（启用/禁用）
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/changestatus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changestatus(@RequestParam("accountId") String accountId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		try {
			success = accountService.changeStatus(accountId);
			message = success ? "更改成功" : "更改失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 删除微信号
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestParam("accountId") String accountId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		try {
			success = accountService.deleteAccount(accountId);
			message = success ? "删除成功" : "删除失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 查看账号详情
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/viewinfo", method = RequestMethod.POST)
	@ResponseBody
	public WxWechatAccount viewinfo(@RequestParam("accountId") String accountId) {
		return accountService.info(accountId);
	}
}
