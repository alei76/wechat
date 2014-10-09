/************************************************************************************
 * @File name   :      GroupService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-15
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
 * 2014-5-15 下午03:58:15			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.member.service;

import java.util.List;

import com.covisint.wechat.data.model.WxGroup;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.exception.AjaxException;

/**
 *
 */
public interface GroupService {

	/**
	 * 创建系统分组-未分组
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-15
	 */
	public boolean createSystemGroup(String accountId, String userId);

	/**
	 * 
	 * 获取系统分组-未分组
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-15
	 */
	public WxGroup getSystemGroup(String accountId);

	/**
	 * 获取所有分组(树)
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-15
	 */
	public List<ZtreeMenu> listgroup(String accountId);

	/**
	 * 获取所有分组
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-15
	 */
	public List<WxGroup> findAll(String accountId);

	/**
	 * 保存分组
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-16
	 */
	public boolean saveGroup(WxGroup wxGroup, String accountId, String userId) throws AjaxException;

	/**
	 * 删除分组
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-16
	 */
	public boolean deleteGroup(String groupId, String accountId);

	/**
	 * 判断分组是否存在
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public boolean checkExists(String accountId, String name);
}
