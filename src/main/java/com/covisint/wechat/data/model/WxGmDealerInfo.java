/************************************************************************************
 * @File name   :      WxGmDealerInfo.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-07-14
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
 * 2014-7-14 16:07:42			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 通用经销商信息
 * WX_GM_DEALER_INFO表
 *
 */
public class WxGmDealerInfo implements Serializable, RowMapper<WxGmDealerInfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6415149405642752602L;
	private java.lang.String dealerId;// 经销商标识
	private java.lang.String code;// 经销商编号
	private java.lang.String name;// 名称
	private java.lang.String province;// 省
	private java.lang.String city;// 市
	private java.lang.String address;// 详细地址
	private java.lang.String tel;// 座机
	private java.lang.String phone;// 电话
	private java.lang.String longitude;// 经度
	private java.lang.String latitude;// 纬度

	/**
     * 获取经销商标识属性
     *
     * @return dealerId
     */
	public java.lang.String getDealerId() {
		return dealerId;
	}
	
	/**
	 * 设置经销商标识属性
	 *
	 * @param dealerId
	 */
	public void setDealerId(java.lang.String dealerId) {
		this.dealerId = dealerId;
	}
	
	/**
     * 获取经销商编号属性
     *
     * @return code
     */
	public java.lang.String getCode() {
		return code;
	}
	
	/**
	 * 设置经销商编号属性
	 *
	 * @param code
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
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
     * 获取省属性
     *
     * @return province
     */
	public java.lang.String getProvince() {
		return province;
	}
	
	/**
	 * 设置省属性
	 *
	 * @param province
	 */
	public void setProvince(java.lang.String province) {
		this.province = province;
	}
	
	/**
     * 获取市属性
     *
     * @return city
     */
	public java.lang.String getCity() {
		return city;
	}
	
	/**
	 * 设置市属性
	 *
	 * @param city
	 */
	public void setCity(java.lang.String city) {
		this.city = city;
	}
	
	/**
     * 获取详细地址属性
     *
     * @return address
     */
	public java.lang.String getAddress() {
		return address;
	}
	
	/**
	 * 设置详细地址属性
	 *
	 * @param address
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	/**
     * 获取座机属性
     *
     * @return tel
     */
	public java.lang.String getTel() {
		return tel;
	}
	
	/**
	 * 设置座机属性
	 *
	 * @param tel
	 */
	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}
	
	/**
     * 获取电话属性
     *
     * @return phone
     */
	public java.lang.String getPhone() {
		return phone;
	}
	
	/**
	 * 设置电话属性
	 *
	 * @param phone
	 */
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	
	/**
     * 获取经度属性
     *
     * @return longitude
     */
	public java.lang.String getLongitude() {
		return longitude;
	}
	
	/**
	 * 设置经度属性
	 *
	 * @param longitude
	 */
	public void setLongitude(java.lang.String longitude) {
		this.longitude = longitude;
	}
	
	/**
     * 获取纬度属性
     *
     * @return latitude
     */
	public java.lang.String getLatitude() {
		return latitude;
	}
	
	/**
	 * 设置纬度属性
	 *
	 * @param latitude
	 */
	public void setLatitude(java.lang.String latitude) {
		this.latitude = latitude;
	}
	

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("WxGmDealerInfo");
        sb.append("{dealerId=").append(dealerId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", address=").append(address);
        sb.append(", tel=").append(tel);
        sb.append(", phone=").append(phone);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
		sb.append('}');
        return sb.toString();
    }
    
    @Override
	public WxGmDealerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxGmDealerInfo domain = new WxGmDealerInfo();
		domain.setDealerId(rs.getString("DEALER_ID"));
		domain.setCode(rs.getString("CODE"));
		domain.setName(rs.getString("NAME"));
		domain.setProvince(rs.getString("PROVINCE"));
		domain.setCity(rs.getString("CITY"));
		domain.setAddress(rs.getString("ADDRESS"));
		domain.setTel(rs.getString("TEL"));
		domain.setPhone(rs.getString("PHONE"));
		domain.setLongitude(rs.getString("LONGITUDE"));
		domain.setLatitude(rs.getString("LATITUDE"));
		return domain;
	}
}