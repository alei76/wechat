/************************************************************************************
 * @File name   :      WxMenuInfoMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-04
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
 * 2014-5-4 14:40:02			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxMenuInfoMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_MENU_INFO (MENU_ID, TYPE, NAME, EVENT_KEY, TARGET, ACCOUNT_ID, PARENT_ID, ORDER_BY, CREATE_TIME, CREATE_BY, STATUS, CHECK_BIND, ANON_TARGET, ROBOT_KEYWORD, ANON_ROBOT_KEYWORD, OAUTH_SCOPE) VALUES (:menuId, :type, :name, :eventKey, :target, :accountId, :parentId, :orderBy, :createTime, :createBy, :status, :checkBind, :anonTarget, :robotKeyword, :anonRobotKeyword, :oauthScope)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_MENU_INFO " + dynamicUpdate(paramMap) + " where MENU_ID = :menuId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT MENU_ID, TYPE, NAME, EVENT_KEY, TARGET, ACCOUNT_ID, PARENT_ID, ORDER_BY, CREATE_TIME, CREATE_BY, STATUS, CHECK_BIND, ANON_TARGET, ROBOT_KEYWORD, ANON_ROBOT_KEYWORD, OAUTH_SCOPE FROM WX_MENU_INFO" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT MENU_ID, TYPE, NAME, EVENT_KEY, TARGET, ACCOUNT_ID, PARENT_ID, ORDER_BY, CREATE_TIME, CREATE_BY, STATUS, CHECK_BIND, ANON_TARGET, ROBOT_KEYWORD, ANON_ROBOT_KEYWORD, OAUTH_SCOPE FROM WX_MENU_INFO" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT MENU_ID, TYPE, NAME, EVENT_KEY, TARGET, ACCOUNT_ID, PARENT_ID, ORDER_BY, CREATE_TIME, CREATE_BY, STATUS, CHECK_BIND, ANON_TARGET, ROBOT_KEYWORD, ANON_ROBOT_KEYWORD, OAUTH_SCOPE FROM WX_MENU_INFO" + getCondition(paramMap);
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
			if (paramMap.get("menuId") != null) {
				dynamic.append(" MENU_ID = :menuId,");
			}
			if (paramMap.get("type") != null) {
				dynamic.append(" TYPE = :type,");
			}
			if (paramMap.get("name") != null) {
				dynamic.append(" NAME = :name,");
			}
			if (paramMap.get("eventKey") != null) {
				dynamic.append(" EVENT_KEY = :eventKey,");
			}
			if (paramMap.get("target") != null) {
				dynamic.append(" TARGET = :target,");
			}
			if (paramMap.get("accountId") != null) {
				dynamic.append(" ACCOUNT_ID = :accountId,");
			}
			if (paramMap.get("parentId") != null) {
				dynamic.append(" PARENT_ID = :parentId,");
			}
			if (paramMap.get("orderBy") != null) {
				dynamic.append(" ORDER_BY = :orderBy,");
			}
			if (paramMap.get("createTime") != null) {
				dynamic.append(" CREATE_TIME = :createTime,");
			}
			if (paramMap.get("createBy") != null) {
				dynamic.append(" CREATE_BY = :createBy,");
			}
			if (paramMap.get("status") != null) {
				dynamic.append(" STATUS = :status,");
			}
			if (paramMap.get("checkBind") != null) {
				dynamic.append(" CHECK_BIND = :checkBind,");
			}
			if (paramMap.get("anonTarget") != null) {
				dynamic.append(" ANON_TARGET = :anonTarget,");
			}
			if (paramMap.get("robotKeyword") != null) {
				dynamic.append(" ROBOT_KEYWORD = :robotKeyword,");
			}
			if (paramMap.get("anonRobotKeyword") != null) {
				dynamic.append(" ANON_ROBOT_KEYWORD = :anonRobotKeyword,");
			}
			if (paramMap.get("oauthScope") != null) {
				dynamic.append(" OAUTH_SCOPE = :oauthScope,");
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
	private static String getCondition(Map<String, Object> paramMap) {
		if (paramMap == null || paramMap.size() == 0) {
			return "";
		} else {
			StringBuffer condition = new StringBuffer(" WHERE ");
			if (paramMap.get("menuId") != null) {
				condition.append(" AND MENU_ID = :menuId");
			}
			if (paramMap.get("type") != null) {
				condition.append(" AND TYPE = :type");
			}
			if (paramMap.get("name") != null) {
				condition.append(" AND NAME = :name");
			}
			if (paramMap.get("eventKey") != null) {
				condition.append(" AND EVENT_KEY = :eventKey");
			}
			if (paramMap.get("target") != null) {
				condition.append(" AND TARGET = :target");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("parentId") != null) {
				condition.append(" AND PARENT_ID = :parentId");
			}
			if (paramMap.get("orderBy") != null) {
				condition.append(" AND ORDER_BY = :orderBy");
			}
			if (paramMap.get("createTime") != null) {
				condition.append(" AND CREATE_TIME = :createTime");
			}
			if (paramMap.get("createBy") != null) {
				condition.append(" AND CREATE_BY = :createBy");
			}
			if (paramMap.get("status") != null) {
				condition.append(" AND STATUS = :status");
			}
			if (paramMap.get("checkBind") != null) {
				condition.append(" AND CHECK_BIND = :checkBind");
			}
			if (paramMap.get("anonTarget") != null) {
				condition.append(" AND ANON_TARGET = :anonTarget");
			}
			if (paramMap.get("robotKeyword") != null) {
				condition.append(" AND ROBOT_KEYWORD = :robotKeyword");
			}
			if (paramMap.get("anonRobotKeyword") != null) {
				condition.append(" AND ANON_ROBOT_KEYWORD = :anonRobotKeyword");
			}
			if (paramMap.get("oauthScope") != null) {
				condition.append(" AND OAUTH_SCOPE = :oauthScope");
			}
			if (paramMap.get("except") != null) {
				condition.append(" AND MENU_ID <> :except");
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
	 * @Date : 2014-5-4
	 * @param paramMap
	 * @return
	 */
	public static String getCreateSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_MENU_INFO (MENU_ID, TYPE, NAME, EVENT_KEY, TARGET, ACCOUNT_ID, PARENT_ID, ORDER_BY, CREATE_TIME, CREATE_BY, STATUS, CHECK_BIND, ANON_TARGET, ROBOT_KEYWORD, ANON_ROBOT_KEYWORD, OAUTH_SCOPE) SELECT :menuId, :type, :name, :eventKey, :target, :accountId, :parentId, nvl(MAX(order_by),0) + 1, to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'), :createBy, :status, :checkBind, :anonTarget, :robotKeyword, :anonRobotKeyword, :oauthScope FROM WX_MENU_INFO WHERE ACCOUNT_ID = :accountId AND PARENT_ID = :parentId";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-4
	 * @param paramMap
	 * @return
	 */
	public static String getWxMenu(Map<String, Object> paramMap) {
		String sql = "SELECT MENU_ID AS MENU_ID, NAME AS MENU_NAME, NAME AS MENU_DESC, PARENT_ID AS PARENT_ID, '' AS MENU_URL FROM WX_MENU_INFO" + getCondition(paramMap) + " ORDER BY PARENT_ID DESC, ORDER_BY";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-4
	 * @param paramMap
	 * @return
	 */
	public static String getMenuInfo(Map<String, Object> paramMap) {
		String sql = "SELECT MENU_ID, TYPE, NAME, EVENT_KEY, TARGET, ACCOUNT_ID, PARENT_ID, ORDER_BY, CREATE_TIME, CREATE_BY, STATUS, CHECK_BIND, ANON_TARGET, ROBOT_KEYWORD, ANON_ROBOT_KEYWORD, OAUTH_SCOPE, (SELECT COUNT(1) FROM WX_MENU_INFO WHERE PARENT_ID = :menuId AND STATUS = :enable) AS SUB_COUNT FROM WX_MENU_INFO" + getCondition(paramMap);
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 * @param paramMap
	 * @return
	 */
	public static String getDeleteSql(Map<String, Object> paramMap) {
		String sql = "update WX_MENU_INFO set STATUS = :status where MENU_ID = :menuId or parent_id = :menuId";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 * @param paramMap
	 * @return
	 */
	public static String getInitParentMenu(Map<String, Object> paramMap) {
		String sql = "UPDATE WX_MENU_INFO SET TYPE = NULL, EVENT_KEY = NULL, TARGET = NULL, CHECK_BIND = :checkBind, ANON_TARGET = NULL, ROBOT_KEYWORD = NULL, ANON_ROBOT_KEYWORD = NULL, OAUTH_SCOPE = NULL WHERE MENU_ID = :menuId and PARENT_ID = :parentId";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 * @param paramMap
	 * @return
	 */
	public static String getMoveOtherUpSql(Map<String, Object> paramMap) {
		String sql = "UPDATE WX_MENU_INFO A SET A.ORDER_BY = A.ORDER_BY + 1 WHERE A.STATUS = :status AND EXISTS (SELECT 1 FROM WX_MENU_INFO B WHERE A.PARENT_ID = B.PARENT_ID AND B.MENU_ID = :menuId AND A.ORDER_BY = B.ORDER_BY - 1)";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 * @param paramMap
	 * @return
	 */
	public static String getMoveSelfUpSql(Map<String, Object> paramMap) {
		String sql = " UPDATE WX_MENU_INFO SET ORDER_BY = ORDER_BY - 1 WHERE MENU_ID = :menuId";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 * @param paramMap
	 * @return
	 */
	public static String getMoveOtherDownSql(Map<String, Object> paramMap) {
		String sql = "UPDATE WX_MENU_INFO A SET A.ORDER_BY = A.ORDER_BY - 1 WHERE A.STATUS = :status AND EXISTS (SELECT 1 FROM WX_MENU_INFO B WHERE A.PARENT_ID = B.PARENT_ID AND B.MENU_ID = :menuId AND A.ORDER_BY = B.ORDER_BY + 1)";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 * @param paramMap
	 * @return
	 */
	public static String getMoveSelfDownSql(Map<String, Object> paramMap) {
		String sql = " UPDATE WX_MENU_INFO SET ORDER_BY = ORDER_BY + 1 WHERE MENU_ID = :menuId";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 * @param paramMap
	 * @return
	 */
	public static String getValidateMoveUpSql(Map<String, Object> paramMap) {
		String sql = "SELECT COUNT(1) FROM WX_MENU_INFO WHERE MENU_ID = :menuId AND ORDER_BY = '1'";
		return sql;
	}

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 * @param paramMap
	 * @return
	 */
	public static String getMenuSql(Map<String, Object> paramMap) {
		String sql = "SELECT WMI.MENU_ID, WMI.TYPE, WMI.NAME, WMI.EVENT_KEY, CASE WMI.TYPE WHEN '01' THEN WLR.RESOURCE_ID WHEN '02' THEN WRT.TEMPLATE_ID ELSE NULL END AS TARGET, WMI.ACCOUNT_ID, WMI.OAUTH_SCOPE FROM WX_MENU_INFO WMI LEFT JOIN WX_LINK_RESOURCE WLR ON WLR.RESOURCE_ID = WMI.TARGET AND WLR.TYPE = :resourceType AND WLR.STATUS = :resourceStatus LEFT JOIN WX_REPLYMSG_TEMPLATE WRT ON WRT.TEMPLATE_ID = WMI.TARGET AND WRT.STATUS = :templateStatus WHERE WMI.ACCOUNT_ID = :accountId AND WMI.STATUS = :status AND WMI.PARENT_ID = :parentId ORDER BY WMI.ORDER_BY";
		return sql;
	}

	public static String getCheckExistsSql(Map<String, Object> paramMap) {
		String sql = "SELECT COUNT(1) FROM WX_MENU_INFO" + getCondition(paramMap);
		return sql;
	}
}