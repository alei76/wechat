package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxUserRolesMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_USER_ROLES (ROLE_ID, ROLE_NAME, ROLE_DESC, CREATE_BY, CREATE_TIME, STATUS, ROLE_TYPE) VALUES (:roleId, :roleName, :roleDesc, :createBy, to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'), :status, :roleType)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_USER_ROLES " + dynamicUpdate(paramMap) + " where ROLE_ID = :roleId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT ROLE_ID, ROLE_NAME, ROLE_DESC, CREATE_BY, CREATE_TIME, STATUS, ROLE_TYPE FROM WX_USER_ROLES" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT ROLE_ID, ROLE_NAME, ROLE_DESC, CREATE_BY, CREATE_TIME, STATUS, ROLE_TYPE FROM WX_USER_ROLES" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT ROLE_ID, ROLE_NAME, ROLE_DESC, CREATE_BY, CREATE_TIME, STATUS, ROLE_TYPE FROM WX_USER_ROLES" + getCondition(paramMap);
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
			if (paramMap.get("roleId") != null) {
				condition.append(" AND ROLE_ID = :roleId");
			}
			if (paramMap.get("roleName") != null) {
				condition.append(" AND ROLE_NAME = :roleName");
			}
			if (paramMap.get("likeRoleName") != null) {
				condition.append(" AND ROLE_NAME LIKE :likeRoleName");
			}
			if (paramMap.get("roleDesc") != null) {
				condition.append(" AND ROLE_DESC = :roleDesc");
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
			if (paramMap.get("roleType") != null) {
				condition.append(" AND ROLE_TYPE = :roleType");
			}
			if (paramMap.get("except") != null) {
				condition.append(" AND ROLE_ID <> :except");
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
			if (paramMap.get("roleId") != null) {
				dynamic.append(" ROLE_ID = :roleId,");
			}
			if (paramMap.get("roleName") != null) {
				dynamic.append(" ROLE_NAME = :roleName,");
			}
			if (paramMap.get("roleDesc") != null) {
				dynamic.append(" ROLE_DESC = :roleDesc,");
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
			if (paramMap.get("roleType") != null) {
				dynamic.append(" ROLE_TYPE = :roleType,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-14
	 * @param paramMap
	 * @return
	 */
	public static String getChangeStatusSql(Map<String, Object> paramMap) {
		String sql = " UPDATE WX_USER_ROLES SET STATUS = (CASE WHEN STATUS = :enable THEN :disable ELSE :enable END) WHERE ROLE_ID = :roleId";
		return sql;
	}

	public static String getCheckExistsSql(Map<String, Object> paramMap) {
		String sql = "SELECT COUNT(1) FROM WX_USER_ROLES" + getCondition(paramMap);
		return sql;
	}
}
