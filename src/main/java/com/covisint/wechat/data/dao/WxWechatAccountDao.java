package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxWechatAccount;
import com.covisint.wechat.page.Page;

public interface WxWechatAccountDao {
	public int insert(WxWechatAccount wxWechatAccount);

	public int update(WxWechatAccount wxWechatAccount);

	public WxWechatAccount get(java.lang.String accountId);

	public Map<String, Object> findOne(java.lang.String accountId);

	public List<Map<String, Object>> findList(Map<String, Object> paramMap);

	public List<WxWechatAccount> findDomain(Map<String, Object> paramMap);

	public Page<WxWechatAccount> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	public List<WxWechatAccount> findUserAccount(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-14
	 * @param accountId
	 * @return
	 */
	public int changeStatus(String accountId);

	public int checkExists(Map<String, Object> paramMap);

}