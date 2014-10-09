/************************************************************************************
 * @File name   :      TemplateService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-30
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
 * 2014-4-30 下午03:01:22			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.service;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxNewsMessage;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;

/**
 *
 */
public interface TemplateService {

	/**
	 * 分页查询消息模板
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-30
	 */
	public Page<WxReplymsgTemplate> pageTemplate(Map<String, Object> paramMap, Integer current, Integer pagesize);

	/**
	 * 列表查询消息模板
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 */
	public List<WxReplymsgTemplate> listTemplate(Map<String, Object> paramMap);

	/**
	 * 保存动态消息模板
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 */
	public boolean saveDynamicMsg(WxReplymsgTemplate wxReplymsgTemplate, String userId, String accountId) throws AjaxException;

	/**
	 * 创建小i机器人模板
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-14
	 */
	public boolean createRobotTemplate(String accountId, String userId);

	/**
	 * 保存静态消息模板
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-19
	 */
	public boolean saveStaticTemplate(WxReplymsgTemplate wxReplymsgTemplate, String userId, String accountId) throws AjaxException;

	/**
	 * 查询模板信息
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-23
	 */
	public WxReplymsgTemplate infoTemplate(String templateId);

	/**
	 * 保存图文消息
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-7-7
	 */
	public boolean saveNewsMessage(WxReplymsgTemplate template, List<Map<String, Object>> items, String userId, String accountId) throws AjaxException;

	/**
	 * 查询所有图文消息项
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public List<WxNewsMessage> listNewsMsgItems(String templateId);

	/**
	 * 修改图文消息
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-7-9
	 */
	public boolean editNewsMessage(WxReplymsgTemplate template, List<Map<String, Object>> items, String[] delItemsArray, String userId, String accountId);

	/**
	 * 删除模板
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public boolean changestatus(String templateId) throws AjaxException;
}
