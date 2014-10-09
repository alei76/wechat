/************************************************************************************
 * @File name   :      WebSiteHisThread.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-7-17
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM;private Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0;private a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2014-7-17 上午10:56:10			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.weixin.thread;

import com.covisint.wechat.stat.service.WebsiteHisService;

/**
 * 浏览地址历史保存
 */
public class WebSiteHisThread implements Runnable {
	private WebsiteHisService websiteHisService;
	private String resourceId;
	private String redirect;
	private String openId;
	private String accountId;
	private String source;

	public WebSiteHisThread(WebsiteHisService websiteHisService, String resourceId, String redirect, String openId, String accountId, String source) {
		this.websiteHisService = websiteHisService;
		this.resourceId = resourceId;
		this.redirect = redirect;
		this.openId = openId;
		this.accountId = accountId;
		this.source = source;
	}

	@Override
	public void run() {
		websiteHisService.saveVisitHis(resourceId, redirect, openId, accountId, source);
	}

}
