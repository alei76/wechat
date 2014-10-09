/************************************************************************************
 * @File name   :      WxMessageHis.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-06-17
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
 * 2014-6-17 9:39:25			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 微信消息记录表 WX_MESSAGE_HIS表
 * 
 */
public class WxMessageHis implements Serializable, RowMapper<WxMessageHis> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5470312067822245958L;
	private java.lang.String messageHisId;// 消息标识
	private java.lang.String openId;// 发送人
	private java.lang.String receiver;// 接收人
	private java.lang.String accountId;// 所属微信
	private java.lang.String createTime;// 发送时间
	private java.lang.String msgType;// 消息类型
	private java.lang.String content;// 消息内容
	private java.lang.String attaId;// 附件标识
	private java.lang.String localTime;// 本地时间
	private java.lang.String msgSource;// 消息来源
	private java.lang.String msgId;// 消息标识
	private java.lang.String replyMsgId;// 回复消息标识

	public static final String SOURCE_USER = "01";// 用户
	public static final String SOURCE_IROBOT = "02";// 机器人
	public static final String SOURCE_TEMPLATE = "03";// 系统模板
	public static final String SOURCE_SERVER = "04";// 客服
	public static final String SOURCE_WEBSERVICE = "05";// 外部接口

	/**
	 * 获取消息标识属性
	 * 
	 * @return messageHisId
	 */
	public java.lang.String getMessageHisId() {
		return messageHisId;
	}

	/**
	 * 设置消息标识属性
	 * 
	 * @param messageHisId
	 */
	public void setMessageHisId(java.lang.String messageHisId) {
		this.messageHisId = messageHisId;
	}

	/**
	 * 获取发送人属性
	 * 
	 * @return openId
	 */
	public java.lang.String getOpenId() {
		return openId;
	}

	/**
	 * 设置发送人属性
	 * 
	 * @param openId
	 */
	public void setOpenId(java.lang.String openId) {
		this.openId = openId;
	}

	/**
	 * 获取接收人属性
	 * 
	 * @return receiver
	 */
	public java.lang.String getReceiver() {
		return receiver;
	}

	/**
	 * 设置接收人属性
	 * 
	 * @param receiver
	 */
	public void setReceiver(java.lang.String receiver) {
		this.receiver = receiver;
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
	 * 获取发送时间属性
	 * 
	 * @return createTime
	 */
	public java.lang.String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置发送时间属性
	 * 
	 * @param createTime
	 */
	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取消息类型属性
	 * 
	 * @return msgType
	 */
	public java.lang.String getMsgType() {
		return msgType;
	}

	/**
	 * 设置消息类型属性
	 * 
	 * @param msgType
	 */
	public void setMsgType(java.lang.String msgType) {
		this.msgType = msgType;
	}

	/**
	 * 获取消息内容属性
	 * 
	 * @return content
	 */
	public java.lang.String getContent() {
		return content;
	}

	/**
	 * 设置消息内容属性
	 * 
	 * @param content
	 */
	public void setContent(java.lang.String content) {
		this.content = content;
	}

	/**
	 * 获取附件标识属性
	 * 
	 * @return attaId
	 */
	public java.lang.String getAttaId() {
		return attaId;
	}

	/**
	 * 设置附件标识属性
	 * 
	 * @param attaId
	 */
	public void setAttaId(java.lang.String attaId) {
		this.attaId = attaId;
	}

	/**
	 * 获取本地时间属性
	 * 
	 * @return localTime
	 */
	public java.lang.String getLocalTime() {
		return localTime;
	}

	/**
	 * 设置本地时间属性
	 * 
	 * @param localTime
	 */
	public void setLocalTime(java.lang.String localTime) {
		this.localTime = localTime;
	}

	/**
	 * 获取消息来源属性
	 * 
	 * @return msgSource
	 */
	public java.lang.String getMsgSource() {
		return msgSource;
	}

	/**
	 * 设置消息来源属性
	 * 
	 * @param msgSource
	 */
	public void setMsgSource(java.lang.String msgSource) {
		this.msgSource = msgSource;
	}

	/**
	 * 获取消息标识属性
	 * 
	 * @return msgId
	 */
	public java.lang.String getMsgId() {
		return msgId;
	}

	/**
	 * 设置消息标识属性
	 * 
	 * @param msgId
	 */
	public void setMsgId(java.lang.String msgId) {
		this.msgId = msgId;
	}

	/**
	 * 获取回复消息标识属性
	 * 
	 * @return replyMsgId
	 */
	public java.lang.String getReplyMsgId() {
		return replyMsgId;
	}

	/**
	 * 设置回复消息标识属性
	 * 
	 * @param replyMsgId
	 */
	public void setReplyMsgId(java.lang.String replyMsgId) {
		this.replyMsgId = replyMsgId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxMessageHis");
		sb.append("{messageHisId=").append(messageHisId);
		sb.append(", openId=").append(openId);
		sb.append(", receiver=").append(receiver);
		sb.append(", accountId=").append(accountId);
		sb.append(", createTime=").append(createTime);
		sb.append(", msgType=").append(msgType);
		sb.append(", content=").append(content);
		sb.append(", attaId=").append(attaId);
		sb.append(", localTime=").append(localTime);
		sb.append(", msgSource=").append(msgSource);
		sb.append(", msgId=").append(msgId);
		sb.append(", replyMsgId=").append(replyMsgId);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxMessageHis mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxMessageHis domain = new WxMessageHis();
		domain.setMessageHisId(rs.getString("MESSAGE_HIS_ID"));
		domain.setOpenId(rs.getString("OPEN_ID"));
		domain.setReceiver(rs.getString("RECEIVER"));
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		domain.setMsgType(rs.getString("MSG_TYPE"));
		domain.setContent(rs.getString("CONTENT"));
		domain.setAttaId(rs.getString("ATTA_ID"));
		domain.setLocalTime(rs.getString("LOCAL_TIME"));
		domain.setMsgSource(rs.getString("MSG_SOURCE"));
		domain.setMsgId(rs.getString("MSG_ID"));
		domain.setReplyMsgId(rs.getString("REPLY_MSG_ID"));
		return domain;
	}
}