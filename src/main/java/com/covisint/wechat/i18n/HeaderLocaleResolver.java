/************************************************************************************
 * @File name   :      HeaderLocaleResolver.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-30
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
 * 2014-4-30 上午09:13:55			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

/**
 *
 */
public class HeaderLocaleResolver implements LocaleResolver {
	private Locale locale;

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		this.locale = locale;
	}
}
