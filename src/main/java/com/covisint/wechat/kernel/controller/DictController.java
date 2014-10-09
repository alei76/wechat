/************************************************************************************
 * @File name   :      DictController.java
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
package com.covisint.wechat.kernel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.data.model.WxDataDictionary;
import com.covisint.wechat.kernel.service.DictService;

/**
 * 数据字典控制器
 */
@Controller
@RequestMapping("/anon/dict")
public class DictController {
	@Autowired
	private DictService dictService;

	/**
	 * 获取数据字典信息
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<WxDataDictionary> list(@RequestParam("dictCode") String dictCode) {
		return dictService.listDict(dictCode);
	}
}
