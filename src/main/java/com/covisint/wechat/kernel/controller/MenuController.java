/************************************************************************************
 * @File name   :      MenuController.java
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
 * 2014-4-25 上午10:42:13			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.kernel.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.kernel.service.MenuService;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 菜单控制器
 */
@Controller
@RequestMapping("/kernel/menu")
public class MenuController {
	@Autowired
	@Qualifier("userMenu")
	private MenuService menuService;

	/**
	 * 获取用户菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	@ResponseBody
	public List<ZtreeMenu> menu() {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		if (currentUser != null) {
			String accountId = currentUser.getCurrentAccount();
			String userId = currentUser.getUserId();
			String userType = currentUser.getUserType();
			return menuService.getUserMenu(userId, userType, accountId);
		} else {
			return Collections.EMPTY_LIST;
		}
	}

	/**
	 * 获取所有菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/resource", method = RequestMethod.POST)
	@ResponseBody
	public List<ZtreeMenu> resource() {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		if (currentUser != null) {
			String accountId = currentUser.getCurrentAccount();
			String userId = currentUser.getUserId();
			String userType = currentUser.getUserType();
			return menuService.getResource(userId, userType, accountId);
		} else {
			return Collections.EMPTY_LIST;
		}
	}
}
