package com.covisint.wechat.weixin.service;

import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.PropertiesUtils;

/**
 * 粉丝管理
 * 
 * @author mæw
 * @date 2014-1-6
 */
public interface MemberService {
	public static final String USER_INFO = PropertiesUtils.getValue("weixin.api_address") + "/cgi-bin/user/info?access_token=";
	public static final String USER_LIST = PropertiesUtils.getValue("weixin.api_address") + "/cgi-bin/user/get?access_token=";

	/**
	 * 获取粉丝基本信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getInfo(String openId, String accountId) throws WeChatException;

	/**
	 * 获取粉丝基本信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getUserInfo(String openId, String accessToken) throws WeChatException;

	/**
	 * 获取粉丝列表
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getFansList(String nextOpenid, String accountId) throws WeChatException;

}
