package com.covisint.wechat.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxDataDictionaryDao;
import com.covisint.wechat.data.mapper.WxDataDictionaryMapper;
import com.covisint.wechat.data.model.WxDataDictionary;
import com.covisint.wechat.page.Page;

@Component
public class WxDataDictionaryDaoImpl implements WxDataDictionaryDao {
	@Autowired
	private DaoSupport dao;
	
	@Override
	public int insert(WxDataDictionary wxDataDictionary) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("catalogCode", wxDataDictionary.getCatalogCode());
		paramMap.put("catalogDesc", wxDataDictionary.getCatalogDesc());
		paramMap.put("itemCode", wxDataDictionary.getItemCode());
		paramMap.put("itemDesc", wxDataDictionary.getItemDesc());
		paramMap.put("itemOrder", wxDataDictionary.getItemOrder());
		paramMap.put("enabled", wxDataDictionary.getEnabled());
		return dao.insert(WxDataDictionaryMapper.getInsertSql(paramMap), paramMap);
	}
	
	@Override
	public int update(WxDataDictionary wxDataDictionary) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("catalogCode", wxDataDictionary.getCatalogCode());
		paramMap.put("catalogDesc", wxDataDictionary.getCatalogDesc());
		paramMap.put("itemCode", wxDataDictionary.getItemCode());
		paramMap.put("itemDesc", wxDataDictionary.getItemDesc());
		paramMap.put("itemOrder", wxDataDictionary.getItemOrder());
		paramMap.put("enabled", wxDataDictionary.getEnabled());
		return dao.update(WxDataDictionaryMapper.getUpdateSql(paramMap), paramMap);
	}
	
	@Override
	public WxDataDictionary get(java.lang.String catalogCode, java.lang.String itemCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("catalogCode", catalogCode);
		paramMap.put("itemCode", itemCode);
		return dao.get(WxDataDictionaryMapper.getFindOneSql(paramMap), paramMap, new WxDataDictionary());
	}
	
	@Override
	public Map<String, Object> findOne(java.lang.String catalogCode, java.lang.String itemCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("catalogCode", catalogCode);
		paramMap.put("itemCode", itemCode);
		return dao.findOne(WxDataDictionaryMapper.getFindOneSql(paramMap), paramMap);
	}
	
	@Override
	public List<Map<String, Object>> findList(Map<String, Object> paramMap) {
		return dao.find(WxDataDictionaryMapper.getFindAllSql(paramMap), paramMap);
	}

	@Override
	public List<WxDataDictionary> findDomain(Map<String, Object> paramMap) {
		return dao.find(WxDataDictionaryMapper.getFindAllSql(paramMap), paramMap, new WxDataDictionary());
	}

	@Override
	public Page<WxDataDictionary> pageDomain(Map<String, Object> paramMap, int current, int pagesize) {
		return dao.page(WxDataDictionaryMapper.getPageSql(paramMap), paramMap, current, pagesize, new WxDataDictionary());
	}
}