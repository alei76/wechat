package com.covisint.wechat.weixin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.covisint.wechat.content.service.AttachmentService;
import com.covisint.wechat.data.model.WxMediaAtta;

@Controller
@RequestMapping("/api-wechat/mediaplay")
public class MediaPlayController {
	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping("/video/{attaId}")
	public String auth(Model model, @PathVariable("attaId") String attaId) {
		WxMediaAtta mediaAtta = attachmentService.getAtta(attaId);
		model.addAttribute("mediaAtta", mediaAtta);
		return "wechat/playload/video";
	}
}
