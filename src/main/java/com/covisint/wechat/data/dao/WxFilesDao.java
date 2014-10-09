/************************************************************************************
 * @File name   :      WxFilesDao.java
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
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxFiles;
import com.covisint.wechat.page.Page;

/**
 * 文件表数据访问接口
 * 
 */
public interface WxFilesDao {
	public int insert(WxFiles wxFiles);

	public int update(WxFiles wxFiles);

	public WxFiles get(java.lang.String fileId);

	public List<WxFiles> findDomain(Map<String, Object> paramMap);

	public Page<WxFiles> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public int[] batchInsert(List<WxFiles> files);

	public WxFiles getFile(Map<String, Object> paramMap);

	public int updateStatus(Map<String, Object> paramMap);

}