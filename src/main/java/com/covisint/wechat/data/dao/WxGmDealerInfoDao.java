/************************************************************************************
 * @File name   :      WxGmDealerInfoDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-07-14
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
 * 2014-7-14 16:07:42			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxGmDealerInfo;

/**
 * 通用经销商信息数据访问接口
 * 
 */
public interface WxGmDealerInfoDao {
	public WxGmDealerInfo get(java.lang.String dealerId);

	public List<WxGmDealerInfo> findDomain(Map<String, Object> paramMap);

}