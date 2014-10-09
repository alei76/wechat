package com.covisint.wechat.weixin.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ImageOutMessage extends OutMessage {
	@XStreamAlias("Image")
	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
