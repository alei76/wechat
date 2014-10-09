/************************************************************************************
 * @File name   :      AttachmentService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-7
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
 * 2014-5-7 下午02:46:51			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.covisint.wechat.data.model.WxFiles;
import com.covisint.wechat.data.model.WxMediaAtta;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;

/**
 *
 */
public interface AttachmentService {

	/**
	 * 分页查询富媒体文件
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-7
	 */
	public Page<WxMediaAtta> pageMediaAtta(Map<String, Object> paramMap, Integer current, Integer pagesize);

	/**
	 * 保存媒体文件
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-9
	 */
	public boolean saveMedia(WxMediaAtta wxMediaAtta, String userId, String accountId, MultipartFile image, MultipartFile media) throws AjaxException;

	/**
	 * 编辑保存媒体文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public boolean editMedia(WxMediaAtta wxMediaAtta, String accountId, MultipartFile image, MultipartFile media) throws AjaxException;

	/**
	 * 查询媒体文件信息
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-30
	 */
	public WxMediaAtta getAtta(String attaId);

	/**
	 * 保存图片类型媒体文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String saveImage(String userId, String accountId, MultipartFile image);

	/**
	 * 获取媒体下特定类型的文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public WxFiles getFile(String attaId, String type);
}
