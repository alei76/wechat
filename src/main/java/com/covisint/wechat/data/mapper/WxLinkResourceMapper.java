/************************************************************************************
 * @File name   :      WxLinkResourceMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-07-23
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
 * 2014-7-23 14:39:03			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxLinkResourceMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_LINK_RESOURCE (RESOURCE_ID, NAME, ACCOUNT_ID, RESOURCE_HREF, TYPE, CREATE_BY, CREATE_TIME, STATUS) VALUES (:resourceId, :name, :accountId, :resourceHref, :type, :createBy, to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'), :status)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_LINK_RESOURCE " + dynamicUpdate(paramMap) + " where RESOURCE_ID = :resourceId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT RESOURCE_ID, NAME, ACCOUNT_ID, RESOURCE_HREF, TYPE, CREATE_BY, CREATE_TIME, STATUS FROM WX_LINK_RESOURCE" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT RESOURCE_ID, NAME, ACCOUNT_ID, RESOURCE_HREF, TYPE, CREATE_BY, CREATE_TIME, STATUS FROM WX_LINK_RESOURCE" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT RESOURCE_ID, NAME, ACCOUNT_ID, RESOURCE_HREF, TYPE, CREATE_BY, CREATE_TIME, STATUS, WDD1.ITEM_DESC AS STATUS_CN FROM WX_LINK_RESOURCE INNER JOIN WX_DATA_DICTIONARY WDD1 ON WDD1.ITEM_CODE = STATUS AND WDD1.CATALOG_CODE = :statusDictCode " + getCondition(paramMap);
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
			if (paramMap.get("name") != null) {
				condition.append(" AND NAME = :name");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("resourceHref") != null) {
				condition.append(" AND RESOURCE_HREF = :resourceHref");
			}
			if (paramMap.get("type") != null) {
				condition.append(" AND TYPE = :type");
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
			if (paramMap.get("likeName") != null) {
				condition.append(" AND NAME LIKE :likeName");
			}
			if (paramMap.get("except") != null) {
				condition.append(" AND RESOURCE_ID <> :except");
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
			if (paramMap.get("resourceId") != null) {
				dynamic.append(" RESOURCE_ID = :resourceId,");
			}
			if (paramMap.get("name") != null) {
				dynamic.append(" NAME = :name,");
			}
			if (paramMap.get("accountId") != null) {
				dynamic.append(" ACCOUNT_ID = :accountId,");
			}
			if (paramMap.get("resourceHref") != null) {
				dynamic.append(" RESOURCE_HREF = :resourceHref,");
			}
			if (paramMap.get("type") != null) {
				dynamic.append(" TYPE = :type,");
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

	public static String getChangeStatusSql(Map<String, Object> paramMap) {
		String sql = "UPDATE WX_LINK_RESOURCE SET STATUS = (CASE WHEN STATUS = :enable THEN :disable ELSE :enable END) WHERE RESOURCE_ID = :resourceId";
		return sql;
	}

	public static String getCheckExistsSql(Map<String, Object> paramMap) {
		String sql = "SELECT COUNT(1) FROM WX_LINK_RESOURCE" + getCondition(paramMap);
		return sql;
	}

	public static String getUpdateStatusSql() {
		String sql = "UPDATE wx_link_resource SET status = :status WHERE resource_id = :resourceId";
		return sql;
	}
}
