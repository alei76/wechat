/************************************************************************************
 * @File name   :      WxReplyMsgMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-09
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
 * 2014-5-9 17:34:25			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxReplyMsgMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_REPLY_MSG (REPLY_ID, ACCOUNT_ID, KEYWORD, TEMPLATE_ID, TIGGER_TYPE, STATUS, CREATE_BY, CREATE_TIME, CHECK_BIND, ANON_TEMPLATE_ID) VALUES (:replyId, :accountId, :keyword, :templateId, :tiggerType, :status, :createBy, to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'), :checkBind, :anonTemplateId)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_REPLY_MSG " + dynamicUpdate(paramMap) + " where REPLY_ID = :replyId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT REPLY_ID, ACCOUNT_ID, KEYWORD, TEMPLATE_ID, TIGGER_TYPE, STATUS, CREATE_BY, CREATE_TIME, CHECK_BIND, ANON_TEMPLATE_ID FROM WX_REPLY_MSG" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT REPLY_ID, ACCOUNT_ID, KEYWORD, TEMPLATE_ID, TIGGER_TYPE, STATUS, CREATE_BY, CREATE_TIME, CHECK_BIND, ANON_TEMPLATE_ID FROM WX_REPLY_MSG" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT WRM.REPLY_ID, WRM.ACCOUNT_ID, WRM.KEYWORD, WRM.TEMPLATE_ID, WRM.TIGGER_TYPE, WRM.STATUS, WRM.CREATE_BY, WRM.CREATE_TIME, WRM.CHECK_BIND, WRM.ANON_TEMPLATE_ID, WDD1.ITEM_DESC AS STAUTS_CN, WDD2.ITEM_DESC AS CHECK_BIND_CN, WDD3.ITEM_DESC AS TIGGER_TYPE_CN, WRT.NAME AS TEMPLATE_CN, AWRT.NAME AS ANON_TEMPLATE_CN FROM WX_REPLY_MSG WRM INNER JOIN WX_DATA_DICTIONARY WDD1 ON WDD1.ITEM_CODE = WRM.STATUS AND WDD1.CATALOG_CODE = :autoreplyMessageStatus INNER JOIN WX_DATA_DICTIONARY WDD2 ON WDD2.ITEM_CODE = WRM.CHECK_BIND AND WDD2.CATALOG_CODE = :replyCheckBind INNER JOIN WX_DATA_DICTIONARY WDD3 ON WDD3.ITEM_CODE = WRM.TIGGER_TYPE AND WDD3.CATALOG_CODE = :messageTiggerType LEFT JOIN WX_REPLYMSG_TEMPLATE WRT ON WRT.TEMPLATE_ID = WRM.TEMPLATE_ID LEFT JOIN WX_REPLYMSG_TEMPLATE AWRT ON AWRT.TEMPLATE_ID = WRM.ANON_TEMPLATE_ID "
				+ getPageCondition(paramMap);
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
			if (paramMap.get("replyId") != null) {
				condition.append(" AND REPLY_ID = :replyId");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("keyword") != null) {
				condition.append(" AND KEYWORD = :keyword");
			}
			if (paramMap.get("likeKeyword") != null) {
				condition.append(" AND KEYWORD LIKE :likeKeyword");
			}
			if (paramMap.get("templateId") != null) {
				condition.append(" AND TEMPLATE_ID = :templateId");
			}
			if (paramMap.get("tiggerType") != null) {
				condition.append(" AND TIGGER_TYPE = :tiggerType");
			}
			if (paramMap.get("status") != null) {
				condition.append(" AND STATUS = :status");
			}
			if (paramMap.get("createBy") != null) {
				condition.append(" AND CREATE_BY = :createBy");
			}
			if (paramMap.get("createTime") != null) {
				condition.append(" AND CREATE_TIME = :createTime");
			}
			if (paramMap.get("checkBind") != null) {
				condition.append(" AND CHECK_BIND = :checkBind");
			}
			if (paramMap.get("anonTemplateId") != null) {
				condition.append(" AND ANON_TEMPLATE_ID = :anonTemplateId");
			}
			if (paramMap.get("except") != null) {
				condition.append(" AND REPLY_ID <> :except");
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
	private static String getPageCondition(Map<String, Object> paramMap) {
		if (paramMap == null || paramMap.size() == 0) {
			return "";
		} else {
			StringBuffer condition = new StringBuffer(" WHERE ");
			if (paramMap.get("replyId") != null) {
				condition.append(" AND WRM.REPLY_ID = :replyId");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND WRM.ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("keyword") != null) {
				condition.append(" AND WRM.KEYWORD = :keyword");
			}
			if (paramMap.get("likeKeyword") != null) {
				condition.append(" AND WRM.KEYWORD LIKE :likeKeyword");
			}
			if (paramMap.get("templateId") != null) {
				condition.append(" AND WRM.TEMPLATE_ID = :templateId");
			}
			if (paramMap.get("tiggerType") != null) {
				condition.append(" AND WRM.TIGGER_TYPE = :tiggerType");
			}
			if (paramMap.get("status") != null) {
				condition.append(" AND WRM.STATUS = :status");
			}
			if (paramMap.get("createBy") != null) {
				condition.append(" AND WRM.CREATE_BY = :createBy");
			}
			if (paramMap.get("createTime") != null) {
				condition.append(" AND WRM.CREATE_TIME = :createTime");
			}
			if (paramMap.get("checkBind") != null) {
				condition.append(" AND WRM.CHECK_BIND = :checkBind");
			}
			if (paramMap.get("anonTemplateId") != null) {
				condition.append(" AND WRM.ANON_TEMPLATE_ID = :anonTemplateId");
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
			if (paramMap.get("replyId") != null) {
				dynamic.append(" REPLY_ID = :replyId,");
			}
			if (paramMap.get("accountId") != null) {
				dynamic.append(" ACCOUNT_ID = :accountId,");
			}
			if (paramMap.get("keyword") != null) {
				dynamic.append(" KEYWORD = :keyword,");
			}
			if (paramMap.get("templateId") != null) {
				dynamic.append(" TEMPLATE_ID = :templateId,");
			}
			if (paramMap.get("tiggerType") != null) {
				dynamic.append(" TIGGER_TYPE = :tiggerType,");
			}
			if (paramMap.get("status") != null) {
				dynamic.append(" STATUS = :status,");
			}
			if (paramMap.get("createBy") != null) {
				dynamic.append(" CREATE_BY = :createBy,");
			}
			if (paramMap.get("createTime") != null) {
				dynamic.append(" CREATE_TIME = :createTime,");
			}
			if (paramMap.get("checkBind") != null) {
				dynamic.append(" CHECK_BIND = :checkBind,");
			}
			if (paramMap.get("anonTemplateId") != null) {
				dynamic.append(" ANON_TEMPLATE_ID = :anonTemplateId,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}

	public static String getChangeStatusSql(Map<String, Object> paramMap) {
		String sql = "UPDATE WX_REPLY_MSG SET STATUS = (CASE WHEN STATUS = :enable THEN :disable ELSE :enable END) WHERE REPLY_ID = :replyId";
		return sql;
	}

	public static String getCheckExistsSql(Map<String, Object> paramMap) {
		String sql = "SELECT COUNT(1) FROM WX_REPLY_MSG " + getCondition(paramMap);
		return sql;
	}
}
