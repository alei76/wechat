package com.covisint.wechat.weixin.model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 输出图文消息
 */
public class NewsOutMessage extends OutMessage {

	@XStreamAlias("ArticleCount")
	private Integer articleCount;
	@XStreamAlias("Articles")
	private Articles articles;

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		if (articles != null) {
			if (articles.getItems().size() > 10) {
				List<ArticleItem> items = new ArrayList<ArticleItem>(articles.getItems().subList(0, 10));
				articles.setItems(items);
			}
			articleCount = articles.getItems().size();
		}
		this.articles = articles;
	}
}
