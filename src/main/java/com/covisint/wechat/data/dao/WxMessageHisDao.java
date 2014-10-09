/************************************************************************************
 * @File name   :      WxMessageHisDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-06-16
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
 * 2014-6-16 17:41:27			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxMessageHis;
import com.covisint.wechat.page.Page;

/**
 * 微信消息记录表数据访问接口
 * 
 */
public interface WxMessageHisDao {
	public int insert(WxMessageHis wxMessageHis);

	public int update(WxMessageHis wxMessageHis);

	public WxMessageHis get(java.lang.String messageHisId);

	public List<WxMessageHis> findDomain(Map<String, Object> paramMap);

	public Page<WxMessageHis> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public WxMessageHis getMsgHis(Map<String, Object> paramMap);

	public Page<Map<String, Object>> pageMsgHis(Map<String, Object> paramMap, Integer current, Integer pagesize);

	public Page<Map<String, Object>> pageMsgCollect(Map<String, Object> paramMap, Integer current, Integer pagesize);

}