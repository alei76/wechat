/************************************************************************************
 * @File name   :      WxGroupDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-15
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
 * 2014-5-15 15:56:59			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxGroup;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.page.Page;

/**
 * 微信分组表数据访问接口
 * 
 */
public interface WxGroupDao {
	public int insert(WxGroup wxGroup);

	public int update(WxGroup wxGroup);

	public WxGroup get(java.lang.String groupId);

	public List<WxGroup> findDomain(Map<String, Object> paramMap);

	public Page<WxGroup> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public WxGroup getSystemGroup(Map<String, Object> paramMap);

	public List<ZtreeMenu> listGroup(Map<String, Object> paramMap);

	public int checkExists(Map<String, Object> paramMap);

}