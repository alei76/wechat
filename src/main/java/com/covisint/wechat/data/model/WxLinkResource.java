/************************************************************************************
 * @File name   :      WxLinkResource.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-29
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
 * 2014-4-29 11:13:42			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 外部资源表 WX_LINK_RESOURCE表
 * 
 */
public class WxLinkResource implements Serializable, RowMapper<WxLinkResource> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4118380461773688541L;
	private java.lang.String resourceId;// 资源标识
	private java.lang.String name;// 资源名称
	private java.lang.String accountId;// 所属微信
	private java.lang.String resourceHref;// 资源路径
	private java.lang.String type;// 资源类型
	private java.lang.String createBy;// 创建人
	private java.lang.String createTime;// 创建时间
	private java.lang.String status;// 状态

	private String statusCn;// 状态字典中文

	public static final String TYPE_WEBSITE_ADDRESS = "01";// 网址
	public static final String TYPE_WEB_SERVICE = "02";// 接口

	public static final String LINK_STATUS_ENABLE = "01";// 启用
	public static final String LINK_STATUS_DISABLE = "02";// 禁用

	public String getStatusCn() {
		return statusCn;
	}

	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
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
	 * 获取资源名称属性
	 * 
	 * @return name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置资源名称属性
	 * 
	 * @param name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
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
	 * 获取资源路径属性
	 * 
	 * @return resourceHref
	 */
	public java.lang.String getResourceHref() {
		return resourceHref;
	}

	/**
	 * 设置资源路径属性
	 * 
	 * @param resourceHref
	 */
	public void setResourceHref(java.lang.String resourceHref) {
		this.resourceHref = resourceHref;
	}

	/**
	 * 获取资源类型属性
	 * 
	 * @return type
	 */
	public java.lang.String getType() {
		return type;
	}

	/**
	 * 设置资源类型属性
	 * 
	 * @param type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 获取创建人属性
	 * 
	 * @return createBy
	 */
	public java.lang.String getCreateBy() {
		return createBy;
	}

	/**
	 * 设置创建人属性
	 * 
	 * @param createBy
	 */
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return createTime
	 */
	public java.lang.String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param createTime
	 */
	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取状态属性
	 * 
	 * @return status
	 */
	public java.lang.String getStatus() {
		return status;
	}

	/**
	 * 设置状态属性
	 * 
	 * @param status
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxLinkResource");
		sb.append("{resourceId=").append(resourceId);
		sb.append(", name=").append(name);
		sb.append(", accountId=").append(accountId);
		sb.append(", resourceHref=").append(resourceHref);
		sb.append(", type=").append(type);
		sb.append(", createBy=").append(createBy);
		sb.append(", createTime=").append(createTime);
		sb.append(", status=").append(status);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxLinkResource mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxLinkResource domain = new WxLinkResource();
		domain.setResourceId(rs.getString("RESOURCE_ID"));
		domain.setName(rs.getString("NAME"));
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		domain.setResourceHref(rs.getString("RESOURCE_HREF"));
		domain.setType(rs.getString("TYPE"));
		domain.setCreateBy(rs.getString("CREATE_BY"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		domain.setStatus(rs.getString("STATUS"));
		return domain;
	}
}