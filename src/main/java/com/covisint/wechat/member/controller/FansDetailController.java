/************************************************************************************
 * @File name   :      FansDetailController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-18
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
 * 2014-6-18 下午03:54:49			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.data.model.WxFans;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.member.service.FansService;
import com.covisint.wechat.member.service.MessageHisService;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.stat.service.WebsiteHisService;
import com.covisint.wechat.utils.SessionUtils;

/**
 *
 */
@Controller
@RequestMapping("/info/fans")
public class FansDetailController {
	@Autowired
	private FansService fansService;
	@Autowired
	private MessageHisService messageHisService;
	@Autowired
	private WebsiteHisService websiteHisService;

	/**
	 * 跳转至粉丝详情页面
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/list/{fansId}", method = RequestMethod.GET)
	public String detail(@PathVariable("fansId") String fansId, Model model) {
		model.addAttribute("fansId", fansId);
		WxFans wxFans = fansService.infoFans(fansId);
		model.addAttribute("wxFans", wxFans);
		return "member/fans/detail";
	}

	/**
	 * 消息记录分页查询
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@ResponseBody
	@RequestMapping(value = "/grid", method = RequestMethod.POST)
	public Page<Map<String, Object>> grid(@RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		paramMap.put("accountId", currentUser.getCurrentAccount());
		return messageHisService.pageMsgHis(paramMap, current, pagesize);
	}

	/**
	 * 消息汇总分页显示
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@ResponseBody
	@RequestMapping(value = "/collect", method = RequestMethod.POST)
	public Page<Map<String, Object>> collect(@RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		paramMap.put("accountId", currentUser.getCurrentAccount());
		return messageHisService.pageMsgCollect(paramMap, current, pagesize);
	}

	/**
	 * 访问记录分页查询
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@ResponseBody
	@RequestMapping(value = "/website", method = RequestMethod.POST)
	public Page<Map<String, Object>> website(@RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		paramMap.put("accountId", currentUser.getCurrentAccount());
		return websiteHisService.pageWebsiteHis(paramMap, current, pagesize);
	}

	@ResponseBody
	@RequestMapping(value = "/asyncfansinfo", method = RequestMethod.POST)
	public Map<String, Object> asyncfansinfo(@RequestParam("fansId") String fansId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		try {
			success = fansService.updateFansInfo(fansId);
			if (success) {
				WxFans fans = fansService.infoFans(fansId);
				result.put("fans", fans);
			}
			message = success ? "更新成功" : "更新失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}
}
