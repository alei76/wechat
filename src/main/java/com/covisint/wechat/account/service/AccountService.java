/************************************************************************************
 * @File name   :      AccountService.java
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
package com.covisint.wechat.account.service;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxWechatAccount;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;

/**
 * 微信账号Service
 */
public interface AccountService {
	/**
	 * 分页查询
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public Page<WxWechatAccount> pageAccount(Map<String, Object> paramMap, int current, int pagesize);

	/**
	 * 保存微信账号
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public boolean saveAccount(WxWechatAccount wxWechatAccount, String userId) throws AjaxException;

	/**
	 * 列表查询
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-28
	 */
	public List<WxWechatAccount> listAccount(Map<String, Object> paramMap);

	/**
	 * 查询详情
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-14
	 */
	public WxWechatAccount info(String accountId);

	/**
	 * 更改微信账号状态
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-14
	 */
	public boolean changeStatus(String accountId) throws AjaxException;

	/**
	 * 删除微信号
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public boolean deleteAccount(String accountId) throws AjaxException;
}
