/************************************************************************************
 * @File name   :      FansServiceImpl.java
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
 * 2014-5-15 上午11:31:57			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.member.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.WxFansDao;
import com.covisint.wechat.data.mapper.WxFansMapper;
import com.covisint.wechat.data.model.WxFans;
import com.covisint.wechat.data.model.WxGroup;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.member.service.FansService;
import com.covisint.wechat.member.service.GroupService;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.poiexport.support.JdbcExcelExportDao;
import com.covisint.wechat.utils.IdentifierUtils;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.utils.ThreadPoolUtils;
import com.covisint.wechat.weixin.service.MemberService;
import com.covisint.wechat.weixin.thread.UpdateFansInfoThread;

/**
 *
 */
@Service
public class FansServiceImpl implements FansService {
	@Autowired
	private WxFansDao wxFansDao;
	@Autowired
	private JdbcExcelExportDao exportDao;
	@Autowired
	private MemberService memberService;
	@Autowired
	private GroupService groupService;

	@SuppressWarnings("unchecked")
	@Override
	public boolean syncFansList(String nextOpenid, String accountId) throws AjaxException {
		WxGroup wxGroup = groupService.getSystemGroup(accountId);
		String groupId = wxGroup.getGroupId();
		try {
			String jsonString = memberService.getFansList(null, accountId);
			Map<String, Object> result = JacksonJsonMapper.jsonToObject(jsonString, Map.class);
			String nextOpenId = (String) result.get("next_openid");
			Map<String, Object> dataMap = (Map<String, Object>) result.get("data");
			List<String> openList = (List<String>) dataMap.get("openid");
			List<String> newList = this.getNewOpenIds(openList, accountId);// 获取增量openId数据
			wxFansDao.batchInsert(newList, accountId, groupId);// 批量保存
			String lastId = openList.get(openList.size() - 1);
			if (!lastId.equals(nextOpenId)) {// 根据返回值判断是否需要递归获取
				return this.syncFansList(nextOpenId, accountId);
			}
			return true;
		} catch (WeChatException e) {
			throw new AjaxException(e);
		}
	}

	@Override
	public Page<WxFans> pageFans(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		paramMap.put("status", WxFans.STATUS_ENABLE);
		String keyword = (String) paramMap.get("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			paramMap.put("likeNickName", "%" + keyword + "%");
		}
		return wxFansDao.pageDomain(paramMap, current, pagesize);
	}

	@Override
	public boolean changeFansGroup(String fromGroupId, String toGroupId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fromGroupId", fromGroupId);
		paramMap.put("toGroupId", toGroupId);
		return wxFansDao.changeFansGroup(paramMap) > 0;
	}

	@Override
	public boolean changeGroup(String groupId, List<String> fansIdList) {
		wxFansDao.changeGroup(groupId, fansIdList);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateFansInfo(String fansId) throws AjaxException {
		int row = 0;
		WxFans fans = wxFansDao.get(fansId);
		if (fans != null) {
			String openId = fans.getOpenId();
			String accountId = fans.getAccountId();
			try {
				String member = memberService.getInfo(openId, accountId);// 从微信平台获取粉丝基本信息
				Map<String, Object> memberInfo = JacksonJsonMapper.jsonToObject(member, Map.class);
				String nickName = memberInfo.get("nickname").toString();
				String sex = memberInfo.get("sex").toString();
				String language = memberInfo.get("language").toString();
				String city = memberInfo.get("city").toString();
				String province = memberInfo.get("province").toString();
				String country = memberInfo.get("country").toString();
				String headimgUrl = memberInfo.get("headimgurl").toString();
				String subTime = memberInfo.get("subscribe_time").toString();
				fans.setCity(city);
				fans.setCountry(country);
				fans.setHeadimgUrl(headimgUrl);
				fans.setLanguage(language);
				fans.setNickName(nickName);
				fans.setProvince(province);
				fans.setSex(sex);
				fans.setSubTime(subTime);
				row = wxFansDao.update(fans);
			} catch (WeChatException e) {
				throw new AjaxException(e);
			}
		}
		return row > 0;
	}

	@Override
	public boolean subscribeFans(String openId, String accountId) {
		int row = 0;
		WxFans wxFans = this.getWxFans(openId, accountId);// 查询该openId是否曾经关注过
		String fansId = IdentifierUtils.getId().generate().toString();
		if (wxFans == null) {
			WxGroup wxGroup = groupService.getSystemGroup(accountId);// 关注默认添加至未分组
			String groupId = wxGroup.getGroupId();
			wxFans = new WxFans();
			wxFans.setAccountId(accountId);
			wxFans.setFansId(fansId);
			wxFans.setOpenId(openId);
			wxFans.setGroupId(groupId);
			wxFans.setStatus(WxFans.STATUS_ENABLE);
			row = wxFansDao.insert(wxFans);
		} else {
			fansId = wxFans.getFansId();
			wxFans.setStatus(WxFans.STATUS_ENABLE);// 重新关注后，将状态改为正常
			row = wxFansDao.update(wxFans);
		}
		if (row > 0) {
			UpdateFansInfoThread task = new UpdateFansInfoThread(fansId, this);// 更新用户基本信息
			ThreadPoolUtils.execute(task);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public WxFans getWxFans(String openId, String accountId) {
		return wxFansDao.get(openId, accountId);
	}

	@Override
	public boolean unsubscribe(String openId, String accountId) {
		WxFans wxFans = new WxFans();
		wxFans.setOpenId(openId);
		wxFans.setAccountId(accountId);
		wxFans.setStatus(WxFans.STATUS_DISABLE);
		return wxFansDao.unsubscribe(wxFans) > 0;
	}

	@Override
	public WxFans infoFans(String fansId) {
		return wxFansDao.info(fansId);
	}

	@Override
	public List<String> listprov(String accountId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("status", WxFans.STATUS_ENABLE);
		return wxFansDao.listProv(paramMap);
	}

	@Override
	public void exportFans(Map<String, Object> paramMap, OutputStream outputStream) throws IOException {
		exportDao.export(WxFansMapper.getPageSql(paramMap), paramMap, "fans_export", outputStream);
	}

	/**
	 * 从list中比较出数据库中不存在的openId，做到增量保存
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private List<String> getNewOpenIds(List<String> openIds, String accountId) {
		return wxFansDao.getNewOpenIds(openIds, accountId);
	}

}
