/************************************************************************************
 * @File name   :      MenuSupport.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-8-5
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
 * 2014-8-5 下午05:39:41			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.support;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.kernel.service.MenuService;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 菜单标签处理类
 */
@Component
public class MenuSupport {
	@Autowired
	private MenuService menuService;
	private static MenuSupport support;

	@PostConstruct
	public void init() {
		support = this;
		support.menuService = menuService;
	}

	/**
	 * 获取菜单
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@SuppressWarnings("unchecked")
	public static List<ZtreeMenu> getMenu() {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		if (currentUser != null) {
			String accountId = currentUser.getCurrentAccount();
			String userId = currentUser.getUserId();
			String userType = currentUser.getUserType();
			return support.menuService.getUserMenu(userId, userType, accountId);
		} else {
			return Collections.EMPTY_LIST;
		}
	}
}
