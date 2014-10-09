/************************************************************************************
 * @File name   :      WxFansMember.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-28
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
 * 2014-5-28 15:04:47			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 粉丝会员关系表 WX_FANS_MEMBER表
 * 
 */
public class WxFansMember implements Serializable, RowMapper<WxFansMember> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -928472608397720524L;
	private java.lang.String fansMemberId;// 关系标识
	private java.lang.String fansId;// 粉丝标识
	private java.lang.String memberId;// 会员标识
	private java.lang.String fullName;// 用户姓名
	private java.lang.String mobile;// 手机号
	private java.lang.String createTime;// 绑定时间

	/**
	 * 获取关系标识属性
	 * 
	 * @return fansMemberId
	 */
	public java.lang.String getFansMemberId() {
		return fansMemberId;
	}

	/**
	 * 设置关系标识属性
	 * 
	 * @param fansMemberId
	 */
	public void setFansMemberId(java.lang.String fansMemberId) {
		this.fansMemberId = fansMemberId;
	}

	/**
	 * 获取粉丝标识属性
	 * 
	 * @return fansId
	 */
	public java.lang.String getFansId() {
		return fansId;
	}

	/**
	 * 设置粉丝标识属性
	 * 
	 * @param fansId
	 */
	public void setFansId(java.lang.String fansId) {
		this.fansId = fansId;
	}

	/**
	 * 获取会员标识属性
	 * 
	 * @return memberId
	 */
	public java.lang.String getMemberId() {
		return memberId;
	}

	/**
	 * 设置会员标识属性
	 * 
	 * @param memberId
	 */
	public void setMemberId(java.lang.String memberId) {
		this.memberId = memberId;
	}

	/**
	 * 获取用户姓名属性
	 * 
	 * @return fullName
	 */
	public java.lang.String getFullName() {
		return fullName;
	}

	/**
	 * 设置用户姓名属性
	 * 
	 * @param fullName
	 */
	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}

	/**
	 * 获取手机号属性
	 * 
	 * @return mobile
	 */
	public java.lang.String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机号属性
	 * 
	 * @param mobile
	 */
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取绑定时间属性
	 * 
	 * @return createTime
	 */
	public java.lang.String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置绑定时间属性
	 * 
	 * @param createTime
	 */
	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxFansMember");
		sb.append("{fansMemberId=").append(fansMemberId);
		sb.append(", fansId=").append(fansId);
		sb.append(", memberId=").append(memberId);
		sb.append(", fullName=").append(fullName);
		sb.append(", mobile=").append(mobile);
		sb.append(", createTime=").append(createTime);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxFansMember mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxFansMember domain = new WxFansMember();
		domain.setFansMemberId(rs.getString("FANS_MEMBER_ID"));
		domain.setFansId(rs.getString("FANS_ID"));
		domain.setMemberId(rs.getString("MEMBER_ID"));
		domain.setFullName(rs.getString("FULL_NAME"));
		domain.setMobile(rs.getString("MOBILE"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		return domain;
	}
}