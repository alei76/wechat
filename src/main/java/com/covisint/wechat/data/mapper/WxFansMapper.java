/************************************************************************************
 * @File name   :      WxFansMapper.java
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
 * 2014-5-15 15:09:37			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.List;
import java.util.Map;

public class WxFansMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_FANS (FANS_ID, OPEN_ID, NICK_NAME, SEX, CITY, COUNTRY, PROVINCE, LANGUAGE, HEADIMG_URL, ACCOUNT_ID, GROUP_ID, STATUS, SUB_TIME, UNSUB_TIME) VALUES (:fansId, :openId, :nickName, :sex, :city, :country, :province, :language, :headimgUrl, :accountId, :groupId, :status, (SYSDATE - TO_DATE('1970-01-01', 'yyyy-mm-dd')) * 86400 - 8 * 3600, :unsubTime)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_FANS " + dynamicUpdate(paramMap) + " where FANS_ID = :fansId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT FANS_ID, OPEN_ID, NICK_NAME, SEX, CITY, COUNTRY, PROVINCE, LANGUAGE, HEADIMG_URL, ACCOUNT_ID, GROUP_ID, STATUS, SUB_TIME, UNSUB_TIME FROM WX_FANS" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT FANS_ID, OPEN_ID, NICK_NAME, SEX, CITY, COUNTRY, PROVINCE, LANGUAGE, HEADIMG_URL, ACCOUNT_ID, GROUP_ID, STATUS, SUB_TIME, UNSUB_TIME FROM WX_FANS" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT WF.FANS_ID, WF.OPEN_ID, WF.NICK_NAME, WF.SEX, WF.CITY, WF.COUNTRY, WF.PROVINCE, WF.LANGUAGE, WF.HEADIMG_URL, WF.ACCOUNT_ID, WF.GROUP_ID, WF.STATUS, WF.SUB_TIME, WF.UNSUB_TIME, WG.GROUP_NAME, WFM.FANS_MEMBER_ID, WFM.MEMBER_ID, WFM.FULL_NAME, WFM.MOBILE, WFM.CREATE_TIME FROM WX_FANS WF INNER JOIN WX_GROUP WG ON WG.GROUP_ID = WF.GROUP_ID LEFT JOIN WX_FANS_MEMBER WFM ON WFM.FANS_ID = WF.FANS_ID "
				+ getPageCondition(paramMap);
		return sql;
	}

	private static String getPageCondition(Map<String, Object> paramMap) {
		if (paramMap == null || paramMap.size() == 0) {
			return "";
		} else {
			StringBuffer condition = new StringBuffer(" WHERE ");
			if (paramMap.get("fansId") != null) {
				condition.append(" AND WF.FANS_ID = :fansId");
			}
			if (paramMap.get("openId") != null) {
				condition.append(" AND WF.OPEN_ID = :openId");
			}
			if (paramMap.get("nickName") != null) {
				condition.append(" AND WF.NICK_NAME = :nickName");
			}
			if (paramMap.get("sex") != null) {
				condition.append(" AND WF.SEX = :sex");
			}
			if (paramMap.get("city") != null) {
				condition.append(" AND WF.CITY = :city");
			}
			if (paramMap.get("country") != null) {
				condition.append(" AND WF.COUNTRY = :country");
			}
			if (paramMap.get("province") != null) {
				condition.append(" AND WF.PROVINCE = :province");
			}
			if (paramMap.get("language") != null) {
				condition.append(" AND WF.LANGUAGE = :language");
			}
			if (paramMap.get("headimgUrl") != null) {
				condition.append(" AND WF.HEADIMG_URL = :headimgUrl");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND WF.ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("groupId") != null) {
				condition.append(" AND WF.GROUP_ID = :groupId");
			}
			if (paramMap.get("status") != null) {
				condition.append(" AND WF.STATUS = :status");
			}
			if (paramMap.get("subTime") != null) {
				condition.append(" AND WF.SUB_TIME = :subTime");
			}
			if (paramMap.get("unsubTime") != null) {
				condition.append(" AND WF.UNSUB_TIME = :unsubTime");
			}
			if (paramMap.get("likeNickName") != null) {
				condition.append(" AND WF.NICK_NAME LIKE :likeNickName");
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
			if (paramMap.get("fansId") != null) {
				condition.append(" AND FANS_ID = :fansId");
			}
			if (paramMap.get("openId") != null) {
				condition.append(" AND OPEN_ID = :openId");
			}
			if (paramMap.get("nickName") != null) {
				condition.append(" AND NICK_NAME = :nickName");
			}
			if (paramMap.get("sex") != null) {
				condition.append(" AND SEX = :sex");
			}
			if (paramMap.get("city") != null) {
				condition.append(" AND CITY = :city");
			}
			if (paramMap.get("country") != null) {
				condition.append(" AND COUNTRY = :country");
			}
			if (paramMap.get("province") != null) {
				condition.append(" AND PROVINCE = :province");
			}
			if (paramMap.get("language") != null) {
				condition.append(" AND LANGUAGE = :language");
			}
			if (paramMap.get("headimgUrl") != null) {
				condition.append(" AND HEADIMG_URL = :headimgUrl");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("groupId") != null) {
				condition.append(" AND GROUP_ID = :groupId");
			}
			if (paramMap.get("status") != null) {
				condition.append(" AND STATUS = :status");
			}
			if (paramMap.get("subTime") != null) {
				condition.append(" AND SUB_TIME = :subTime");
			}
			if (paramMap.get("unsubTime") != null) {
				condition.append(" AND UNSUB_TIME = :unsubTime");
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
			if (paramMap.get("fansId") != null) {
				dynamic.append(" FANS_ID = :fansId,");
			}
			if (paramMap.get("openId") != null) {
				dynamic.append(" OPEN_ID = :openId,");
			}
			if (paramMap.get("nickName") != null) {
				dynamic.append(" NICK_NAME = :nickName,");
			}
			if (paramMap.get("sex") != null) {
				dynamic.append(" SEX = :sex,");
			}
			if (paramMap.get("city") != null) {
				dynamic.append(" CITY = :city,");
			}
			if (paramMap.get("country") != null) {
				dynamic.append(" COUNTRY = :country,");
			}
			if (paramMap.get("province") != null) {
				dynamic.append(" PROVINCE = :province,");
			}
			if (paramMap.get("language") != null) {
				dynamic.append(" LANGUAGE = :language,");
			}
			if (paramMap.get("headimgUrl") != null) {
				dynamic.append(" HEADIMG_URL = :headimgUrl,");
			}
			if (paramMap.get("accountId") != null) {
				dynamic.append(" ACCOUNT_ID = :accountId,");
			}
			if (paramMap.get("groupId") != null) {
				dynamic.append(" GROUP_ID = :groupId,");
			}
			if (paramMap.get("status") != null) {
				dynamic.append(" STATUS = :status,");
			}
			if (paramMap.get("subTime") != null) {
				dynamic.append(" SUB_TIME = :subTime,");
			}
			if (paramMap.get("unsubTime") != null) {
				dynamic.append(" UNSUB_TIME = :unsubTime,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-15
	 * @return
	 */
	public static String getBatchInsertSql() {
		String sql = "INSERT INTO WX_FANS (FANS_ID, OPEN_ID, ACCOUNT_ID, STATUS, SUB_TIME, GROUP_ID) VALUES (:fansId, :openId, :accountId, :status, (SYSDATE - TO_DATE('1970-01-01', 'yyyy-mm-dd')) * 86400 - 8 * 3600, :groupId)";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-16
	 * @param paramMap
	 * @return
	 */
	public static String getGroupChangeSql(Map<String, Object> paramMap) {
		String sql = "UPDATE wx_fans SET group_id = :toGroupId WHERE group_id = :fromGroupId";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-16
	 * @return
	 */
	public static String getBatchUpdate() {
		String sql = "UPDATE WX_FANS SET GROUP_ID = :groupId WHERE FANS_ID = :fansId";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-6-4
	 * @param paramMap
	 * @return
	 */
	public static String getUnSubSql(Map<String, Object> paramMap) {
		String sql = "update WX_FANS set STATUS = :status, UNSUB_TIME = (SYSDATE - TO_DATE('1970-01-01', 'yyyy-mm-dd')) * 86400 - 8 * 3600 where OPEN_ID = :openId and ACCOUNT_ID = :accountId";
		return sql;
	}

	@SuppressWarnings("unchecked")
	public static String getNewOpenIdsSql(Map<String, Object> paramMap) {
		StringBuffer sql = new StringBuffer("WITH TEMP_VIEW AS ( ");
		List<String> openIds = (List<String>) paramMap.get("openIds");
		if (openIds != null) {
			int count = openIds.size();
			for (int i = 0; i < count; i++) {
				sql.append(" SELECT '" + openIds.get(i) + "' OPEN_ID FROM DUAL ");
				if (i < count - 1) {
					sql.append(" UNION ALL ");
				}
			}
		}
		sql.append(" ) SELECT OPEN_ID FROM TEMP_VIEW MINUS SELECT OPEN_ID FROM WX_FANS WHERE ACCOUNT_ID = :accountId");
		return sql.toString();
	}

	public static String getListProvSql(Map<String, Object> paramMap) {
		String sql = "SELECT DISTINCT PROVINCE FROM WX_FANS" + getCondition(paramMap);
		return sql;
	}
}
