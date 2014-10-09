/************************************************************************************
 * @File name   :      StatUserService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-4
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
 * 2014-6-4 下午03:55:16			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.stat.service;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface StatUserService {

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-4
	 * @return
	 */
	public List<Map<String, Object>> userSexSummary(String accoundId);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-4
	 * @param accoundId
	 * @return
	 */
	public List<Map<String, Object>> userLangSummary(String accoundId);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-13
	 * @param accoundId
	 * @return
	 */
	public Map<String, List<? extends Object>> trendsummary(String accoundId, String startDate, String endDate, Integer type);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-26
	 * @param accoundId
	 * @return
	 */
	public Map<String, Object> lastsub(String accoundId);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-26
	 * @param accoundId
	 * @return
	 */
	public Map<String, Object> lastunsub(String accoundId);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-26
	 * @param accoundId
	 * @return
	 */
	public Map<String, Object> lasttotal(String accoundId);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-27
	 * @param accoundId
	 * @return
	 */
	public Map<String, Object> lastpure(String accoundId);

}
