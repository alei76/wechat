/************************************************************************************
 * @File name   :      Generator.java
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

/**
 * ID生成接口定义
 */
public interface Generator {
	/**
	 * 获取ID
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public Serializable generate();

}
