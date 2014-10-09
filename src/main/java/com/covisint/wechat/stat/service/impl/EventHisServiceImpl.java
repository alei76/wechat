/************************************************************************************
 * @File name   :      EventHisServiceImpl.java
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
 * 2014-6-4 上午10:21:29			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.stat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.WxEventHisDao;
import com.covisint.wechat.data.model.WxEventHis;
import com.covisint.wechat.stat.service.EventHisService;
import com.covisint.wechat.utils.IdentifierUtils;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.weixin.model.InMessage;

/**
 *
 */
@Service
public class EventHisServiceImpl implements EventHisService {
	@Autowired
	private WxEventHisDao wxEventHisDao;

	@Override
	public WxEventHis getEventHis(String openId, String accountId, String createTime) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openId", openId);
		paramMap.put("accountId", accountId);
		paramMap.put("createTime", createTime);
		return wxEventHisDao.getEventHis(paramMap);
	}

	@Override
	public boolean checkExists(InMessage msg) {
		String openId = msg.getFromUserName();
		String accountId = msg.getAccountId();
		String createTime = msg.getCreateTime().toString();
		WxEventHis his = this.getEventHis(openId, accountId, createTime);
		return his == null;
	}

	@Override
	public boolean saveEvent(InMessage msg) {
		WxEventHis wxEventHis = new WxEventHis();
		wxEventHis.setAccountId(msg.getAccountId());
		wxEventHis.setCreateTime(msg.getCreateTime().toString());
		wxEventHis.setEventHisId(IdentifierUtils.getId().generate().toString());
		wxEventHis.setEventKey(msg.getEventKey());
		wxEventHis.setEventType(msg.getEvent());
		wxEventHis.setOpenId(msg.getFromUserName());
		wxEventHis.setMsgContent(JacksonJsonMapper.objectToJson(msg, false));
		int row = wxEventHisDao.insert(wxEventHis);
		return row > 0;
	}
}
