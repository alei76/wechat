/************************************************************************************
 * @File name   :      WxRpFansCountDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-06-18
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
 * 2014-6-18 11:37:48			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxRpFansCount;
import com.covisint.wechat.page.Page;

/**
 * 粉丝量统计表(日)数据访问接口
 * 
 */
public interface WxRpFansCountDao {
	public int insert(WxRpFansCount wxRpFansCount);

	public int update(WxRpFansCount wxRpFansCount);

	public WxRpFansCount get(java.lang.String recordId);

	public List<WxRpFansCount> findDomain(Map<String, Object> paramMap);

	public Page<WxRpFansCount> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public long checkReport(Long recordDay);

	public int[] insertBatch(List<WxRpFansCount> insertData);

	public Map<String, Integer> statPureSummary(Map<String, Object> paramMap);

	public Map<String, Object> lastTotal(Map<String, Object> paramMap);

}