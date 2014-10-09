package com.covisint.wechat.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covisint.wechat.data.DaoSupport;
import com.covisint.wechat.data.dao.WxMenusDao;
import com.covisint.wechat.data.mapper.WxMenusMapper;
import com.covisint.wechat.data.model.ZtreeMenu;

@Component
public class WxMenusDaoImpl implements WxMenusDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public List<ZtreeMenu> findSysMenu(Map<String, Object> paramMap) {
		return dao.find(WxMenusMapper.getSysMenu(paramMap), paramMap, new ZtreeMenu());
	}

	@Override
	public List<ZtreeMenu> findUsrMenu(Map<String, Object> paramMap) {
		return dao.find(WxMenusMapper.getUsrMenu(paramMap), paramMap, new ZtreeMenu());
	}

	/**
	 * overridden:
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 * @see com.covisint.wechat.data.dao.WxMenusDao#findAllResource()
	 **/
	@Override
	public List<ZtreeMenu> findAllResource() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("enable", "01");
		return dao.find(WxMenusMapper.getAllMenu(paramMap), paramMap, new ZtreeMenu());
	}

}