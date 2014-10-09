/************************************************************************************
 * @File name   :      LinkResourceServiceImpl.java
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
 * 2014-4-29 下午02:08:45			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.constant.DictCode;
import com.covisint.wechat.content.service.LinkResourceService;
import com.covisint.wechat.data.dao.WxLinkResourceDao;
import com.covisint.wechat.data.model.WxLinkResource;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.IdentifierUtils;
import com.covisint.wechat.utils.SessionUtils;

/**
 *
 */
@Service
public class LinkResourceServiceImpl implements LinkResourceService {
	@Autowired
	private WxLinkResourceDao wxLinkResourceDao;

	@Override
	public Page<WxLinkResource> pageLinkResource(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		String keyword = (String) paramMap.get("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			paramMap.put("likeName", "%" + keyword + "%");
		}
		paramMap.put("statusDictCode", DictCode.LINK_RESOURCE_STATUS);
		paramMap.put("status", WxLinkResource.LINK_STATUS_ENABLE);
		return wxLinkResourceDao.pageDomain(paramMap, current, pagesize);
	}

	@Override
	public boolean saveLinkResource(WxLinkResource wxLinkResource, String userId, String accountId) throws AjaxException {
		int row = 0;
		if (this.validate_resource(wxLinkResource)) {
			String resourceId = wxLinkResource.getResourceId();
			if (StringUtils.isEmpty(resourceId)) {
				wxLinkResource.setCreateBy(userId);
				wxLinkResource.setAccountId(accountId);
				wxLinkResource.setResourceId(IdentifierUtils.getId().generate().toString());
				wxLinkResource.setStatus(WxLinkResource.LINK_STATUS_ENABLE);
				if (this.validate_update(wxLinkResource, false)) {
					row = wxLinkResourceDao.insert(wxLinkResource);
				}
			} else {
				wxLinkResource.setAccountId(accountId);
				if (this.validate_update(wxLinkResource, true)) {
					row = wxLinkResourceDao.update(wxLinkResource);
				}
			}
		}
		return row > 0;
	}

	@Override
	public List<WxLinkResource> listLinkResource(Map<String, Object> paramMap) {
		WxSystemUser current = SessionUtils.getCurrentUser();
		paramMap.put("accountId", current.getCurrentAccount());
		paramMap.put("status", WxLinkResource.LINK_STATUS_ENABLE);
		return wxLinkResourceDao.findDomain(paramMap);
	}

	@Override
	public boolean changestatus(String resourceId) throws AjaxException {
		WxLinkResource linkResource = this.info(resourceId);
		linkResource.setStatus(WxLinkResource.LINK_STATUS_DISABLE);
		return wxLinkResourceDao.update(linkResource) > 0;
	}

	@Override
	public WxLinkResource info(String resourceId) {
		return wxLinkResourceDao.get(resourceId);
	}

	@Override
	public boolean batchchange(List<String> resourceIds) throws AjaxException {
		wxLinkResourceDao.updateBatch(WxLinkResource.LINK_STATUS_DISABLE, resourceIds);
		return true;
	}

	/**
	 * 后台验证数据合法性
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_resource(WxLinkResource wxLinkResource) throws AjaxException {
		String type = wxLinkResource.getType();
		String name = wxLinkResource.getName();
		if (StringUtils.isEmpty(name)) {
			String label = "页面名称";
			if (WxLinkResource.TYPE_WEB_SERVICE.equals(type)) {
				label = "接口名称";
			}
			throw new AjaxException("请填写" + label);
		}
		String href = wxLinkResource.getResourceHref();
		if (StringUtils.isEmpty(href)) {
			String label = "页面地址";
			if (WxLinkResource.TYPE_WEB_SERVICE.equals(type)) {
				label = "接口地址";
			}
			throw new AjaxException("请填写" + label);
		}
		return true;
	}

	/**
	 * 后台验证数据合法性-判断是否存在该资源或接口
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_update(WxLinkResource wxLinkResource, boolean update) throws AjaxException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", WxLinkResource.LINK_STATUS_ENABLE);
		paramMap.put("type", wxLinkResource.getType());
		paramMap.put("name", wxLinkResource.getName());
		paramMap.put("accountId", wxLinkResource.getAccountId());
		if (update) {
			paramMap.put("except", wxLinkResource.getResourceId());
		}
		int row = wxLinkResourceDao.checkExists(paramMap);
		if (row > 0) {
			String label = "页面名称";
			String type = wxLinkResource.getType();
			if (WxLinkResource.TYPE_WEB_SERVICE.equals(type)) {
				label = "接口名称";
			}
			throw new AjaxException("该" + label + "已存在");
		}
		return true;
	}
}
