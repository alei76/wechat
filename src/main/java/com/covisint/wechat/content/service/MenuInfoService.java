/************************************************************************************
 * @File name   :      MenuService.java
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
 * 2014-5-4 下午01:30:33			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.service;

import java.util.List;

import com.covisint.wechat.data.model.WxMenuInfo;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.exception.AjaxException;

/**
 *
 */
public interface MenuInfoService {

	/**
	 * 创建微信菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-4
	 */
	public boolean createMenu(WxMenuInfo wxMenuInfo, String accountId, String userId) throws AjaxException;

	/**
	 * 获取微信菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-4
	 */
	public List<ZtreeMenu> getWxMenu(String accountId);

	/**
	 * 
	 * 查询菜单详情
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-4
	 */
	public WxMenuInfo getMenuInfo(String menuId);

	/**
	 * 删除微信菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 */
	public boolean delMenu(String menuId);

	/**
	 * 更新微信菜单信息
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 */
	public boolean updateMenuInfo(WxMenuInfo wxMenuInfo, String accountId) throws AjaxException;

	/**
	 * 初始化一级菜单（启用二级菜单时）
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 */
	public boolean updateParentMenu(String menuId);

	/**
	 * 上移菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 */
	public boolean moveMenuUp(String menuId);

	/**
	 * 下移菜单
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 */
	public boolean moveMenuDown(String menuId);

	/**
	 * 同步菜单至微信服务器
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 */
	public boolean asyncMenu(String accountId) throws AjaxException;

}
