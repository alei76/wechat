/************************************************************************************
 * @File name   :      WxWechatAccount.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-14
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
 * 2014-5-14 15:07:41			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 公众号信息表 WX_WECHAT_ACCOUNT表
 * 
 */
public class WxWechatAccount implements Serializable, RowMapper<WxWechatAccount> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6410129042977909308L;
	private java.lang.String accountId;// 公众号标识
	private java.lang.String name;// 微信名称
	private java.lang.String type;// 微信号类型
	private java.lang.String appId;// 微信接口ID
	private java.lang.String appSecret;// 微信接口密钥
	private java.lang.String token;// 接口验证信息
	private java.lang.String createBy;// 创建人
	private java.lang.String createTime;// 创建时间
	private java.lang.String status;// 状态
	private java.lang.String accountNo;// 微信号
	private java.lang.String notifyPath;// 微信配置路径
	private java.lang.String typeCn;// 类型字典中文

	public static final String ACCOUNT_STATUS_ENABLE = "01";// 状态启用
	public static final String ACCOUNT_STATUS_DISABLE = "02";// 状态禁用
	public static final String ACCOUNT_STATUS_DELETE = "03";// 已删除

	public static final String ACCOUNT_TYPE_SERVICE = "01";// 服务号
	public static final String ACCOUNT_TYPE_SUBSCRIBE = "02";// 订阅号

	public java.lang.String getNotifyPath() {
		return notifyPath;
	}

	public void setNotifyPath(java.lang.String notifyPath) {
		this.notifyPath = notifyPath;
	}

	public java.lang.String getTypeCn() {
		return typeCn;
	}

	public void setTypeCn(java.lang.String typeCn) {
		this.typeCn = typeCn;
	}

	/**
	 * 获取公众号标识属性
	 * 
	 * @return accountId
	 */
	public java.lang.String getAccountId() {
		return accountId;
	}

	/**
	 * 设置公众号标识属性
	 * 
	 * @param accountId
	 */
	public void setAccountId(java.lang.String accountId) {
		this.accountId = accountId;
	}

	/**
	 * 获取微信名称属性
	 * 
	 * @return name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置微信名称属性
	 * 
	 * @param name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取微信号类型属性
	 * 
	 * @return type
	 */
	public java.lang.String getType() {
		return type;
	}

	/**
	 * 设置微信号类型属性
	 * 
	 * @param type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 获取微信接口ID属性
	 * 
	 * @return appId
	 */
	public java.lang.String getAppId() {
		return appId;
	}

	/**
	 * 设置微信接口ID属性
	 * 
	 * @param appId
	 */
	public void setAppId(java.lang.String appId) {
		this.appId = appId;
	}

	/**
	 * 获取微信接口密钥属性
	 * 
	 * @return appSecret
	 */
	public java.lang.String getAppSecret() {
		return appSecret;
	}

	/**
	 * 设置微信接口密钥属性
	 * 
	 * @param appSecret
	 */
	public void setAppSecret(java.lang.String appSecret) {
		this.appSecret = appSecret;
	}

	/**
	 * 获取接口验证信息属性
	 * 
	 * @return token
	 */
	public java.lang.String getToken() {
		return token;
	}

	/**
	 * 设置接口验证信息属性
	 * 
	 * @param token
	 */
	public void setToken(java.lang.String token) {
		this.token = token;
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

	/**
	 * 获取微信号属性
	 * 
	 * @return accountNo
	 */
	public java.lang.String getAccountNo() {
		return accountNo;
	}

	/**
	 * 设置微信号属性
	 * 
	 * @param accountNo
	 */
	public void setAccountNo(java.lang.String accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxWechatAccount");
		sb.append("{accountId=").append(accountId);
		sb.append(", name=").append(name);
		sb.append(", type=").append(type);
		sb.append(", appId=").append(appId);
		sb.append(", appSecret=").append(appSecret);
		sb.append(", token=").append(token);
		sb.append(", createBy=").append(createBy);
		sb.append(", createTime=").append(createTime);
		sb.append(", status=").append(status);
		sb.append(", accountNo=").append(accountNo);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxWechatAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxWechatAccount domain = new WxWechatAccount();
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		domain.setName(rs.getString("NAME"));
		domain.setType(rs.getString("TYPE"));
		domain.setAppId(rs.getString("APP_ID"));
		domain.setAppSecret(rs.getString("APP_SECRET"));
		domain.setToken(rs.getString("TOKEN"));
		domain.setCreateBy(rs.getString("CREATE_BY"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		domain.setStatus(rs.getString("STATUS"));
		domain.setAccountNo(rs.getString("ACCOUNT_NO"));
		return domain;
	}
}