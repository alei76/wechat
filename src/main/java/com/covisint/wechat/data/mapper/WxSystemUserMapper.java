package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxSystemUserMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_SYSTEM_USER (USER_ID, USER_NAME, PASSWORD, FULL_NAME, STATUS, USER_TYPE, CREATE_BY, CREATE_TIME) VALUES (:userId, :userName, :password, :fullName, :status, :userType, :createBy, to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'))";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_SYSTEM_USER set USER_ID = :userId, USER_NAME = :userName, PASSWORD = :password, FULL_NAME = :fullName, STATUS = :status, USER_TYPE = :userType, CREATE_BY = :createBy, CREATE_TIME = :createTime where USER_ID = :userId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT USER_ID, USER_NAME, PASSWORD, FULL_NAME, STATUS, USER_TYPE, CREATE_BY, CREATE_TIME FROM WX_SYSTEM_USER" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT USER_ID, USER_NAME, PASSWORD, FULL_NAME, STATUS, USER_TYPE, CREATE_BY, CREATE_TIME FROM WX_SYSTEM_USER" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT USER_ID, USER_NAME, PASSWORD, FULL_NAME, STATUS, USER_TYPE, CREATE_BY, CREATE_TIME FROM WX_SYSTEM_USER" + getCondition(paramMap);
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
			if (paramMap.get("userId") != null) {
				condition.append(" AND USER_ID = :userId");
			}
			if (paramMap.get("userName") != null) {
				condition.append(" AND USER_NAME = :userName");
			}
			if (paramMap.get("password") != null) {
				condition.append(" AND PASSWORD = :password");
			}
			if (paramMap.get("fullName") != null) {
				condition.append(" AND FULL_NAME = :fullName");
			}
			if (paramMap.get("likeFullName") != null) {
				condition.append(" AND FULL_NAME LIKE :likeFullName");
			}
			if (paramMap.get("status") != null) {
				condition.append(" AND STATUS = :status");
			}
			if (paramMap.get("userType") != null) {
				condition.append(" AND USER_TYPE = :userType");
			}
			if (paramMap.get("createBy") != null) {
				condition.append(" AND CREATE_BY = :createBy");
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
	 * @Author : 马恩伟
	 * @Date : 2014-5-14
	 * @param paramMap
	 * @return
	 */
	public static String getChangeStatusSql(Map<String, Object> paramMap) {
		String sql = "UPDATE WX_SYSTEM_USER SET STATUS = (CASE WHEN STATUS = :enable THEN :disable ELSE :enable END) WHERE USER_ID = :userId";
		return sql;
	}
}
