/************************************************************************************
 * @File name   :      CountFansJob.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-18
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
 * 2014-6-18 上午11:03:02			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.quartz.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.covisint.wechat.quartz.service.ReportFansService;

/**
 * 统计粉丝总数
 */
public class CountFansJob {
	@Autowired
	private ReportFansService reportFansService;

	public void count() {
		reportFansService.fansCountReport();
	}

}
