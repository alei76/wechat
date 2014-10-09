/************************************************************************************
 * @File name   :      DictService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-25
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
 * 2014-4-25 上午08:55:24			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.kernel.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.WxDataDictionaryDao;
import com.covisint.wechat.data.model.WxDataDictionary;
import com.covisint.wechat.kernel.service.DictService;

@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private WxDataDictionaryDao wxDataDictionaryDao;

	@Override
	public List<WxDataDictionary> listDict(String dictCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("catalogCode", dictCode);
		paramMap.put("enabled", WxDataDictionary.DICT_ENABLE);
		return wxDataDictionaryDao.findDomain(paramMap);
	}

}
