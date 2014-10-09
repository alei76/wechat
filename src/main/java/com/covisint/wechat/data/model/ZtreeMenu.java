package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ZtreeMenu implements Serializable, RowMapper<ZtreeMenu> {
	private String menuId;
	private String menuName;
	private String menuDesc;
	private String parentId;
	private String menuUrl;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5420345559383254705L;

	@Override
	public ZtreeMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
		ZtreeMenu domain = new ZtreeMenu();
		domain.setMenuId(rs.getString("MENU_ID"));
		domain.setMenuName(rs.getString("MENU_NAME"));
		domain.setMenuDesc(rs.getString("MENU_DESC"));
		domain.setParentId(rs.getString("PARENT_ID"));
		domain.setMenuUrl(rs.getString("MENU_URL"));
		return domain;
	}
}
