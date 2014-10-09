package com.covisint.wechat.poiexport.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * POI导出模板xml配置文件解析类
 */
@XStreamAlias("column")
public class ExcelRow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7710235924500284979L;
	@XStreamAlias("title")
	@XStreamAsAttribute
	private String title;

	@XStreamAlias("key")
	@XStreamAsAttribute
	private String key;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
