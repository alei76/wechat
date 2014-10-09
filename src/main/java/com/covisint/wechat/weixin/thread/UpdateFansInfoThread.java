package com.covisint.wechat.weixin.thread;

import com.covisint.wechat.member.service.FansService;

public class UpdateFansInfoThread implements Runnable {
	private String fansId;
	private FansService fansService;

	public UpdateFansInfoThread(String fansId, FansService fansService) {
		this.fansId = fansId;
		this.fansService = fansService;
	}

	@Override
	public void run() {
		fansService.updateFansInfo(fansId);
	}

}
