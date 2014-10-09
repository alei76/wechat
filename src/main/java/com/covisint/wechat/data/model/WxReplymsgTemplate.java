/************************************************************************************
 * @File name   :      WxReplymsgTemplate.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-30
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
 * 2014-4-30 14:56:07			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * 回复消息模板表 WX_REPLYMSG_TEMPLATE表
 * 
 */
public class WxReplymsgTemplate implements Serializable, RowMapper<WxReplymsgTemplate> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7128838935728787083L;
	private java.lang.String templateId;// 模板标识
	private java.lang.String name;// 名称
	private java.lang.String resourceType;// 资源类型
	private java.lang.String resourceId;// 资源地址
	private java.lang.String type;// 消息类型
	private java.lang.String createBy;// 创建人
	private java.lang.String createTime;// 创建时间
	private java.lang.String status;// 状态
	private java.lang.String accountId;// 所属微信
	private java.lang.String content;// 文本消息内容
	private java.lang.String attaId;// 消息附件

	private String statusCn;// 状态字典中文
	private String resourceTypeCn;// 资源类型字典中文
	private String typeCn;// 消息类型字典中文
	private String resourceName;// 资源名称
	private String resourceStatus;// 资源状态

	private String keyword;// 小i机器人关键字
	private String originalXml;// 微信原始报文
	private Map<String, String> signature;// 微信消息合法性验证

	public static final String RESOURCE_TYPE_DYNAMIC = "01";// 动态资源类型
	public static final String RESOURCE_TYPE_STATIC = "02";// 静态资源类型
	public static final String RESOURCE_TYPE_ROBOTS = "03";// 机器人资源类型

	public static final String STATUS_ENABLE = "01";// 状态可用
	public static final String STATUS_DISABLE = "02";// 状态不可用

	public static final String TYPE_TEXT = "01";// 文本消息
	public static final String TYPE_IMAGE = "02";// 图片消息
	public static final String TYPE_VOICE = "03";// 语音消息
	public static final String TYPE_VIDEO = "04";// 视频消息
	public static final String TYPE_MUSIC = "05";// 音乐消息
	public static final String TYPE_NEWS = "06";// 图文消息

	public Integer itemsCount;// 图文消息条数

	public List<WxNewsMessage> items;// 图文消息项

	public Map<String, String> getSignature() {
		return signature;
	}

	public void setSignature(Map<String, String> signature) {
		this.signature = signature;
	}

	public String getResourceStatus() {
		return resourceStatus;
	}

	public void setResourceStatus(String resourceStatus) {
		this.resourceStatus = resourceStatus;
	}

	public String getOriginalXml() {
		return originalXml;
	}

	public void setOriginalXml(String originalXml) {
		this.originalXml = originalXml;
	}

	public Integer getItemsCount() {
		return itemsCount;
	}

	public void setItemsCount(Integer itemsCount) {
		this.itemsCount = itemsCount;
	}

	public List<WxNewsMessage> getItems() {
		return items;
	}

	public void setItems(List<WxNewsMessage> items) {
		this.items = items;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getStatusCn() {
		return statusCn;
	}

	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}

	public String getResourceTypeCn() {
		return resourceTypeCn;
	}

	public void setResourceTypeCn(String resourceTypeCn) {
		this.resourceTypeCn = resourceTypeCn;
	}

	public String getTypeCn() {
		return typeCn;
	}

	public void setTypeCn(String typeCn) {
		this.typeCn = typeCn;
	}

	/**
	 * 获取模板标识属性
	 * 
	 * @return templateId
	 */
	public java.lang.String getTemplateId() {
		return templateId;
	}

	/**
	 * 设置模板标识属性
	 * 
	 * @param templateId
	 */
	public void setTemplateId(java.lang.String templateId) {
		this.templateId = templateId;
	}

	/**
	 * 获取名称属性
	 * 
	 * @return name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置名称属性
	 * 
	 * @param name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取资源类型属性
	 * 
	 * @return resourceType
	 */
	public java.lang.String getResourceType() {
		return resourceType;
	}

	/**
	 * 设置资源类型属性
	 * 
	 * @param resourceType
	 */
	public void setResourceType(java.lang.String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * 获取资源地址属性
	 * 
	 * @return resourceId
	 */
	public java.lang.String getResourceId() {
		return resourceId;
	}

	/**
	 * 设置资源地址属性
	 * 
	 * @param resourceId
	 */
	public void setResourceId(java.lang.String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * 获取消息类型属性
	 * 
	 * @return type
	 */
	public java.lang.String getType() {
		return type;
	}

	/**
	 * 设置消息类型属性
	 * 
	 * @param type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
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
	 * 获取文本消息内容属性
	 * 
	 * @return content
	 */
	public java.lang.String getContent() {
		return content;
	}

	/**
	 * 设置文本消息内容属性
	 * 
	 * @param content
	 */
	public void setContent(java.lang.String content) {
		this.content = content;
	}

	/**
	 * 获取消息附件属性
	 * 
	 * @return attaId
	 */
	public java.lang.String getAttaId() {
		return attaId;
	}

	/**
	 * 设置消息附件属性
	 * 
	 * @param attaId
	 */
	public void setAttaId(java.lang.String attaId) {
		this.attaId = attaId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxReplymsgTemplate");
		sb.append("{templateId=").append(templateId);
		sb.append(", name=").append(name);
		sb.append(", resourceType=").append(resourceType);
		sb.append(", resourceId=").append(resourceId);
		sb.append(", type=").append(type);
		sb.append(", createBy=").append(createBy);
		sb.append(", createTime=").append(createTime);
		sb.append(", status=").append(status);
		sb.append(", accountId=").append(accountId);
		sb.append(", content=").append(content);
		sb.append(", attaId=").append(attaId);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxReplymsgTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxReplymsgTemplate domain = new WxReplymsgTemplate();
		domain.setTemplateId(rs.getString("TEMPLATE_ID"));
		domain.setName(rs.getString("NAME"));
		domain.setResourceType(rs.getString("RESOURCE_TYPE"));
		domain.setResourceId(rs.getString("RESOURCE_ID"));
		domain.setType(rs.getString("TYPE"));
		domain.setCreateBy(rs.getString("CREATE_BY"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		domain.setStatus(rs.getString("STATUS"));
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		domain.setContent(rs.getString("CONTENT"));
		domain.setAttaId(rs.getString("ATTA_ID"));
		return domain;
	}
}