/************************************************************************************
 * @File name   :      WxFansDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-15
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
 * 2014-5-15 15:09:37			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxFans;
import com.covisint.wechat.page.Page;

/**
 * 微信粉丝表数据访问接口
 * 
 */
public interface WxFansDao {
	public int insert(WxFans wxFans);

	public int update(WxFans wxFans);

	public WxFans get(java.lang.String fansId);

	public List<WxFans> findDomain(Map<String, Object> paramMap);

	public Page<WxFans> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public int[] batchInsert(List<String> openList, String accountId, String groupId);

	public int changeFansGroup(Map<String, Object> paramMap);

	public int[] changeGroup(String groupId, List<String> fansIdList);

	public WxFans get(String openId, String accountId);

	public int unsubscribe(WxFans wxFans);

	public WxFans info(String fansId);

	public List<String> getNewOpenIds(List<String> openIds, String accountId);

	public List<String> listProv(Map<String, Object> paramMap);
}