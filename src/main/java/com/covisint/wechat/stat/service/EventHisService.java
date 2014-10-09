/************************************************************************************
 * @File name   :      EventHisService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-4
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
 * 2014-6-4 上午10:10:23			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.stat.service;

import com.covisint.wechat.data.model.WxEventHis;
import com.covisint.wechat.weixin.model.InMessage;

/**
 * 事件消息历史业务
 */
public interface EventHisService {
	public WxEventHis getEventHis(String openId, String accountId, String createTime);

	public boolean checkExists(InMessage msg);

	public boolean saveEvent(InMessage msg);
}
