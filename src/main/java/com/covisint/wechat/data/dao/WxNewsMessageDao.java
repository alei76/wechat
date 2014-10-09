/************************************************************************************
 * @File name   :      WxNewsMessageDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-07-07
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
 * 2014-7-7 17:34:20			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxNewsMessage;
import com.covisint.wechat.page.Page;

/**
 * 图文消息表数据访问接口
 * 
 */
public interface WxNewsMessageDao {
	public int insert(WxNewsMessage wxNewsMessage);

	public int update(WxNewsMessage wxNewsMessage);

	public WxNewsMessage get(java.lang.String newsMessageId);

	public List<WxNewsMessage> findDomain(Map<String, Object> paramMap);

	public Page<WxNewsMessage> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public int[] batchInsert(List<WxNewsMessage> newsMessageItems);

	public int[] batchUpdate(List<WxNewsMessage> update);

	public int[] batchDelete(String[] delItemsArray);

}