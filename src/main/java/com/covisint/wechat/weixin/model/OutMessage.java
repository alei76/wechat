package com.covisint.wechat.weixin.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OutMessage {
	@XStreamAlias("ToUserName")
	private String toUserName;
	@XStreamAlias("FromUserName")
	private String fromUserName;
	@XStreamAlias("CreateTime")
	private Long createTime;
	@XStreamAlias("MsgType")
	private String msgType;
	@XStreamAlias("MsgSource")
	private String msgSource;

	public String getMsgSource() {
		return msgSource;
	}

	public void setMsgSource(String msgSource) {
		this.msgSource = msgSource;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}