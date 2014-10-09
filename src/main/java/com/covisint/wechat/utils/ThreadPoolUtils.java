/************************************************************************************
 * @File name   :      ThreadPoolUtils.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-7-10
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
 * 2014-7-10 下午03:26:34			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * spring 线程池
 */
@Component
public class ThreadPoolUtils {
	@Autowired
	@Qualifier("messageExecutor")
	/**
	 * 消息处理线程池（可设置多个pool）
	 */
	private ThreadPoolTaskExecutor poolTaskExecutor;
	private static ThreadPoolUtils executor;

	/**
	 * 初始化工具类默认属性
	 */
	@PostConstruct
	public void init() {
		executor = this;
		executor.poolTaskExecutor = poolTaskExecutor;
	}

	/**
	 * 添加执行任务
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public static void execute(Runnable task) {
		executor.poolTaskExecutor.execute(task);
	}
}
