/************************************************************************************
 * @File name   :      FansController.java
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
 * 2014-5-15 上午09:46:37			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.data.model.WxFans;
import com.covisint.wechat.data.model.WxGroup;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.member.service.FansService;
import com.covisint.wechat.member.service.GroupService;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.utils.SessionUtils;

/**
 *
 */
@Controller
@RequestMapping("/member/fans")
public class FansController {
	@Autowired
	private FansService fansService;
	@Autowired
	private GroupService groupService;

	/**
	 * 跳转至页面
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-15
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "member/fans/list";
	}

	/**
	 * 分页显示粉丝列表
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@ResponseBody
	@RequestMapping(value = "/grid", method = RequestMethod.POST)
	public Page<WxFans> grid(@RequestParam("page") Integer current, @RequestParam("rows") Integer pagesize, @RequestParam Map<String, Object> paramMap) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		paramMap.put("accountId", currentUser.getCurrentAccount());
		return fansService.pageFans(paramMap, current, pagesize);
	}

	/**
	 * 同步微信端粉丝列表至平台
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-15
	 */
	@ResponseBody
	@RequestMapping(value = "/syncfanslist", method = RequestMethod.POST)
	public Map<String, Object> syncfanslist() {
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser current = SessionUtils.getCurrentUser();
		boolean success = false;
		String message = null;
		try {
			success = fansService.syncFansList(null, current.getCurrentAccount());
			message = success ? "同步完成" : "同步失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 用户分组树形结构
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@ResponseBody
	@RequestMapping(value = "/grouptree", method = RequestMethod.POST)
	public List<ZtreeMenu> grouptree() {
		WxSystemUser current = SessionUtils.getCurrentUser();
		return groupService.listgroup(current.getCurrentAccount());
	}

	/**
	 * 用户分组列表查询
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@ResponseBody
	@RequestMapping(value = "/listgroup", method = RequestMethod.POST)
	public List<WxGroup> listgroup() {
		WxSystemUser current = SessionUtils.getCurrentUser();
		return groupService.findAll(current.getCurrentAccount());
	}

	/**
	 * 所有省份供查询选择
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@ResponseBody
	@RequestMapping(value = "/listprov", method = RequestMethod.POST)
	public List<String> listprov() {
		WxSystemUser current = SessionUtils.getCurrentUser();
		String accountId = current.getCurrentAccount();
		return fansService.listprov(accountId);
	}

	/**
	 * 创建用户分组
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@ResponseBody
	@RequestMapping(value = "/creategroup", method = RequestMethod.POST)
	public Map<String, Object> creategroup(WxGroup wxGroup) {
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser current = SessionUtils.getCurrentUser();
		boolean success = false;
		String message = null;
		try {
			success = groupService.saveGroup(wxGroup, current.getCurrentAccount(), current.getUserId());
			message = success ? "创建成功" : "创建失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 删除用户分组
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@ResponseBody
	@RequestMapping(value = "/deletegroup", method = RequestMethod.POST)
	public Map<String, Object> deletegroup(@RequestParam("groupId") String groupId) {
		Map<String, Object> result = new HashMap<String, Object>();
		WxSystemUser current = SessionUtils.getCurrentUser();
		boolean success = false;
		success = groupService.deleteGroup(groupId, current.getCurrentAccount());
		String message = success ? "删除成功" : "删除失败";
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 批量移动用户到分组
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/changegroup", method = RequestMethod.POST)
	public Map<String, Object> changegroup(@RequestParam("groupId") String groupId, @RequestParam("fansId") String fansId) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> fansIdList = JacksonJsonMapper.jsonToObject(fansId, List.class);
		boolean success = false;
		success = fansService.changeGroup(groupId, fansIdList);
		String message = success ? "更改成功" : "更改失败";
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * 同步粉丝基本信息至平台
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@ResponseBody
	@RequestMapping(value = "/asyncfansinfo", method = RequestMethod.POST)
	public Map<String, Object> asyncfansinfo(@RequestParam("fansId") String fansId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean success = false;
		String message = null;
		try {
			success = fansService.updateFansInfo(fansId);
			message = success ? "更新成功" : "更新失败";
		} catch (AjaxException e) {
			message = e.getMessage();
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}

	/**
	 * excel导出
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(@RequestParam Map<String, Object> paramMap, HttpServletResponse response) {
		WxSystemUser currentUser = SessionUtils.getCurrentUser();
		paramMap.put("accountId", currentUser.getCurrentAccount());
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel;charset=utf8");
			response.setContentType("application/x-msdownload");
			String fileName = "粉丝列表.xlsx";
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
			fansService.exportFans(paramMap, response.getOutputStream());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
