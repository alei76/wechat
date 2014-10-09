package com.covisint.wechat.weixin.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Music")
public class Music {
	@XStreamAlias("Title")
	private String title;

	@XStreamAlias("Description")
	private String description;

	@XStreamAlias("MusicUrl")
	private String musicUrl;

	@XStreamAlias("HQMusicUrl")
	private String hQMusicUrl;

	@XStreamAlias("ThumbMediaId")
	private String thumbMediaId;

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

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String gethQMusicUrl() {
		return hQMusicUrl;
	}

	public void sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
	}
}
