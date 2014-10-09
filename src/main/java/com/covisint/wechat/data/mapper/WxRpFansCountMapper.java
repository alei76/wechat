/************************************************************************************
 * @File name   :      WxRpFansCountMapper.java
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
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxRpFansCountMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_RP_FANS_COUNT (RECORD_ID, RECORD_DAY, ACCOUNT_ID, FANS_COUNT) VALUES (:recordId, :recordDay, :accountId, :fansCount)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_RP_FANS_COUNT " + dynamicUpdate(paramMap) +" where RECORD_ID = :recordId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT RECORD_ID, RECORD_DAY, ACCOUNT_ID, FANS_COUNT FROM WX_RP_FANS_COUNT" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT RECORD_ID, RECORD_DAY, ACCOUNT_ID, FANS_COUNT FROM WX_RP_FANS_COUNT" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT RECORD_ID, RECORD_DAY, ACCOUNT_ID, FANS_COUNT FROM WX_RP_FANS_COUNT" + getCondition(paramMap);
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
			if (paramMap.get("recordId") != null) {
				condition.append(" AND RECORD_ID = :recordId");
			}
			if (paramMap.get("recordDay") != null) {
				condition.append(" AND RECORD_DAY = :recordDay");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("fansCount") != null) {
				condition.append(" AND FANS_COUNT = :fansCount");
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
	
	/**
	 * 获取动态update
	 * 
	 * @param paramMap
	 * @return
	 */
	private static String dynamicUpdate(Map<String, Object> paramMap) {
		if (paramMap == null || paramMap.size() == 0) {
			return "";
		} else {
			StringBuffer dynamic = new StringBuffer(" SET ");
			if (paramMap.get("recordId") != null) {
				dynamic.append(" RECORD_ID = :recordId,");
			}
			if (paramMap.get("recordDay") != null) {
				dynamic.append(" RECORD_DAY = :recordDay,");
			}
			if (paramMap.get("accountId") != null) {
				dynamic.append(" ACCOUNT_ID = :accountId,");
			}
			if (paramMap.get("fansCount") != null) {
				dynamic.append(" FANS_COUNT = :fansCount,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}
}
