package com.covisint.wechat.weixin.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.AccessTokenData;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.weixin.service.BasicWeChatService;

@Component
public class AccessTokenUtils {
	private static AccessTokenUtils utils;
	@Autowired
	private BasicWeChatService basicWeChatService;

	public AccessTokenUtils() {
		utils = this;
		utils.basicWeChatService = this.basicWeChatService;
	}

	/**
	 * 获取access_token
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getAccessToken(String accountId) throws WeChatException {
		boolean validate = AccessTokenData.getInstance().validateToken(accountId);// 本地验证是否有效
		if (!validate) {
			String jsonStr = utils.basicWeChatService.getAccessToken(accountId);// 从微信获取access_token
			Map<String, Object> map = JacksonJsonMapper.jsonToObject(jsonStr, Map.class);
			if (null == map.get("access_token")) {
				Integer errcode = (Integer) map.get("errcode");
				throw new WeChatException(errcode + "");
			}
			String token = map.get("access_token").toString();
			Long expiresIn = Long.valueOf(map.get("expires_in").toString());
			AccessTokenData.getInstance().putToken(accountId, token, expiresIn);// 存放至本地内存
		}
		return AccessTokenData.getInstance().getToken(accountId);
	}

	/**
	 * 获取APP_ID及APP_SECRET
	 */
	public static Map<String, String> getAccountInfo(String accountId) throws WeChatException {
		return utils.basicWeChatService.getAccountInfo(accountId);
	}
}
