/************************************************************************************
 * @File name   :      WxMessageHisMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-06-17
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
 * 2014-6-17 9:39:26			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxMessageHisMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_MESSAGE_HIS (MESSAGE_HIS_ID, OPEN_ID, RECEIVER, ACCOUNT_ID, CREATE_TIME, MSG_TYPE, CONTENT, ATTA_ID, LOCAL_TIME, MSG_SOURCE, MSG_ID, REPLY_MSG_ID) VALUES (:messageHisId, :openId, :receiver, :accountId, :createTime, :msgType, :content, :attaId, (SYSDATE - TO_DATE('1970-01-01', 'yyyy-mm-dd')) * 86400 - 8 * 3600, :msgSource, :msgId, :replyMsgId)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_MESSAGE_HIS " + dynamicUpdate(paramMap) + " where MESSAGE_HIS_ID = :messageHisId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT MESSAGE_HIS_ID, OPEN_ID, RECEIVER, ACCOUNT_ID, CREATE_TIME, MSG_TYPE, CONTENT, ATTA_ID, LOCAL_TIME, MSG_SOURCE, MSG_ID, REPLY_MSG_ID FROM WX_MESSAGE_HIS" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT MESSAGE_HIS_ID, OPEN_ID, RECEIVER, ACCOUNT_ID, CREATE_TIME, MSG_TYPE, CONTENT, ATTA_ID, LOCAL_TIME, MSG_SOURCE, MSG_ID, REPLY_MSG_ID FROM WX_MESSAGE_HIS" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT MESSAGE_HIS_ID, OPEN_ID, RECEIVER, ACCOUNT_ID, CREATE_TIME, MSG_TYPE, CONTENT, ATTA_ID, LOCAL_TIME, MSG_SOURCE, MSG_ID, REPLY_MSG_ID FROM WX_MESSAGE_HIS" + getCondition(paramMap);
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
			if (paramMap.get("messageHisId") != null) {
				condition.append(" AND MESSAGE_HIS_ID = :messageHisId");
			}
			if (paramMap.get("openId") != null) {
				condition.append(" AND OPEN_ID = :openId");
			}
			if (paramMap.get("receiver") != null) {
				condition.append(" AND RECEIVER = :receiver");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("createTime") != null) {
				condition.append(" AND CREATE_TIME = :createTime");
			}
			if (paramMap.get("msgType") != null) {
				condition.append(" AND MSG_TYPE = :msgType");
			}
			if (paramMap.get("content") != null) {
				condition.append(" AND CONTENT = :content");
			}
			if (paramMap.get("attaId") != null) {
				condition.append(" AND ATTA_ID = :attaId");
			}
			if (paramMap.get("localTime") != null) {
				condition.append(" AND LOCAL_TIME = :localTime");
			}
			if (paramMap.get("msgSource") != null) {
				condition.append(" AND MSG_SOURCE = :msgSource");
			}
			if (paramMap.get("msgId") != null) {
				condition.append(" AND MSG_ID = :msgId");
			}
			if (paramMap.get("replyMsgId") != null) {
				condition.append(" AND REPLY_MSG_ID = :replyMsgId");
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
			if (paramMap.get("messageHisId") != null) {
				dynamic.append(" MESSAGE_HIS_ID = :messageHisId,");
			}
			if (paramMap.get("openId") != null) {
				dynamic.append(" OPEN_ID = :openId,");
			}
			if (paramMap.get("receiver") != null) {
				dynamic.append(" RECEIVER = :receiver,");
			}
			if (paramMap.get("accountId") != null) {
				dynamic.append(" ACCOUNT_ID = :accountId,");
			}
			if (paramMap.get("createTime") != null) {
				dynamic.append(" CREATE_TIME = :createTime,");
			}
			if (paramMap.get("msgType") != null) {
				dynamic.append(" MSG_TYPE = :msgType,");
			}
			if (paramMap.get("content") != null) {
				dynamic.append(" CONTENT = :content,");
			}
			if (paramMap.get("attaId") != null) {
				dynamic.append(" ATTA_ID = :attaId,");
			}
			if (paramMap.get("localTime") != null) {
				dynamic.append(" LOCAL_TIME = :localTime,");
			}
			if (paramMap.get("msgSource") != null) {
				dynamic.append(" MSG_SOURCE = :msgSource,");
			}
			if (paramMap.get("msgId") != null) {
				dynamic.append(" MSG_ID = :msgId,");
			}
			if (paramMap.get("replyMsgId") != null) {
				dynamic.append(" REPLY_MSG_ID = :replyMsgId,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}

	public static String getPageMsgHisSql(Map<String, Object> paramMap) {
		String sql = "SELECT WMH.MESSAGE_HIS_ID, WF.NICK_NAME, TO_CHAR(TO_DATE('1970-01-01', 'yyyy-mm-dd') + WMH.CREATE_TIME / 86400 + 8 / 24, 'yyyy-mm-dd hh24:MI:ss') AS SEND_TIME, WMH.MSG_TYPE, WMH.CONTENT, TO_CHAR(TO_DATE('1970-01-01', 'yyyy-mm-dd') + WMHR.CREATE_TIME / 86400 + 8 / 24, 'yyyy-mm-dd hh24:MI:ss') AS REPLY_CREATE_TIME, WMHR.MSG_TYPE AS REPLY_MSG_TYPE, WMHR.CONTENT AS REPLY_CONTENT, WDD.ITEM_DESC AS REPLY_MSG_SOURCE_CN FROM WX_MESSAGE_HIS WMH INNER JOIN WX_FANS WF ON WF.OPEN_ID = WMH.OPEN_ID AND WF.ACCOUNT_ID = WMH.ACCOUNT_ID LEFT JOIN WX_MESSAGE_HIS WMHR INNER JOIN WX_DATA_DICTIONARY WDD ON WDD.ITEM_CODE = WMHR.MSG_SOURCE AND WDD.CATALOG_CODE = :catalogCode ON WMH.MSG_ID = WMHR.REPLY_MSG_ID WHERE WF.FANS_ID = :fansId AND WMH.ACCOUNT_ID = :accountId ORDER BY WMH.CREATE_TIME DESC";
		return sql;
	}

	public static String getPageMsgCollectSql(Map<String, Object> paramMap) {
		StringBuffer sql = new StringBuffer(
				"SELECT WMH.MESSAGE_HIS_ID, WMH.MSG_TYPE, WMH.CONTENT, TO_CHAR(TO_DATE('1970-01-01', 'yyyy-mm-dd') + WMH.CREATE_TIME / 86400 + 8 / 24, 'yyyy-mm-dd hh24:MI:ss') AS SEND_TIME, WDD.ITEM_DESC AS REPLY_MSG_SOURCE_CN FROM WX_MESSAGE_HIS WMH INNER JOIN WX_FANS WF ON WF.OPEN_ID = WMH.OPEN_ID OR WF.OPEN_ID = WMH.RECEIVER INNER JOIN WX_DATA_DICTIONARY WDD ON WDD.ITEM_CODE = WMH.MSG_SOURCE AND WDD.CATALOG_CODE = :catalogCode WHERE WMH.ACCOUNT_ID = :accountId AND WF.FANS_ID = :fansId");
		if (paramMap.get("msgSource") != null) {
			sql.append(" AND WMH.MSG_SOURCE = :msgSource");
		}
		if (paramMap.get("startTime") != null) {
			sql.append(" AND WMH.CREATE_TIME >= :startTime");
		}
		if (paramMap.get("endTime") != null) {
			sql.append(" AND WMH.CREATE_TIME <= :endTime");
		}
		sql.append(" ORDER BY WMH.CREATE_TIME DESC, WMH.MSG_ID");
		return sql.toString();
	}
}
