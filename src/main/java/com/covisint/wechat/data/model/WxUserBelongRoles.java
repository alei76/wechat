/************************************************************************************
 * @File name   :      WxUserBelongRoles.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-28
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
 * 2014-4-28 14:02:13			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 用户所属角色 WX_USER_BELONG_ROLES表
 * 
 */
public class WxUserBelongRoles implements Serializable, RowMapper<WxUserBelongRoles> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8081757577314195974L;
	private java.lang.String roleId;// 角色标识
	private java.lang.String userId;// 用户标识
	private java.lang.String accountId;// 微信账号

	private java.util.List<String> roleList;// 角色列表

	/**
	 * 获取角色列表
	 * 
	 * @return roleId
	 */
	public java.util.List<String> getRoleList() {
		return roleList;
	}

	/**
	 * 设置角色列表
	 * 
	 * @return roleId
	 */
	public void setRoleList(java.util.List<String> roleList) {
		this.roleList = roleList;
	}

	/**
	 * 获取角色标识属性
	 * 
	 * @return roleId
	 */
	public java.lang.String getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色标识属性
	 * 
	 * @param roleId
	 */
	public void setRoleId(java.lang.String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取用户标识属性
	 * 
	 * @return userId
	 */
	public java.lang.String getUserId() {
		return userId;
	}

	/**
	 * 设置用户标识属性
	 * 
	 * @param userId
	 */
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	/**
	 * 获取微信账号属性
	 * 
	 * @return accountId
	 */
	public java.lang.String getAccountId() {
		return accountId;
	}

	/**
	 * 设置微信账号属性
	 * 
	 * @param accountId
	 */
	public void setAccountId(java.lang.String accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxUserBelongRoles");
		sb.append("{roleId=").append(roleId);
		sb.append(", userId=").append(userId);
		sb.append(", accountId=").append(accountId);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxUserBelongRoles mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxUserBelongRoles domain = new WxUserBelongRoles();
		domain.setRoleId(rs.getString("ROLE_ID"));
		domain.setUserId(rs.getString("USER_ID"));
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		return domain;
	}
}