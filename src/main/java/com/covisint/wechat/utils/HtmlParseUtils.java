/************************************************************************************
 * @File name   :      HtmlParseUtils.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-30
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
 * 2014-6-30 下午04:47:53			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 页面处理html工具类（covisint.tld）使用
 */
public class HtmlParseUtils {
	/**
	 * 将换行替换为<br/>
	 * 
	 * @author 马恩伟
	 * @date 2014-9-1
	 */
	public static String replaceNewLine(String source) {
		return source.replaceAll("\\n", "<br/>");
	}

	/**
	 * 将消息内容里的A标签处理，去掉拦截前缀路径，增加点击打开新标签(_blank)
	 * 
	 * @author 马恩伟
	 * @date 2014-9-1
	 */
	public static String replaceLink(String content) {
		String str = content;
		Pattern p = Pattern.compile("<a\\s.*?href\\s*=\\s*\'?\"?([^(\\s\")]+)\\s*\'?\"?[^>]*>(.*?)</a>");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String link = m.group(0);
			String text = m.group(2);
			String url = m.group(1);
			UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(url).build();
			MultiValueMap<String, String> param = uriBuilder.getQueryParams();
			String redirect = param.getFirst("r");
			try {
				String href = URLDecoder.decode(redirect, "utf-8");
				String htmlString = "<a target=\"_blank\" href=\"" + href + "\">" + text + "</a>";
				str = str.replace(link, htmlString);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * 将消息内容是json格式的字符串，转换为集合对象（图文消息）
	 * 
	 * @author 马恩伟
	 * @date 2014-9-1
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> toList(String content) {
		return JacksonJsonMapper.jsonToObject(content, List.class);
	}

	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> toMap(String content) {
		return JacksonJsonMapper.jsonToObject(content, Map.class);
	}
}
