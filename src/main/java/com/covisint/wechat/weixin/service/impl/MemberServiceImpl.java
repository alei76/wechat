package com.covisint.wechat.weixin.service.impl;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.weixin.service.MemberService;
import com.covisint.wechat.weixin.util.AccessTokenUtils;
import com.covisint.wechat.weixin.util.HttpUtil;

@Service
public class MemberServiceImpl implements MemberService {

	@Override
	public String getInfo(String openId, String accountId) throws WeChatException {
		String accessToken = AccessTokenUtils.getAccessToken(accountId);
		return this.getUserInfo(openId, accessToken);
	}

	@Override
	public String getUserInfo(String openId, String accessToken) throws WeChatException {
		try {
			String url = USER_INFO.concat(accessToken + "&openid=" + openId);
			return HttpUtil.get(url);
		} catch (ClientProtocolException e) {
			throw new WeChatException(e);
		} catch (IOException e) {
			throw new WeChatException(e);
		}
	}

	@Override
	public String getFansList(String nextOpenid, String accountId) throws WeChatException {
		try {
			String accessToken = AccessTokenUtils.getAccessToken(accountId);
			String url = USER_LIST.concat(accessToken);
			if (!StringUtils.isEmpty(nextOpenid)) {
				url = url.concat("&next_openid=" + nextOpenid);
			}
			return HttpUtil.get(url);
		} catch (ClientProtocolException e) {
			throw new WeChatException(e);
		} catch (IOException e) {
			throw new WeChatException(e);
		}
	}

}
