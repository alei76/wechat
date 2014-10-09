/************************************************************************************
 * @File name   :      UserManageController.java
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
package com.covisint.wechat.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.account.service.AccountService;
import com.covisint.wechat.admin.service.RoleService;
import com.covisint.wechat.admin.service.UserAdminService;
import com.covisint.wechat.admin.service.UserBelongRoleService;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.data.model.WxUserBelongRoles;
import com.covisint.wechat.data.model.WxUserRoles;
import com.covisint.wechat.data.model.WxWechatAccount;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.JacksonJsonMapper;

/**
 * 用户管理控制器
 */
@Controller
@RequestMapping("/admin/user")
public class UserManageController {
	@Autowired
	private UserAdminService userAdminService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserBelongRoleService userBelongRoleService;

	/**
	 * 跳转至页面
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<WxWechatAccount> accountList = accountService.listAccount(null);// 所有微信账户
		model.addAttribute("accountList", accountList);
		List<WxUserRoles> roleList = roleService.listRole(null);// 所有角色列表
		model.addAttribute("roleList", roleList);
		return "admin/user/list";
	}

	/**
	 * 分页列表显示
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/grid", method = RequestMethod.POST)
	@ResponseBody
	public Page<WxSystemUser> grid(@RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap) {
		return userAdminService.pageUser(paramMap, current, pagesize);
	}

	/**
	 * 列表显示会员可使用的微信号
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-28
	 */
	@RequestMapping(value = "/listrole", method = RequestMethod.POST)
	@ResponseBody
	public List<WxUserBelongRoles> listUserRoles(@RequestParam("userId") String userId) {
		return userBelongRoleService.listUserRoles(userId);
	}

	/**
	 * 保存用户的权限
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-28
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(@RequestParam("json") String json, @RequestParam("userId") String userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> userRoleList = JacksonJsonMapper.jsonToObject(json, List.class);
		boolean success = userBelongRoleService.save(userRoleList, userId);
		String message = success ? "保存成功" : "保存失败";
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 更改用户的状态(启用/禁用)
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/changestatus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changestatus(@RequestParam("userId") String userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		success = userAdminService.changeStatus(userId);
		String message = success ? "更改成功" : "更改失败";
		result.put("success", success);
		result.put("message", message);
		return result;
	}
}
