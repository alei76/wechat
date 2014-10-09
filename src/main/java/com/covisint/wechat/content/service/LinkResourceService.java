/************************************************************************************
 * @File name   :      LinkResourceService.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-29
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
 * 2014-4-29 下午02:08:09			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.service;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxLinkResource;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;

/**
 * 外部资源管理Service
 */
public interface LinkResourceService {

	/**
	 * 分页列表显示
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-29
	 */
	public Page<WxLinkResource> pageLinkResource(Map<String, Object> paramMap, Integer current, Integer pagesize);

	/**
	 * 保存外部资料
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-29
	 */
	public boolean saveLinkResource(WxLinkResource wxLinkResource, String userId, String accountId) throws AjaxException;

	/**
	 * 列表显示
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 */
	public List<WxLinkResource> listLinkResource(Map<String, Object> paramMap);

	/**
	 * 
	 * 更改资源状态
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-13
	 */
	public boolean changestatus(String resourceId) throws AjaxException;

	/**
	 * 查询资源详情
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-13
	 */
	public WxLinkResource info(String resourceId);

	/**
	 * 批量删除
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public boolean batchchange(List<String> resourceIds) throws AjaxException;

}
