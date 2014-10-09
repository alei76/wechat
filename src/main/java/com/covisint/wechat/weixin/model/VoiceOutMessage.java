package com.covisint.wechat.weixin.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class VoiceOutMessage extends OutMessage {
	@XStreamAlias("Voice")
	private Voice voice;

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

}
