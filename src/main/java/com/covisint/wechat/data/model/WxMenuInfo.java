/************************************************************************************
 * @File name   :      WxMenuInfo.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-07-30
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
 * 2014-7-30 10:57:42			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 微信菜单表 WX_MENU_INFO表
 * 
 */
public class WxMenuInfo implements Serializable, RowMapper<WxMenuInfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7264118895449916910L;
	private java.lang.String menuId;// 菜单标识
	private java.lang.String type;// 菜单类型
	private java.lang.String name;// 菜单名称
	private java.lang.String eventKey;// 微信菜单KEY
	private java.lang.String target;// 对应事件
	private java.lang.String accountId;// 所属微信
	private java.lang.String parentId;// 所属菜单
	private java.lang.String orderBy;// 菜单索引
	private java.lang.String createTime;// 创建时间
	private java.lang.String createBy;// 创建人
	private java.lang.String status;// 状态
	private java.lang.String checkBind;// 验证绑定
	private java.lang.String anonTarget;// 未绑定事件
	private java.lang.String robotKeyword;// 小i关键字
	private java.lang.String anonRobotKeyword;// 小i关键字
	private java.lang.String oauthScope;// 授权作用域
	private java.lang.Integer subCount;// 子菜单数量

	public static final String MENU_TYPE_EVENT = "02";// 消息
	public static final String MENU_TYPE_WEBSITE = "01";// 网页

	public static final String STATUS_ENABLE = "01";// 启用
	public static final String STATUS_DISABLE = "02";// 删除

	public static final String ROOT_PARNET_MENUID = "0";// 页面根菜单ID

	public static final String CHECK_BIND_ENABLE = "01";// 检查绑定
	public static final String CHECK_BIND_DISABLE = "02";// 忽略检查绑定

	public static final String SCOPE_SNSAPI_BASE = "01";// 授权作用域-基础
	public static final String SCOPE_SNSAPI_USERINFO = "02";// 授权作用域-全部
	public static final String SCOPE_NO = "03";// 不适用oauth2.0菜单

	public java.lang.Integer getSubCount() {
		return subCount;
	}

	public void setSubCount(java.lang.Integer subCount) {
		this.subCount = subCount;
	}

	/**
	 * 获取菜单标识属性
	 * 
	 * @return menuId
	 */
	public java.lang.String getMenuId() {
		return menuId;
	}

	/**
	 * 设置菜单标识属性
	 * 
	 * @param menuId
	 */
	public void setMenuId(java.lang.String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 获取菜单类型属性
	 * 
	 * @return type
	 */
	public java.lang.String getType() {
		return type;
	}

	/**
	 * 设置菜单类型属性
	 * 
	 * @param type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 获取菜单名称属性
	 * 
	 * @return name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置菜单名称属性
	 * 
	 * @param name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取微信菜单KEY属性
	 * 
	 * @return eventKey
	 */
	public java.lang.String getEventKey() {
		return eventKey;
	}

	/**
	 * 设置微信菜单KEY属性
	 * 
	 * @param eventKey
	 */
	public void setEventKey(java.lang.String eventKey) {
		this.eventKey = eventKey;
	}

	/**
	 * 获取对应事件属性
	 * 
	 * @return target
	 */
	public java.lang.String getTarget() {
		return target;
	}

	/**
	 * 设置对应事件属性
	 * 
	 * @param target
	 */
	public void setTarget(java.lang.String target) {
		this.target = target;
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
	 * 获取所属菜单属性
	 * 
	 * @return parentId
	 */
	public java.lang.String getParentId() {
		return parentId;
	}

	/**
	 * 设置所属菜单属性
	 * 
	 * @param parentId
	 */
	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取菜单索引属性
	 * 
	 * @return orderBy
	 */
	public java.lang.String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置菜单索引属性
	 * 
	 * @param orderBy
	 */
	public void setOrderBy(java.lang.String orderBy) {
		this.orderBy = orderBy;
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
	 * 获取验证绑定属性
	 * 
	 * @return checkBind
	 */
	public java.lang.String getCheckBind() {
		return checkBind;
	}

	/**
	 * 设置验证绑定属性
	 * 
	 * @param checkBind
	 */
	public void setCheckBind(java.lang.String checkBind) {
		this.checkBind = checkBind;
	}

	/**
	 * 获取未绑定事件属性
	 * 
	 * @return anonTarget
	 */
	public java.lang.String getAnonTarget() {
		return anonTarget;
	}

	/**
	 * 设置未绑定事件属性
	 * 
	 * @param anonTarget
	 */
	public void setAnonTarget(java.lang.String anonTarget) {
		this.anonTarget = anonTarget;
	}

	/**
	 * 获取小i关键字属性
	 * 
	 * @return robotKeyword
	 */
	public java.lang.String getRobotKeyword() {
		return robotKeyword;
	}

	/**
	 * 设置小i关键字属性
	 * 
	 * @param robotKeyword
	 */
	public void setRobotKeyword(java.lang.String robotKeyword) {
		this.robotKeyword = robotKeyword;
	}

	/**
	 * 获取小i关键字属性
	 * 
	 * @return anonRobotKeyword
	 */
	public java.lang.String getAnonRobotKeyword() {
		return anonRobotKeyword;
	}

	/**
	 * 设置小i关键字属性
	 * 
	 * @param anonRobotKeyword
	 */
	public void setAnonRobotKeyword(java.lang.String anonRobotKeyword) {
		this.anonRobotKeyword = anonRobotKeyword;
	}

	/**
	 * 获取授权作用域属性
	 * 
	 * @return oauthScope
	 */
	public java.lang.String getOauthScope() {
		return oauthScope;
	}

	/**
	 * 设置授权作用域属性
	 * 
	 * @param oauthScope
	 */
	public void setOauthScope(java.lang.String oauthScope) {
		this.oauthScope = oauthScope;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxMenuInfo");
		sb.append("{menuId=").append(menuId);
		sb.append(", type=").append(type);
		sb.append(", name=").append(name);
		sb.append(", eventKey=").append(eventKey);
		sb.append(", target=").append(target);
		sb.append(", accountId=").append(accountId);
		sb.append(", parentId=").append(parentId);
		sb.append(", orderBy=").append(orderBy);
		sb.append(", createTime=").append(createTime);
		sb.append(", createBy=").append(createBy);
		sb.append(", status=").append(status);
		sb.append(", checkBind=").append(checkBind);
		sb.append(", anonTarget=").append(anonTarget);
		sb.append(", robotKeyword=").append(robotKeyword);
		sb.append(", anonRobotKeyword=").append(anonRobotKeyword);
		sb.append(", oauthScope=").append(oauthScope);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxMenuInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxMenuInfo domain = new WxMenuInfo();
		domain.setMenuId(rs.getString("MENU_ID"));
		domain.setType(rs.getString("TYPE"));
		domain.setName(rs.getString("NAME"));
		domain.setEventKey(rs.getString("EVENT_KEY"));
		domain.setTarget(rs.getString("TARGET"));
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		domain.setParentId(rs.getString("PARENT_ID"));
		domain.setOrderBy(rs.getString("ORDER_BY"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		domain.setCreateBy(rs.getString("CREATE_BY"));
		domain.setStatus(rs.getString("STATUS"));
		domain.setCheckBind(rs.getString("CHECK_BIND"));
		domain.setAnonTarget(rs.getString("ANON_TARGET"));
		domain.setRobotKeyword(rs.getString("ROBOT_KEYWORD"));
		domain.setAnonRobotKeyword(rs.getString("ANON_ROBOT_KEYWORD"));
		domain.setOauthScope(rs.getString("OAUTH_SCOPE"));
		return domain;
	}
}