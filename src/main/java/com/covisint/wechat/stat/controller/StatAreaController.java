/************************************************************************************
 * @File name   :      StatAreaController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-27
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
 * 2014-6-27 上午10:38:41			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.stat.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.stat.service.WebsiteHisService;
import com.covisint.wechat.utils.SessionUtils;

/**
 *
 */
@Controller
@RequestMapping("/stat/area")
public class StatAreaController {
	@Autowired
	private WebsiteHisService websiteHisService;

	@RequestMapping("/summary")
	public String summary() {
		return "/stat/area/summary";
	}

	@ResponseBody
	@RequestMapping(value = "/areagrid", method = RequestMethod.POST)
	public Page<Map<String, Object>> areagrid(@RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		paramMap.put("accountId", currentUser.getCurrentAccount());
		return websiteHisService.pageAreaSummary(paramMap, current, pagesize);
	}
}
