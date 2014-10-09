/************************************************************************************
 * @File name   :      WxUserBelongRolesMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-28
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
 * 2014-4-28 14:02:14			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxUserBelongRolesMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_USER_BELONG_ROLES (ROLE_ID, USER_ID, ACCOUNT_ID) VALUES (:roleId, :userId, :accountId)";
		return sql;
	}

	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getBatchInsertSql() {
		String sql = "INSERT INTO WX_USER_BELONG_ROLES (ROLE_ID, USER_ID, ACCOUNT_ID) VALUES (:roleId, :userId, :accountId)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_USER_BELONG_ROLES set ROLE_ID = :roleId, USER_ID = :userId, ACCOUNT_ID = :accountId where ACCOUNT_ID = :accountId and ROLE_ID = :roleId and USER_ID = :userId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT ROLE_ID, USER_ID, ACCOUNT_ID FROM WX_USER_BELONG_ROLES" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT ROLE_ID, USER_ID, ACCOUNT_ID FROM WX_USER_BELONG_ROLES" + getCondition(paramMap) + " ORDER BY ACCOUNT_ID";
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT ROLE_ID, USER_ID, ACCOUNT_ID FROM WX_USER_BELONG_ROLES" + getCondition(paramMap);
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
			if (paramMap.get("roleId") != null) {
				condition.append(" AND ROLE_ID = :roleId");
			}
			if (paramMap.get("userId") != null) {
				condition.append(" AND USER_ID = :userId");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND ACCOUNT_ID = :accountId");
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
	 * @Author : 马恩伟
	 * @Date : 2014-4-28
	 * @return
	 */
	public static String getDeleteSql() {
		String sql = "DELETE FROM WX_USER_BELONG_ROLES WHERE USER_ID = :userId";
		return sql;
	}

}
