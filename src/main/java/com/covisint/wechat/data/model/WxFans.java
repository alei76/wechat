/************************************************************************************
 * @File name   :      WxFans.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-06-04
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
 * 2014-6-4 11:11:31			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 微信粉丝表 WX_FANS表
 * 
 */
public class WxFans implements Serializable, RowMapper<WxFans> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6746111872083649587L;
	private java.lang.String fansId;// 粉丝标识
	private java.lang.String openId;// 微信号
	private java.lang.String nickName;// 昵称
	private java.lang.String sex;// 性别
	private java.lang.String city;// 城市
	private java.lang.String country;// 国家
	private java.lang.String province;// 省份
	private java.lang.String language;// 语言
	private java.lang.String headimgUrl;// 用户头像
	private java.lang.String accountId;// 所属微信
	private java.lang.String groupId;// 所属分组
	private java.lang.String status;// 状态
	private java.lang.String subTime;// 关注时间
	private java.lang.String unsubTime;// 取消关注时间
	private String groupCn;// 分组名称

	private WxFansMember wxFansMember = null;// 微信会员信息

	public static final String STATUS_ENABLE = "01";// 正常
	public static final String STATUS_DISABLE = "02";// 注销

	public WxFansMember getWxFansMember() {
		return wxFansMember;
	}

	public void setWxFansMember(WxFansMember wxFansMember) {
		this.wxFansMember = wxFansMember;
	}

	public String getGroupCn() {
		return groupCn;
	}

	public void setGroupCn(String groupCn) {
		this.groupCn = groupCn;
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
	 * 获取昵称属性
	 * 
	 * @return nickName
	 */
	public java.lang.String getNickName() {
		return nickName;
	}

	/**
	 * 设置昵称属性
	 * 
	 * @param nickName
	 */
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	/**
	 * 获取性别属性
	 * 
	 * @return sex
	 */
	public java.lang.String getSex() {
		return sex;
	}

	/**
	 * 设置性别属性
	 * 
	 * @param sex
	 */
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	/**
	 * 获取城市属性
	 * 
	 * @return city
	 */
	public java.lang.String getCity() {
		return city;
	}

	/**
	 * 设置城市属性
	 * 
	 * @param city
	 */
	public void setCity(java.lang.String city) {
		this.city = city;
	}

	/**
	 * 获取国家属性
	 * 
	 * @return country
	 */
	public java.lang.String getCountry() {
		return country;
	}

	/**
	 * 设置国家属性
	 * 
	 * @param country
	 */
	public void setCountry(java.lang.String country) {
		this.country = country;
	}

	/**
	 * 获取省份属性
	 * 
	 * @return province
	 */
	public java.lang.String getProvince() {
		return province;
	}

	/**
	 * 设置省份属性
	 * 
	 * @param province
	 */
	public void setProvince(java.lang.String province) {
		this.province = province;
	}

	/**
	 * 获取语言属性
	 * 
	 * @return language
	 */
	public java.lang.String getLanguage() {
		return language;
	}

	/**
	 * 设置语言属性
	 * 
	 * @param language
	 */
	public void setLanguage(java.lang.String language) {
		this.language = language;
	}

	/**
	 * 获取用户头像属性
	 * 
	 * @return headimgUrl
	 */
	public java.lang.String getHeadimgUrl() {
		return headimgUrl;
	}

	/**
	 * 设置用户头像属性
	 * 
	 * @param headimgUrl
	 */
	public void setHeadimgUrl(java.lang.String headimgUrl) {
		this.headimgUrl = headimgUrl;
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
	 * 获取所属分组属性
	 * 
	 * @return groupId
	 */
	public java.lang.String getGroupId() {
		return groupId;
	}

	/**
	 * 设置所属分组属性
	 * 
	 * @param groupId
	 */
	public void setGroupId(java.lang.String groupId) {
		this.groupId = groupId;
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
	 * 获取关注时间属性
	 * 
	 * @return subTime
	 */
	public java.lang.String getSubTime() {
		return subTime;
	}

	/**
	 * 设置关注时间属性
	 * 
	 * @param subTime
	 */
	public void setSubTime(java.lang.String subTime) {
		this.subTime = subTime;
	}

	/**
	 * 获取取消关注时间属性
	 * 
	 * @return unsubTiime
	 */
	public java.lang.String getUnsubTime() {
		return unsubTime;
	}

	/**
	 * 设置取消关注时间属性
	 * 
	 * @param unsubTiime
	 */
	public void setUnsubTime(java.lang.String unsubTime) {
		this.unsubTime = unsubTime;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxFans");
		sb.append("{fansId=").append(fansId);
		sb.append(", openId=").append(openId);
		sb.append(", nickName=").append(nickName);
		sb.append(", sex=").append(sex);
		sb.append(", city=").append(city);
		sb.append(", country=").append(country);
		sb.append(", province=").append(province);
		sb.append(", language=").append(language);
		sb.append(", headimgUrl=").append(headimgUrl);
		sb.append(", accountId=").append(accountId);
		sb.append(", groupId=").append(groupId);
		sb.append(", status=").append(status);
		sb.append(", subTime=").append(subTime);
		sb.append(", unsubTime=").append(unsubTime);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxFans mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxFans domain = new WxFans();
		domain.setFansId(rs.getString("FANS_ID"));
		domain.setOpenId(rs.getString("OPEN_ID"));
		domain.setNickName(rs.getString("NICK_NAME"));
		domain.setSex(rs.getString("SEX"));
		domain.setCity(rs.getString("CITY"));
		domain.setCountry(rs.getString("COUNTRY"));
		domain.setProvince(rs.getString("PROVINCE"));
		domain.setLanguage(rs.getString("LANGUAGE"));
		domain.setHeadimgUrl(rs.getString("HEADIMG_URL"));
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		domain.setGroupId(rs.getString("GROUP_ID"));
		domain.setStatus(rs.getString("STATUS"));
		domain.setSubTime(rs.getString("SUB_TIME"));
		domain.setUnsubTime(rs.getString("UNSUB_TIME"));
		return domain;
	}
}