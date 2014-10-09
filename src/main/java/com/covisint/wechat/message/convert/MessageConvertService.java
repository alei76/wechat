/************************************************************************************
 * @File name   :      MessageConvertService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-30
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
 * 2014-5-30 上午10:09:06			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.message.convert;

import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.exception.MessageException;

/**
 * 消息转换处理类
 */
public interface MessageConvertService {
	/**
	 * 根据消息模板拼装消息内容
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getMessage(WxReplymsgTemplate template, String openId, String accountNo, String accountId) throws MessageException;
}