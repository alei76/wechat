/************************************************************************************
 * @File name   :      WxMediaAttaMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-08-13
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
 * 2014-8-13 14:02:36			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxMediaAttaMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_MEDIA_ATTA (ATTA_ID, NAME, TYPE, ACCOUNT_ID, CREATE_BY, CREATE_TIME, STATUS) VALUES (:attaId, :name, :type, :accountId, :createBy, to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'), :status)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_MEDIA_ATTA " + dynamicUpdate(paramMap) + " where ATTA_ID = :attaId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT ATTA_ID, NAME, TYPE, ACCOUNT_ID, CREATE_BY, CREATE_TIME, STATUS FROM WX_MEDIA_ATTA" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT ATTA_ID, NAME, TYPE, ACCOUNT_ID, CREATE_BY, CREATE_TIME, STATUS FROM WX_MEDIA_ATTA" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT ATTA_ID, NAME, TYPE, ACCOUNT_ID, CREATE_BY, CREATE_TIME, STATUS, WDD1.ITEM_DESC AS STATUS_CN, WDD2.ITEM_DESC AS TYPE_CN FROM WX_MEDIA_ATTA INNER JOIN WX_DATA_DICTIONARY WDD1 ON WDD1.ITEM_CODE = STATUS AND WDD1.CATALOG_CODE = :mediaStatus INNER JOIN WX_DATA_DICTIONARY WDD2 ON WDD2.ITEM_CODE = TYPE AND WDD2.CATALOG_CODE = :mediaType " + getCondition(paramMap)
				+ " ORDER BY CREATE_TIME DESC";
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
			if (paramMap.get("attaId") != null) {
				condition.append(" AND ATTA_ID = :attaId");
			}
			if (paramMap.get("name") != null) {
				condition.append(" AND NAME = :name");
			}
			if (paramMap.get("likeName") != null) {
				condition.append(" AND NAME LIKE :likeName");
			}
			if (paramMap.get("type") != null) {
				condition.append(" AND TYPE = :type");
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
			if (paramMap.get("status") != null) {
				condition.append(" AND STATUS = :status");
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
			if (paramMap.get("attaId") != null) {
				dynamic.append(" ATTA_ID = :attaId,");
			}
			if (paramMap.get("name") != null) {
				dynamic.append(" NAME = :name,");
			}
			if (paramMap.get("type") != null) {
				dynamic.append(" TYPE = :type,");
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
			if (paramMap.get("status") != null) {
				dynamic.append(" STATUS = :status,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}
}
