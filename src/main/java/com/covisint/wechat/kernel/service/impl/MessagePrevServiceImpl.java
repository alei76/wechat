/************************************************************************************
 * @File name   :      MessagePrevServiceImpl.java
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
 * 2014-6-30 下午01:53:56			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.kernel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.WxReplymsgTemplateDao;
import com.covisint.wechat.data.model.WxMessageHis;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.kernel.service.MessagePrevService;
import com.covisint.wechat.member.service.MessageHisService;
import com.covisint.wechat.message.factory.MessageConvertServiceFactory;
import com.covisint.wechat.weixin.model.TextOutMessage;
import com.covisint.wechat.weixin.util.XStreamFactory;

/**
 *
 */
@Service
public class MessagePrevServiceImpl implements MessagePrevService {
	@Autowired
	private MessageConvertServiceFactory converService;
	@Autowired
	private WxReplymsgTemplateDao wxReplymsgTemplateDao;
	@Autowired
	private MessageHisService messageHisService;

	@Override
	public WxMessageHis prevMessage(String templateId, String keyword, String openId, String accountNo, String accountId) {
		WxReplymsgTemplate template = wxReplymsgTemplateDao.get(templateId);
		String outMsgXml = null;
		if (template != null) {
			String resourceType = template.getResourceType();
			if (WxReplymsgTemplate.RESOURCE_TYPE_DYNAMIC.equals(resourceType)) {
				outMsgXml = getErrorMsg();
			} else {
				template.setKeyword(keyword);
				outMsgXml = converService.convertTemplate(template, openId, accountNo, accountId);
				// outMsgXml =
				// "<xml> <ToUserName><![CDATA[toUser]]></ToUserName> <FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>12345678</CreateTime> <MsgType><![CDATA[news]]></MsgType> <ArticleCount>1</ArticleCount> <Articles> <item> <Title><![CDATA[测试]]></Title> <Description><![CDATA[描述描述fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff]]></Description> <PicUrl><![CDATA[https://mmbiz.qlogo.cn/mmbiz/1eOX5Fjnggdu3JD42xLWzVnp5PkZM91NVFDyAbrK0ibNKsa0wFXysHJj8JZKDfWibAAh03EfW6bJfhdeCjvN4FqQ/0]]></PicUrl> <Url><![CDATA[http://www.baidu.com]]></Url> </item></Articles> </xml>";
			}
		}
		if (!StringUtils.isEmpty(outMsgXml))
			return messageHisService.getOutMsgHis(outMsgXml, accountId, openId, accountNo, accountId);
		else
			return null;
	}

	private String getErrorMsg() {
		TextOutMessage out = new TextOutMessage();
		out.setContent("抱歉，动态消息模板无法预览。");
		out.setMsgType("text");
		return XStreamFactory.toXml(out);
	}
}
