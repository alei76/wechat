/************************************************************************************
 * @File name   :      WeChatException.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-7
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
 * 2014-5-7 上午09:50:20			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.exception;

/**
 * 微信接口自定义异常定义
 */
public class WeChatException extends RuntimeException {

	private static final long serialVersionUID = 2346424238567869022L;

	public WeChatException() {
		super();
	}

	public WeChatException(String message, Throwable cause) {
		super(message, cause);
	}

	public WeChatException(String message) {
		super(message);
	}

	public WeChatException(Throwable cause) {
		super(cause);
	}

}
