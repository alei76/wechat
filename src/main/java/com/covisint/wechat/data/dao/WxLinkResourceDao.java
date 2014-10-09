/************************************************************************************
 * @File name   :      WxLinkResourceDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-04-29
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
 * 2014-4-29 11:13:42			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxLinkResource;
import com.covisint.wechat.page.Page;

/**
 * 外部资源表数据访问接口
 * 
 */
public interface WxLinkResourceDao {
	public int insert(WxLinkResource wxLinkResource);

	public int update(WxLinkResource wxLinkResource);

	public WxLinkResource get(java.lang.String resourceId);

	public Map<String, Object> findOne(java.lang.String resourceId);

	public List<Map<String, Object>> findList(Map<String, Object> paramMap);

	public List<WxLinkResource> findDomain(Map<String, Object> paramMap);

	public Page<WxLinkResource> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-13
	 * @param resourceId
	 * @return
	 */
	public int changestatus(String resourceId);

	public int checkExists(Map<String, Object> paramMap);

	public int[] updateBatch(String status, List<String> resourceIds);

}