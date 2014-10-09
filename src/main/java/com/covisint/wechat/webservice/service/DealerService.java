/************************************************************************************
 * @File name   :      DealerService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-7-14
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
 * 2014-7-14 下午03:25:57			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.webservice.service;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxGmDealerInfo;

/**
 * SGM供应商Service
 */
public interface DealerService {

	/**
	 * 根据名称，城市，经纬度获取经销商列表信息
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-7-16
	 */
	public List<Map<String, Object>> list(String name, String city, String lng, String lat);

	/**
	 * 根据经销商ID获取详细信息
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-7-16
	 */
	public WxGmDealerInfo info(String dealerId);

}
