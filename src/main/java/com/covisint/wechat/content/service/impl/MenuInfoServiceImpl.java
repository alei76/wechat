/************************************************************************************
 * @File name   :      MenuInfoServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-4
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
 * 2014-5-4 下午01:31:09			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.covisint.wechat.content.service.MenuInfoService;
import com.covisint.wechat.data.dao.WxMenuInfoDao;
import com.covisint.wechat.data.model.WxLinkResource;
import com.covisint.wechat.data.model.WxMenuInfo;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.IdentifierUtils;
import com.covisint.wechat.utils.ValidateUtils;
import com.covisint.wechat.weixin.model.Button;
import com.covisint.wechat.weixin.service.BasicWeChatService;
import com.covisint.wechat.weixin.service.MemuService;

/**
 *
 */
@Service
public class MenuInfoServiceImpl implements MenuInfoService {
	@Autowired
	private WxMenuInfoDao wxMenuInfoDao;
	@Autowired
	private MemuService memuService;
	@Value("${weixin.platform}")
	private String platform;
	@Value("${weixin.authlink}")
	private String oauth;
	@Autowired
	private BasicWeChatService basicWeChatService;

	@Override
	public boolean createMenu(WxMenuInfo wxMenuInfo, String accountId, String userId) throws AjaxException {
		if (this.checkExists(accountId, wxMenuInfo)) {
			wxMenuInfo.setAccountId(accountId);
			wxMenuInfo.setMenuId(IdentifierUtils.getId().generate().toString());
			wxMenuInfo.setCreateBy(userId);
			wxMenuInfo.setStatus(WxMenuInfo.STATUS_ENABLE);
			String parentId = wxMenuInfo.getParentId();
			String name = wxMenuInfo.getName();
			if (WxMenuInfo.ROOT_PARNET_MENUID.equals(parentId)) {
				if (ValidateUtils.length(name) > 10) {
					throw new AjaxException("一级菜单名称长度不能超过5个中文字符");
				}
			}
			if (!WxMenuInfo.ROOT_PARNET_MENUID.equals(parentId)) {
				this.updateParentMenu(parentId);
			}
			int row = wxMenuInfoDao.createMenu(wxMenuInfo);
			return row > 0;
		}
		return false;
	}

	@Override
	public List<ZtreeMenu> getWxMenu(String accountId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("status", WxMenuInfo.STATUS_ENABLE);
		return wxMenuInfoDao.getWxMenu(paramMap);
	}

	@Override
	public WxMenuInfo getMenuInfo(String menuId) {
		return wxMenuInfoDao.getMenuInfo(menuId);
	}

	@Override
	public boolean delMenu(String menuId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		paramMap.put("status", WxMenuInfo.STATUS_DISABLE);
		return wxMenuInfoDao.deleteMenu(paramMap) > 0;
	}

	@Override
	public boolean updateMenuInfo(WxMenuInfo wxMenuInfo, String accountId) throws AjaxException {
		int row = 0;
		if (this.validate_menu(wxMenuInfo)) {
			String type = wxMenuInfo.getType();
			if (WxMenuInfo.MENU_TYPE_EVENT.equals(type)) {
				String eventKey = wxMenuInfo.getEventKey();
				if (StringUtils.isEmpty(eventKey)) {
					wxMenuInfo.setEventKey(IdentifierUtils.getId().generate().toString());
				}
				wxMenuInfo.setOauthScope("");
			} else {
				wxMenuInfo.setEventKey("");
				wxMenuInfo.setCheckBind(WxMenuInfo.CHECK_BIND_DISABLE);
				wxMenuInfo.setAnonTarget("");
				String scope = wxMenuInfo.getOauthScope();
				if (StringUtils.isEmpty(scope)) {// 如果没有选择
					wxMenuInfo.setOauthScope(WxMenuInfo.SCOPE_NO);// 默认为不适用oauth2.0菜单
				}
			}
			if (this.checkExists(accountId, wxMenuInfo)) {
				row = wxMenuInfoDao.update(wxMenuInfo);
			}
		}
		return row > 0;
	}

	@Override
	public boolean updateParentMenu(String menuId) {
		if (!StringUtils.isEmpty(menuId)) {
			return wxMenuInfoDao.updateParentMenu(menuId) > 0;
		}
		return false;
	}

	@Override
	public boolean moveMenuUp(String menuId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		paramMap.put("status", WxMenuInfo.STATUS_ENABLE);
		long row = wxMenuInfoDao.validateToMoveUp(paramMap);// 验证是否可上移
		if (row == 0) {
			return wxMenuInfoDao.moveMenuUp(paramMap) > 0;
		}
		return false;
	}

	@Override
	public boolean moveMenuDown(String menuId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		paramMap.put("status", WxMenuInfo.STATUS_ENABLE);
		return wxMenuInfoDao.moveMenuDown(paramMap) > 0;
	}

