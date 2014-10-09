/************************************************************************************
 * @File name   :      WxGroupMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-15
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
 * 2014-5-15 15:56:59			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxGroupMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_GROUP (GROUP_ID, GROUP_NAME, ACCOUNT_ID, CREATE_BY, CREATE_TIME, TYPE, STATUS) VALUES (:groupId, :groupName, :accountId, :createBy, to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'), :type, :status)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_GROUP " + dynamicUpdate(paramMap) + " where GROUP_ID = :groupId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT GROUP_ID, GROUP_NAME, ACCOUNT_ID, CREATE_BY, CREATE_TIME, TYPE, STATUS FROM WX_GROUP" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT GROUP_ID, GROUP_NAME, ACCOUNT_ID, CREATE_BY, CREATE_TIME, TYPE, STATUS FROM WX_GROUP" + getCondition(paramMap) + " ORDER BY CREATE_TIME";
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT GROUP_ID, GROUP_NAME, ACCOUNT_ID, CREATE_BY, CREATE_TIME, TYPE, STATUS FROM WX_GROUP" + getCondition(paramMap);
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
			if (paramMap.get("groupId") != null) {
				condition.append(" AND GROUP_ID = :groupId");
			}
			if (paramMap.get("groupName") != null) {
				condition.append(" AND GROUP_NAME = :groupName");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("createBy") != null) {
				condition.append(" AND CREATE_BY = :createBy");
			}
			if (paramMap.get("createTime") != null) {
				condition.append(" AND CREATE_TIME = :createTime");
			}
			if (paramMap.get("type") != null) {
				condition.append(" AND TYPE = :type");
			}
			if (paramMap.get("status") != null) {
				condition.append(" AND STATUS = :status");
			}
			if (paramMap.get("except") != null) {
				condition.append(" AND GROUP_ID <> :except");
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
			if (paramMap.get("groupId") != null) {
				dynamic.append(" GROUP_ID = :groupId,");
			}
			if (paramMap.get("groupName") != null) {
				dynamic.append(" GROUP_NAME = :groupName,");
			}
			if (paramMap.get("accountId") != null) {
				dynamic.append(" ACCOUNT_ID = :accountId,");
			}
			if (paramMap.get("createBy") != null) {
				dynamic.append(" CREATE_BY = :createBy,");
			}
			if (paramMap.get("createTime") != null) {
				dynamic.append(" CREATE_TIME = :createTime,");
			}
			if (paramMap.get("type") != null) {
				dynamic.append(" TYPE = :type,");
			}
			if (paramMap.get("status") != null) {
				dynamic.append(" STATUS = :status,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-15
	 * @param paramMap
	 * @return
	 */
	public static String getListSql(Map<String, Object> paramMap) {
		String sql = "SELECT GROUP_ID AS MENU_ID, GROUP_NAME AS MENU_NAME, GROUP_NAME AS MENU_DESC, '0' AS PARENT_ID, TYPE AS MENU_URL FROM WX_GROUP " + getCondition(paramMap) + " ORDER BY CREATE_TIME";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-8-7
	 * @param paramMap
	 * @return
	 */
	public static String getCheckExistsSql(Map<String, Object> paramMap) {
		String sql = "SELECT COUNT(1) FROM WX_GROUP " + getCondition(paramMap);
		return sql;
	}
}
