/************************************************************************************
 * @File name   :      UUIDGenerator.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-25
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
 * 2014-4-25 上午08:55:24			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.identifier;

import java.io.Serializable;
import java.util.UUID;

/**
 * ID生成接口定义实现类
 */
public class UUIDGenerator implements Generator {
	/**
	 * 使用UUID生成
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 * 
	 */
	@Override
	public Serializable generate() {
		return UUID.randomUUID();
	}
}
