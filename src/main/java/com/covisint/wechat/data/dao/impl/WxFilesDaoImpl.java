/************************************************************************************
 * @File name   :      WxFilesDaoImpl.java
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
 * 2014-8-13 14:02:37			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxFilesDao;
import com.covisint.wechat.data.mapper.WxFilesMapper;
import com.covisint.wechat.data.model.WxFiles;
import com.covisint.wechat.page.Page;

/**
 * 文件表数据访问接口
 * 
 */
@Component
public class WxFilesDaoImpl implements WxFilesDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxFiles wxFiles) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fileId", wxFiles.getFileId());
		paramMap.put("attaId", wxFiles.getAttaId());
		paramMap.put("fileName", wxFiles.getFileName());
		paramMap.put("fileSuffix", wxFiles.getFileSuffix());
		paramMap.put("filePath", wxFiles.getFilePath());
		paramMap.put("fileType", wxFiles.getFileType());
		paramMap.put("createTime", wxFiles.getCreateTime());
		paramMap.put("status", wxFiles.getStatus());
		paramMap.put("mediaId", wxFiles.getMediaId());
		paramMap.put("lastUpdate", wxFiles.getLastUpdate());
		return dao.insert(WxFilesMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxFiles wxFiles) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fileId", wxFiles.getFileId());
		paramMap.put("attaId", wxFiles.getAttaId());
		paramMap.put("fileName", wxFiles.getFileName());
		paramMap.put("fileSuffix", wxFiles.getFileSuffix());
		paramMap.put("filePath", wxFiles.getFilePath());
		paramMap.put("fileType", wxFiles.getFileType());
		paramMap.put("createTime", wxFiles.getCreateTime());
		paramMap.put("status", wxFiles.getStatus());
		paramMap.put("mediaId", wxFiles.getMediaId());
		paramMap.put("lastUpdate", wxFiles.getLastUpdate());
		return dao.update(WxFilesMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxFiles get(java.lang.String fileId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fileId", fileId);
		return dao.get(WxFilesMapper.getFindOneSql(paramMap), paramMap, new WxFiles());
	}

	@Override
	public List<WxFiles> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxFilesMapper.getFindAllSql(paramMap), paramMap, new WxFiles());
	}

	@Override
	public Page<WxFiles> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxFilesMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxFiles());
	}

	@Override
	public int[] batchInsert(List<WxFiles> files) {
		int count = files.size();
		SqlParameterSource[] batchArgs = new SqlParameterSource[count];
		for (int i = 0; i < count; i++) {
			WxFiles wxFiles = files.get(i);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("fileId", wxFiles.getFileId());
			paramMap.put("attaId", wxFiles.getAttaId());
			paramMap.put("fileName", wxFiles.getFileName());
			paramMap.put("fileSuffix", wxFiles.getFileSuffix());
			paramMap.put("filePath", wxFiles.getFilePath());
			paramMap.put("fileType", wxFiles.getFileType());
			paramMap.put("createTime", wxFiles.getCreateTime());
			paramMap.put("status", wxFiles.getStatus());
			paramMap.put("mediaId", wxFiles.getMediaId());
			paramMap.put("lastUpdate", wxFiles.getLastUpdate());
			batchArgs[i] = new MapSqlParameterSource(paramMap);
		}
		return dao.getJdbcTemplate().batchUpdate(WxFilesMapper.getInsertSql(null), batchArgs);
	}

	@Override
	public WxFiles getFile(Map<String, Object> paramMap) {
		return dao.get(WxFilesMapper.getFindOneSql(paramMap), paramMap, new WxFiles());
	}

	@Override
	public int updateStatus(Map<String, Object> paramMap) {
		return dao.update(WxFilesMapper.getUpdateStatusSql(paramMap), paramMap);
	}
}