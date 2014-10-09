/************************************************************************************
 * @File name   :      GroupServiceImpl.java
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
 * 2014-5-15 下午03:58:26			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.WxGroupDao;
import com.covisint.wechat.data.model.WxGroup;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.member.service.FansService;
import com.covisint.wechat.member.service.GroupService;
import com.covisint.wechat.utils.IdentifierUtils;

/**
 *
 */
@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private WxGroupDao wxGroupDao;
	@Autowired
	private FansService fansService;

	@Override
	public boolean createSystemGroup(String accountId, String userId) {
		WxGroup wxGroup = new WxGroup();
		wxGroup.setAccountId(accountId);
		wxGroup.setCreateBy(userId);
		wxGroup.setGroupId(IdentifierUtils.getId().generate().toString());
		wxGroup.setGroupName("未分组");
		wxGroup.setType(WxGroup.TYPE_SYSTEM);
		wxGroup.setStatus(WxGroup.STATUS_ENABLE);
		return wxGroupDao.insert(wxGroup) > 0;
	}

	@Override
	public WxGroup getSystemGroup(String accountId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("type", WxGroup.TYPE_SYSTEM);
		return wxGroupDao.getSystemGroup(paramMap);
	}

	@Override
	public List<ZtreeMenu> listgroup(String accountId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("status", WxGroup.STATUS_ENABLE);
		List<ZtreeMenu> groupList = wxGroupDao.listGroup(paramMap);
		// 页面列表特有项
		ZtreeMenu all = new ZtreeMenu();
		all.setMenuDesc("全部用户");
		all.setMenuId("0");
		all.setMenuName("全部用户");
		groupList.add(all);
		return groupList;
	}

	@Override
	public List<WxGroup> findAll(String accountId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("status", WxGroup.STATUS_ENABLE);
		return wxGroupDao.findDomain(paramMap);
	}

	@Override
	public boolean saveGroup(WxGroup wxGroup, String accountId, String userId) throws AjaxException {
		String groupId = wxGroup.getGroupId();
		int row = 0;
		if (this.validate_group(wxGroup)) {
			if (StringUtils.isEmpty(groupId)) {
				wxGroup.setAccountId(accountId);
				wxGroup.setCreateBy(userId);
				wxGroup.setGroupId(IdentifierUtils.getId().generate().toString());
				wxGroup.setStatus(WxGroup.STATUS_ENABLE);
				wxGroup.setType(WxGroup.TYPE_NORMAL);
				if (this.checkExists(accountId, wxGroup.getGroupName())) {
					row = wxGroupDao.insert(wxGroup);
				} else {
					throw new AjaxException("请分组已存在");
				}
			} else {
				if (this.updateValidate(accountId, wxGroup.getGroupName(), groupId)) {
					row = wxGroupDao.update(wxGroup);
				} else {
					throw new AjaxException("请分组已存在");
				}
			}
		}
		return row > 0;
	}

	@Override
	public boolean deleteGroup(String groupId, String accountId) {
		WxGroup systemGroup = this.getSystemGroup(accountId);
		String toGroupId = systemGroup.getGroupId();
		fansService.changeFansGroup(groupId, toGroupId);
		WxGroup wxGroup = new WxGroup();
		wxGroup.setGroupId(groupId);
		wxGroup.setStatus(WxGroup.STATUS_DISABLE);
		return wxGroupDao.update(wxGroup) > 0;
	}

	@Override
	public boolean checkExists(String accountId, String name) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("status", WxGroup.STATUS_ENABLE);
		paramMap.put("type", WxGroup.TYPE_NORMAL);
		paramMap.put("groupName", name);
		int row = wxGroupDao.checkExists(paramMap);
		return row == 0;
	}

	/**
	 * 后台数据合法性验证——非空验证
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_group(WxGroup wxGroup) throws AjaxException {
		String name = wxGroup.getGroupName();
		if (StringUtils.isEmpty(name)) {
			throw new AjaxException("请填写分组名称");
		}
		return true;
	}

	/**
	 * 后台数据合法性验证——验证该名称分组是否存在
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean updateValidate(String accountId, String name, String except) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("status", WxGroup.STATUS_ENABLE);
		paramMap.put("type", WxGroup.TYPE_NORMAL);
		paramMap.put("groupName", name);
		paramMap.put("except", except);
		int row = wxGroupDao.checkExists(paramMap);
		return row == 0;
	}
}
