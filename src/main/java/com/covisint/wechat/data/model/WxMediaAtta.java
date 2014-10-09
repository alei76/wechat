/************************************************************************************
 * @File name   :      WxMediaAtta.java
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
 * 2014-8-13 14:02:36			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 富媒体表 WX_MEDIA_ATTA表
 * 
 */
public class WxMediaAtta implements Serializable, RowMapper<WxMediaAtta> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2473892786209712819L;
	private java.lang.String attaId;// 媒体标识
	private java.lang.String name;// 媒体名称
	private java.lang.String type;// 媒体类型
	private java.lang.String accountId;// 所属微信
	private java.lang.String createBy;// 创建人
	private java.lang.String createTime;// 创建时间
	private java.lang.String status;// 状态

	public static final String TYPE_PIC = "01";// 图片
	public static final String TYPE_AUDIO = "02";// 音频
	public static final String TYPE_VEDIO = "03";// 视频

	public static final String STATUS_ENABLE = "01";// 启用
	public static final String STATUS_DISABLE = "02";// 删除

	private String statusCn;// 状态字典中文
	private String typeCn;// 文件类型字典中文

	public String getStatusCn() {
		return statusCn;
	}

	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}

	public String getTypeCn() {
		return typeCn;
	}

	public void setTypeCn(String typeCn) {
		this.typeCn = typeCn;
	}

	/**
	 * 获取媒体标识属性
	 * 
	 * @return attaId
	 */
	public java.lang.String getAttaId() {
		return attaId;
	}

	/**
	 * 设置媒体标识属性
	 * 
	 * @param attaId
	 */
	public void setAttaId(java.lang.String attaId) {
		this.attaId = attaId;
	}

	/**
	 * 获取媒体名称属性
	 * 
	 * @return name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置媒体名称属性
	 * 
	 * @param name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取媒体类型属性
	 * 
	 * @return type
	 */
	public java.lang.String getType() {
		return type;
	}

	/**
	 * 设置媒体类型属性
	 * 
	 * @param type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
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
		sb.append("WxMediaAtta");
		sb.append("{attaId=").append(attaId);
		sb.append(", name=").append(name);
		sb.append(", type=").append(type);
		sb.append(", accountId=").append(accountId);
		sb.append(", createBy=").append(createBy);
		sb.append(", createTime=").append(createTime);
		sb.append(", status=").append(status);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxMediaAtta mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxMediaAtta domain = new WxMediaAtta();
		domain.setAttaId(rs.getString("ATTA_ID"));
		domain.setName(rs.getString("NAME"));
		domain.setType(rs.getString("TYPE"));
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		domain.setCreateBy(rs.getString("CREATE_BY"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		domain.setStatus(rs.getString("STATUS"));
		return domain;
	}
}