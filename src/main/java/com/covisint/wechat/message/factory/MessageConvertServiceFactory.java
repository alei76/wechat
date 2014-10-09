/************************************************************************************
 * @File name   :      MessageConvertServiceFactory.java
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
 * 2014-5-30 上午10:08:32			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.message.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.exception.MessageException;
import com.covisint.wechat.message.convert.MessageConvertService;

/**
 * 消息转换工厂处理
 */
@Component
public class MessageConvertServiceFactory {
	@Autowired
	@Qualifier("staticMessageConvert")
	private MessageConvertService staticMessageConvert;

	@Autowired
	@Qualifier("dynamicMessageConvert")
	private MessageConvertService dynamicMessageConvert;

	@Autowired
	@Qualifier("robotsMessageConvert")
	private MessageConvertService robotsMessageConvert;

	/**
	 * 根据模板类型选用相应消息处理类
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private MessageConvertService getConvertService(WxReplymsgTemplate template) throws MessageException {
		if (template != null) {
			String resourceType = template.getResourceType();
			if (WxReplymsgTemplate.RESOURCE_TYPE_DYNAMIC.equals(resourceType)) {
				return dynamicMessageConvert;
			} else if (WxReplymsgTemplate.RESOURCE_TYPE_STATIC.equals(resourceType)) {
				return staticMessageConvert;
			} else {
				return robotsMessageConvert;
			}
		}
		throw new MessageException("not found convert");
	}

	/**
	 * 消息模板处理
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String convertTemplate(WxReplymsgTemplate template, String openId, String accountNo, String accountId) throws MessageException {
		MessageConvertService service = this.getConvertService(template);
		return service.getMessage(template, openId, accountNo, accountId);
	}
}
