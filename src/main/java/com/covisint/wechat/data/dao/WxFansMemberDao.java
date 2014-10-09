/************************************************************************************
 * @File name   :      WxFansMemberDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-21
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
 * 2014-5-21 16:46:59			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxFansMember;
import com.covisint.wechat.page.Page;

/**
 * 粉丝会员关系表数据访问接口
 * 
 */
public interface WxFansMemberDao {
	public int insert(WxFansMember wxFansMember);

	public int update(WxFansMember wxFansMember);

	public WxFansMember get(java.lang.String fansMemberId);

	public List<WxFansMember> findDomain(Map<String, Object> paramMap);

	public Page<WxFansMember> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public Long checkExists(Map<String, Object> paramMap);

	public int bindMember(Map<String, Object> paramMap);

}