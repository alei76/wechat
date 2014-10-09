/************************************************************************************
 * @File name   :      WxFiles.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-08-13
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
 * 2014-8-13 15:04:34			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 文件表 WX_FILES表
 * 
 */
public class WxFiles implements Serializable, RowMapper<WxFiles> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7534833023109686904L;
	private java.lang.String fileId;// 文件标识
	private java.lang.String attaId;// 所属媒体
	private java.lang.String fileName;// 文件名
	private java.lang.String fileSuffix;// 文件格式
	private java.lang.String filePath;// 文件路径
	private java.lang.String fileType;// 文件类型
	private java.lang.String createTime;// 创建时间
	private java.lang.String status;// 状态
	private java.lang.String mediaId;// 微信资源标识
	private java.lang.String lastUpdate;// 最后上传日期

	public static final String TYPE_PIC = "01";// 图片
	public static final String TYPE_MEDIA = "02";// 媒体

	public static final String STATUS_ENABLE = "01";// 启用
	public static final String STATUS_DISABLE = "02";// 删除

	/**
	 * 获取文件标识属性
	 * 
	 * @return fileId
	 */
	public java.lang.String getFileId() {
		return fileId;
	}

	/**
	 * 设置文件标识属性
	 * 
	 * @param fileId
	 */
	public void setFileId(java.lang.String fileId) {
		this.fileId = fileId;
	}

	/**
	 * 获取所属媒体属性
	 * 
	 * @return attaId
	 */
	public java.lang.String getAttaId() {
		return attaId;
	}

	/**
	 * 设置所属媒体属性
	 * 
	 * @param attaId
	 */
	public void setAttaId(java.lang.String attaId) {
		this.attaId = attaId;
	}

	/**
	 * 获取文件名属性
	 * 
	 * @return fileName
	 */
	public java.lang.String getFileName() {
		return fileName;
	}

	/**
	 * 设置文件名属性
	 * 
	 * @param fileName
	 */
	public void setFileName(java.lang.String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取文件格式属性
	 * 
	 * @return fileSuffix
	 */
	public java.lang.String getFileSuffix() {
		return fileSuffix;
	}

	/**
	 * 设置文件格式属性
	 * 
	 * @param fileSuffix
	 */
	public void setFileSuffix(java.lang.String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	/**
	 * 获取文件路径属性
	 * 
	 * @return filePath
	 */
	public java.lang.String getFilePath() {
		return filePath;
	}

	/**
	 * 设置文件路径属性
	 * 
	 * @param filePath
	 */
	public void setFilePath(java.lang.String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 获取文件类型属性
	 * 
	 * @return fileType
	 */
	public java.lang.String getFileType() {
		return fileType;
	}

	/**
	 * 设置文件类型属性
	 * 
	 * @param fileType
	 */
	public void setFileType(java.lang.String fileType) {
		this.fileType = fileType;
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
	 * 获取微信资源标识属性
	 * 
	 * @return mediaId
	 */
	public java.lang.String getMediaId() {
		return mediaId;
	}

	/**
	 * 设置微信资源标识属性
	 * 
	 * @param mediaId
	 */
	public void setMediaId(java.lang.String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * 获取最后上传日期属性
	 * 
	 * @return lastUpdate
	 */
	public java.lang.String getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * 设置最后上传日期属性
	 * 
	 * @param lastUpdate
	 */
	public void setLastUpdate(java.lang.String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("WxFiles");
		sb.append("{fileId=").append(fileId);
		sb.append(", attaId=").append(attaId);
		sb.append(", fileName=").append(fileName);
		sb.append(", fileSuffix=").append(fileSuffix);
		sb.append(", filePath=").append(filePath);
		sb.append(", fileType=").append(fileType);
		sb.append(", createTime=").append(createTime);
		sb.append(", status=").append(status);
		sb.append(", mediaId=").append(mediaId);
		sb.append(", lastUpdate=").append(lastUpdate);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public WxFiles mapRow(ResultSet rs, int rowNum) throws SQLException {
		WxFiles domain = new WxFiles();
		domain.setFileId(rs.getString("FILE_ID"));
		domain.setAttaId(rs.getString("ATTA_ID"));
		domain.setFileName(rs.getString("FILE_NAME"));
		domain.setFileSuffix(rs.getString("FILE_SUFFIX"));
		domain.setFilePath(rs.getString("FILE_PATH"));
		domain.setFileType(rs.getString("FILE_TYPE"));
		domain.setCreateTime(rs.getString("CREATE_TIME"));
		domain.setStatus(rs.getString("STATUS"));
		domain.setMediaId(rs.getString("MEDIA_ID"));
		domain.setLastUpdate(rs.getString("LAST_UPDATE"));
		return domain;
	}
}