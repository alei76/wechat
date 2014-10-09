/************************************************************************************
 * @File name   :      ParamUrlDecoder.java
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
 * 2014-5-21 下午02:51:32			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.webservice.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.covisint.wechat.utils.JacksonJsonMapper;

/**
 *
 */
public class ParamUrlDecoder {
	/**
	 * URLDecoder解码
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-21
	 */
	public static Map<String, String> decoderMap(Map<String, String> param) {
		Map<String, String> result = new HashMap<String, String>(param.size());
		try {
			Set<Entry<String, String>> entrySet = param.entrySet();
			for (Entry<String, String> entry : entrySet) {
				String value = entry.getValue();
				result.put(entry.getKey(), URLDecoder.decode(value, "utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取jsonp响应数据
	 */
	public static String getJsonpReturn(Map<String, Object> result, String callback) {
		String data = JacksonJsonMapper.objectToJson(result, false);
		return callback + "(" + data + ");";
	}
}
