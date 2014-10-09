/************************************************************************************
 * @File name   :      WxMenuInfoDao.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-05-04
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2014-5-4 14:40:02			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data.dao;

import java.util.List;
import java.util.Map;

import com.covisint.wechat.data.model.WxMenuInfo;
import com.covisint.wechat.data.model.ZtreeMenu;
import com.covisint.wechat.page.Page;

/**
 * 微信菜单表数据访问接口
 * 
 */
public interface WxMenuInfoDao {
	public int insert(WxMenuInfo wxMenuInfo);

	public int update(WxMenuInfo wxMenuInfo);

	public WxMenuInfo get(java.lang.String menuId);

	public Map<String, Object> findOne(java.lang.String menuId);

	public List<Map<String, Object>> findList(Map<String, Object> paramMap);

	public List<WxMenuInfo> findDomain(Map<String, Object> paramMap);

	public Page<WxMenuInfo> pageDomain(Map<String, Object> paramMap, int current, int pagesize);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-4
	 * @param menuInfo
	 * @return
	 */
	public int createMenu(WxMenuInfo wxMenuInfo);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-4
	 * @param paramMap
	 * @return
	 */
	public List<ZtreeMenu> getWxMenu(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-4
	 * @param menuId
	 * @return
	 */
	public WxMenuInfo getMenuInfo(String menuId);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 * @param paramMap
	 * @return
	 */
	public int deleteMenu(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 * @param menuId
	 * @return
	 */
	public int updateParentMenu(String menuId);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-5
	 * @param paramMap
	 * @return
	 */
	public int moveMenuUp(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 * @param paramMap
	 * @return
	 */
	public int moveMenuDown(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 * @param paramMap
	 * @return
	 */
	public Long validateToMoveUp(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-6
	 * @param paramMap
	 * @return
	 */
	public List<WxMenuInfo> findMenuDomain(Map<String, Object> paramMap);

	/**
	 * @Author : 马恩伟
	 * @Date : 2014-5-20
	 * @param paramMap
	 * @return
	 */
	public WxMenuInfo getMessageMenu(Map<String, Object> paramMap);

	public int checkExists(Map<String, Object> paramMap);

}