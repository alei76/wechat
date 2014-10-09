/************************************************************************************
 * @File name   :      WxWebsiteHisMapper.java
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
 * 2014-6-17 16:36:15			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxWebsiteHisMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_WEBSITE_HIS (VISIT_HIS_ID, VISIT_DATE, VISIT_TIME, ACCOUNT_ID, RESOURCE_ID, VIEW_HREF, OPEN_ID, VIEW_SOURCE, VIEW_PROVINCE) SELECT :visitHisId, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'HH24:MI:SS'), WF.ACCOUNT_ID, :resourceId, :viewHref, WF.OPEN_ID, :viewSource, WF.PROVINCE FROM WX_FANS WF WHERE WF.OPEN_ID = :openId AND WF.ACCOUNT_ID = :accountId ";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_WEBSITE_HIS " + dynamicUpdate(paramMap) + " where VISIT_HIS_ID = :visitHisId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT VISIT_HIS_ID, VISIT_DATE, VISIT_TIME, ACCOUNT_ID, RESOURCE_ID, VIEW_HREF, OPEN_ID, VIEW_SOURCE FROM WX_WEBSITE_HIS" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT VISIT_HIS_ID, VISIT_DATE, VISIT_TIME, ACCOUNT_ID, RESOURCE_ID, VIEW_HREF, OPEN_ID, VIEW_SOURCE FROM WX_WEBSITE_HIS" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT VISIT_HIS_ID, VISIT_DATE, VISIT_TIME, ACCOUNT_ID, RESOURCE_ID, VIEW_HREF, OPEN_ID, VIEW_SOURCE FROM WX_WEBSITE_HIS" + getCondition(paramMap);
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
			if (paramMap.get("visitHisId") != null) {
				condition.append(" AND VISIT_HIS_ID = :visitHisId");
			}
			if (paramMap.get("visitDate") != null) {
				condition.append(" AND VISIT_DATE = :visitDate");
			}
			if (paramMap.get("visitTime") != null) {
				condition.append(" AND VISIT_TIME = :visitTime");
			}
			if (paramMap.get("accountId") != null) {
				condition.append(" AND ACCOUNT_ID = :accountId");
			}
			if (paramMap.get("resourceId") != null) {
				condition.append(" AND RESOURCE_ID = :resourceId");
			}
			if (paramMap.get("viewHref") != null) {
				condition.append(" AND VIEW_HREF = :viewHref");
			}
			if (paramMap.get("openId") != null) {
				condition.append(" AND OPEN_ID = :openId");
			}
			if (paramMap.get("viewSource") != null) {
				condition.append(" AND VIEW_SOURCE = :viewSource");
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
			if (paramMap.get("visitHisId") != null) {
				dynamic.append(" VISIT_HIS_ID = :visitHisId,");
			}
			if (paramMap.get("visitDate") != null) {
				dynamic.append(" VISIT_DATE = :visitDate,");
			}
			if (paramMap.get("visitTime") != null) {
				dynamic.append(" VISIT_TIME = :visitTime,");
			}
			if (paramMap.get("accountId") != null) {
				dynamic.append(" ACCOUNT_ID = :accountId,");
			}
			if (paramMap.get("resourceId") != null) {
				dynamic.append(" RESOURCE_ID = :resourceId,");
			}
			if (paramMap.get("viewHref") != null) {
				dynamic.append(" VIEW_HREF = :viewHref,");
			}
			if (paramMap.get("openId") != null) {
				dynamic.append(" OPEN_ID = :openId,");
			}
			if (paramMap.get("viewSource") != null) {
				dynamic.append(" VIEW_SOURCE = :viewSource,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}

	public static String getPageWebsiteHisSql(Map<String, Object> paramMap) {
		StringBuffer sql = new StringBuffer("SELECT WWH.VISIT_DATE, WWH.VIEW_HREF, WWH.OPEN_ID, COUNT(1) AS CLICKS, MAX(WLR.NAME) AS VIEW_NAME FROM WX_WEBSITE_HIS WWH INNER JOIN WX_FANS WF ON WF.OPEN_ID = WWH.OPEN_ID AND WF.ACCOUNT_ID = WWH.ACCOUNT_ID LEFT JOIN WX_LINK_RESOURCE WLR ON WLR.RESOURCE_ID = WWH.RESOURCE_ID WHERE WF.FANS_ID = :fansId");
		if (paramMap.get("startTime") != null) {
			sql.append(" AND WWH.VISIT_DATE >= :startTime");
		}
		if (paramMap.get("endTime") != null) {
			sql.append(" AND WWH.VISIT_DATE <= :endTime");
		}
		sql.append(" GROUP BY WWH.VISIT_DATE, WWH.VIEW_HREF, WWH.OPEN_ID ORDER BY WWH.VISIT_DATE DESC");
		return sql.toString();
	}

	public static String getPageDaySummarySql(Map<String, Object> paramMap) {
		StringBuffer sql = new StringBuffer("SELECT WWH.VISIT_DATE, WWH.VIEW_HREF, COUNT(1) AS CLICK, MAX(WLR.NAME) AS VIEW_NAME, COUNT(DISTINCT WWH.OPEN_ID) AS MEMBER FROM WX_WEBSITE_HIS WWH LEFT JOIN WX_LINK_RESOURCE WLR ON WLR.RESOURCE_ID = WWH.RESOURCE_ID WHERE WWH.ACCOUNT_ID = :accountId");
		if (paramMap.get("startTime") != null) {
			sql.append(" AND WWH.VISIT_DATE >= :startTime");
		}
		if (paramMap.get("endTime") != null) {
			sql.append(" AND WWH.VISIT_DATE <= :endTime");
		}
		sql.append(" GROUP BY WWH.VISIT_DATE, WWH.VIEW_HREF ");
		if (paramMap.get("order") != null) {
			sql.append(" ORDER BY " + paramMap.get("order").toString());
		}
		return sql.toString();
	}

	public static String getDaySummarySql(Map<String, Object> paramMap) {
		StringBuffer sql = new StringBuffer("SELECT VISIT_DATE, COUNT(1) AS CLICK, COUNT(DISTINCT OPEN_ID) AS MEMBER FROM WX_WEBSITE_HIS WHERE ACCOUNT_ID = :accountId");
		if (paramMap.get("startTime") != null) {
			sql.append(" AND VISIT_DATE >= :startTime");
		}
		if (paramMap.get("endTime") != null) {
			sql.append(" AND VISIT_DATE <= :endTime");
		}
		sql.append(" GROUP BY VISIT_DATE ORDER BY VISIT_DATE");
		return sql.toString();
	}

	public static String getPageTimeSummarySql(Map<String, Object> paramMap) {
		StringBuffer sql = new StringBuffer("SELECT TO_CHAR(TO_DATE(WWH.VISIT_TIME, 'hh24:MI:ss'),'hh24') AS VISIT_TIME, WWH.VIEW_HREF, COUNT(1) AS CLICK, MAX(WLR.NAME) AS VIEW_NAME, COUNT(DISTINCT WWH.OPEN_ID) AS MEMBER FROM WX_WEBSITE_HIS WWH LEFT JOIN WX_LINK_RESOURCE WLR ON WLR.RESOURCE_ID = WWH.RESOURCE_ID WHERE WWH.ACCOUNT_ID = :accountId");
		if (paramMap.get("start") != null) {
			sql.append(" AND TO_CHAR(TO_DATE(WWH.VISIT_TIME, 'hh24:MI:ss'), 'hh24') >= TO_NUMBER(:start)");
		}
		if (paramMap.get("end") != null) {
			sql.append(" AND TO_CHAR(TO_DATE(WWH.VISIT_TIME, 'hh24:MI:ss'), 'hh24') <= TO_NUMBER(:end)");
		}
		sql.append(" GROUP BY TO_CHAR(TO_DATE(WWH.VISIT_TIME, 'hh24:MI:ss'),'hh24'), WWH.VIEW_HREF ");
		if (paramMap.get("order") != null) {
			sql.append(" ORDER BY " + paramMap.get("order").toString());
		}
		return sql.toString();
	}

	public static String getTimeSummarySql(Map<String, Object> paramMap) {
		StringBuffer sql = new StringBuffer("SELECT TO_CHAR(TO_DATE(VISIT_TIME, 'hh24:MI:ss'), 'hh24') AS VISIT_TIME, COUNT(1) AS CLICK, COUNT(DISTINCT OPEN_ID) AS MEMBER FROM WX_WEBSITE_HIS WHERE ACCOUNT_ID = :accountId");
		if (paramMap.get("start") != null) {
			sql.append(" AND TO_CHAR(TO_DATE(VISIT_TIME, 'hh24:MI:ss'), 'hh24') >= TO_NUMBER(:start)");
		}
		if (paramMap.get("end") != null) {
			sql.append(" AND TO_CHAR(TO_DATE(VISIT_TIME, 'hh24:MI:ss'), 'hh24') <= TO_NUMBER(:end)");
		}
		sql.append(" GROUP BY TO_CHAR(TO_DATE(VISIT_TIME, 'hh24:MI:ss'), 'hh24') ORDER BY VISIT_TIME ");
		return sql.toString();
	}

	public static String getPageAreaSummarySql(Map<String, Object> paramMap) {
		StringBuffer sql = new StringBuffer("SELECT COUNT(1) AS CLICK, COUNT(DISTINCT WWH.OPEN_ID) AS MEMBER, WWH.VIEW_PROVINCE AS PROVINCE, (SELECT COUNT(DISTINCT T1.OPEN_ID) FROM WX_WEBSITE_HIS T1 WHERE T1.ACCOUNT_ID = :accountId");
		if (paramMap.get("startTime") != null) {
			sql.append(" AND T1.VISIT_DATE >= :startTime");
		}
		if (paramMap.get("endTime") != null) {
			sql.append(" AND T1.VISIT_DATE <= :endTime");
		}
		sql.append(" ) AS ALL_MEMBER FROM WX_WEBSITE_HIS WWH INNER JOIN WX_FANS WF ON WF.OPEN_ID = WWH.OPEN_ID AND WF.ACCOUNT_ID = WWH.ACCOUNT_ID WHERE WWH.ACCOUNT_ID = :accountId");
		if (paramMap.get("startTime") != null) {
			sql.append(" AND WWH.VISIT_DATE >= :startTime");
		}
		if (paramMap.get("endTime") != null) {
			sql.append(" AND WWH.VISIT_DATE <= :endTime");
		}
		sql.append(" GROUP BY WWH.VIEW_PROVINCE");
		if (paramMap.get("order") != null) {
			sql.append(" ORDER BY " + paramMap.get("order").toString());
		}
		return sql.toString();
	}
}
