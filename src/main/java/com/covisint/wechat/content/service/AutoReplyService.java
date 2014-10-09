/************************************************************************************
 * @File name   :      AutoReplyService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-9
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
 * 2014-5-9 下午04:36:19			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.service;

import java.util.Map;

import com.covisint.wechat.data.model.WxReplyMsg;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;

/**
 *
 */
public interface AutoReplyService {

	/**
	 * 分页查询自动回复
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-9
	 */
	public Page<WxReplyMsg> pageAutoReply(Map<String, Object> paramMap, Integer current, Integer pagesize);

	/**
	 * 保存事件类消息回复
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-12
	 */
	public boolean saveBehaviorMsg(WxReplyMsg wxReplyMsg, String userId, String accountId) throws AjaxException;

	/**
	 * 查询事件类消息回复详情
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-12
	 */
	public WxReplyMsg getBehaviorMsg(String tiggerType, String accountId);

	/**
	 * 保存文字消息回复
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-12
	 */
	public boolean saveMsgreply(WxReplyMsg wxReplyMsg, String userId, String accountId) throws AjaxException;

	/**
	 * 查询文字消息回复详情
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-13
	 */
	public WxReplyMsg info(String replyId);

	/**
	 * 
	 * 更改消息回复状态
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-13
	 */
	public boolean changeStatus(String replyId) throws AjaxException;

	/**
	 * 检查关键字回复设置是否存在
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public boolean checkKeyWordExists(String accountId, String keyword);

}
