/************************************************************************************
 * @File name   :      MessagePrevService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-30
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
 * 2014-6-30 下午01:53:46			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.kernel.service;

import com.covisint.wechat.data.model.WxMessageHis;

/**
 *
 */
public interface MessagePrevService {
	/**
	 * 消息预览
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public WxMessageHis prevMessage(String templateId, String keyword, String openId, String accountNo, String accountId);
}
