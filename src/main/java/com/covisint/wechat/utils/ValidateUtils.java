/************************************************************************************
 * @File name   :      ValidateUtils.java
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
package com.covisint.wechat.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 合法性验证工具类
 */
public class ValidateUtils {

	/**
	 * 验证对象是否为空
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		boolean isEmpty = false;
		if (obj == null) {
			isEmpty = true;
		} else if (obj instanceof String) {
			isEmpty = ((String) obj).trim().isEmpty();
		} else if (obj instanceof Collection) {
			isEmpty = (((Collection) obj).size() == 0);
		} else if (obj instanceof Map) {
			isEmpty = ((Map) obj).size() == 0;
		} else if (obj.getClass().isArray()) {
			isEmpty = Array.getLength(obj) == 0;
		}
		return isEmpty;
	}

	/**
	 * 获取字符串长度，中文算2个。
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public static int length(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}

}
