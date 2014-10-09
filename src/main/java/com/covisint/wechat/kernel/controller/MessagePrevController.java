/************************************************************************************
 * @File name   :      MessagePrevController.java
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
 * 2014-6-30 上午10:59:24			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.kernel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.covisint.wechat.data.model.WxMessageHis;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.data.model.WxWechatAccount;
import com.covisint.wechat.kernel.service.MessagePrevService;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 消息预览控制器
 */
@Controller
@RequestMapping("/kernel/message")
public class MessagePrevController {
	@Autowired
	private MessagePrevService messagePrevService;

	/**
	 * 消息预览
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public String view(@RequestParam("keyword") String keyword, @RequestParam("templateId") String templateId, Model model) {
		String openId = SessionUtils.getCurrentUser().getUserId();
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		String accountId = currentUser.getCurrentAccount();
		WxWechatAccount account = SessionUtils.getCurrentAccount(accountId);
		String accountNo = account.getAccountNo();
		WxMessageHis message = messagePrevService.prevMessage(templateId, keyword, openId, accountNo, accountId);
		model.addAttribute("message", message);
		return "content/template/template-prev";
	}
}
