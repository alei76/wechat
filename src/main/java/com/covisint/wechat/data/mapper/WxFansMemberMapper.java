/************************************************************************************
 * @File name   :      WxFansMemberMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-28
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
 * 2014-5-28 15:04:47			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxFansMemberMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_FANS_MEMBER (FANS_MEMBER_ID, FANS_ID, MEMBER_ID, FULL_NAME, MOBILE, CREATE_TIME) VALUES (:fansMemberId, :fansId, :memberId, :fullName, :mobile, :createTime)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_FANS_MEMBER " + dynamicUpdate(paramMap) + " where FANS_MEMBER_ID = :fansMemberId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT FANS_MEMBER_ID, FANS_ID, MEMBER_ID, FULL_NAME, MOBILE, CREATE_TIME FROM WX_FANS_MEMBER" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT FANS_MEMBER_ID, FANS_ID, MEMBER_ID, FULL_NAME, MOBILE, CREATE_TIME FROM WX_FANS_MEMBER" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT FANS_MEMBER_ID, FANS_ID, MEMBER_ID, FULL_NAME, MOBILE, CREATE_TIME FROM WX_FANS_MEMBER" + getCondition(paramMap);
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
			if (paramMap.get("fansMemberId") != null) {
				condition.append(" AND FANS_MEMBER_ID = :fansMemberId");
			}
			if (paramMap.get("fansId") != null) {
				condition.append(" AND FANS_ID = :fansId");
			}
			if (paramMap.get("memberId") != null) {
				condition.append(" AND MEMBER_ID = :memberId");
			}
			if (paramMap.get("fullName") != null) {
				condition.append(" AND FULL_NAME = :fullName");
			}
			if (paramMap.get("mobile") != null) {
				condition.append(" AND MOBILE = :mobile");
			}
			if (paramMap.get("createTime") != null) {
				condition.append(" AND CREATE_TIME = :createTime");
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
			if (paramMap.get("fansMemberId") != null) {
				dynamic.append(" FANS_MEMBER_ID = :fansMemberId,");
			}
			if (paramMap.get("fansId") != null) {
				dynamic.append(" FANS_ID = :fansId,");
			}
			if (paramMap.get("memberId") != null) {
				dynamic.append(" MEMBER_ID = :memberId,");
			}
			if (paramMap.get("fullName") != null) {
				dynamic.append(" FULL_NAME = :fullName,");
			}
			if (paramMap.get("mobile") != null) {
				dynamic.append(" MOBILE = :mobile,");
			}
			if (paramMap.get("createTime") != null) {
				dynamic.append(" CREATE_TIME = :createTime,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}

	public static String getCheckExistsSql(Map<String, Object> paramMap) {
		String sql = "SELECT COUNT(1) FROM WX_FANS_MEMBER WFM INNER JOIN WX_FANS WF ON WF.FANS_ID = WFM.FANS_ID WHERE WF.OPEN_ID = :openId";
		return sql;
	}

	public static String getBindMemberSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_FANS_MEMBER (FANS_MEMBER_ID, FANS_ID, MEMBER_ID, FULL_NAME, MOBILE, CREATE_TIME) SELECT :fansMemberId, FANS_ID, :memberId, :fullName, :mobile, to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') FROM WX_FANS WHERE OPEN_ID = :openId ";
		return sql;
	}
}
