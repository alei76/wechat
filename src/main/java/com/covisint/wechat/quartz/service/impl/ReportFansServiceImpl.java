/************************************************************************************
 * @File name   :      ReportFansServiceImpl.java
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
 * 2014-6-18 下午01:38:25			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.quartz.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.UserStatDao;
import com.covisint.wechat.data.dao.WxRpFansCountDao;
import com.covisint.wechat.data.model.WxFans;
import com.covisint.wechat.data.model.WxRpFansCount;
import com.covisint.wechat.quartz.service.ReportFansService;
import com.covisint.wechat.utils.DateUtils;
import com.covisint.wechat.utils.IdentifierUtils;

/**
 *
 */
@Service
public class ReportFansServiceImpl implements ReportFansService {
	@Autowired
	private WxRpFansCountDao wxRpFansCountDao;
	@Autowired
	private UserStatDao userStatDao;

	@Override
	public void fansCountReport() {
		String today = DateUtils.currentStringDate();
		Date yestday = DateUtils.parse(today);
		yestday = DateUtils.add(Calendar.DAY_OF_MONTH, yestday, -1);
		Long l_yestday = yestday.getTime() / 1000;
		long rows = wxRpFansCountDao.checkReport(l_yestday);
		if (rows == 0) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("status", WxFans.STATUS_ENABLE);
			Map<String, Integer> data = userStatDao.statFansSummary(paramMap);
			int count = data.size();
			List<WxRpFansCount> insertData = new ArrayList<WxRpFansCount>(count);
			Iterator<String> i_accountId = data.keySet().iterator();
			while (i_accountId.hasNext()) {
				String accountId = i_accountId.next();
				Integer fansCount = data.get(accountId);
				WxRpFansCount rp = new WxRpFansCount();
				rp.setAccountId(accountId);
				rp.setFansCount(Long.valueOf(fansCount));
				rp.setRecordId(IdentifierUtils.getId().generate().toString());
				rp.setRecordDay(l_yestday);
				insertData.add(rp);
			}
			wxRpFansCountDao.insertBatch(insertData);
		}

	}
}
