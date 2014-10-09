/************************************************************************************
 * @File name   :      WxMediaAttaDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-07
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
 * 2014-5-7 15:19:25			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxMediaAtta;
import com.covisint.wechat.page.Page;

/**
 * 富媒体文件表数据访问接口
 *
 */
public interface WxMediaAttaDao {
    public int insert(WxMediaAtta wxMediaAtta);
    
    public int update(WxMediaAtta wxMediaAtta);
    
	public WxMediaAtta get(java.lang.String attaId);
	
	public List<WxMediaAtta> findDomain(Map<String, Object> paramMap);
	
	public Page<WxMediaAtta> pageDomain(Map<String, Object> paramMap, int current, int pagesize);
	
}