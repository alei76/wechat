package com.covisint.wechat.data.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxWechatAccountDao;
import com.covisint.wechat.data.mapper.WxWechatAccountMapper;
import com.covisint.wechat.data.model.WxWechatAccount;
import com.covisint.wechat.page.Page;

@Component
public class WxWechatAccountDaoImpl implements WxWechatAccountDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(WxWechatAccount wxWechatAccount) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", wxWechatAccount.getAccountId());
		paramMap.put("name", wxWechatAccount.getName());
		paramMap.put("type", wxWechatAccount.getType());
		paramMap.put("appId", wxWechatAccount.getAppId());
		paramMap.put("appSecret", wxWechatAccount.getAppSecret());
		paramMap.put("token", wxWechatAccount.getToken());
		paramMap.put("createBy", wxWechatAccount.getCreateBy());
		paramMap.put("createTime", wxWechatAccount.getCreateTime());
		paramMap.put("status", wxWechatAccount.getStatus());
		paramMap.put("accountNo", wxWechatAccount.getAccountNo());
		return dao.insert(WxWechatAccountMapper.getInsertSql(paramMap), paramMap);
	}

	@Override
	public int update(WxWechatAccount wxWechatAccount) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", wxWechatAccount.getAccountId());
		paramMap.put("name", wxWechatAccount.getName());
		paramMap.put("type", wxWechatAccount.getType());
		paramMap.put("appId", wxWechatAccount.getAppId());
		paramMap.put("appSecret", wxWechatAccount.getAppSecret());
		paramMap.put("token", wxWechatAccount.getToken());
		paramMap.put("createBy", wxWechatAccount.getCreateBy());
		paramMap.put("createTime", wxWechatAccount.getCreateTime());
		paramMap.put("status", wxWechatAccount.getStatus());
		paramMap.put("accountNo", wxWechatAccount.getAccountNo());
		return dao.update(WxWechatAccountMapper.getUpdateSql(paramMap), paramMap);
	}

	@Override
	public WxWechatAccount get(java.lang.String accountId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		return dao.get(WxWechatAccountMapper.getFindOneSql(paramMap), paramMap, new WxWechatAccount());
	}

	@Override
	public Map<String, Object> findOne(java.lang.String accountId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		return dao.findOne(WxWechatAccountMapper.getFindOneSql(paramMap), paramMap);
	}

	@Override
	public List<Map<String, Object>> findList(Map<String, Object> paramMap) {
		return dao.find(WxWechatAccountMapper.getFindAllSql(paramMap), paramMap);
	}

	@Override
	public List<WxWechatAccount> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxWechatAccountMapper.getFindAllSql(paramMap), paramMap, new WxWechatAccount());
	}

	@Override
	public Page<WxWechatAccount> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxWechatAccountMapper.getPageSql(paramMap), paramMap, current, pagesize, new RowMapper<WxWechatAccount>() {

			@Override
			public WxWechatAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxWechatAccount domain = new WxWechatAccount();
				domain.setAccountId(rs.getString("ACCOUNT_ID"));
				domain.setName(rs.getString("NAME"));
				domain.setType(rs.getString("TYPE"));
				domain.setAppId(rs.getString("APP_ID"));
				domain.setAppSecret(rs.getString("APP_SECRET"));
				domain.setToken(rs.getString("TOKEN"));
				domain.setCreateBy(rs.getString("CREATE_BY"));
				domain.setCreateTime(rs.getString("CREATE_TIME"));
				domain.setStatus(rs.getString("STATUS"));
				domain.setAccountNo(rs.getString("ACCOUNT_NO"));
				domain.setTypeCn(rs.getString("TYPE_CN"));
				return domain;
			}
		});
	}

	@Override
	public List<WxWechatAccount> findUserAccount(Map<String, Object> paramMap) {
		return dao.find(WxWechatAccountMapper.getUserAccountSql(paramMap), paramMap, new WxWechatAccount());
	}

	@Override
	public int changeStatus(String accountId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		paramMap.put("enable", WxWechatAccount.ACCOUNT_STATUS_ENABLE);
		paramMap.put("disable", WxWechatAccount.ACCOUNT_STATUS_DISABLE);
		return dao.update(WxWechatAccountMapper.getChangeStatusSql(paramMap), paramMap);
	}

	@Override
	public int checkExists(Map<String, Object> paramMap) {
		return dao.getJdbcTemplate().queryForObject(WxWechatAccountMapper.getCheckExistsSql(paramMap), paramMap, Integer.class);
	}
}