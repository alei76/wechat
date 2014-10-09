/************************************************************************************
 * @File name   :      WxReplyMsgDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-09
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
 * 2014-5-9 17:34:25			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxReplyMsg;
import com.covisint.wechat.page.Page;

/**
 * 自动回复关系表数据访问接口
 * 
 */
public interface WxReplyMsgDao {
	public int insert(WxReplyMsg wxReplyMsg);

	public int update(WxReplyMsg wxReplyMsg);

	public WxReplyMsg get(java.lang.String replyId);

	public List<WxReplyMsg> findDomain(Map<String, Object> paramMap);

	public Page<WxReplyMsg> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public WxReplyMsg getBehaviorMsg(String accountId, String tiggerType);

	public int changeStatus(String replyId);

	public int checkExists(Map<String, Object> paramMap);

}