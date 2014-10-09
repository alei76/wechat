/************************************************************************************
 * @File name   :      WxEventHisDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-06-04
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
 * 2014-6-4 10:05:47			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxEventHis;
import com.covisint.wechat.page.Page;

/**
 * 消息事件记录表数据访问接口
 * 
 */
public interface WxEventHisDao {
	public int insert(WxEventHis wxEventHis);

	public int update(WxEventHis wxEventHis);

	public WxEventHis get(java.lang.String eventHisId);

	public List<WxEventHis> findDomain(Map<String, Object> paramMap);

	public Page<WxEventHis> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public WxEventHis getEventHis(Map<String, Object> paramMap);

}