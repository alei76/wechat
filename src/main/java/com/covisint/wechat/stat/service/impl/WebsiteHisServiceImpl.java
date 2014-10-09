/************************************************************************************
 * @File name   :      WebsiteHisServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-17
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
 * 2014-6-17 下午04:47:33			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.stat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.covisint.wechat.data.dao.WxWebsiteHisDao;
import com.covisint.wechat.data.model.WxWebsiteHis;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.stat.service.WebsiteHisService;
import com.covisint.wechat.utils.IdentifierUtils;

/**
 *
 */
@Service
public class WebsiteHisServiceImpl implements WebsiteHisService {
	@Autowired
	private WxWebsiteHisDao wxWebsiteHisDao;

	@Override
	public boolean saveVisitHis(String resourceId, String redirect, String openId, String accountId, String source) {
		WxWebsiteHis websiteHis = new WxWebsiteHis();
		websiteHis.setAccountId(accountId);
		websiteHis.setOpenId(openId);
		websiteHis.setResourceId(resourceId);
		UriComponents redirectUri = UriComponentsBuilder.fromHttpUrl(redirect).build().encode();
		String weblink = redirectUri.getScheme() + "://" + redirectUri.getHost();
		if (redirectUri.getPort() > 0) {
			weblink += ":" + redirectUri.getPort();
		}
		if (redirectUri.getPath() != null) {
			weblink += redirectUri.getPath();
		}
		websiteHis.setViewHref(weblink);
		websiteHis.setViewSource(source);
		websiteHis.setVisitHisId(IdentifierUtils.getId().generate().toString());
		return wxWebsiteHisDao.insert(websiteHis) > 0;
	}

	@Override
	public Page<Map<String, Object>> pageWebsiteHis(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		String startDay = (String) paramMap.get("startDay");
		if (!StringUtils.isEmpty(startDay)) {
			paramMap.put("startTime", startDay);
		}
		String endDay = (String) paramMap.get("endDay");
		if (!StringUtils.isEmpty(endDay)) {
			paramMap.put("endTime", endDay);
		}
		return wxWebsiteHisDao.pageWebsiteHis(paramMap, current, pagesize);
	}

	@Override
	public Page<Map<String, Object>> pageDaySummary(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		String startDay = (String) paramMap.get("startDay");
		if (!StringUtils.isEmpty(startDay)) {
			paramMap.put("startTime", startDay);
		}
		String endDay = (String) paramMap.get("endDay");
		if (!StringUtils.isEmpty(endDay)) {
			paramMap.put("endTime", endDay);
		}
		String sortColumn = (String) paramMap.get("sort");
		String sortOrder = (String) paramMap.get("order");
		String order = this.getOrder(sortColumn, sortOrder);
		if (!StringUtils.isEmpty(order)) {
			paramMap.put("order", order);
		}
		return wxWebsiteHisDao.pageDaySummary(paramMap, current, pagesize);
	}

	public Map<String, Object> daysummary(Map<String, Object> paramMap) {
		Map<String, Object> result = new HashMap<String, Object>();
		String startDay = (String) paramMap.get("startDay");
		if (!StringUtils.isEmpty(startDay)) {
			paramMap.put("startTime", startDay);
		}
		String endDay = (String) paramMap.get("endDay");
		if (!StringUtils.isEmpty(endDay)) {
			paramMap.put("endTime", endDay);
		}
		List<Map<String, Object>> data = wxWebsiteHisDao.daysummary(paramMap);
		List<String> dayList = new ArrayList<String>();
		List<Integer> clickList = new ArrayList<Integer>();
		List<Integer> memberList = new ArrayList<Integer>();
		for (Map<String, Object> m : data) {
			String date = (String) m.get("VISIT_DATE");
			int click = Integer.valueOf(m.get("CLICK").toString());
			int member = Integer.valueOf(m.get("MEMBER").toString());
			dayList.add(date);
			clickList.add(click);
			memberList.add(member);
		}
		result.put("day", dayList);
		result.put("click", clickList);
		result.put("member", memberList);
		return result;
	}

	@Override
	public Page<Map<String, Object>> pageTimeSummary(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		String startTime = (String) paramMap.get("startTime");
		if (!StringUtils.isEmpty(startTime)) {
			paramMap.put("start", startTime);
		}
		String endTime = (String) paramMap.get("endTime");
		if (!StringUtils.isEmpty(endTime)) {
			paramMap.put("end", endTime);
		}
		String sortColumn = (String) paramMap.get("sort");
		String sortOrder = (String) paramMap.get("order");
		String order = this.getOrder(sortColumn, sortOrder);
		if (!StringUtils.isEmpty(order)) {
			paramMap.put("order", order);
		}
		return wxWebsiteHisDao.pageTimeSummary(paramMap, current, pagesize);
	}

	@Override
	public Map<String, Object> timesummary(Map<String, Object> paramMap) {
		Map<String, Object> result = new HashMap<String, Object>();
		String startTime = (String) paramMap.get("startTime");
		if (!StringUtils.isEmpty(startTime)) {
			paramMap.put("start", startTime);
		}
		String endTime = (String) paramMap.get("endTime");
		if (!StringUtils.isEmpty(endTime)) {
			paramMap.put("end", endTime);
		}
		List<Map<String, Object>> data = wxWebsiteHisDao.timesummary(paramMap);
		List<String> timeList = new ArrayList<String>();
		List<Integer> clickList = new ArrayList<Integer>();
		List<Integer> memberList = new ArrayList<Integer>();
		for (Map<String, Object> m : data) {
			String time = (String) m.get("VISIT_TIME");
			int click = Integer.valueOf(m.get("CLICK").toString());
			int member = Integer.valueOf(m.get("MEMBER").toString());
			timeList.add(time);
			clickList.add(click);
			memberList.add(member);
		}
		result.put("time", timeList);
		result.put("click", clickList);
		result.put("member", memberList);
		return result;
	}

	@Override
	public Page<Map<String, Object>> pageAreaSummary(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		String startDay = (String) paramMap.get("startDay");
		if (!StringUtils.isEmpty(startDay)) {
			paramMap.put("startTime", startDay);
		}
		String endDay = (String) paramMap.get("endDay");
		if (!StringUtils.isEmpty(endDay)) {
			paramMap.put("endTime", endDay);
		}
		String sortColumn = (String) paramMap.get("sort");
		String sortOrder = (String) paramMap.get("order");
		String order = this.getOrder(sortColumn, sortOrder);
		if (!StringUtils.isEmpty(order)) {
			paramMap.put("order", order);
		}
		return wxWebsiteHisDao.pageAreaSummary(paramMap, current, pagesize);
	}

	/**
	 * 后台拼装 order 以免sql注入
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private String getOrder(String sortColumn, String sortOrder) {
		if (StringUtils.isEmpty(sortOrder)) {
			sortOrder = "DESC";
		} else if ("asc".equals(sortOrder)) {
			sortOrder = "ASC";
		} else {
			sortOrder = "DESC";
		}
		if (StringUtils.isEmpty(sortColumn)) {
			return null;
		} else if ("VISIT_DATE".equals(sortColumn)) {
			return "WWH.VISIT_DATE " + sortOrder;
		} else if ("CLICK".equals(sortColumn)) {
			return "CLICK " + sortOrder;
		} else if ("MEMBER".equals(sortColumn)) {
			return "MEMBER " + sortOrder;
		} else if ("VISIT_TIME".equals(sortColumn)) {
			return "TO_CHAR(TO_DATE(WWH.VISIT_TIME, 'hh24:MI:ss'),'hh24') " + sortOrder;
		}
		return null;
	}
}
