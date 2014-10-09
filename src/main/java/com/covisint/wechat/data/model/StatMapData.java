/************************************************************************************
 * @File name   :      StatMapData.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-18
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
 * 2014-6-18 下午02:46:59			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;

/**
 *
 */
public class StatMapData implements RowCallbackHandler {
	private Map<String, Integer> rowData = new HashMap<String, Integer>();

	@Override
	public void processRow(ResultSet rs) throws SQLException {
		rowData.put(rs.getString("DATE_TIME"), rs.getInt("COUNT"));
	}

	public Map<String, Integer> getRowData() {
		return rowData;
	}
}
