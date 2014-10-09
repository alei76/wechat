/************************************************************************************
 * @File name   :      EventViewServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-8-5
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
 * 2014-8-5 下午03:20:58			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.covisint.wechat.content.service.LinkResourceService;
import com.covisint.wechat.content.service.MenuInfoService;
import com.covisint.wechat.data.model.WxLinkResource;
import com.covisint.wechat.data.model.WxMenuInfo;
import com.covisint.wechat.data.model.WxWebsiteHis;
import com.covisint.wechat.stat.service.WebsiteHisService;
import com.covisint.wechat.weixin.model.InMessage;
import com.covisint.wechat.weixin.service.EventViewService;

/**
 *
 */
@Service
public class EventViewServiceImpl implements EventViewService {
	@Value("${weixin.platform}")
	private String platform;
	@Value("${weixin.authlink}")
	private String oauth;
	@Autowired
	private LinkResourceService linkResourceService;
	@Autowired
	private WebsiteHisService websiteHisService;
	@Autowired
	private MenuInfoService menuInfoService;

	@Override
	public void viewMessage(InMessage msg) {
		String eventKey = msg.getEventKey();
		String openId = msg.getFromUserName();
		String accountId = msg.getAccountId();
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(eventKey).build();
		MultiValueMap<String, String> param = uriComponents.getQueryParams();
		String redirect = param.getFirst("redirect_uri");
		uriComponents = UriComponentsBuilder.fromHttpUrl(redirect).build();
		String menuId = StringUtils.replace(uriComponents.toString(), platform + oauth, "");
		WxMenuInfo menuInfo = menuInfoService.getMenuInfo(menuId);
		String resourceId = menuInfo.getTarget();
		WxLinkResource resource = linkResourceService.info(resourceId);
		String path = resource.getResourceHref();
		websiteHisService.saveVisitHis(resourceId, path, openId, accountId, WxWebsiteHis.SOURCE_MENU);
	}

}
