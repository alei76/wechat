package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.ZtreeMenu;

public interface WxMenusDao {

	public List<ZtreeMenu> findSysMenu(Map<String, Object> paramMap);

	public List<ZtreeMenu> findUsrMenu(Map<String, Object> paramMap);

	public List<ZtreeMenu> findAllResource();
}