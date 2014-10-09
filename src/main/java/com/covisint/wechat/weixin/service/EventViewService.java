/************************************************************************************
 * @File name   :      EventViewService.java
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
 * 2014-8-5 下午03:20:49			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.weixin.service;

import com.covisint.wechat.weixin.model.InMessage;

/**
 * 菜单网页类型保存Service
 */
public interface EventViewService {
	/**
	 * 保存菜单网页的访问历史
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public void viewMessage(InMessage msg);
}
