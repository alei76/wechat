/************************************************************************************
 * @File name   :      EventMsgThread.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-7-17
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
 * 2014-7-17 上午10:25:55			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.weixin.thread;

import com.covisint.wechat.stat.service.EventHisService;
import com.covisint.wechat.weixin.model.InMessage;

/**
 * 事件消息保存线程
 */
public class EventMsgThread implements Runnable {
	private EventHisService eventHisService;
	private InMessage inMessage;

	public EventMsgThread(EventHisService eventHisService, InMessage inMessage) {
		this.eventHisService = eventHisService;
		this.inMessage = inMessage;
	}

	@Override
	public void run() {
		String event = inMessage.getEvent();// 事件类型
		if (event.equals("subscribe") || event.equals("CLICK") || event.equals("unsubscribe")) {// 订阅，取消订阅，菜单事件类型
			eventHisService.saveEvent(inMessage);
		}
	}

}
