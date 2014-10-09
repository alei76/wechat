/************************************************************************************
 * @File name   :      SqlSessionDaoSupport.java
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.covisint.wechat.page.Page;
import com.covisint.wechat.page.PageContainer;

/**
 * 持久化接口定义实现类
 */
public class SqlSessionDaoSupport implements DaoSupport {
	private static Logger logger = LoggerFactory.getLogger(SqlSessionDaoSupport.class);
	/**
	 * ORACLE 分页查询头部
	 */
	private final static String PAGE_SQL_PREFIX = "select * from ( select temp_row.*, rownum num from ( ";
	/**
	 * ORACLE 分页查询尾部
	 */
	private final static String PAGE_SQL_END = " ) temp_row where rownum <= :pagina_end ) where num > :pagina_start";
	/**
	 * ORACLE 分页查询总条数头部
	 */
	private final static String TOTAL_SQL_PREFIX = " SELECT count(1) FROM ( ";
	/**
	 * ORACLE 分页查询总条数尾部
	 */
	private final static String TOTAL_SQL_END = " ) total_view ";

	private NamedParameterJdbcTemplate jdbcTemplate;

	public SqlSessionDaoSupport(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(String sql, Map<String, Object> paramMap) {
		if (logger.isDebugEnabled()) {
			logger.debug("doinsert sql : " + sql + ", and paramMap : " + paramMap);
		}
		return this.getJdbcTemplate().update(sql, paramMap);
	}

	@Override
	public int update(String sql, Map<String, Object> paramMap) {
		if (logger.isDebugEnabled()) {
			logger.debug("doupdate sql : " + sql + ", and paramMap : " + paramMap);
		}
		return this.getJdbcTemplate().update(sql, paramMap);
	}

	@Override
	public <E> E get(String sql, Map<String, Object> paramMap, RowMapper<E> rowMapper) {
		if (logger.isDebugEnabled()) {
			logger.debug("doget sql : " + sql + ", and paramMap : " + paramMap);
		}
		try {
			return this.getJdbcTemplate().queryForObject(sql, paramMap, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> findOne(String sql, Map<String, Object> paramMap) {
		if (logger.isDebugEnabled()) {
			logger.debug("doget sql : " + sql + ", and paramMap : " + paramMap);
		}
		try {
			return this.getJdbcTemplate().queryForObject(sql, paramMap, new ColumnMapRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> find(String sql, Map<String, Object> paramMap) {
		return this.find(sql, paramMap, new ColumnMapRowMapper());
	}

	@Override
	public <E> List<E> find(String sql, Map<String, Object> paramMap, RowMapper<E> rowMapper) {
		if (logger.isDebugEnabled()) {
			logger.debug("dofind sql : " + sql + ", and paramMap : " + paramMap);
		}
		return this.getJdbcTemplate().query(sql, paramMap, rowMapper);
	}

	@Override
	public <E> Page<E> page(String sql, Map<String, Object> paramMap, int current, int pagesize, RowMapper<E> rowMapper) {
		if (logger.isDebugEnabled()) {
			logger.debug("dopage sql : " + sql + ", and paramMap : " + paramMap);
		}
		StringBuffer totalSQL = new StringBuffer(TOTAL_SQL_PREFIX);
		totalSQL.append(sql);
		totalSQL.append(TOTAL_SQL_END);
		Long total = this.getJdbcTemplate().queryForObject(totalSQL.toString(), paramMap, Long.class);
		StringBuffer paginationSQL = new StringBuffer(PAGE_SQL_PREFIX);
		paginationSQL.append(sql);
		paginationSQL.append(PAGE_SQL_END);
		int start = PageContainer.startRow(current, pagesize, total);
		int end = PageContainer.endRow(current, pagesize, total);
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		paramMap.put("pagina_start", start);
		paramMap.put("pagina_end", end);
		List<E> records = this.getJdbcTemplate().query(paginationSQL.toString(), paramMap, rowMapper);
		return new PageContainer<E>(total, pagesize, current, records);
	}

	@Override
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public int delete(String sql, Map<String, Object> paramMap) {
		if (logger.isDebugEnabled()) {
			logger.debug("dodelete sql : " + sql + ", and paramMap : " + paramMap);
		}
		return this.getJdbcTemplate().update(sql, paramMap);
	}

}
