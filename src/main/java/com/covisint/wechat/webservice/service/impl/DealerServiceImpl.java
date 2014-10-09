/************************************************************************************
 * @File name   :      DealerServiceImpl.java
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
 * 2014-7-14 下午03:26:06			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.webservice.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.WxGmDealerInfoDao;
import com.covisint.wechat.data.model.WxGmDealerInfo;
import com.covisint.wechat.webservice.service.DealerService;

/**
 *
 */
@Service
public class DealerServiceImpl implements DealerService, Comparator<Map<String, Object>> {
	private final static double EARTH_RADIUS = 6378.137;// 地球半径

	@Autowired
	private WxGmDealerInfoDao wxGmDealerInfoDao;

	@Override
	public List<Map<String, Object>> list(String name, String city, String lng, String lat) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(name)) {
			paramMap.put("name", "%" + name + "%");
		}
		if (!StringUtils.isEmpty(city)) {
			paramMap.put("city", city);
		}
		List<WxGmDealerInfo> data = wxGmDealerInfoDao.findDomain(paramMap);
		double lat1 = Double.valueOf(lat);
		double lng1 = Double.valueOf(lng);
		List<Map<String, Object>> result = this.getDealerList(data, lng1, lat1);
		Collections.sort(result, this);
		return result;
	}

	@Override
	public WxGmDealerInfo info(String dealerId) {
		return wxGmDealerInfoDao.get(dealerId);
	}

	/**
	 * 根据距离排序的实现方法
	 */
	@Override
	public int compare(Map<String, Object> o1, Map<String, Object> o2) {
		double dis1 = Double.valueOf(o1.get("distance").toString());
		double dis2 = Double.valueOf(o2.get("distance").toString());
		if (dis1 > dis2) {
			return 1;
		} else if (dis1 == dis2) {
			return 0;
		} else {
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> getDealerList(List<WxGmDealerInfo> data, double lng, double lat) {
		if (data == null) {
			return Collections.EMPTY_LIST;
		} else {
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			DecimalFormat df = new DecimalFormat("#.#");
			for (WxGmDealerInfo wgdi : data) {
				Map<String, Object> info = new HashMap<String, Object>();
				info.put("id", wgdi.getDealerId());// id
				info.put("name", wgdi.getName());// 名称
				double lat2 = Double.valueOf(wgdi.getLatitude());
				double lng2 = Double.valueOf(wgdi.getLongitude());
				double distance = calcDistance(lat, lng, lat2, lng2);// 根据经纬度计算相距距离
				info.put("distance", df.format(distance));// 距离
				info.put("address", wgdi.getAddress());// 地址
				info.put("tel", wgdi.getTel());// 电话
				result.add(info);
			}
			return result;
		}
	}

	// 计算地球两点直线距离
	private static double calcDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		return s;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

}
