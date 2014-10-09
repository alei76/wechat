package com.covisint.wechat.weixin.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MusicOutMessage extends OutMessage {
	@XStreamAlias("Music")
	private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

}
