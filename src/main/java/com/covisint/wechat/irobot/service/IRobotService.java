/************************************************************************************
 * @File name   :      IRobotService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-5
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
 * 2014-6-5 下午03:26:56			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.irobot.service;

import java.util.Map;

import com.covisint.wechat.exception.WebServiceException;

/**
 * 小i机器人接口Service
 */
public interface IRobotService {
	public static final String MESSAGE_CONTENT = "message_content";
	public static final String MESSAGE_TYPE = "message_type";

	/**
	 * 小i机器人接口方法包装
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-5
	 */
	public Map<String, String> getAnswer(String keyword, String openId, String brand) throws WebServiceException;

}
