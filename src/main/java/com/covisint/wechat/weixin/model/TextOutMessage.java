package com.covisint.wechat.weixin.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 输出文字消息
 * 
 */
public class TextOutMessage extends OutMessage {
	// 文本消息
	@XStreamAlias("Content")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
