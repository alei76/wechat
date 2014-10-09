/************************************************************************************
 * @File name   :      WxRoleResourceMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-25
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
 * 2014-4-25 16:08:31			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxRoleResourceMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_ROLE_RESOURCE (RESOURCE_ID, ROLE_ID) VALUES (:resourceId, :roleId)";
		return sql;
	}

	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getBatchInsertSql() {
		String sql = "INSERT INTO WX_ROLE_RESOURCE (RESOURCE_ID, ROLE_ID) VALUES (:resourceId, :roleId)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_ROLE_RESOURCE set RESOURCE_ID = :resourceId, ROLE_ID = :roleId where RESOURCE_ID = :resourceId and ROLE_ID = :roleId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT RESOURCE_ID, ROLE_ID FROM WX_ROLE_RESOURCE" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT RESOURCE_ID, ROLE_ID FROM WX_ROLE_RESOURCE" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT RESOURCE_ID, ROLE_ID FROM WX_ROLE_RESOURCE" + getCondition(paramMap);
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
			if (paramMap.get("resourceId") != null) {
				condition.append(" AND RESOURCE_ID = :resourceId");
			}
			if (paramMap.get("roleId") != null) {
				condition.append(" AND ROLE_ID = :roleId");
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

	public static String getDeleteSql() {
		String sql = "DELETE FROM WX_ROLE_RESOURCE WHERE ROLE_ID = :roleId";
		return sql;
	}
}
