package com.covisint.wechat.security.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.dao.WxSystemUserDao;
import com.covisint.wechat.data.dao.WxWechatAccountDao;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.data.model.WxWechatAccount;
import com.covisint.wechat.exception.AuthException;
import com.covisint.wechat.security.model.Credentials;
import com.covisint.wechat.security.service.UserService;
import com.covisint.wechat.utils.MD5;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private WxSystemUserDao wxSystemUserDao;
	@Autowired
	private WxWechatAccountDao wxWechatAccountDao;

	@Override
	public WxSystemUser authUser(Credentials credentials) throws AuthException {
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
			password = MD5.md5(password);
			WxSystemUser user = wxSystemUserDao.getCurrentUser(username, password);
			if (user != null) {
				if (user.getStatus().equals(WxSystemUser.ADMIN_STATUS_ENABLE)) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("userId", user.getUserId());
					paramMap.put("status", WxWechatAccount.ACCOUNT_STATUS_ENABLE);
					List<WxWechatAccount> accountList = wxWechatAccountDao.findUserAccount(paramMap);// 用户所拥有的微信号
					if (accountList != null && accountList.size() > 0) {
						WxWechatAccount defaultAccount = accountList.get(0);// 从中取出一个作为用户登录的默认微信账户
						user.setCurrentAccount(defaultAccount.getAccountId());
					}
					user.setAccountList(accountList);
					return user;
				} else {
					throw new AuthException("您的账号已被停用。请联系管理员");
				}
			}
			throw new AuthException("账号或密码错误，请重新输入。");
		} else {
			throw new AuthException("账号或密码不能为空。");
		}
	}
}
