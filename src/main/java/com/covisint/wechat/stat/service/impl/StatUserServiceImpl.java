/************************************************************************************
 * @File name   :      StatUserServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-4
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
 * 2014-6-4 下午03:55:25			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.stat.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.UserStatDao;
import com.covisint.wechat.data.dao.WxRpFansCountDao;
import com.covisint.wechat.data.model.WxFans;
import com.covisint.wechat.stat.service.StatUserService;
import com.covisint.wechat.utils.DateUtils;

/**
 *
 */
@Service
public class StatUserServiceImpl implements StatUserService {
	@Autowired
	private UserStatDao userStatDao;
	@Autowired
	private WxRpFansCountDao wxRpFansCountDao;

	@Override
	public List<Map<String, Object>> userSexSummary(String accoundId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accoundId);
		paramMap.put("status", WxFans.STATUS_ENABLE);
		return userStatDao.statSexSummary(paramMap);
	}

	@Override
	public List<Map<String, Object>> userLangSummary(String accoundId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accoundId);
		paramMap.put("status", WxFans.STATUS_ENABLE);
		return userStatDao.statLangSummary(paramMap);
	}

	@Override
	public Map<String, List<? extends Object>> trendsummary(String accoundId, String startDate, String endDate, Integer type) {
		if (type == 0) {
			return this.subStatSummary(accoundId, startDate, endDate);
		} else if (type == 1) {
			return this.unsubStatSummary(accoundId, startDate, endDate);
		} else if (type == 2) {
			return this.statPureSummary(accoundId, startDate, endDate);
		} else if (type == 3) {
			return this.countFansSummary(accoundId, startDate, endDate);
		} else {
			return null;
		}
	}

	private Map<String, List<? extends Object>> countFansSummary(String accoundId, String startDate, String endDate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Date start = DateUtils.parse(startDate);
		Date end = DateUtils.parse(endDate);
		end = DateUtils.add(Calendar.DAY_OF_MONTH, end, 1);
		long startTime = start.getTime() / 1000;
		long endTime = end.getTime() / 1000;
		paramMap.put("accountId", accoundId);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		Map<String, Integer> data = wxRpFansCountDao.statPureSummary(paramMap);
		return this.parseSeriesData(start, end, data);
	}

	private Map<String, List<? extends Object>> statPureSummary(String accoundId, String startDate, String endDate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Date start = DateUtils.parse(startDate);
		Date end = DateUtils.parse(endDate);
		end = DateUtils.add(Calendar.DAY_OF_MONTH, end, 1);
		long startTime = start.getTime() / 1000;
		long endTime = end.getTime() / 1000;
		paramMap.put("accountId", accoundId);
		paramMap.put("eventType", "subscribe");
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		Map<String, Integer> data = userStatDao.statPureSummary(paramMap);
		return this.parseSeriesData(start, end, data);
	}

	private Map<String, List<? extends Object>> subStatSummary(String accoundId, String startDate, String endDate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Date start = DateUtils.parse(startDate);
		Date end = DateUtils.parse(endDate);
		end = DateUtils.add(Calendar.DAY_OF_MONTH, end, 1);
		long startTime = start.getTime() / 1000;
		long endTime = end.getTime() / 1000;
		paramMap.put("accountId", accoundId);
		paramMap.put("eventType", "subscribe");
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		Map<String, Integer> data = userStatDao.statTrendSummary(paramMap);
		return this.parseSeriesData(start, end, data);
	}

	private Map<String, List<? extends Object>> unsubStatSummary(String accoundId, String startDate, String endDate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Date start = DateUtils.parse(startDate);
		Date end = DateUtils.parse(endDate);
		end = DateUtils.add(Calendar.DAY_OF_MONTH, end, 1);
		long startTime = start.getTime() / 1000;
		long endTime = end.getTime() / 1000;
		paramMap.put("accountId", accoundId);
		paramMap.put("eventType", "unsubscribe");
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		Map<String, Integer> data = userStatDao.statTrendSummary(paramMap);
		return this.parseSeriesData(start, end, data);
	}

	private Map<String, List<? extends Object>> parseSeriesData(Date startDate, Date endDate, Map<String, Integer> data) {
		if (data != null && data.size() > 0) {
			Map<String, List<? extends Object>> result = new HashMap<String, List<? extends Object>>();
			List<String> dateList = new ArrayList<String>();
			List<Integer> countList = new ArrayList<Integer>();
			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while (true) {
				if (start.equals(end)) {
					break;
				}
				String current = sdf.format(start.getTime());
				dateList.add(current);
				Integer count = data.get(current);
				if (count == null) {
					count = 0;
				}
				countList.add(count);
				start.add(Calendar.DAY_OF_MONTH, 1);
			}
			result.put("dateList", dateList);
			result.put("countList", countList);
			return result;
		} else {
			return null;
		}
	}

	@Override
	public Map<String, Object> lastsub(String accoundId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accoundId);
		paramMap.put("eventType", "subscribe");
		Map<String, Object> lastSubData = userStatDao.lastSub(paramMap);
		return lastSubData;
	}

	@Override
	public Map<String, Object> lastunsub(String accoundId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accoundId);
		paramMap.put("eventType", "unsubscribe");
		Map<String, Object> lastUnSubData = userStatDao.lastUnSub(paramMap);
		return lastUnSubData;
	}

	@Override
	public Map<String, Object> lasttotal(String accoundId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accoundId);
		Map<String, Object> lastTotalData = wxRpFansCountDao.lastTotal(paramMap);
		return lastTotalData;
	}

	@Override
	public Map<String, Object> lastpure(String accoundId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accoundId);
		paramMap.put("eventType", "subscribe");
		Map<String, Object> lastSubData = userStatDao.lastPure(paramMap);
		return lastSubData;
	}
}
