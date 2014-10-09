/************************************************************************************
 * @File name   :      MenuController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-4
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
 * 2014-5-4 上午10:28:53			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.content.service.LinkResourceService;
import com.covisint.wechat.content.service.MenuInfoService;
import com.covisint.wechat.content.service.TemplateService;
import com.covisint.wechat.data.model.WxLinkResource;
import com.covisint.wechat.data.model.WxMenuInfo;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 微信菜单管理控制器
 */
@Controller
@RequestMapping("/content/menu")
public class MenuInfoController {
	@Autowired
	private MenuInfoService menuInfoService;
	@Autowired
	private LinkResourceService linkResourceService;
	@Autowired
	private TemplateService templateService;

	/**
	 * 跳转至页面
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-4
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "content/menu/list";
	}

	/**
	 * 创建微信菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-4
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(WxMenuInfo menuInfo) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		WxSystemUser current = SessionUtils.getCurrentUser();
		String message = null;
		try {
			success = menuInfoService.createMenu(menuInfo, current.getCurrentAccount(), current.getUserId());
			message = success ? "保存成功" : "保存失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 树形显示微信菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-4
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/wxmenu", method = RequestMethod.POST)
	@ResponseBody
	public List<ZtreeMenu> wxmenu() {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		if (currentUser != null) {
			String accountId = currentUser.getCurrentAccount();
			return menuInfoService.getWxMenu(accountId);
		} else {
			return Collections.EMPTY_LIST;
		}
	}

	/**
	 * 查询微信菜单详情
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-4
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	@ResponseBody
	public WxMenuInfo info(@RequestParam("menuId") String menuId) {
		return menuInfoService.getMenuInfo(menuId);
	}

	/**
	 * 所有可选的外部资源
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 */
	@RequestMapping(value = "/linkresource", method = RequestMethod.POST)
	@ResponseBody
	public List<WxLinkResource> linkresource(@RequestParam Map<String, Object> paramMap) {
		return linkResourceService.listLinkResource(paramMap);
	}

	/**
	 * 所有可选的消息模板
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 */
	@RequestMapping(value = "/template", method = RequestMethod.POST)
	@ResponseBody
	public List<WxReplymsgTemplate> template(@RequestParam Map<String, Object> paramMap) {
		return templateService.listTemplate(paramMap);
	}

	/**
	 * 删除微信菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 */
	@RequestMapping(value = "/delMenu", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delMenu(@RequestParam("menuId") String menuId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = menuInfoService.delMenu(menuId);
		String message = success ? "删除成功" : "删除失败";
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 编辑菜单详情
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(WxMenuInfo wxMenuInfo) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		try {
			String accountId = currentUser.getCurrentAccount();
			success = menuInfoService.updateMenuInfo(wxMenuInfo, accountId);
			message = success ? "更新成功" : "更新失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 上移微信菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 */
	@RequestMapping(value = "/moveup", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> moveup(@RequestParam("menuId") String menuId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		success = menuInfoService.moveMenuUp(menuId);
		String message = success ? "更新成功" : "更新失败";
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 下移微信菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 */
	@RequestMapping(value = "/movedown", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> movedown(@RequestParam("menuId") String menuId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		success = menuInfoService.moveMenuDown(menuId);
		String message = success ? "更新成功" : "更新失败";
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 将菜单同步至腾讯微信官方
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 */
	@RequestMapping(value = "/async", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> async() {
		WxSystemUser current = SessionUtils.getCurrentUser();
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		try {
			success = menuInfoService.asyncMenu(current.getCurrentAccount());
			message = success ? "更新成功" : "更新失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}
}
