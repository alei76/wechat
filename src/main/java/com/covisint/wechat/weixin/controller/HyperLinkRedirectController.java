/************************************************************************************
 * @File name   :      HyperLinkRedirectController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-20
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
 * 2014-5-20 下午02:41:42			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.weixin.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.covisint.wechat.content.service.LinkResourceService;
import com.covisint.wechat.content.service.MenuInfoService;
import com.covisint.wechat.data.model.WxLinkResource;
import com.covisint.wechat.data.model.WxMenuInfo;
import com.covisint.wechat.data.model.WxWebsiteHis;
import com.covisint.wechat.stat.service.WebsiteHisService;
import com.covisint.wechat.utils.ThreadPoolUtils;
import com.covisint.wechat.weixin.thread.WebSiteHisThread;

/**
 * 路径处理控制器
 */
@Controller
@RequestMapping("/api-wechat/hyperlink")
public class HyperLinkRedirectController {
	@Autowired
	private LinkResourceService linkResourceService;
	@Autowired
	private MenuInfoService menuInfoService;
	@Autowired
	private WebsiteHisService websiteHisService;

	/**
	 * 处理OAuth2.0的路径重定向（一般用户菜单配置上的路径）
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping("/auth/{menuId}")
	public String auth(Model model, @PathVariable("menuId") String menuId, @RequestParam(value = "code", required = false, defaultValue = "") String code) {
		WxMenuInfo menuInfo = menuInfoService.getMenuInfo(menuId);
		if (menuInfo != null) {
			String resourceId = menuInfo.getTarget();
			WxLinkResource resource = linkResourceService.info(resourceId);
			if (resource != null) {
				String path = this.getRedirectPath(resource, code);
				model.addAttribute("path", path);
				return "redirect_view";
			}
		}
		return null;// 可以跳转至404
	}

	/**
	 * 重定向到外部资源或路径 （一般用户消息回复内容，图文消息链接）
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping("/redirect")
	public String redirect(Model model, @RequestParam(value = "r") String redirect, @RequestParam(value = "o") String openId, @RequestParam(value = "a") String accountId) {
		WebSiteHisThread task = new WebSiteHisThread(websiteHisService, null, redirect, openId, accountId, WxWebsiteHis.SOURCE_MESSAGE);
		ThreadPoolUtils.execute(task);
		model.addAttribute("path", redirect);
		return "redirect_view";
	}

	/**
	 * 获取重定向路径
	 */
	private String getRedirectPath(WxLinkResource resource, String code) {
		String path = resource.getResourceHref();
		if (!StringUtils.isEmpty(code) && !"authdeny".equals(code)) {
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(path);
			uriBuilder.queryParam("code", code);
			return uriBuilder.build().encode().toUriString();
		}
		return path;
	}
}
