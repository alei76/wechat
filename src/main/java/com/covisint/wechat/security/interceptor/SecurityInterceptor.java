/************************************************************************************
 * @File name   :      SecurityInterceptor.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-7-15
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
 * 2014-7-15 下午01:59:45			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.security.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.covisint.wechat.admin.service.UserAdminService;
import com.covisint.wechat.constant.Constant;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.utils.PropertiesUtils;
import com.covisint.wechat.utils.SessionUtils;

/**
 * 权限拦截器
 */
public class SecurityInterceptor implements HandlerInterceptor {
	/**
	 * 需要拦截的（正则）
	 */
	private Pattern _mapping = null;
	/**
	 * 不需要拦截的（正则）
	 */
	private Pattern _exclude = null;
	private static String HEADER_KEY = PropertiesUtils.getValue("sso.key");
	@Autowired
	private UserAdminService userAdminService;

	public void setMapping(String mapping) {
		_mapping = Pattern.compile(mapping);
	}

	public void setExclude(String exclude) {
		_exclude = Pattern.compile(exclude);
	}

	// 前置拦截
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getServletPath();
		if (_exclude != null) {
			Matcher m = _exclude.matcher(uri);
			if (m.matches()) {
				return true;// 在排除范围内的
			}
		}
		if (_mapping != null) {
			Matcher m = _mapping.matcher(uri);
			if (m.matches()) {
				return this.check_user_valid(request, response);// 验证登录人身份
				//return this.check_sso_valid(request, response);// 验证登录人身份
			}
		}
		return true;// 无法找到路径匹配的
	}

	/**
	 * SSO方式登录人身份验证
	 * 
	 * @author 马恩伟
	 * @date 2014-8-25
	 */
	private boolean check_sso_valid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userName = request.getHeader(HEADER_KEY);// 请求头中包含登录人信息
		if (!StringUtils.isEmpty(userName)) {
			WxSystemUser currentUser = userAdminService.getSystemUser(userName);// 从数据库中获取该用户，如果不存在则自动创建
			if (currentUser != null) {
				SessionUtils.put(Constant.CURRENT_USER, currentUser);
				return true;
			}
		}
		System.out.println("重新登录");// 一些其他的错误，TODO，可操作重定向至验证，重新登录等页面。
		return false;
	}

	/**
	 * 登录/登出方式登录人身份验证
	 * 
	 * @author 马恩伟
	 * @date 2014-8-25
	 */
	private boolean check_user_valid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		WxSystemUser user = SessionUtils.getCurrentUser();
		if (user != null) {
			return true;
		} else {
			this.redirect(request, response);
			return false;
		}
	}

	/**
	 * 登录/登出方式重定向执行方法(一般指session过期)
	 * 
	 * @author 马恩伟
	 * @date 2014-8-25
	 */
	private void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String type = request.getHeader("x-requested-with");// 是否是页面ajax请求
		String ajax = request.getParameter("_ajax");// 是否是页面ajax请求
		String contextPath = request.getContextPath();
		String login = contextPath + "/login";
		if ("XMLHttpRequest".equals(type) || "true".equals(ajax)) {// 页面ajax异步请求
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("redirect", true);
			result.put("path", login);
			String json = JacksonJsonMapper.objectToJson(result, false);
			response.setContentType("text/json");
			response.setCharacterEncoding("utf-8");
			response.setStatus(403);
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} else {
			response.sendRedirect(login);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
