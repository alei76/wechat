package com.covisint.wechat.weixin.service;

import java.util.List;

import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.PropertiesUtils;
import com.covisint.wechat.weixin.model.Button;

/**
 * 菜单管理
 * 
 * @author mæw
 * @date 2014-1-13
 */
public interface MemuService {
	public static final String MENU_URL = PropertiesUtils.getValue("weixin.api_address") + "/cgi-bin/menu/create?access_token=";

	/**
	 * 创建菜单
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public Integer createMenu(List<Button> menu, String accountId) throws WeChatException;
}
