/************************************************************************************
 * @File name   :      JacksonJsonMapper.java
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

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.covisint.wechat.jackson.JacksonObjectMapper;

/**
 * JSON转换工具类
 */
public class JacksonJsonMapper {
	static volatile ObjectMapper objectMapper = null;

	private JacksonJsonMapper() {
	}

	private static ObjectMapper getInstance() {
		if (objectMapper == null) {
			synchronized (ObjectMapper.class) {
				if (objectMapper == null) {
					objectMapper = new JacksonObjectMapper();
				}
			}
		}
		return objectMapper;
	}

	/**
	 * 将对象转成json字符串
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	private static String objectToJson(Object beanobject) {
		ObjectMapper mapper = getInstance();
		String resutl = null;
		try {
			resutl = mapper.writeValueAsString(beanobject);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resutl;
	}

	/**
	 * 将对象转成json字符串（是否包含null属性）
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public static String objectToJson(Object beanobject, boolean includeNull) {
		if (includeNull) {
			return objectToJson(beanobject);
		} else {
			ObjectMapper mapper = getInstance();
			mapper.setSerializationInclusion(Inclusion.NON_NULL);
			String resutl = null;
			try {
				resutl = mapper.writeValueAsString(beanobject);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return resutl;
		}
	}

	/**
	 * 将json字符串转成对象
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public static <T> T jsonToObject(String jsonString, Class<T> claszz) {
		T t = null;
		try {
			t = getInstance().readValue(jsonString, claszz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}
}
