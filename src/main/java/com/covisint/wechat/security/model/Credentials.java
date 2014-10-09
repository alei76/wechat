/************************************************************************************
 * @File name   :      Credentials.java
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
package com.covisint.wechat.security.model;

import java.io.Serializable;

/**
 * 登陆用户Model类
 */
public final class Credentials implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8707668640921778636L;
	/** The username. */
	private String username;
	/** The password. */
	private String password;
	/** captcha. */
	private String captcha;
	/** rememberMe. */
	private boolean rememberMe;
	/** autoLogin. */
	private boolean autoLogin;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Credentials that = (Credentials) o;
		if (password != null ? !password.equals(that.password) : that.password != null)
			return false;
		if (username != null ? !username.equals(that.username) : that.username != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = username != null ? username.hashCode() : 0;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}

}
