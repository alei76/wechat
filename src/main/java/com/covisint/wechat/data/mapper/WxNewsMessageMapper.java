/************************************************************************************
 * @File name   :      WxNewsMessageMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-07-07
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
 * 2014-7-7 17:34:20			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxNewsMessageMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_NEWS_MESSAGE (NEWS_MESSAGE_ID, TEMPLATE_ID, TITLE, DESCRIPTION, ATTA_ID, TARGET_HREF, MSG_INDEX) VALUES (:newsMessageId, :templateId, :title, :description, :attaId, :targetHref, :msgIndex)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_NEWS_MESSAGE " + dynamicUpdate(paramMap) + " where NEWS_MESSAGE_ID = :newsMessageId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT NEWS_MESSAGE_ID, TEMPLATE_ID, TITLE, DESCRIPTION, ATTA_ID, TARGET_HREF, MSG_INDEX FROM WX_NEWS_MESSAGE" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT NEWS_MESSAGE_ID, TEMPLATE_ID, TITLE, DESCRIPTION, ATTA_ID, TARGET_HREF, MSG_INDEX FROM WX_NEWS_MESSAGE" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT NEWS_MESSAGE_ID, TEMPLATE_ID, TITLE, DESCRIPTION, ATTA_ID, TARGET_HREF, MSG_INDEX FROM WX_NEWS_MESSAGE" + getCondition(paramMap);
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
			if (paramMap.get("newsMessageId") != null) {
				condition.append(" AND NEWS_MESSAGE_ID = :newsMessageId");
			}
			if (paramMap.get("templateId") != null) {
				condition.append(" AND TEMPLATE_ID = :templateId");
			}
			if (paramMap.get("title") != null) {
				condition.append(" AND TITLE = :title");
			}
			if (paramMap.get("description") != null) {
				condition.append(" AND DESCRIPTION = :description");
			}
			if (paramMap.get("attaId") != null) {
				condition.append(" AND ATTA_ID = :attaId");
			}
			if (paramMap.get("targetHref") != null) {
				condition.append(" AND TARGET_HREF = :targetHref");
			}
			if (paramMap.get("msgIndex") != null) {
				condition.append(" AND MSG_INDEX = :msgIndex");
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
			if (paramMap.get("newsMessageId") != null) {
				dynamic.append(" NEWS_MESSAGE_ID = :newsMessageId,");
			}
			if (paramMap.get("templateId") != null) {
				dynamic.append(" TEMPLATE_ID = :templateId,");
			}
			if (paramMap.get("title") != null) {
				dynamic.append(" TITLE = :title,");
			}
			if (paramMap.get("description") != null) {
				dynamic.append(" DESCRIPTION = :description,");
			}
			if (paramMap.get("attaId") != null) {
				dynamic.append(" ATTA_ID = :attaId,");
			}
			if (paramMap.get("targetHref") != null) {
				dynamic.append(" TARGET_HREF = :targetHref,");
			}
			if (paramMap.get("msgIndex") != null) {
				dynamic.append(" MSG_INDEX = :msgIndex,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-7-9
	 * @return
	 */
	public static String getUpdateSql() {
		String sql = "update WX_NEWS_MESSAGE SET TITLE = :title, DESCRIPTION = :description, ATTA_ID = :attaId, TARGET_HREF = :targetHref, MSG_INDEX = :msgIndex where NEWS_MESSAGE_ID = :newsMessageId";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-7-9
	 * @return
	 */
	public static String getDeleteSql() {
		String sql = "DELETE FROM WX_NEWS_MESSAGE WHERE NEWS_MESSAGE_ID = :newsMessageId";
		return sql;
	}
}
