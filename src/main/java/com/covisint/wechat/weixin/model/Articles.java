package com.covisint.wechat.weixin.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 多图文消息
 */
@XStreamAlias("Articles")
public class Articles {
	@XStreamImplicit(itemFieldName = "item")
	private List<ArticleItem> items;

	public List<ArticleItem> getItems() {
		return items;
	}

	public void setItems(List<ArticleItem> items) {
		this.items = items;
	}

}
