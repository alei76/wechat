/************************************************************************************
 * @File name   :      EventMessageReplyService.java
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
 * 2014-5-20 上午10:25:47			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.message.service;

import java.util.Map;

/**
 * 事件消息回复业务
 */
public interface EventMessageReplyService {
	/**
	 * 获取菜单消息类型回复
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-20
	 */
	public String getMenuMessage(String eventKey, String accountId, String openId, String accountNo, String originalXml, Map<String, String> signature);

	/**
	 * 获取关注事件消息回复
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-20
	 */
	public String getSubscribeMessage(String accountId, String openId, String accountNo, String originalXml, Map<String, String> signature);

	/**
	 * 获取地理位置上报消息回复
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-20
	 */
	public String getLocationMessage(String accountId, String latitude, String longitude, String openId, String accountNo, String originalXml, Map<String, String> signature);
	
	/**
	 * 获取取消关注事件消息回复
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-9-11
	 */
	public String getUnSubscribeMessage(String accountId, String openId, String accountNo, String originalXml, Map<String, String> signature);
}
