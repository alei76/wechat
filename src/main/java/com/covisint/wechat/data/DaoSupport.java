/************************************************************************************
 * @File name   :      DaoSupport.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-25
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
 * 2014-4-25 上午08:55:24			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.data;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.covisint.wechat.page.Page;

/**
 * 持久化接口定义
 */
public interface DaoSupport {
	/**
	 * 保存
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public int insert(String sql, Map<String, Object> paramMap);

	/**
	 * 更新
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public int update(String sql, Map<String, Object> paramMap);

	/**
	 * 删除
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public int delete(String sql, Map<String, Object> paramMap);

	/**
	 * 查询单个信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public <E> E get(String sql, Map<String, Object> paramMap, RowMapper<E> rowMapper);

	/**
	 * 查询单个信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public Map<String, Object> findOne(String sql, Map<String, Object> paramMap);

	/**
	 * 查询列表信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public List<Map<String, Object>> find(String sql, Map<String, Object> paramMap);

	/**
	 * 查询列表信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public <E> List<E> find(String sql, Map<String, Object> paramMap, RowMapper<E> rowMapper);

	/**
	 * 分页查询
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public <E> Page<E> page(String sql, Map<String, Object> paramMap, int current, int pagesize, RowMapper<E> rowMapper);

	/**
	 * 获取连接
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public NamedParameterJdbcTemplate getJdbcTemplate();

}
