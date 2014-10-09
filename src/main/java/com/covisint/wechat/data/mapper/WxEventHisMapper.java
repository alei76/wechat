/************************************************************************************
 * @File name   :      WxEventHisMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-06-04
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
 * 2014-6-4 10:05:47			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxEventHisMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_EVENT_HIS (EVENT_HIS_ID, EVENT_TYPE, CREATE_TIME, OPEN_ID, ACCOUNT_ID, EVENT_KEY, MSG_CONTENT) VALUES (:eventHisId, :eventType, :createTime, :openId, :accountId, :eventKey, :msgContent)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_EVENT_HIS " + dynamicUpdate(paramMap) +" where EVENT_HIS_ID = :eventHisId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT EVENT_HIS_ID, EVENT_TYPE, CREATE_TIME, OPEN_ID, ACCOUNT_ID, EVENT_KEY, MSG_CONTENT FROM WX_EVENT_HIS" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT EVENT_HIS_ID, EVENT_TYPE, CREATE_TIME, OPEN_ID, ACCOUNT_ID, EVENT_KEY, MSG_CONTENT FROM WX_EVENT_HIS" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT EVENT_HIS_ID, EVENT_TYPE, CREATE_TIME, OPEN_ID, ACCOUNT_ID, EVENT_KEY, MSG_CONTENT FROM WX_EVENT_HIS" + getCondition(paramMap);
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
			if (paramMap.get("eventHisId") != null) {
				condition.append(" AND EVENT_HIS_ID = :eventHisId");
			}
			if (paramMap.get("eventType") != null) {
				condition.append(" AND EVENT_TYPE = :eventType");
			}
			if (paramMap.get("createTime") != null) {
				condition.append(" AND CREATE_TIME = :createTime");
			}
			if (paramMap.get("openId") != null) {
				condition.append(" AND OPEN_ID = :openId");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("eventKey") != null) {
				condition.append(" AND EVENT_KEY = :eventKey");
			}
			if (paramMap.get("msgContent") != null) {
				condition.append(" AND MSG_CONTENT = :msgContent");
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
			if (paramMap.get("eventHisId") != null) {
				dynamic.append(" EVENT_HIS_ID = :eventHisId,");
			}
			if (paramMap.get("eventType") != null) {
				dynamic.append(" EVENT_TYPE = :eventType,");
			}
			if (paramMap.get("createTime") != null) {
				dynamic.append(" CREATE_TIME = :createTime,");
			}
			if (paramMap.get("openId") != null) {
				dynamic.append(" OPEN_ID = :openId,");
			}
			if (paramMap.get("accountId") != null) {
				dynamic.append(" ACCOUNT_ID = :accountId,");
			}
			if (paramMap.get("eventKey") != null) {
				dynamic.append(" EVENT_KEY = :eventKey,");
			}
			if (paramMap.get("msgContent") != null) {
				dynamic.append(" MSG_CONTENT = :msgContent,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}
}
