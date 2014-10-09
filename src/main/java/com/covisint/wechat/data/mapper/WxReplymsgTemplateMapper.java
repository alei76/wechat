/************************************************************************************
 * @File name   :      WxReplymsgTemplateMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-30
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
 * 2014-4-30 14:56:08			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxReplymsgTemplateMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_REPLYMSG_TEMPLATE (TEMPLATE_ID, NAME, RESOURCE_TYPE, RESOURCE_ID, TYPE, CREATE_BY, CREATE_TIME, STATUS, ACCOUNT_ID, CONTENT, ATTA_ID) VALUES (:templateId, :name, :resourceType, :resourceId, :type, :createBy, to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'), :status, :accountId, :content, :attaId)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_REPLYMSG_TEMPLATE " + dynamicUpdate(paramMap) + " where TEMPLATE_ID = :templateId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT TEMPLATE_ID, NAME, RESOURCE_TYPE, RESOURCE_ID, TYPE, CREATE_BY, CREATE_TIME, STATUS, ACCOUNT_ID, CONTENT, ATTA_ID FROM WX_REPLYMSG_TEMPLATE" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT TEMPLATE_ID, NAME, RESOURCE_TYPE, RESOURCE_ID, TYPE, CREATE_BY, CREATE_TIME, STATUS, ACCOUNT_ID, CONTENT, ATTA_ID FROM WX_REPLYMSG_TEMPLATE" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT WRT.TEMPLATE_ID, WRT.NAME, WRT.RESOURCE_TYPE, WRT.RESOURCE_ID, WRT.TYPE, WRT.CREATE_BY, WRT.CREATE_TIME, WRT.STATUS, WRT.ACCOUNT_ID, WRT.CONTENT, WRT.ATTA_ID, WDD1.ITEM_DESC AS STATUS_CN, WDD2.ITEM_DESC AS RESOURCE_TYPE_CN, WDD3.ITEM_DESC AS TYPE_CN, WLR.NAME AS RESOURCE_NAME, WLR.STATUS AS RESOURCE_STATUS FROM WX_REPLYMSG_TEMPLATE WRT INNER JOIN WX_DATA_DICTIONARY WDD1 ON WDD1.ITEM_CODE = WRT.STATUS AND WDD1.CATALOG_CODE = :messageTemplateStatus INNER JOIN WX_DATA_DICTIONARY WDD2 ON WDD2.ITEM_CODE = WRT.RESOURCE_TYPE AND WDD2.CATALOG_CODE = :msgResourcceType INNER JOIN WX_DATA_DICTIONARY WDD3 ON WDD3.ITEM_CODE = WRT.TYPE AND WDD3.CATALOG_CODE = :messageType LEFT JOIN WX_LINK_RESOURCE WLR ON WLR.RESOURCE_ID = WRT.RESOURCE_ID "
				+ getPageCondition(paramMap);
		return sql;
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
			if (paramMap.get("templateId") != null) {
				dynamic.append(" TEMPLATE_ID = :templateId,");
			}
			if (paramMap.get("name") != null) {
				dynamic.append(" NAME = :name,");
			}
			if (paramMap.get("resourceType") != null) {
				dynamic.append(" RESOURCE_TYPE = :resourceType,");
			}
			if (paramMap.get("resourceId") != null) {
				dynamic.append(" RESOURCE_ID = :resourceId,");
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
			if (paramMap.get("accountId") != null) {
				dynamic.append(" ACCOUNT_ID = :accountId,");
			}
			if (paramMap.get("content") != null) {
				dynamic.append(" CONTENT = :content,");
			}
			if (paramMap.get("attaId") != null) {
				dynamic.append(" ATTA_ID = :attaId,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}

	/**
	 * 获取where条件sql
	 * 
	 * @param paramMap
	 * @return
	 */
	private static String getPageCondition(Map<String, Object> paramMap) {
		if (paramMap == null || paramMap.size() == 0) {
			return "";
		} else {
			StringBuffer condition = new StringBuffer(" WHERE ");
			if (paramMap.get("templateId") != null) {
				condition.append(" AND WRT.TEMPLATE_ID = :templateId");
			}
			if (paramMap.get("name") != null) {
				condition.append(" AND WRT.NAME = :name");
			}
			if (paramMap.get("likeName") != null) {
				condition.append(" AND WRT.NAME LIKE :likeName");
			}
			if (paramMap.get("resourceType") != null) {
				condition.append(" AND WRT.RESOURCE_TYPE = :resourceType");
			}
			if (paramMap.get("resourceId") != null) {
				condition.append(" AND WRT.RESOURCE_ID = :resourceId");
			}
			if (paramMap.get("type") != null) {
				condition.append(" AND WRT.TYPE = :type");
			}
			if (paramMap.get("except") != null) {
				condition.append(" AND WRT.RESOURCE_TYPE <> :except");
			}
			if (paramMap.get("createBy") != null) {
				condition.append(" AND WRT.CREATE_BY = :createBy");
			}
			if (paramMap.get("createTime") != null) {
				condition.append(" AND WRT.CREATE_TIME = :createTime");
			}
			if (paramMap.get("status") != null) {
				condition.append(" AND WRT.STATUS = :status");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND WRT.ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("content") != null) {
				condition.append(" AND WRT.CONTENT = :content");
			}
			if (paramMap.get("attaId") != null) {
				condition.append(" AND WRT.ATTA_ID = :attaId");
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
			if (paramMap.get("templateId") != null) {
				condition.append(" AND TEMPLATE_ID = :templateId");
			}
			if (paramMap.get("name") != null) {
				condition.append(" AND NAME = :name");
			}
			if (paramMap.get("resourceType") != null) {
				condition.append(" AND RESOURCE_TYPE = :resourceType");
			}
			if (paramMap.get("resourceId") != null) {
				condition.append(" AND RESOURCE_ID = :resourceId");
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
			if (paramMap.get("accountId") != null) {
				condition.append(" AND ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("content") != null) {
				condition.append(" AND CONTENT = :content");
			}
			if (paramMap.get("attaId") != null) {
				condition.append(" AND ATTA_ID = :attaId");
			}
			if (paramMap.get("except") != null) {
				condition.append(" AND TEMPLATE_ID <> :except");
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

	public static String getInfoSql(Map<String, Object> paramMap) {
		String sql = "SELECT WRT.TEMPLATE_ID, WRT.NAME, WRT.RESOURCE_TYPE, WRT.RESOURCE_ID, WRT.TYPE, WRT.CREATE_BY, WRT.CREATE_TIME, WRT.STATUS, WRT.ACCOUNT_ID, WRT.CONTENT, WRT.ATTA_ID, (SELECT COUNT(1) FROM WX_NEWS_MESSAGE WNM WHERE WRT.TEMPLATE_ID = WNM.TEMPLATE_ID) AS ITEMS_COUNT FROM WX_REPLYMSG_TEMPLATE WRT WHERE WRT.TEMPLATE_ID = :templateId";
		return sql;
	}

	public static String getCheckExistsSql(Map<String, Object> paramMap) {
		String sql = "SELECT COUNT(1) FROM WX_REPLYMSG_TEMPLATE" + getCondition(paramMap);
		return sql;
	}
}
