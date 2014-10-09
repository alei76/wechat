/************************************************************************************
 * @File name   :      FansService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-15
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
 * 2014-5-15 上午11:31:38			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.member.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxFans;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;

/**
 *
 */
public interface FansService {

	/**
	 * 从微信端获取粉丝列表
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-15
	 */
	public boolean syncFansList(String nextOpenid, String accountId) throws AjaxException;

	/**
	 * 分页查询所有粉丝
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-15
	 */
	public Page<WxFans> pageFans(Map<String, Object> paramMap, Integer current, Integer pagesize);

	/**
	 * 批量更改粉丝分组 从一个组到另一个组
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-20
	 */
	public boolean changeFansGroup(String fromGroupId, String toGroupId);

	/**
	 * 更改粉丝分组
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-16
	 */
	public boolean changeGroup(String groupId, List<String> fansIdList);

	/**
	 * 更新粉丝资料
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-28
	 */
	public boolean updateFansInfo(String fansId) throws AjaxException;

	/**
	 * 粉丝关注 事件处理
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-28
	 */
	public boolean subscribeFans(String openId, String accountId);

	/**
	 * 获取粉丝信息(从本地库)
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-28
	 */
	public WxFans getWxFans(String openId, String accountId);

	/**
	 * 获取粉丝信息(从本地库)
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-18
	 */
	public WxFans infoFans(String fansId);

	/**
	 * 粉丝取消关注 事件处理
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-6-4
	 */
	public boolean unsubscribe(String openId, String accountId);

	/**
	 * 所有粉丝省份列表
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public List<String> listprov(String accountId);

	/**
	 * excel导出
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public void exportFans(Map<String, Object> paramMap, OutputStream outputStream) throws IOException;
}
