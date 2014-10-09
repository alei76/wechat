package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * WX_DATA_DICTIONARY表
 * 
 * @author mew
 * 
 */
public class WxDataDictionary implements Serializable, RowMapper<WxDataDictionary> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4049695209808924640L;
	private java.lang.String catalogCode;
	private java.lang.String catalogDesc;
	private java.lang.String itemCode;
	private java.lang.String itemDesc;
	private java.lang.Short itemOrder;
	private java.lang.String enabled;

	public static final String DICT_ENABLE = "01";// 启用
	public static final String DICT_DISABLE = "02";// 禁用

	public java.lang.String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(java.lang.String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public java.lang.String getCatalogDesc() {
		return catalogDesc;
	}

	public void setCatalogDesc(java.lang.String catalogDesc) {
		this.catalogDesc = catalogDesc;
	}

	public java.lang.String getItemCode() {
		return itemCode;
	}

	public void setItemCode(java.lang.String itemCode) {
		this.itemCode = itemCode;
	}

	public java.lang.String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(java.lang.String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public java.lang.Short getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(java.lang.Short itemOrder) {
		this.itemOrder = itemOrder;
	}

	public java.lang.String getEnabled() {
		return enabled;
	}

	public void setEnabled(java.lang.String enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxDataDictionary");
		sb.append("{catalogCode=").append(catalogCode);
		sb.append(", catalogDesc=").append(catalogDesc);
		sb.append(", itemCode=").append(itemCode);
		sb.append(", itemDesc=").append(itemDesc);
		sb.append(", itemOrder=").append(itemOrder);
		sb.append(", enabled=").append(enabled);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxDataDictionary mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxDataDictionary domain = new WxDataDictionary();
		domain.setCatalogCode(rs.getString("CATALOG_CODE"));
		domain.setCatalogDesc(rs.getString("CATALOG_DESC"));
		domain.setItemCode(rs.getString("ITEM_CODE"));
		domain.setItemDesc(rs.getString("ITEM_DESC"));
		domain.setItemOrder(rs.getShort("ITEM_ORDER"));
		domain.setEnabled(rs.getString("ENABLED"));
		return domain;
	}
}