package com.covisint.wechat.utils;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存存放AccessToken
 */
public class AccessTokenData {
	private static AccessTokenData instance = null;
	private Map<String, AccessToken> map = null;

	private AccessTokenData() {
		map = new ConcurrentHashMap<String, AccessToken>();
	}

	public static AccessTokenData getInstance() {
		if (instance == null) {
			instance = new AccessTokenData();
		}
		return instance;
	}

	/**
	 * 验证是否token有效
	 */
	public boolean validateToken(String username) {
		if (this.contain(username)) {
			AccessToken accessToken = map.get(username);
			return accessToken.isExpires();
		}
		return false;
	}

	/**
	 * 保存token
	 */
	public void putToken(String username, String token) {
		map.put(username, new AccessToken(token));
	}

	/**
	 * 保存token
	 */
	public void putToken(String username, String token, Long expiresIn) {
		map.put(username, new AccessToken(token, expiresIn));
	}

	/**
	 * 获取token
	 */
	public String getToken(String username) {
		return map.get(username).getToken();
	}

	/**
	 * 判断是否存在
	 */
	private boolean contain(String username) {
		return map.containsKey(username);
	}

	/**
	 * Token实体对象
	 */
	class AccessToken {
		private String token;// token
		private Long regTime;// 存放时间
		private Long expiresIn;// 有效时间

		/**
		 * 构造函数
		 */
		public AccessToken(String token) {
			this(token, 7200L);// 默认7200秒
		}

		/**
		 * 构造函数
		 */
		public AccessToken(String token, Long expiresIn) {
			this.token = token;
			this.regTime = Calendar.getInstance().getTimeInMillis();
			this.expiresIn = expiresIn;
		}

		public String getToken() {
			return token;
		}

		/**
		 * 验证是否已过期
		 */
		public boolean isExpires() {
			Long current = Calendar.getInstance().getTimeInMillis();
			return current - regTime < expiresIn * 1000;
		}
	}
}
