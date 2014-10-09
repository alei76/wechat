/************************************************************************************
 * @File name   :      WxGroup.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-15
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
 * 2014-5-15 15:56:59			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 微信分组表 WX_GROUP表
 * 
 */
public class WxGroup implements Serializable, RowMapper<WxGroup> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6851809000226540498L;
	private java.lang.String groupId;// 分组标识
	private java.lang.String groupName;// 分组名称
	private java.lang.String accountId;// 所属微信
	private java.lang.String createBy;// 创建人
	private java.lang.String createTime;// 创建时间
	private java.lang.String type;// 分组类型
	private java.lang.String status;// 状态

	public static final String STATUS_ENABLE = "01";// 正常
	public static final String STATUS_DISABLE = "02";// 删除

	public static final String TYPE_SYSTEM = "01";// 系统分组
	public static final String TYPE_NORMAL = "02";// 普通分组

	/**
	 * 获取分组标识属性
	 * 
	 * @return groupId
	 */
	public java.lang.String getGroupId() {
		return groupId;
	}

	/**
	 * 设置分组标识属性
	 * 
	 * @param groupId
	 */
	public void setGroupId(java.lang.String groupId) {
		this.groupId = groupId;
	}

	/**
	 * 获取分组名称属性
	 * 
	 * @return groupName
	 */
	public java.lang.String getGroupName() {
		return groupName;
	}

	/**
	 * 设置分组名称属性
	 * 
	 * @param groupName
	 */
	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
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
	 * 获取分组类型属性
	 * 
	 * @return type
	 */
	public java.lang.String getType() {
		return type;
	}

	/**
	 * 设置分组类型属性
	 * 
	 * @param type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
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
		sb.append("WxGroup");
		sb.append("{groupId=").append(groupId);
		sb.append(", groupName=").append(groupName);
		sb.append(", accountId=").append(accountId);
		sb.append(", createBy=").append(createBy);
		sb.append(", createTime=").append(createTime);
		sb.append(", type=").append(type);
		sb.append(", status=").append(status);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxGroup domain = new WxGroup();
		domain.setGroupId(rs.getString("GROUP_ID"));
		domain.setGroupName(rs.getString("GROUP_NAME"));
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		domain.setCreateBy(rs.getString("CREATE_BY"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		domain.setType(rs.getString("TYPE"));
		domain.setStatus(rs.getString("STATUS"));
		return domain;
	}
}