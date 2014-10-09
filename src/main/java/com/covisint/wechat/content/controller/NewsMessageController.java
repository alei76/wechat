/************************************************************************************
 * @File name   :      NewsMessageController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-7-2
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
 * 2014-7-2 下午04:29:50			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.content.service.TemplateService;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 图文消息控制器
 */
@Controller
@RequestMapping("/content/newsmessage")
public class NewsMessageController {
	@Autowired
	private TemplateService templateService;

	/**
	 * 跳转至页面 单图文、多图文
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-30
	 * @return
	 */
	@RequestMapping(value = "/create/{type}", method = RequestMethod.GET)
	public String create(@PathVariable("type") String type, Model model) {
		model.addAttribute("type", WxReplymsgTemplate.TYPE_NEWS);
		return "content/template/" + type + "_news";
	}

	/**
	 * 保存图文消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/savenews", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> savenews(@RequestParam("template") String template, @RequestParam("items") String items) {
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		boolean success = false;
		String message = null;
		WxReplymsgTemplate templateDomain = JacksonJsonMapper.jsonToObject(template, WxReplymsgTemplate.class);// 模板对象
		List<Map<String, Object>> itemsList = JacksonJsonMapper.jsonToObject(items, List.class);// 图文项
		String userId = currentUser.getUserId();
		String accountId = currentUser.getCurrentAccount();
		try {
			success = templateService.saveNewsMessage(templateDomain, itemsList, userId, accountId);
			message = success ? "保存成功" : "保存失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 编辑修改图文消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/editnews", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editnews(@RequestParam("template") String template, @RequestParam("items") String items, @RequestParam("delItems") String delItems) {
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		boolean success = false;
		String message = null;
		WxReplymsgTemplate templateDomain = JacksonJsonMapper.jsonToObject(template, WxReplymsgTemplate.class);
		List<Map<String, Object>> itemsList = JacksonJsonMapper.jsonToObject(items, List.class);
		String[] delItemsArray = StringUtils.split(delItems, ",");
		String userId = currentUser.getUserId();
		String accountId = currentUser.getCurrentAccount();
		try {
			success = templateService.editNewsMessage(templateDomain, itemsList, delItemsArray, userId, accountId);
			message = success ? "保存成功" : "保存失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}
}
