/************************************************************************************
 * @File name   :      UserStatDao.java
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
 * 2014-6-4 下午03:58:41			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface UserStatDao {

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-4
	 * @return
	 */
	public List<Map<String, Object>> statSexSummary(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-4
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> statLangSummary(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-13
	 * @param paramMap
	 * @return
	 */
	public Map<String, Integer> statTrendSummary(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-13
	 * @param paramMap
	 * @return
	 */
	public Map<String, Integer> statPureSummary(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-13
	 * @param paramMap
	 * @return
	 */
	public Map<String, Integer> statFansSummary(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-26
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> lastSub(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-26
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> lastUnSub(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-27
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> lastPure(Map<String, Object> paramMap);

}
