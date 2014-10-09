/************************************************************************************
 * @File name   :      ArticleItem.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-6
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
 * 2014-6-6 上午11:10:30			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.weixin.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 */
@XStreamAlias("item")
public class ArticleItem {
	@XStreamAlias("Title")
	private String title;
	@XStreamAlias("Description")
	private String description;
	@XStreamAlias("PicUrl")
	private String picurl;
	@XStreamAlias("Url")
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
