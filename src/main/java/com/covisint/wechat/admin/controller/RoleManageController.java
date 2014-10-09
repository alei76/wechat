/************************************************************************************
 * @File name   :      RoleManageController.java
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.admin.service.RoleService;
import com.covisint.wechat.data.model.WxRoleResource;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.data.model.WxUserRoles;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 角色管理控制器
 */
@Controller
@RequestMapping("/admin/role")
public class RoleManageController {
	@Autowired
	private RoleService roleService;

	/**
	 * 跳转至页面
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "admin/role/list";
	}

	/**
	 * 分页列表显示
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/grid", method = RequestMethod.POST)
	@ResponseBody
	public Page<WxUserRoles> grid(@RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap) {
		return roleService.pageRole(paramMap, current, pagesize);
	}

	/**
	 * 保存角色
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(WxUserRoles wxUserRoles) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		try {
			success = roleService.saveRole(wxUserRoles, currentUser.getUserId());
			message = success ? "保存成功" : "保存失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 保存角色所拥有的功能
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveResource", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveResource(@RequestParam("resourceArray") String resourceArray, @RequestParam("roleId") String roleId) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> resourceIds = JacksonJsonMapper.jsonToObject(resourceArray, List.class);
		boolean success = false;
		success = roleService.saveResource(roleId, resourceIds);
		String message = success ? "保存成功" : "保存失败";
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 查询角色下所有的功能
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/listResource", method = RequestMethod.POST)
	@ResponseBody
	public List<WxRoleResource> listResource(@RequestParam("roleId") String roleId) {
		return roleService.listResource(roleId);
	}

	/**
	 * 获取角色详情
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/viewinfo", method = RequestMethod.POST)
	@ResponseBody
	public WxUserRoles viewinfo(@RequestParam("roleId") String roleId) {
		return roleService.info(roleId);
	}

	/**
	 * 更改角色状态（启用/禁用）
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/changestatus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changestatus(@RequestParam("roleId") String roleId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		try {
			success = roleService.changeStatus(roleId);
			message = success ? "更改成功" : "更改失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}
}
