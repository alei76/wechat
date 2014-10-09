/************************************************************************************
 * @File name   :      PageContainer.java
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
package com.covisint.wechat.page;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分页器实现类
 * 
 * @param <E>
 */
public class PageContainer<E> implements Page<E> {
	private static Logger logger = LoggerFactory.getLogger(PageContainer.class);
	public int pagesize = DEFAULT_PAGE_ROWS;

	/** 记录的总数 */
	private long totalRecords = 0;

	/** 得到的页码 */
	public int current = 1;

	/** 记录集合 */
	private Collection<E> records;

	/**
	 * 构造分页器
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public PageContainer(long totalRecords, int pagesize, int current, Collection<E> records) {
		this.totalRecords = totalRecords;
		this.pagesize = pagesize;
		this.current = current;
		this.records = records;
		if (logger.isDebugEnabled()) {
			logger.debug("totalRecords : " + totalRecords + ", and current : " + current + ", and pagesize : " + pagesize);
		}
	}

	@Override
	public long getTotal() {
		return totalRecords;
	}

	@Override
	public int getPageCount() {
		return pageCount(pagesize, totalRecords);
	}

	@Override
	public int getCurrent() {
		return current(current, pagesize, totalRecords);
	}

	@Override
	public Collection<E> getRows() {
		return this.records;
	}

	/**
	 * 计算开始行数
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public static int startRow(int current, int size, long total) {
		current = current(current, size, total);
		if (current <= 1)
			return 0;
		else
			return (current * size) - size;
	}

	/**
	 * 获取当前页
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	private static int current(int current, int size, long total) {
		if (current > pageCount(size, total)) {
			return pageCount(size, total);
		} else if (current < 1) {
			return 1;
		} else {
			return current;
		}
	}

	/**
	 * 计算总页数
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	private static int pageCount(int size, long total) {
		int count = (int) (total / size);
		if (total % size != 0) {
			count++;
		}
		return count;
	}

	/**
	 * 计算结束行数
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 */
	public static int endRow(int current, int size, long total) {
		current = current(current, size, total);
		int end = current * size;
		if (end >= total) {
			return (int) total;
		} else {
			return end;
		}
	}

	/**
	 * 得到每页行数
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-26
	 */
	@Override
	public int getPageSize() {
		return this.pagesize;
	}

}
