/************************************************************************************
 * @File name   :      TextMsgThread.java
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
 * 2014-7-17 上午10:31:03			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.weixin.thread;

import com.covisint.wechat.member.service.MessageHisService;
import com.covisint.wechat.weixin.model.InMessage;

/**
 * 文本消息保存线程
 */
public class TextMsgThread implements Runnable {
	private MessageHisService messageHisService;
	private InMessage inMessage;
	private String outXml;

	public TextMsgThread(MessageHisService messageHisService, InMessage inMessage, String outXml) {
		this.messageHisService = messageHisService;
		this.inMessage = inMessage;
		this.outXml = outXml;
	}

	@Override
	public void run() {
		messageHisService.saveMsgReply(inMessage, outXml);
	}

}
