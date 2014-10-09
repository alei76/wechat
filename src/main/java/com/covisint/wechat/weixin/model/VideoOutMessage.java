package com.covisint.wechat.weixin.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class VideoOutMessage extends OutMessage {
	@XStreamAlias("Video")
	private Video video;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}
