/************************************************************************************
 * @File name   :      WxWebsiteHis.java
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
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 网址访问记录表 WX_WEBSITE_HIS表
 * 
 */
public class WxWebsiteHis implements Serializable, RowMapper<WxWebsiteHis> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1488254701538434565L;
	private java.lang.String visitHisId;// 记录标识
	private java.lang.String visitDate;// 记录日期
	private java.lang.String visitTime;// 记录时间
	private java.lang.String accountId;// 所属微信
	private java.lang.String resourceId;// 资源标识
	private java.lang.String viewHref;// 访问路径
	private java.lang.String openId;// 微信号
	private java.lang.String viewSource;// 路径来源

	public static final String SOURCE_MESSAGE = "01";
	public static final String SOURCE_MENU = "02";

	/**
	 * 获取记录标识属性
	 * 
	 * @return visitHisId
	 */
	public java.lang.String getVisitHisId() {
		return visitHisId;
	}

	/**
	 * 设置记录标识属性
	 * 
	 * @param visitHisId
	 */
	public void setVisitHisId(java.lang.String visitHisId) {
		this.visitHisId = visitHisId;
	}

	/**
	 * 获取记录日期属性
	 * 
	 * @return visitDate
	 */
	public java.lang.String getVisitDate() {
		return visitDate;
	}

	/**
	 * 设置记录日期属性
	 * 
	 * @param visitDate
	 */
	public void setVisitDate(java.lang.String visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * 获取记录时间属性
	 * 
	 * @return visitTime
	 */
	public java.lang.String getVisitTime() {
		return visitTime;
	}

	/**
	 * 设置记录时间属性
	 * 
	 * @param visitTime
	 */
	public void setVisitTime(java.lang.String visitTime) {
		this.visitTime = visitTime;
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
	 * 获取资源标识属性
	 * 
	 * @return resourceId
	 */
	public java.lang.String getResourceId() {
		return resourceId;
	}

	/**
	 * 设置资源标识属性
	 * 
	 * @param resourceId
	 */
	public void setResourceId(java.lang.String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * 获取访问路径属性
	 * 
	 * @return viewHref
	 */
	public java.lang.String getViewHref() {
		return viewHref;
	}

	/**
	 * 设置访问路径属性
	 * 
	 * @param viewHref
	 */
	public void setViewHref(java.lang.String viewHref) {
		this.viewHref = viewHref;
	}

	/**
	 * 获取微信号属性
	 * 
	 * @return openId
	 */
	public java.lang.String getOpenId() {
		return openId;
	}

	/**
	 * 设置微信号属性
	 * 
	 * @param openId
	 */
	public void setOpenId(java.lang.String openId) {
		this.openId = openId;
	}

	/**
	 * 获取路径来源属性
	 * 
	 * @return viewSource
	 */
	public java.lang.String getViewSource() {
		return viewSource;
	}

	/**
	 * 设置路径来源属性
	 * 
	 * @param viewSource
	 */
	public void setViewSource(java.lang.String viewSource) {
		this.viewSource = viewSource;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxWebsiteHis");
		sb.append("{visitHisId=").append(visitHisId);
		sb.append(", visitDate=").append(visitDate);
		sb.append(", visitTime=").append(visitTime);
		sb.append(", accountId=").append(accountId);
		sb.append(", resourceId=").append(resourceId);
		sb.append(", viewHref=").append(viewHref);
		sb.append(", openId=").append(openId);
		sb.append(", viewSource=").append(viewSource);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxWebsiteHis mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxWebsiteHis domain = new WxWebsiteHis();
		domain.setVisitHisId(rs.getString("VISIT_HIS_ID"));
		domain.setVisitDate(rs.getString("VISIT_DATE"));
		domain.setVisitTime(rs.getString("VISIT_TIME"));
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		domain.setResourceId(rs.getString("RESOURCE_ID"));
		domain.setViewHref(rs.getString("VIEW_HREF"));
		domain.setOpenId(rs.getString("OPEN_ID"));
		domain.setViewSource(rs.getString("VIEW_SOURCE"));
		return domain;
	}
}