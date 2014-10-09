/************************************************************************************
 * @File name   :      WxFilesMapper.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-08-13
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
 * 2014-8-13 15:04:34			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxFilesMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_FILES (FILE_ID, ATTA_ID, FILE_NAME, FILE_SUFFIX, FILE_PATH, FILE_TYPE, CREATE_TIME, STATUS, MEDIA_ID, LAST_UPDATE) VALUES (:fileId, :attaId, :fileName, :fileSuffix, :filePath, :fileType, to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'), :status, :mediaId, :lastUpdate)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_FILES " + dynamicUpdate(paramMap) + " where FILE_ID = :fileId";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT FILE_ID, ATTA_ID, FILE_NAME, FILE_SUFFIX, FILE_PATH, FILE_TYPE, CREATE_TIME, STATUS, MEDIA_ID, LAST_UPDATE FROM WX_FILES" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT FILE_ID, ATTA_ID, FILE_NAME, FILE_SUFFIX, FILE_PATH, FILE_TYPE, CREATE_TIME, STATUS, MEDIA_ID, LAST_UPDATE FROM WX_FILES" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT FILE_ID, ATTA_ID, FILE_NAME, FILE_SUFFIX, FILE_PATH, FILE_TYPE, CREATE_TIME, STATUS, MEDIA_ID, LAST_UPDATE FROM WX_FILES" + getCondition(paramMap);
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
			if (paramMap.get("fileId") != null) {
				condition.append(" AND FILE_ID = :fileId");
			}
			if (paramMap.get("attaId") != null) {
				condition.append(" AND ATTA_ID = :attaId");
			}
			if (paramMap.get("fileName") != null) {
				condition.append(" AND FILE_NAME = :fileName");
			}
			if (paramMap.get("fileSuffix") != null) {
				condition.append(" AND FILE_SUFFIX = :fileSuffix");
			}
			if (paramMap.get("filePath") != null) {
				condition.append(" AND FILE_PATH = :filePath");
			}
			if (paramMap.get("fileType") != null) {
				condition.append(" AND FILE_TYPE = :fileType");
			}
			if (paramMap.get("createTime") != null) {
				condition.append(" AND CREATE_TIME = :createTime");
			}
			if (paramMap.get("status") != null) {
				condition.append(" AND STATUS = :status");
			}
			if (paramMap.get("mediaId") != null) {
				condition.append(" AND MEDIA_ID = :mediaId");
			}
			if (paramMap.get("lastUpdate") != null) {
				condition.append(" AND LAST_UPDATE = :lastUpdate");
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
			if (paramMap.get("fileId") != null) {
				dynamic.append(" FILE_ID = :fileId,");
			}
			if (paramMap.get("attaId") != null) {
				dynamic.append(" ATTA_ID = :attaId,");
			}
			if (paramMap.get("fileName") != null) {
				dynamic.append(" FILE_NAME = :fileName,");
			}
			if (paramMap.get("fileSuffix") != null) {
				dynamic.append(" FILE_SUFFIX = :fileSuffix,");
			}
			if (paramMap.get("filePath") != null) {
				dynamic.append(" FILE_PATH = :filePath,");
			}
			if (paramMap.get("fileType") != null) {
				dynamic.append(" FILE_TYPE = :fileType,");
			}
			if (paramMap.get("createTime") != null) {
				dynamic.append(" CREATE_TIME = :createTime,");
			}
			if (paramMap.get("status") != null) {
				dynamic.append(" STATUS = :status,");
			}
			if (paramMap.get("mediaId") != null) {
				dynamic.append(" MEDIA_ID = :mediaId,");
			}
			if (paramMap.get("lastUpdate") != null) {
				dynamic.append(" LAST_UPDATE = :lastUpdate,");
			}
			dynamic = dynamic.deleteCharAt(dynamic.length() - 1);
			return dynamic.toString();
		}
	}

	public static String getUpdateStatusSql(Map<String, Object> paramMap) {
		String sql = "UPDATE WX_FILES SET STATUS = :disable WHERE ATTA_ID = :attaId AND STATUS = :enable AND FILE_TYPE = :fileType";
		return sql;
	}
}
