/************************************************************************************
 * @File name   :      KeyWordReplyService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-20
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
 * 2014-5-20 上午10:54:51			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.message.service;

import java.util.Map;

/**
 * 关键字消息回复业务
 */
public interface KeyWordReplyService {
	/**
	 * 获取关键字消息回复
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-20
	 */
	public String getTextMessage(String keyword, String accountId, String openId, String accountNo, String originalXml, Map<String, String> signature);

	/**
	 * 获取默认消息回复
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-20
	 */
	public String getDefaultMessage(String keyword, String accountId, String openId, String accountNo, String originalXml, Map<String, String> signature);
}
