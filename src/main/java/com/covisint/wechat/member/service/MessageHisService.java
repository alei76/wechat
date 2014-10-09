/************************************************************************************
 * @File name   :      MessageHisService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-17
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
 * 2014-6-17 上午09:49:02			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.member.service;

import java.util.Map;

import com.covisint.wechat.data.model.WxMessageHis;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.weixin.model.InMessage;

/**
 * 消息历史Service
 */
public interface MessageHisService {
	/**
	 * 检查是否存在该消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public boolean checkExists(String msgId);

	/**
	 * 获取该消息历史信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public WxMessageHis getMsgByMsgId(String msgId);

	/**
	 * 根据参数拼装消息历史对象
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public WxMessageHis getOutMsgHis(String outMsgXml, String accountId, String toUser, String fromUser, String msgId);

	/**
	 * 保存消息回复历史信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public void saveMsgReply(InMessage inMessage, String outMessage);

	/**
	 * 分页显示消息记录
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public Page<Map<String, Object>> pageMsgHis(Map<String, Object> paramMap, Integer current, Integer pagesize);

	/**
	 * 消息汇总分页查询
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public Page<Map<String, Object>> pageMsgCollect(Map<String, Object> paramMap, Integer current, Integer pagesize);
}
