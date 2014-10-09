/************************************************************************************
 * @File name   :      Page.java
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

/**
 * 
 * 分页器接口，根据page,pageSize,total用于页面上分页显示多项内容，计算页码和当前页的偏移量，方便页面分页使用.
 * 
 */
public interface Page<E> {
	/** 默认每页显示容量 */
	public static final int DEFAULT_PAGE_ROWS = 10;

	/**
	 * 取总记录数
	 */
	public long getTotal();

	/**
	 * 取总页数.
	 */
	public int getPageCount();

	/**
	 * 得到当前页码
	 */
	public int getCurrent();

	/**
	 * 得到该页的数据
	 */
	public Collection<E> getRows();

	/**
	 * 得到每页行数
	 */
	public int getPageSize();
}
