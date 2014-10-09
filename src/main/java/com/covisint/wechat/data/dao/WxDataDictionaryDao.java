package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxDataDictionary;
import com.covisint.wechat.page.Page;

public interface WxDataDictionaryDao {
    public int insert(WxDataDictionary wxDataDictionary);
    
    public int update(WxDataDictionary wxDataDictionary);
    
	public WxDataDictionary get(java.lang.String catalogCode, java.lang.String itemCode);
	
	public Map<String, Object> findOne(java.lang.String catalogCode, java.lang.String itemCode);
	
	public List<Map<String, Object>> findList(Map<String, Object> paramMap);
	
	public List<WxDataDictionary> findDomain(Map<String, Object> paramMap);
	
	public Page<WxDataDictionary> pageDomain(Map<String, Object> paramMap, int current, int pagesize);
	
}