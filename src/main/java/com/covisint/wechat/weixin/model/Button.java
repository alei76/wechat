package com.covisint.wechat.weixin.model;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单按钮
 * 
 */
public class Button implements Serializable {
	private static final long serialVersionUID = 6199705530561911836L;

	private String type;
	private String name;
	private String key;
	private String url;
	private List<Button> sub_button;

	public static final String VIEW = "view";
	public static final String CLICK = "click";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Button> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<Button> sub_button) {
		this.sub_button = sub_button;
	}
}
