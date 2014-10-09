/************************************************************************************
 * @File name   :      WebsiteHisService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-17
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
 * 2014-6-17 下午04:47:26			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.stat.service;

import java.util.Map;

import com.covisint.wechat.page.Page;

/**
 * 资源访问历史Service
 */
public interface WebsiteHisService {

	/**
	 * 保存访问记录
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-17
	 */
	public boolean saveVisitHis(String resourceId, String redirect, String openId, String accountId, String source);

	/**
	 * 分页查询资源访问记录
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-20
	 */
	public Page<Map<String, Object>> pageWebsiteHis(Map<String, Object> paramMap, Integer current, Integer pagesize);

	/**
	 * 按时间统计访问分页查询
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-24
	 */
	public Page<Map<String, Object>> pageDaySummary(Map<String, Object> paramMap, Integer current, Integer pagesize);

	/**
	 * 按时间统计访问——统计图
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-24
	 */
	public Map<String, Object> daysummary(Map<String, Object> paramMap);

	/**
	 * 按时段统计分页查询
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-24
	 */
	public Page<Map<String, Object>> pageTimeSummary(Map<String, Object> paramMap, Integer current, Integer pagesize);

	/**
	 * 按时段统计访问——统计图
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-24
	 */
	public Map<String, Object> timesummary(Map<String, Object> paramMap);

	/**
	 * 按访问区域分页查询
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-27
	 */
	public Page<Map<String, Object>> pageAreaSummary(Map<String, Object> paramMap, Integer current, Integer pagesize);

}
