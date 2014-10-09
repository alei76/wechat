package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * WX_SYSTEM_USER表
 * 
 * @author mew
 * 
 */
public class WxSystemUser implements Serializable, RowMapper<WxSystemUser> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 60547128621598419L;
	private java.lang.String userId;
	private java.lang.String userName;
	private java.lang.String password;
	private java.lang.String fullName;
	private java.lang.String status;
	private java.lang.String userType;
	private java.lang.String createBy;
	private java.lang.String createTime;
	private java.lang.String currentAccount;
	private java.util.List<WxWechatAccount> accountList;

	public static final String ADMIN_TYPE_SYSTEM = "01";// 系统内置用户
	public static final String ADMIN_TYPE_NORMAL = "02";// 普通用户

	public static final String ADMIN_STATUS_ENABLE = "01";// 状态启用
	public static final String ADMIN_STATUS_DISABLE = "02";// 状态禁用

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getFullName() {
		return fullName;
	}

	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getUserType() {
		return userType;
	}

	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}

	public java.lang.String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.util.List<WxWechatAccount> getAccountList() {
		return accountList;
	}

	public void setAccountList(java.util.List<WxWechatAccount> accountList) {
		this.accountList = accountList;
	}

	public java.lang.String getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(java.lang.String currentAccount) {
		this.currentAccount = currentAccount;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxSystemUser");
		sb.append("{userId=").append(userId);
		sb.append(", userName=").append(userName);
		sb.append(", password=").append(password);
		sb.append(", fullName=").append(fullName);
		sb.append(", status=").append(status);
		sb.append(", userType=").append(userType);
		sb.append(", createBy=").append(createBy);
		sb.append(", createTime=").append(createTime);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxSystemUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxSystemUser domain = new WxSystemUser();
		domain.setUserId(rs.getString("USER_ID"));
		domain.setUserName(rs.getString("USER_NAME"));
		domain.setPassword(rs.getString("PASSWORD"));
		domain.setFullName(rs.getString("FULL_NAME"));
		domain.setStatus(rs.getString("STATUS"));
		domain.setUserType(rs.getString("USER_TYPE"));
		domain.setCreateBy(rs.getString("CREATE_BY"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		return domain;
	}
}