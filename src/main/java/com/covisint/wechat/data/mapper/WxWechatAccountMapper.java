package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxWechatAccountMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_WECHAT_ACCOUNT (ACCOUNT_ID, NAME, TYPE, APP_ID, APP_SECRET, TOKEN, CREATE_BY, CREATE_TIME, STATUS, ACCOUNT_NO) VALUES (:accountId, :name, :type, :appId, :appSecret, :token, :createBy, to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'), :status, :accountNo)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_WECHAT_ACCOUNT " + dynamicUpdate(paramMap) + " where ACCOUNT_ID = :accountId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT ACCOUNT_ID, NAME, TYPE, APP_ID, APP_SECRET, TOKEN, CREATE_BY, CREATE_TIME, STATUS, ACCOUNT_NO FROM WX_WECHAT_ACCOUNT" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT ACCOUNT_ID, NAME, TYPE, APP_ID, APP_SECRET, TOKEN, CREATE_BY, CREATE_TIME, STATUS, ACCOUNT_NO FROM WX_WECHAT_ACCOUNT" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT ACCOUNT_ID, NAME, TYPE, APP_ID, APP_SECRET, TOKEN, CREATE_BY, CREATE_TIME, STATUS, ACCOUNT_NO, WDD1.ITEM_DESC AS TYPE_CN FROM WX_WECHAT_ACCOUNT INNER JOIN WX_DATA_DICTIONARY WDD1 ON WDD1.ITEM_CODE = TYPE AND WDD1.CATALOG_CODE = :accountType " + getCondition(paramMap);
		return sql;
	}

	public static String getUserAccountSql(Map<String, Object> paramMap) {
		String sql = "select DISTINCT wwa.ACCOUNT_ID, wwa.NAME, wwa.TYPE, wwa.APP_ID, wwa.APP_SECRET, wwa.TOKEN, wwa.CREATE_BY, wwa.CREATE_TIME, wwa.STATUS, wwa.ACCOUNT_NO from WX_USER_BELONG_ROLES wubr inner join WX_SYSTEM_USER wsu on wubr.USER_ID = wsu.USER_ID inner join WX_WECHAT_ACCOUNT wwa on wwa.ACCOUNT_ID = wubr.ACCOUNT_ID where wubr.USER_ID = :userId and wwa.STATUS = :status ";
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
			if (paramMap.get("accountId") != null) {
				condition.append(" AND ACCOUNT_ID = :accountId");
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
			if (paramMap.get("appId") != null) {
				condition.append(" AND APP_ID = :appId");
			}
			if (paramMap.get("appSecret") != null) {
				condition.append(" AND APP_SECRET = :appSecret");
			}
			if (paramMap.get("token") != null) {
				condition.append(" AND TOKEN = :token");
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
			if (paramMap.get("hiddenStatus") != null) {
				condition.append(" AND STATUS <> :hiddenStatus");
			}
			if (paramMap.get("accountNo") != null) {
				condition.append(" AND ACCOUNT_NO = :accountNo");
			}
			if (paramMap.get("except") != null) {
				condition.append(" AND ACCOUNT_ID <> :except");
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
			if (paramMap.get("accountId") != null) {
				dynamic.append(" ACCOUNT_ID = :accountId,");
			}
			if (paramMap.get("name") != null) {
				dynamic.append(" NAME = :name,");
			}
			if (paramMap.get("type") != null) {
				dynamic.append(" TYPE = :type,");
			}
			if (paramMap.get("appId") != null) {
				dynamic.append(" APP_ID = :appId,");
			}
			if (paramMap.get("appSecret") != null) {
				dynamic.append(" APP_SECRET = :appSecret,");
			}
			if (paramMap.get("token") != null) {
				dynamic.append(" TOKEN = :token,");
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
			if (paramMap.get("accountNo") != null) {
				dynamic.append(" ACCOUNT_NO = :accountNo,");
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
		String sql = "UPDATE WX_WECHAT_ACCOUNT SET STATUS = (CASE WHEN STATUS = :enable THEN :disable ELSE :enable END) WHERE ACCOUNT_ID = :accountId";
		return sql;
	}

	public static String getCheckExistsSql(Map<String, Object> paramMap) {
		String sql = "SELECT COUNT(1) FROM WX_WECHAT_ACCOUNT" + getCondition(paramMap);
		return sql;
	}
}
