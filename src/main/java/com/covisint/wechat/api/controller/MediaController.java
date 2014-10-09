/************************************************************************************
 * @File name   :      MediaController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-7-25
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
 * 2014-7-25 上午10:23:56			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.covisint.wechat.api.service.CgiBinService;
import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.utils.PropertiesUtils;

/**
 * 腾讯微信官方平台媒体文件部分API支持
 */
@Controller
@RequestMapping("/api-wechat/cgi-bin/media")
public class MediaController {
	@Autowired
	private CgiBinService cgiBinService;

	/**
	 * get 形式返回错误
	 * 
	 * @author 马恩伟
	 * @date 2014-9-9
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<String> e_upload(@RequestParam Map<String, String> param) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf-8");
		Map<String, String> error = new HashMap<String, String>();
		error.put("errcode", "43002");
		error.put("errmsg", PropertiesUtils.getValue("43002"));
		String response = JacksonJsonMapper.objectToJson(error, false);
		return new HttpEntity<String>(response, headers);
	}

	/**
	 * 上传多媒体文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public HttpEntity<String> upload(MultipartRequest request, @RequestParam Map<String, String> param) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf-8");
		MultipartFile file = null;
		Iterator<String> fileName = request.getFileNames();
		while (fileName.hasNext()) {
			String name = fileName.next();
			file = request.getFile(name);
		}
		String response;
		try {
			response = cgiBinService.uploadMedia(file, param);
		} catch (WeChatException e) {
			String errcode = e.getMessage();
			Map<String, String> error = new HashMap<String, String>();
			error.put("errcode", errcode);
			error.put("errmsg", PropertiesUtils.getValue(errcode));
			response = JacksonJsonMapper.objectToJson(error, false);
		}
		return new HttpEntity<String>(response, headers);
	}

	/**
	 * 下载多媒体文件
	 * 
	 * @author 马恩伟
	 * @throws IOException
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public void get(@RequestParam Map<String, String> param, HttpServletResponse response) throws IOException {
		try {
			cgiBinService.downloadMedia(param, response);
		} catch (WeChatException e) {
			String errcode = e.getMessage();
			Map<String, String> error = new HashMap<String, String>();
			error.put("errcode", errcode);
			error.put("errmsg", PropertiesUtils.getValue(errcode));
			String json = JacksonJsonMapper.objectToJson(error, false);
			response.setContentType("application/json; charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		}
	}
}
