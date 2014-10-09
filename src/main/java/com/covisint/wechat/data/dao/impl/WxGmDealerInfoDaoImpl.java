/************************************************************************************
 * @File name   :      WxGmDealerInfoDaoImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-07-14
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
 * 2014-7-14 16:07:42			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxGmDealerInfoDao;
import com.covisint.wechat.data.mapper.WxGmDealerInfoMapper;
import com.covisint.wechat.data.model.WxGmDealerInfo;

/**
 * 通用经销商信息数据访问接口
 * 
 */
@Component
public class WxGmDealerInfoDaoImpl implements WxGmDealerInfoDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public WxGmDealerInfo get(java.lang.String dealerId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dealerId", dealerId);
		return dao.get(WxGmDealerInfoMapper.getFindOneSql(paramMap), paramMap, new WxGmDealerInfo());
	}

	@Override
	public List<WxGmDealerInfo> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxGmDealerInfoMapper.getFindAllSql(paramMap), paramMap, new WxGmDealerInfo());
	}

}