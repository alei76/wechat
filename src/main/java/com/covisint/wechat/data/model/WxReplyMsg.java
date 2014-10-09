/************************************************************************************
 * @File name   :      WxReplyMsg.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-09
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
 * 2014-5-9 17:34:25			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 自动回复关系表 WX_REPLY_MSG表
 * 
 */
public class WxReplyMsg implements Serializable, RowMapper<WxReplyMsg> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -277109769989062914L;
	private java.lang.String replyId;// 回复标识
	private java.lang.String accountId;// 所属微信
	private java.lang.String keyword;// 关键词
	private java.lang.String templateId;// 回复模板
	private java.lang.String tiggerType;// 触发类型
	private java.lang.String status;// 状态
	private java.lang.String createBy;// 创建人
	private java.lang.String createTime;// 创建时间
	private java.lang.String checkBind;// 验证绑定
	private java.lang.String anonTemplateId;// 未绑定回复模板

	public static final String TIGGER_TYPE_SUBSCRIBE = "01";// 添加关注
	public static final String TIGGER_TYPE_LOCATION = "02";// 地理位置
	public static final String TIGGER_TYPE_KEYWORD = "03";// 关键字
	public static final String TIGGER_TYPE_DEFAULT = "04";// 默认回复
	public static final String TIGGER_TYPE_UNSUBSCRIBE = "05";// 取消关注

	public static final String STATUS_ENABLE = "01";// 启用
	public static final String STATUS_DISABLE = "02";// 禁用

	public static final String CHECK_BIND_ENABLE = "01";// 检查绑定
	public static final String CHECK_BIND_DISABLE = "02";// 忽略检查绑定

	private String statusCn;// 状态字典中文
	private String tiggerTypeCn;// 触发类型字典中文
	private String checkBindCn;// 验证绑定字典中文
	private String templateCn;// 回复模板名称
	private String anonTemplateCn;// 未绑定回复模板名称

	public String getStatusCn() {
		return statusCn;
	}

	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}

	public String getTiggerTypeCn() {
		return tiggerTypeCn;
	}

	public void setTiggerTypeCn(String tiggerTypeCn) {
		this.tiggerTypeCn = tiggerTypeCn;
	}

	public String getCheckBindCn() {
		return checkBindCn;
	}

	public void setCheckBindCn(String checkBindCn) {
		this.checkBindCn = checkBindCn;
	}

	public String getTemplateCn() {
		return templateCn;
	}

	public void setTemplateCn(String templateCn) {
		this.templateCn = templateCn;
	}

	public String getAnonTemplateCn() {
		return anonTemplateCn;
	}

	public void setAnonTemplateCn(String anonTemplateCn) {
		this.anonTemplateCn = anonTemplateCn;
	}

	/**
	 * 获取回复标识属性
	 * 
	 * @return replyId
	 */
	public java.lang.String getReplyId() {
		return replyId;
	}

	/**
	 * 设置回复标识属性
	 * 
	 * @param replyId
	 */
	public void setReplyId(java.lang.String replyId) {
		this.replyId = replyId;
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
	 * 获取关键词属性
	 * 
	 * @return keyword
	 */
	public java.lang.String getKeyword() {
		return keyword;
	}

	/**
	 * 设置关键词属性
	 * 
	 * @param keyword
	 */
	public void setKeyword(java.lang.String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 获取回复模板属性
	 * 
	 * @return templateId
	 */
	public java.lang.String getTemplateId() {
		return templateId;
	}

	/**
	 * 设置回复模板属性
	 * 
	 * @param templateId
	 */
	public void setTemplateId(java.lang.String templateId) {
		this.templateId = templateId;
	}

	/**
	 * 获取触发类型属性
	 * 
	 * @return tiggerType
	 */
	public java.lang.String getTiggerType() {
		return tiggerType;
	}

	/**
	 * 设置触发类型属性
	 * 
	 * @param tiggerType
	 */
	public void setTiggerType(java.lang.String tiggerType) {
		this.tiggerType = tiggerType;
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
	 * 获取未绑定回复模板属性
	 * 
	 * @return anonTemplateId
	 */
	public java.lang.String getAnonTemplateId() {
		return anonTemplateId;
	}

	/**
	 * 设置未绑定回复模板属性
	 * 
	 * @param anonTemplateId
	 */
	public void setAnonTemplateId(java.lang.String anonTemplateId) {
		this.anonTemplateId = anonTemplateId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxReplyMsg");
		sb.append("{replyId=").append(replyId);
		sb.append(", accountId=").append(accountId);
		sb.append(", keyword=").append(keyword);
		sb.append(", templateId=").append(templateId);
		sb.append(", tiggerType=").append(tiggerType);
		sb.append(", status=").append(status);
		sb.append(", createBy=").append(createBy);
		sb.append(", createTime=").append(createTime);
		sb.append(", checkBind=").append(checkBind);
		sb.append(", anonTemplateId=").append(anonTemplateId);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxReplyMsg mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxReplyMsg domain = new WxReplyMsg();
		domain.setReplyId(rs.getString("REPLY_ID"));
		domain.setAccountId(rs.getString("ACCOUNT_ID"));
		domain.setKeyword(rs.getString("KEYWORD"));
		domain.setTemplateId(rs.getString("TEMPLATE_ID"));
		domain.setTiggerType(rs.getString("TIGGER_TYPE"));
		domain.setStatus(rs.getString("STATUS"));
		domain.setCreateBy(rs.getString("CREATE_BY"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		domain.setCheckBind(rs.getString("CHECK_BIND"));
		domain.setAnonTemplateId(rs.getString("ANON_TEMPLATE_ID"));
		return domain;
	}
}