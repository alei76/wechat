/************************************************************************************
 * @File name   :      IdmMemberServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-21
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
 * 2014-5-21 下午03:34:15			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.webservice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.WxFansMemberDao;
import com.covisint.wechat.utils.IdentifierUtils;
import com.covisint.wechat.webservice.remoting.IdmRemotingService;
import com.covisint.wechat.webservice.service.IdmMemberService;

/**
 *
 */
@Service
public class IdmMemberServiceImpl implements IdmMemberService {
	@Autowired
	private WxFansMemberDao wxFansMemberDao;
	@Autowired
	private IdmRemotingService idmRemotingService;

	@Override
	public boolean bindIdmMember(String fullname, String password, String usermobile, String openId) {
		if (openId == null) {
			return false;
		}
		boolean bind = this.validateMember(openId);// 判断是否已经绑定
		if (bind) {
			return true;
		} else {
			String brand = "凯迪拉克";// 品牌
			String memberId = idmRemotingService.bindMember(usermobile, openId, brand, password);// 调用idm绑定接口,获取绑定后的唯一标识
			if (!StringUtils.isEmpty(memberId)) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("fansMemberId", IdentifierUtils.getId().generate().toString());
				paramMap.put("openId", openId);
				paramMap.put("memberId", memberId);
				paramMap.put("fullName", fullname);
				paramMap.put("mobile", usermobile);
				wxFansMemberDao.bindMember(paramMap);// 将绑定成功后的数据存入数据库
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean validateMember(String openId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openId", openId);
		Long row = wxFansMemberDao.checkExists(paramMap);
		return row > 0;
	}

}
