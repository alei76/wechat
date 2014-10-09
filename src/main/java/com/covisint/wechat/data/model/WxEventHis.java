/************************************************************************************
 * @File name   :      WxEventHis.java
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
 * 2014-6-4 10:05:47			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 消息事件记录表 WX_EVENT_HIS表
 * 
 */
public class WxEventHis implements Serializable, RowMapper<WxEventHis> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8410717735781470140L;
	private java.lang.String eventHisId;// 事件记录标识
	private java.lang.String eventType;// 事件类型
	private java.lang.String createTime;// 触发时间
	private java.lang.String openId;// 微信号
	private java.lang.String accountId;// 所属微信
	private java.lang.String eventKey;// 事件KEY值
	private java.lang.String msgContent;// 消息文本

	/**
	 * 获取事件记录标识属性
	 * 
	 * @return eventHisId
	 */
	public java.lang.String getEventHisId() {
		return eventHisId;
	}

	/**
	 * 设置事件记录标识属性
	 * 
	 * @param eventHisId
	 */
	public void setEventHisId(java.lang.String eventHisId) {
		this.eventHisId = eventHisId;
	}

	/**
	 * 获取事件类型属性
	 * 
	 * @return eventType
	 */
	public java.lang.String getEventType() {
		return eventType;
	}

	/**
	 * 设置事件类型属性
	 * 
	 * @param eventType
	 */
	public void setEventType(java.lang.String eventType) {
		this.eventType = eventType;
	}

	/**
	 * 获取触发时间属性
	 * 
	 * @return createTime
	 */
	public java.lang.String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置触发时间属性
	 * 
	 * @param createTime
	 */
	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
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
	 * 获取事件KEY值属性
	 * 
	 * @return eventKey
	 */
	public java.lang.String getEventKey() {
		return eventKey;
	}

	/**
	 * 设置事件KEY值属性
	 * 
	 * @param eventKey
	 */
	public void setEventKey(java.lang.String eventKey) {
		this.eventKey = eventKey;
	}

	/**
	 * 获取消息文本属性
	 * 
	 * @return msgContent
	 */
	public java.lang.String getMsgContent() {
		return msgContent;
	}

	/**
	 * 设置消息文本属性
	 * 
	 * @param msgContent
	 */
	public void setMsgContent(java.lang.String msgContent) {
		this.msgContent = msgContent;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxEventHis");
		sb.append("{eventHisId=").append(eventHisId);
		sb.append(", eventType=").append(eventType);
		sb.append(", createTime=").append(createTime);
		sb.append(", openId=").append(openId);
		sb.append(", accountId=").append(accountId);
		sb.append(", eventKey=").append(eventKey);
		sb.append(", msgContent=").append(msgContent);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxEventHis mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxEventHis domain = new WxEventHis();
		domain.setEventHisId(rs.getString("EVENT_HIS_ID"));
		domain.setEventType(rs.getString("EVENT_TYPE"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		domain.setOpenId(rs.getString("OPEN_ID"));
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		domain.setEventKey(rs.getString("EVENT_KEY"));
		domain.setMsgContent(rs.getString("MSG_CONTENT"));
		return domain;
	}
}