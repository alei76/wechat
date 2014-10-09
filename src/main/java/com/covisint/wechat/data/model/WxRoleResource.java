/************************************************************************************
 * @File name   :      WxRoleResource.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-25
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
 * 2014-4-25 16:08:31			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 角色资源 WX_ROLE_RESOURCE表
 * 
 */
public class WxRoleResource implements Serializable, RowMapper<WxRoleResource> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1466983769292052101L;
	private java.lang.String resourceId;// 资源标识
	private java.lang.String roleId;// 角色标识

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

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxRoleResource");
		sb.append("{resourceId=").append(resourceId);
		sb.append(", roleId=").append(roleId);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxRoleResource mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxRoleResource domain = new WxRoleResource();
		domain.setResourceId(rs.getString("RESOURCE_ID"));
		domain.setRoleId(rs.getString("ROLE_ID"));
		return domain;
	}
}