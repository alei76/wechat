/************************************************************************************
 * @File name   :      EventViewThread.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-8-5
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
 * 2014-8-5 下午02:46:55			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.weixin.thread;

import com.covisint.wechat.weixin.model.InMessage;
import com.covisint.wechat.weixin.service.EventViewService;

/**
 * 菜单网页类型保存线程
 */
public class EventViewThread implements Runnable {
	private EventViewService eventViewService;
	private InMessage msg;

	public EventViewThread(EventViewService eventViewService, InMessage msg) {
		this.eventViewService = eventViewService;
		this.msg = msg;
	}

	@Override
	public void run() {
		eventViewService.viewMessage(msg);
	}

}
