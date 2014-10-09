/************************************************************************************
 * @File name   :      AutoReplyController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-9
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
 * 2014-5-9 下午02:58:06			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.content.service.AutoReplyService;
import com.covisint.wechat.content.service.TemplateService;
import com.covisint.wechat.data.model.WxReplyMsg;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 自动回复管理控制器
 */
@Controller
@RequestMapping("/content/autoreply")
public class AutoReplyController {
	@Autowired
	private AutoReplyService autoReplyService;
	@Autowired
	private TemplateService templateService;

	/**
	 * 跳转至页面
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-9
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "content/autoreply/list";
	}

	/**
	 * 分页查询
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-9
	 */
	@RequestMapping(value = "/grid", method = RequestMethod.POST)
	@ResponseBody
	public Page<WxReplyMsg> grid(@RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		paramMap.put("accountId", currentUser.getCurrentAccount());
		paramMap.put("tiggerType", WxReplyMsg.TIGGER_TYPE_KEYWORD);
		return autoReplyService.pageAutoReply(paramMap, current, pagesize);
	}

	/**
	 * 跳转至添加页面
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-12
	 */
	@RequestMapping(value = "/content/{type}", method = RequestMethod.GET)
	public String content(@PathVariable("type") String type, Model model) {
		model.addAttribute("tiggerType", type);
		WxSystemUser current = SessionUtils.getCurrentUser();
		String accountId = current.getCurrentAccount();
		if (type.equals(WxReplyMsg.TIGGER_TYPE_KEYWORD)) {
			return "content/autoreply/keyword";// 关键字回复页面
		} else {
			WxReplyMsg replyMsg = autoReplyService.getBehaviorMsg(type, accountId);
			model.addAttribute("domain", replyMsg);
			return "content/autoreply/behavior";// 其他事件类型页面
		}
	}

	/**
	 * 列表所有消息模板
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-12
	 */
	@RequestMapping(value = "/template", method = RequestMethod.POST)
	@ResponseBody
	public List<WxReplymsgTemplate> template(@RequestParam Map<String, Object> paramMap) {
		return templateService.listTemplate(paramMap);
	}

	/**
	 * 保存事件类自动回复
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-12
	 */
	@RequestMapping(value = "/savebehavior", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveBehavior(WxReplyMsg wxReplyMsg) {
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser current = SessionUtils.getCurrentUser();
		boolean success = false;
		String message = null;
		try {
			success = autoReplyService.saveBehaviorMsg(wxReplyMsg, current.getUserId(), current.getCurrentAccount());
			message = success ? "保存成功" : "保存失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 保存消息类自动回复
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-12
	 */
	@RequestMapping(value = "/savemsgreply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveMsgreply(WxReplyMsg wxReplyMsg) {
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser current = SessionUtils.getCurrentUser();
		boolean success = false;
		String message = null;
		try {
			success = autoReplyService.saveMsgreply(wxReplyMsg, current.getUserId(), current.getCurrentAccount());
			message = success ? "保存成功" : "保存失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 删除关键字自动回复
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/changestatus", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changestatus(@RequestParam("replyId") String replyId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		try {
			success = autoReplyService.changeStatus(replyId);
			message = success ? "删除成功" : "删除失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 查询某关键字自动回复设置
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/viewinfo", method = RequestMethod.POST)
	@ResponseBody
	public WxReplyMsg viewinfo(@RequestParam("replyId") String replyId) {
		return autoReplyService.info(replyId);
	}
}
