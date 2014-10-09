package com.covisint.wechat.data.mapper;

import java.util.Map;

public class WxDataDictionaryMapper {
	/**
	 * 保存sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getInsertSql(Map<String, Object> paramMap) {
		String sql = "INSERT INTO WX_DATA_DICTIONARY (CATALOG_CODE, CATALOG_DESC, ITEM_CODE, ITEM_DESC, ITEM_ORDER, ENABLED) VALUES (:catalogCode, :catalogDesc, :itemCode, :itemDesc, :itemOrder, :enabled)";
		return sql;
	}

	/**
	 * 更新sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getUpdateSql(Map<String, Object> paramMap) {
		String sql = "update WX_DATA_DICTIONARY set CATALOG_CODE = :catalogCode, CATALOG_DESC = :catalogDesc, ITEM_CODE = :itemCode, ITEM_DESC = :itemDesc, ITEM_ORDER = :itemOrder, ENABLED = :enabled where CATALOG_CODE = :catalogCode and ITEM_CODE = :itemCode";
		return sql;
	}

	/**
	 * 获取单条记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindOneSql(Map<String, Object> paramMap) {
		String sql = "SELECT CATALOG_CODE, CATALOG_DESC, ITEM_CODE, ITEM_DESC, ITEM_ORDER, ENABLED FROM WX_DATA_DICTIONARY" + getCondition(paramMap);
		return sql;
	}

	/**
	 * 获取集合记录sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getFindAllSql(Map<String, Object> paramMap) {
		String sql = "SELECT CATALOG_CODE, CATALOG_DESC, ITEM_CODE, ITEM_DESC, ITEM_ORDER, ENABLED FROM WX_DATA_DICTIONARY" + getCondition(paramMap) + " ORDER BY ITEM_ORDER";
		return sql;
	}

	/**
	 * 获取分页查询sql
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getPageSql(Map<String, Object> paramMap) {
		String sql = "SELECT CATALOG_CODE, CATALOG_DESC, ITEM_CODE, ITEM_DESC, ITEM_ORDER, ENABLED FROM WX_DATA_DICTIONARY" + getCondition(paramMap);
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
			if (paramMap.get("catalogCode") != null) {
				condition.append(" AND CATALOG_CODE = :catalogCode");
			}
			if (paramMap.get("catalogDesc") != null) {
				condition.append(" AND CATALOG_DESC = :catalogDesc");
			}
			if (paramMap.get("itemCode") != null) {
				condition.append(" AND ITEM_CODE = :itemCode");
			}
			if (paramMap.get("itemDesc") != null) {
				condition.append(" AND ITEM_DESC = :itemDesc");
			}
			if (paramMap.get("itemOrder") != null) {
				condition.append(" AND ITEM_ORDER = :itemOrder");
			}
			if (paramMap.get("enabled") != null) {
				condition.append(" AND ENABLED = :enabled");
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
}
