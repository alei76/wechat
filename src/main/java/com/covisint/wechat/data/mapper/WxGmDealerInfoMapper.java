/************************************************************************************
 * @File name   :      WxGmDealerInfoMapper.java
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
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxGmDealerInfoMapper {

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT DEALER_ID, CODE, NAME, PROVINCE, CITY, ADDRESS, TEL, PHONE, LONGITUDE, LATITUDE FROM WX_GM_DEALER_INFO" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT DEALER_ID, CODE, NAME, PROVINCE, CITY, ADDRESS, TEL, PHONE, LONGITUDE, LATITUDE FROM WX_GM_DEALER_INFO" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取where条件sql
	 * 
	 * @param paramMap
	 * @return
	 */
	private static String getCondition(Map<String, Object> paramMap) {
		if (paramMap == null || paramMap.size() == 0) {
			return "";
		} else {
			StringBuffer condition = new StringBuffer(" WHERE ");
			if (paramMap.get("dealerId") != null) {
				condition.append(" AND DEALER_ID = :dealerId");
			}
			if (paramMap.get("code") != null) {
				condition.append(" AND CODE = :code");
			}
			if (paramMap.get("name") != null) {
				condition.append(" AND NAME LIKE :name");
			}
			if (paramMap.get("province") != null) {
				condition.append(" AND PROVINCE = :province");
			}
			if (paramMap.get("city") != null) {
				condition.append(" AND CITY = :city");
			}
			if (paramMap.get("address") != null) {
				condition.append(" AND ADDRESS = :address");
			}
			if (paramMap.get("tel") != null) {
				condition.append(" AND TEL = :tel");
			}
			if (paramMap.get("phone") != null) {
				condition.append(" AND PHONE = :phone");
			}
			if (paramMap.get("longitude") != null) {
				condition.append(" AND LONGITUDE = :longitude");
			}
			if (paramMap.get("latitude") != null) {
				condition.append(" AND LATITUDE = :latitude");
			}
			int start = condition.indexOf("AND");
			if (start > 0) {
				condition = condition.replace(start, start + 3, "");
				return condition.toString();
			} else {
				return "";
			}
		}
	}
}
