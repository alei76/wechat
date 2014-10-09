/************************************************************************************
 * @File name   :      StatUserController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-3
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
 * 2014-6-3 上午11:37:15			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.stat.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.stat.service.StatUserService;
import com.covisint.wechat.utils.DateUtils;
import com.covisint.wechat.utils.SessionUtils;

/**
 *
 */
@Controller
@RequestMapping("/stat/user")
public class StatUserController {
	@Autowired
	private StatUserService statUserService;

	@RequestMapping("/summary")
	public String summary() {
		return "/stat/user/summary";
	}

	/**
	 * 性别统计
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-13
	 * @return
	 */
	@RequestMapping("/sexsummary")
	@ResponseBody
	public Map<String, Object> sexsummary() {
		Map<String, Object> result = new HashMap<String, Object>();
		String accoundId = SessionUtils.getCurrentUser().getCurrentAccount();
		List<Map<String, Object>> series = statUserService.userSexSummary(accoundId);
		Calendar yestday = Calendar.getInstance();
		yestday.add(Calendar.DATE, -1);
		String tips = DateUtils.toString(yestday.getTime(), "MM.dd");
		result.put("series", series);
		result.put("tips", tips);
		return result;
	}

	/**
	 * 语言统计
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-13
	 * @return
	 */
	@RequestMapping("/langsummary")
	@ResponseBody
	public Map<String, Object> langsummary() {
		Map<String, Object> result = new HashMap<String, Object>();
		String accoundId = SessionUtils.getCurrentUser().getCurrentAccount();
		List<Map<String, Object>> series = statUserService.userLangSummary(accoundId);
		Calendar yestday = Calendar.getInstance();
		yestday.add(Calendar.DATE, -1);
		String tips = DateUtils.toString(yestday.getTime(), "MM.dd");
		result.put("series", series);
		result.put("tips", tips);
		return result;
	}

	/**
	 * 新增人数统计
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-13
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/trendsummary/{type}")
	@ResponseBody
	public Map<String, Object> trendsummary(@PathVariable("type") Integer type, @RequestParam Map<String, String> param) {
		Map<String, Object> result = new HashMap<String, Object>();
		String accoundId = SessionUtils.getCurrentUser().getCurrentAccount();
		String startDate = param.get("startDay");
		String endDate = param.get("endDay");
		Map<String, List<? extends Object>> series = statUserService.trendsummary(accoundId, startDate, endDate, type);
		result.put("success", false);
		if (null != series) {
			List<String> dateList = (List<String>) series.get("dateList");
			List<Integer> countList = (List<Integer>) series.get("countList");
			ObjectMapper om = new ObjectMapper();
			try {
				String date = om.writeValueAsString(dateList);
				String count = om.writeValueAsString(countList);
				String title = this.getType(type);
				result.put("date", date);
				result.put("count", count);
				result.put("title", title);
				result.put("success", true);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@RequestMapping("/lastsub")
	@ResponseBody
	public Map<String, Object> lastsub() {
		String accoundId = SessionUtils.getCurrentUser().getCurrentAccount();
		return statUserService.lastsub(accoundId);
	}

	@RequestMapping("/lastunsub")
	@ResponseBody
	public Map<String, Object> lastunsub() {
		String accoundId = SessionUtils.getCurrentUser().getCurrentAccount();
		return statUserService.lastunsub(accoundId);
	}

	@RequestMapping("/lastpure")
	@ResponseBody
	public Map<String, Object> lastpure() {
		String accoundId = SessionUtils.getCurrentUser().getCurrentAccount();
		return statUserService.lastpure(accoundId);
	}

	@RequestMapping("/lasttotal")
	@ResponseBody
	public Map<String, Object> lasttotal() {
		String accoundId = SessionUtils.getCurrentUser().getCurrentAccount();
		return statUserService.lasttotal(accoundId);
	}

	private String getType(Integer type) {
		switch (type) {
		case 0:
			return "新关注人数";
		case 1:
			return "取消关注人数";
		case 2:
			return "净增关注人数";
		case 3:
			return "累计关注人数";
		default:
			return null;
		}
	}

}
