/************************************************************************************
 * @File name   :      SecurityController.java
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
package com.covisint.wechat.security.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.covisint.wechat.constant.Constant;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.exception.AuthException;
import com.covisint.wechat.security.model.Credentials;
import com.covisint.wechat.security.service.UserService;
import com.covisint.wechat.utils.SessionUtils;
import com.covisint.wechat.utils.ValidateCode;
import com.covisint.wechat.utils.ValidateUtils;

/**
 * 安全认证控制器
 */
@Controller
public class SecurityController {
	@Autowired
	private UserService userService;

	/**
	 * 跳转到登陆页面
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("credentials") Credentials credentials, Model model) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		if (currentUser == null) {
			return "login";
		} else {
			String login = SessionUtils.getRedirectPath("/index");
			model.addAttribute("path", login);
			return "redirect_view";
		}
	}

	/**
	 * 执行登陆操作
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String auth(@ModelAttribute("credentials") Credentials credentials, Model model) {
		String sysCap = (String) SessionUtils.get(Constant.CURRENT_USER_VALIDATE_CODE_KEY);
		if (!ValidateUtils.isEmpty(credentials.getCaptcha())) {
			if (sysCap != null && sysCap.equalsIgnoreCase(credentials.getCaptcha())) {
				try {
					WxSystemUser user = userService.authUser(credentials);
					SessionUtils.put(Constant.CURRENT_USER, user);
					String login = SessionUtils.getRedirectPath("/index");
					model.addAttribute("path", login);
					return "redirect_view";
				} catch (AuthException e) {
					credentials.setPassword(null);
					model.addAttribute("message", e.getMessage());
				}
			} else {
				model.addAttribute("message", "验证码错误，请重新输入。");
			}
		} else {
			model.addAttribute("message", "请输入验证码。");
		}
		credentials.setCaptcha(null);
		return "login";
	}

	/**
	 * 执行登出操作
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(SessionStatus status, Model model) {
		status.setComplete();
		SessionUtils.clear();
		String login = SessionUtils.getRedirectPath("/index");
		model.addAttribute("path", login);
		return "redirect_view";
	}

	/**
	 * 跳转至首页
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = { "/index", "/" })
	public String index(Model model) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		if (currentUser == null) {
			String login = SessionUtils.getRedirectPath("/login");
			model.addAttribute("path", login);
			return "redirect_view";
		} else {
			return "main";
		}
	}

	/**
	 * 获取验证码
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void validateImages(HttpServletResponse response) throws IOException {
		response.reset();
		ValidateCode code = new ValidateCode(100, 40, 4);
		BufferedImage image = code.getImage();
		String validateCode = code.getCode();
		SessionUtils.put(Constant.CURRENT_USER_VALIDATE_CODE_KEY, validateCode);
		ImageIO.write(image, "png", response.getOutputStream());
	}
}
