package com.covisint.wechat.weixin.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.weixin.model.Button;
import com.covisint.wechat.weixin.service.MemuService;
import com.covisint.wechat.weixin.util.AccessTokenUtils;
import com.covisint.wechat.weixin.util.HttpUtil;

@Service
public class MenuServiceImpl implements MemuService {

	@SuppressWarnings("unchecked")
	@Override
	public Integer createMenu(List<Button> menu, String accountId) throws WeChatException {
		try {
			String accessToken = AccessTokenUtils.getAccessToken(accountId);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("button", menu);
			String params = JacksonJsonMapper.objectToJson(result, false);
			String resultJson = HttpUtil.post(MENU_URL.concat(accessToken), params);
			Map<String, Object> resultMap = JacksonJsonMapper.jsonToObject(resultJson, Map.class);
			Integer errcode = (Integer) resultMap.get("errcode");
			return errcode;
		} catch (ClientProtocolException e) {
			throw new WeChatException(e);
		} catch (IOException e) {
			throw new WeChatException(e);
		}
	}
}
