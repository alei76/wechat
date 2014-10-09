/************************************************************************************
 * @File name   :      AttachmentController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-7
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
 * 2014-5-7 下午02:40:44			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.covisint.wechat.content.service.AttachmentService;
import com.covisint.wechat.data.model.WxMediaAtta;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 富媒体文件控制器
 */
@Controller
@RequestMapping("/content/attachment")
public class AttachmentController {
	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 跳转至页面
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-7
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "content/attachment/list";
	}

	/**
	 * 分页列表显示
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-7
	 */
	@RequestMapping(value = "/grid", method = RequestMethod.POST)
	@ResponseBody
	public Page<WxMediaAtta> grid(@RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		paramMap.put("accountId", currentUser.getCurrentAccount());
		return attachmentService.pageMediaAtta(paramMap, current, pagesize);
	}

	/**
	 * 保存多媒体文件
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-8
	 */
	@RequestMapping(value = "/savemedia", method = RequestMethod.POST)
	@ResponseBody
	public HttpEntity<Map<String, Object>> savemedia(@RequestParam("image_file") MultipartFile image, @RequestParam("media_audio_video") MultipartFile media, WxMediaAtta wxMediaAtta) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/html;charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser current = SessionUtils.getCurrentUser();
		boolean success = false;
		String message = null;
		try {
			success = attachmentService.saveMedia(wxMediaAtta, current.getUserId(), current.getCurrentAccount(), image, media);
			message = success ? "保存成功" : "保存失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return new HttpEntity<Map<String, Object>>(result, headers);
	}

	/**
	 * 多图文消息中，选择图片后上传至后台，并返回预览图
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/tempimage", method = RequestMethod.POST)
	@ResponseBody
	public HttpEntity<Map<String, Object>> tempimage(@RequestParam("image_file") MultipartFile image) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/html;charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser current = SessionUtils.getCurrentUser();
		String fileName = attachmentService.saveImage(current.getUserId(), current.getCurrentAccount(), image);
		boolean success = false;
		if (!StringUtils.isEmpty(fileName)) {
			success = true;
		}
		String message = success ? fileName : "保存失败";
		result.put("success", success);
		result.put("message", message);
		return new HttpEntity<Map<String, Object>>(result, headers);
	}

	/**
	 * 跳转至 富媒体文件预览页面
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/preview/{attaId}", method = RequestMethod.GET)
	public String preview(@PathVariable("attaId") String attaId, Model model) {
		WxMediaAtta mediaAtta = attachmentService.getAtta(attaId);
		String type = mediaAtta.getType();
		model.addAttribute("mediaAtta", mediaAtta);
		return "content/attachment/preview_" + type;
	}

	/**
	 * 获取富媒体信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/info/{attaId}", method = RequestMethod.GET)
	public String info(@PathVariable("attaId") String attaId, Model model) {
		WxMediaAtta mediaAtta = attachmentService.getAtta(attaId);
		model.addAttribute("mediaAtta", mediaAtta);
		return "content/attachment/edit";
	}

	/**
	 * 编辑富媒体文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/editmedia", method = RequestMethod.POST)
	@ResponseBody
	public HttpEntity<Map<String, Object>> editmedia(@RequestParam("image_file") MultipartFile image, @RequestParam("media_audio_video") MultipartFile media, WxMediaAtta wxMediaAtta) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/html;charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser current = SessionUtils.getCurrentUser();
		boolean success = false;
		String message = null;
		try {
			success = attachmentService.editMedia(wxMediaAtta, current.getCurrentAccount(), image, media);
			message = success ? "保存成功" : "保存失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return new HttpEntity<Map<String, Object>>(result, headers);
	}
}
