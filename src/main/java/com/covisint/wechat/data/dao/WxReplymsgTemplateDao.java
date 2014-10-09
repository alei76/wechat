/************************************************************************************
 * @File name   :      WxReplymsgTemplateDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-30
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
 * 2014-4-30 14:56:07			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.page.Page;

/**
 * 回复消息模板表数据访问接口
 * 
 */
public interface WxReplymsgTemplateDao {
	public int insert(WxReplymsgTemplate wxReplymsgTemplate);

	public int update(WxReplymsgTemplate wxReplymsgTemplate);

	public WxReplymsgTemplate get(java.lang.String templateId);

	public Map<String, Object> findOne(java.lang.String templateId);

	public List<Map<String, Object>> findList(Map<String, Object> paramMap);

	public List<WxReplymsgTemplate> findDomain(Map<String, Object> paramMap);

	public Page<WxReplymsgTemplate> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public WxReplymsgTemplate info(String templateId);

	public int checkExists(Map<String, Object> paramMap);

}