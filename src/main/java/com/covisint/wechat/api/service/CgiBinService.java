/************************************************************************************
 * @File name   :      CgiBinService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-8-6
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
 * 2014-8-6 下午01:55:59			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.api.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
public interface CgiBinService {
	/**
	 * 获取access_token
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getToken(Map<String, String> param);

	/**
	 * 获取用户基本信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getUserInfo(Map<String, String> param);

	/**
	 * OAuth2.0中 拉取用户信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getSnsUserInfo(Map<String, String> param);

	/**
	 * OAuth2.0中 获取access_token及openId等信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getAccessToken(Map<String, String> param);

	/**
	 * 多媒体文件上传
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String uploadMedia(MultipartFile file, Map<String, String> param);

	/**
	 * 多媒体文件下载
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public void downloadMedia(Map<String, String> param, HttpServletResponse response);

	/**
	 * 发送消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String sendMessage(String body, Map<String, String> param);

}
