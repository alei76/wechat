package com.covisint.wechat.weixin.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 微信平台路径验证
 */
public final class Tools {
	/**
	 * 验证路径合法性
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public static final boolean checkSignature(String token, String signature, String timestamp, String nonce) {
		List<String> params = new ArrayList<String>();
		params.add(token);
		params.add(timestamp);
		params.add(nonce);
		Collections.sort(params, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String temp = params.get(0) + params.get(1) + params.get(2);
		return SHA1.encode(temp).equals(signature);
	}
}
