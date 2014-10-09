/************************************************************************************
 * @File name   :      WxWebsiteHisDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-06-17
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
 * 2014-6-17 16:36:15			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxWebsiteHis;
import com.covisint.wechat.page.Page;

/**
 * 网址访问记录表数据访问接口
 * 
 */
public interface WxWebsiteHisDao {
	public int insert(WxWebsiteHis wxWebsiteHis);

	public int update(WxWebsiteHis wxWebsiteHis);

	public WxWebsiteHis get(java.lang.String visitHisId);

	public List<WxWebsiteHis> findDomain(Map<String, Object> paramMap);

	public Page<WxWebsiteHis> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public Page<Map<String, Object>> pageWebsiteHis(Map<String, Object> paramMap, Integer current, Integer pagesize);

	public Page<Map<String, Object>> pageDaySummary(Map<String, Object> paramMap, Integer current, Integer pagesize);

	public List<Map<String, Object>> daysummary(Map<String, Object> paramMap);

	public Page<Map<String, Object>> pageTimeSummary(Map<String, Object> paramMap, Integer current, Integer pagesize);

	public List<Map<String, Object>> timesummary(Map<String, Object> paramMap);

	public Page<Map<String, Object>> pageAreaSummary(Map<String, Object> paramMap, Integer current, Integer pagesize);

}