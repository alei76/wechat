/************************************************************************************
 * @File name   :      WxRpFansCount.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-06-18
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
 * 2014-6-18 11:37:48			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 粉丝量统计表(日) WX_RP_FANS_COUNT表
 * 
 */
public class WxRpFansCount implements Serializable, RowMapper<WxRpFansCount> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6919421114504655856L;
	private java.lang.String recordId;// 记录标识
	private java.lang.Long recordDay;// 日期
	private java.lang.String accountId;// 所属微信
	private java.lang.Long fansCount;// 粉丝数

	/**
	 * 获取记录标识属性
	 * 
	 * @return recordId
	 */
	public java.lang.String getRecordId() {
		return recordId;
	}

	/**
	 * 设置记录标识属性
	 * 
	 * @param recordId
	 */
	public void setRecordId(java.lang.String recordId) {
		this.recordId = recordId;
	}

	/**
	 * 获取日期属性
	 * 
	 * @return recordDay
	 */
	public java.lang.Long getRecordDay() {
		return recordDay;
	}

	/**
	 * 设置日期属性
	 * 
	 * @param recordDay
	 */
	public void setRecordDay(java.lang.Long recordDay) {
		this.recordDay = recordDay;
	}

	/**
	 * 获取所属微信属性
	 * 
	 * @return accountId
	 */
	public java.lang.String getAccountId() {
		return accountId;
	}

	/**
	 * 设置所属微信属性
	 * 
	 * @param accountId
	 */
	public void setAccountId(java.lang.String accountId) {
		this.accountId = accountId;
	}

	/**
	 * 获取粉丝数属性
	 * 
	 * @return fansCount
	 */
	public java.lang.Long getFansCount() {
		return fansCount;
	}

	/**
	 * 设置粉丝数属性
	 * 
	 * @param fansCount
	 */
	public void setFansCount(java.lang.Long fansCount) {
		this.fansCount = fansCount;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxRpFansCount");
		sb.append("{recordId=").append(recordId);
		sb.append(", recordDay=").append(recordDay);
		sb.append(", accountId=").append(accountId);
		sb.append(", fansCount=").append(fansCount);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxRpFansCount mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxRpFansCount domain = new WxRpFansCount();
		domain.setRecordId(rs.getString("RECORD_ID"));
		domain.setRecordDay(rs.getLong("RECORD_DAY"));
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		domain.setFansCount(rs.getLong("FANS_COUNT"));
		return domain;
	}
}