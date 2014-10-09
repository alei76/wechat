/************************************************************************************
 * @File name   :      SessionUtils.java
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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.covisint.wechat.constant.Constant;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.data.model.WxWechatAccount;

/**
 * Session 操作辅助类
 * 
 * @author maew
 * 
 */
public class SessionUtils {

	/**
	 * 获取HttpServletRequest
	 * 
	 * @author 马恩伟
	 * @date 2014-9-4
	 */
	private static HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
		return request;
	}

	/**
	 * 获取session对象
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	private static HttpSession getSession() {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession(true);
		}
		return session;
	}

	/**
	 * 将对象存入session
	 */
	public static void put(String key, Object obj) {
		getSession().setAttribute(key, obj);
	}

	/**
	 * 获取重定向的跳转路径
	 * 
	 * @author 马恩伟
	 * @date 2014-9-4
	 */
	public static String getRedirectPath(String path) {
		HttpServletRequest request = getRequest();
		String contextPath = request.getContextPath();
		String redirect = contextPath;
		if (StringUtils.isNotEmpty(path)) {
			if (!path.startsWith("/")) {
				path = "/" + path;
			}
			redirect = redirect + path;
		}
		return redirect;
	}

	/**
	 * 从session取对象
	 */
	public static Object get(String key) {
		HttpSession session = getSession();
		if (session != null) {
			return session.getAttribute(key);
		} else {
			return null;
		}
	}

	/**
	 * 检查Session是否存在指定Key值的对象
	 */
	public static boolean contains(String key) {
		return (get(key) != null);
	}

	/**
	 * 清除指定session对象
	 */
	public static void remove(String key) {
		HttpSession session = getSession();
		if (session != null) {
			session.removeAttribute(key);
		}
	}

	/**
	 * 清除所有存放在session的对象
	 */
	public static void clear() {
		HttpSession session = getSession();
		if (session != null) {
			session.invalidate();
		}
	}

	/**
	 * 获取当前登录人信息
	 */
	public static WxSystemUser getCurrentUser() {
		return (WxSystemUser) get(Constant.CURRENT_USER);
	}

	/**
	 * 获取当前登录人所用微信账号信息
	 */
	public static WxWechatAccount getCurrentAccount(String accountId) {
		WxSystemUser current = getCurrentUser();
		if (current != null) {
			List<WxWechatAccount> accountList = current.getAccountList();
			if (null != accountList) {
				int size = accountList.size();
				for (int i = 0; i < size; i++) {
					WxWechatAccount account = accountList.get(i);
					if (account.getAccountId().equals(accountId)) {
						return account;
					}
				}
			}
		}
		return null;
	}
}
