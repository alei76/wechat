/************************************************************************************
 * @File name   :      LinkResourceController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-29
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
 * 2014-4-29 上午11:01:09			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.content.service.LinkResourceService;
import com.covisint.wechat.data.model.WxLinkResource;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 外部资源管理控制器
 */
@Controller
@RequestMapping("/content/linkresource")
public class LinkResourceController {
	@Autowired
	private LinkResourceService linkResourceService;

	/**
	 * 跳转至页面
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-29
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "content/linkresource/list";
	}

	/**
	 * 分页列表显示
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-29
	 */
	@RequestMapping(value = "/grid", method = RequestMethod.POST)
	@ResponseBody
	public Page<WxLinkResource> grid(@RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		paramMap.put("accountId", currentUser.getCurrentAccount());
		return linkResourceService.pageLinkResource(paramMap, current, pagesize);
	}

	/**
	 * 保存外部资源
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-29
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, Object> save(WxLinkResource wxLinkResource) {
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser current = SessionUtils.getCurrentUser();
		boolean success = false;
		String message = null;
		try {
			success = linkResourceService.saveLinkResource(wxLinkResource, current.getUserId(), current.getCurrentAccount());
			message = success ? "保存成功" : "保存失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 删除资源
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-13
	 */
	@ResponseBody
	@RequestMapping(value = "/changestatus", method = RequestMethod.POST)
	public Map<String, Object> changestatus(@RequestParam("resourceId") String resourceId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		try {
			success = linkResourceService.changestatus(resourceId);
			message = success ? "删除成功" : "删除失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 查看资源详情
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-13
	 */
	@ResponseBody
	@RequestMapping(value = "/viewinfo", method = RequestMethod.POST)
	public WxLinkResource viewinfo(@RequestParam("resourceId") String resourceId) {
		return linkResourceService.info(resourceId);
	}

	/**
	 * 批量删除
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/batchchange", method = RequestMethod.POST)
	public Map<String, Object> batchchange(@RequestParam("resource") String resource) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		try {
			List<String> ids = JacksonJsonMapper.jsonToObject(resource, List.class);
			success = linkResourceService.batchchange(ids);
			message = success ? "删除成功" : "删除失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}
}
