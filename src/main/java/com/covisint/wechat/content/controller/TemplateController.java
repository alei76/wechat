/************************************************************************************
 * @File name   :      TemplateController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-30
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
 * 2014-4-30 下午02:51:49			马恩伟			1.0				Initial Version
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

import com.covisint.wechat.content.service.AttachmentService;
import com.covisint.wechat.content.service.LinkResourceService;
import com.covisint.wechat.content.service.TemplateService;
import com.covisint.wechat.data.model.WxLinkResource;
import com.covisint.wechat.data.model.WxMediaAtta;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 消息模板管理控制器
 */
@Controller
@RequestMapping("/content/template")
public class TemplateController {
	@Autowired
	private TemplateService templateService;
	@Autowired
	private LinkResourceService linkResourceService;
	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 跳转至页面
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-30
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "content/template/list";
	}

	/**
	 * 跳转至增加静态模板页面
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-30
	 */
	@RequestMapping(value = "/addstatic", method = RequestMethod.GET)
	public String addstatic() {
		return "content/template/addstatic";
	}

	/**
	 * 跳转至内容编辑页面(文本、图片、语音、视频、图文消息创建页面)
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-16
	 */
	@RequestMapping(value = "/content/{type}", method = RequestMethod.GET)
	public String content(@PathVariable("type") String type, Model model) {
		model.addAttribute("type", type);
		return "content/template/template_" + type;
	}

	/**
	 * 分页列表显示
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-30
	 */
	@RequestMapping(value = "/grid", method = RequestMethod.POST)
	@ResponseBody
	public Page<WxReplymsgTemplate> grid(@RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		paramMap.put("accountId", currentUser.getCurrentAccount());
		return templateService.pageTemplate(paramMap, current, pagesize);
	}

	/**
	 * 所有可选的外部资源
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 */
	@RequestMapping(value = "/linkresource", method = RequestMethod.POST)
	@ResponseBody
	public List<WxLinkResource> linkresource(@RequestParam Map<String, Object> paramMap) {
		return linkResourceService.listLinkResource(paramMap);
	}

	/**
	 * 保存动态消息模板
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 */
	@RequestMapping(value = "/saveDynamicMsg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveDynamicMsg(WxReplymsgTemplate wxReplymsgTemplate) {
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser current = SessionUtils.getCurrentUser();
		boolean success = false;
		String message = null;
		try {
			success = templateService.saveDynamicMsg(wxReplymsgTemplate, current.getUserId(), current.getCurrentAccount());
			message = success ? "保存成功" : "保存失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 保存静态消息模板（图文消息除外）
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/savetemplate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> savetemplate(WxReplymsgTemplate wxReplymsgTemplate) {
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser current = SessionUtils.getCurrentUser();
		boolean success = false;
		String message = null;
		try {
			success = templateService.saveStaticTemplate(wxReplymsgTemplate, current.getUserId(), current.getCurrentAccount());
			message = success ? "保存成功" : "保存失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 消息模板详情
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/info/{templateId}", method = RequestMethod.POST)
	@ResponseBody
	public WxReplymsgTemplate info(@PathVariable("templateId") String templateId) {
		return templateService.infoTemplate(templateId);
	}

	/**
	 * 跳转至编辑消息模板页面
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/info/{templateId}", method = RequestMethod.GET)
	public String info(@PathVariable("templateId") String templateId, Model model) {
		WxReplymsgTemplate template = templateService.infoTemplate(templateId);
		String type = template.getType();
		model.addAttribute("template", template);
		model.addAttribute("type", type);
		if (WxReplymsgTemplate.TYPE_NEWS.equals(type)) {// 如果是图文消息
			int itemCount = template.getItemsCount();
			if (itemCount == 1) {// 单图文
				model.addAttribute("multi", "single");
			} else {// 多图文
				model.addAttribute("multi", "multi");
			}
		}
		return "content/template/edit-static";
	}

	/**
	 * 列表显示所有富媒体
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/attagrid/{type}", method = RequestMethod.GET)
	public String attagrid(@PathVariable("type") String type, @RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap, Model model) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		paramMap.put("accountId", currentUser.getCurrentAccount());
		paramMap.put("type", type);
		Page<WxMediaAtta> p = attachmentService.pageMediaAtta(paramMap, current, pagesize);
		model.addAttribute("page", p);
		return "content/template/resourceGrid";
	}

	/**
	 * 删除消息模板
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@ResponseBody
	@RequestMapping(value = "/changestatus", method = RequestMethod.POST)
	public Map<String, Object> changestatus(@RequestParam("templateId") String templateId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		try {
			success = templateService.changestatus(templateId);
			message = success ? "删除成功" : "删除失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}
}