	@Override
	public boolean asyncMenu(String accountId) throws AjaxException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("status", WxMenuInfo.STATUS_ENABLE);
		paramMap.put("parentId", WxMenuInfo.ROOT_PARNET_MENUID);
		paramMap.put("resourceType", WxLinkResource.TYPE_WEBSITE_ADDRESS);
		paramMap.put("resourceStatus", WxLinkResource.LINK_STATUS_ENABLE);
		paramMap.put("templateStatus", WxReplymsgTemplate.STATUS_ENABLE);
		List<WxMenuInfo> list = wxMenuInfoDao.findMenuDomain(paramMap);// 获取所有一级菜单
		List<Button> menuButtons = new ArrayList<Button>();
		if (list.size() > 3) {
			list = list.subList(0, 3);
		}
		for (WxMenuInfo wmi : list) {// 循环迭代
			Button btn = null;
			paramMap.put("parentId", wmi.getMenuId());
			List<WxMenuInfo> subList = wxMenuInfoDao.findMenuDomain(paramMap);// 获取二级菜单
			if (subList.size() > 0) {// 有二级菜单，认为是一级菜单
				btn = this.convertMenu(wmi, true);// 转换对象
				List<Button> sub_menu = new ArrayList<Button>();
				for (WxMenuInfo subwmi : subList) {// 循环迭代二级菜单
					if (this.validate_sync_data(subwmi)) {// 验证菜单数据合法性
						Button subBtn = this.convertMenu(subwmi, false);
						sub_menu.add(subBtn);
					}
				}
				btn.setSub_button(sub_menu);
			} else {// 没有二级菜单，认为是一般菜单
				if (this.validate_sync_data(wmi)) {
					btn = this.convertMenu(wmi, false);
				}
			}
			if (null != btn) {
				menuButtons.add(btn);
			}
		}
		try {
			Integer errcode = memuService.createMenu(menuButtons, accountId);
			if (errcode == 0) {
				return true;
			} else {
				return false;
			}
		} catch (WeChatException e) {
			throw new AjaxException(e);
		}

	}

	/**
	 * 后台验证数据合法性——同名验证
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean checkExists(String accountId, WxMenuInfo wxMenuInfo) throws AjaxException {
		String menuId = wxMenuInfo.getMenuId();
		String name = wxMenuInfo.getName();
		String parentId = wxMenuInfo.getParentId();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("status", WxMenuInfo.STATUS_ENABLE);
		paramMap.put("parentId", parentId);
		paramMap.put("name", name);
		if (StringUtils.isNotEmpty(menuId)) {
			paramMap.put("except", menuId);
		}
		int row = wxMenuInfoDao.checkExists(paramMap);
		if (row > 0) {
			throw new AjaxException("该菜单下已存在相同名字的菜单");
		}
		return row == 0;
	}

	/**
	 * 后台验证数据合法性-非空验证
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_menu(WxMenuInfo wxMenuInfo) throws AjaxException {
		String name = wxMenuInfo.getName();
		if (StringUtils.isEmpty(name)) {
			throw new AjaxException("请输入菜单名称");
		}
		String parentId = wxMenuInfo.getParentId();
		if (!StringUtils.isEmpty(parentId)) {
			if (!WxMenuInfo.ROOT_PARNET_MENUID.equals(parentId)) {
				String type = wxMenuInfo.getType();
				if (StringUtils.isEmpty(type)) {
					throw new AjaxException("请选择菜单类型");
				}
				String targetId = wxMenuInfo.getTarget();
				if (StringUtils.isEmpty(targetId)) {
					throw new AjaxException("请选择事件地址");
				}
				String check = wxMenuInfo.getCheckBind();
				if (WxMenuInfo.CHECK_BIND_ENABLE.equals(check)) {
					String anonTarget = wxMenuInfo.getAnonTarget();
					if (StringUtils.isEmpty(anonTarget)) {
						throw new AjaxException("请选择未绑定事件地址");
					}
				}
			} else {
				if (ValidateUtils.length(name) > 10) {
					throw new AjaxException("一级菜单名称长度不能超过5个中文字符");
				}
			}
		}
		return true;
	}

	/**
	 * 后台验证数据合法性——非空验证
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_sync_data(WxMenuInfo wxMenuInfo) throws AjaxException {
		String name = wxMenuInfo.getName();
		String type = wxMenuInfo.getType();
		if (StringUtils.isEmpty(type)) {
			throw new AjaxException("请选择菜单《" + name + "》的类型。");
		}
		String target = wxMenuInfo.getTarget();
		if (StringUtils.isEmpty(target)) {
			throw new AjaxException("请选择菜单《" + name + "》的事件地址。");
		}
		return true;
	}

	/**
	 * 转换成微信菜单的对象
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-7
	 */
	private Button convertMenu(WxMenuInfo wxMenuInfo, boolean parentMenu) {
		if (parentMenu) {
			Button button = new Button();
			button.setName(wxMenuInfo.getName());
			return button;
		} else {
			String type = wxMenuInfo.getType();
			if (!StringUtils.isEmpty(type)) {
				if (WxMenuInfo.MENU_TYPE_WEBSITE.equals(type)) {// 网页
					Button button = new Button();
					button.setName(wxMenuInfo.getName());
					button.setType(Button.VIEW);

					String path = platform + oauth + wxMenuInfo.getMenuId();
					UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(path);
					UriComponents redirectUri = uriBuilder.build().encode();
					path = redirectUri.toUriString();
					String accountId = wxMenuInfo.getAccountId();

					String scope = wxMenuInfo.getOauthScope();
					String url = null;
					if (WxMenuInfo.SCOPE_SNSAPI_USERINFO.equals(scope)) {// 高级作用域
						url = basicWeChatService.getOauthInfoUrl(path, accountId);
					} else if (WxMenuInfo.SCOPE_SNSAPI_BASE.equals(scope)) {// 基础作用域
						url = basicWeChatService.getOauthBaseUrl(path, accountId);
					} else {// 不适用Oauth2.0
						url = path;
					}
					button.setUrl(url);
					return button;
				} else if (WxMenuInfo.MENU_TYPE_EVENT.equals(type)) {// 事件
					Button button = new Button();
					button.setName(wxMenuInfo.getName());
					button.setKey(wxMenuInfo.getEventKey());
					button.setType(Button.CLICK);
					return button;
				}
			}
			return null;
		}

	}
}
