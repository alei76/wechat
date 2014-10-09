/************************************************************************************
 * @File name   :      WxNewsMessage.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-07-07
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
 * 2014-7-7 17:34:20			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 图文消息表 WX_NEWS_MESSAGE表
 * 
 */
public class WxNewsMessage implements Serializable, RowMapper<WxNewsMessage> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3192378443754282940L;
	private java.lang.String newsMessageId;// 消息标识
	private java.lang.String templateId;// 所属模板
	private java.lang.String title;// 标题
	private java.lang.String description;// 描述
	private java.lang.String attaId;// 附件标识
	private java.lang.String targetHref;// 原文链接
	private java.lang.String msgIndex;// 索引

	/**
	 * 获取消息标识属性
	 * 
	 * @return newsMessageId
	 */
	public java.lang.String getNewsMessageId() {
		return newsMessageId;
	}

	/**
	 * 设置消息标识属性
	 * 
	 * @param newsMessageId
	 */
	public void setNewsMessageId(java.lang.String newsMessageId) {
		this.newsMessageId = newsMessageId;
	}

	/**
	 * 获取所属模板属性
	 * 
	 * @return templateId
	 */
	public java.lang.String getTemplateId() {
		return templateId;
	}

	/**
	 * 设置所属模板属性
	 * 
	 * @param templateId
	 */
	public void setTemplateId(java.lang.String templateId) {
		this.templateId = templateId;
	}

	/**
	 * 获取标题属性
	 * 
	 * @return title
	 */
	public java.lang.String getTitle() {
		return title;
	}

	/**
	 * 设置标题属性
	 * 
	 * @param title
	 */
	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	/**
	 * 获取描述属性
	 * 
	 * @return description
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * 设置描述属性
	 * 
	 * @param description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
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
	 * 获取原文链接属性
	 * 
	 * @return targetHref
	 */
	public java.lang.String getTargetHref() {
		return targetHref;
	}

	/**
	 * 设置原文链接属性
	 * 
	 * @param targetHref
	 */
	public void setTargetHref(java.lang.String targetHref) {
		this.targetHref = targetHref;
	}

	/**
	 * 获取索引属性
	 * 
	 * @return msgIndex
	 */
	public java.lang.String getMsgIndex() {
		return msgIndex;
	}

	/**
	 * 设置索引属性
	 * 
	 * @param msgIndex
	 */
	public void setMsgIndex(java.lang.String msgIndex) {
		this.msgIndex = msgIndex;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxNewsMessage");
		sb.append("{newsMessageId=").append(newsMessageId);
		sb.append(", templateId=").append(templateId);
		sb.append(", title=").append(title);
		sb.append(", description=").append(description);
		sb.append(", attaId=").append(attaId);
		sb.append(", targetHref=").append(targetHref);
		sb.append(", msgIndex=").append(msgIndex);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxNewsMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxNewsMessage domain = new WxNewsMessage();
		domain.setNewsMessageId(rs.getString("NEWS_MESSAGE_ID"));
		domain.setTemplateId(rs.getString("TEMPLATE_ID"));
		domain.setTitle(rs.getString("TITLE"));
		domain.setDescription(rs.getString("DESCRIPTION"));
		domain.setAttaId(rs.getString("ATTA_ID"));
		domain.setTargetHref(rs.getString("TARGET_HREF"));
		domain.setMsgIndex(rs.getString("MSG_INDEX"));
		return domain;
	}
}