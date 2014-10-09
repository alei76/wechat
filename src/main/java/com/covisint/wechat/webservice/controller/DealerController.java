/************************************************************************************
 * @File name   :      DealerController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-7-14
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
 * 2014-7-14 下午03:12:06			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.webservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.data.model.WxGmDealerInfo;
import com.covisint.wechat.webservice.service.DealerService;
import com.covisint.wechat.webservice.util.ParamUrlDecoder;

/**
 * 经销商jsonp WebService
 */
@Controller
@RequestMapping("/api-wechat/ws/dealer")
public class DealerController {
	@Autowired
	private DealerService dealerService;

	/**
	 * 列表获取经销商数据
	 * 
	 * @author 马恩伟
	 * @date 2014-9-1
	 */
	@RequestMapping(value = "/list.jsonp", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public HttpEntity<String> list(@RequestParam Map<String, String> param, @RequestParam("callback") String callback) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/html;charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> data = ParamUrlDecoder.decoderMap(param);// 前台的值需要urlencode，这边转码
		String city = data.get("city");// 经销商城市
		String lng = data.get("lng");
		String lat = data.get("lat");
		String name = data.get("name");// 名称，可模糊查询
		List<Map<String, Object>> list = dealerService.list(name, city, lng, lat);
		result.put("data", list);
		return new HttpEntity<String>(ParamUrlDecoder.getJsonpReturn(result, callback), headers);
	}

	/**
	 * 根据经销商ID获取详细信息
	 * 
	 * @author 马恩伟
	 * @date 2014-9-1
	 */
	@RequestMapping(value = "/info.jsonp", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public HttpEntity<String> info(@RequestParam Map<String, String> param, @RequestParam("callback") String callback) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/html;charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> data = ParamUrlDecoder.decoderMap(param);// 前台的值需要urlencode，这边转码
		String dealerId = data.get("dealerId");// 经销商ID
		WxGmDealerInfo info = dealerService.info(dealerId);
		result.put("info", info);
		return new HttpEntity<String>(ParamUrlDecoder.getJsonpReturn(result, callback), headers);
	}
}
