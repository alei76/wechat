package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * WX_USER_ROLES表
 * 
 * @author mew
 * 
 */
public class WxUserRoles implements Serializable, RowMapper<WxUserRoles> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3296960752631469799L;
	private java.lang.String roleId;
	private java.lang.String roleName;
	private java.lang.String roleDesc;
	private java.lang.String createBy;
	private java.lang.String createTime;
	private java.lang.String status;
	private java.lang.String roleType;
	public static final String ROLE_TYPE_SYSTEM = "01";// 系统内置角色
	public static final String ROLE_TYPE_NORMAL = "02";// 用户角色

	public static final String STATUS_ENABLE = "01";// 状态启用
	public static final String STATUS_DISABLE = "02";// 状态禁用

	public java.lang.String getRoleId() {
		return roleId;
	}

	public void setRoleId(java.lang.String roleId) {
		this.roleId = roleId;
	}

	public java.lang.String getRoleName() {
		return roleName;
	}

	public void setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
	}

	public java.lang.String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(java.lang.String roleDesc) {
		this.roleDesc = roleDesc;
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

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getRoleType() {
		return roleType;
	}

	public void setRoleType(java.lang.String roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxUserRoles");
		sb.append("{roleId=").append(roleId);
		sb.append(", roleName=").append(roleName);
		sb.append(", roleDesc=").append(roleDesc);
		sb.append(", createBy=").append(createBy);
		sb.append(", createTime=").append(createTime);
		sb.append(", status=").append(status);
		sb.append(", roleType=").append(roleType);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxUserRoles mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxUserRoles domain = new WxUserRoles();
		domain.setRoleId(rs.getString("ROLE_ID"));
		domain.setRoleName(rs.getString("ROLE_NAME"));
		domain.setRoleDesc(rs.getString("ROLE_DESC"));
		domain.setCreateBy(rs.getString("CREATE_BY"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		domain.setStatus(rs.getString("STATUS"));
		domain.setRoleType(rs.getString("ROLE_TYPE"));
		return domain;
	}
}