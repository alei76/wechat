package com.covisint.wechat.wiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/anon/wiki")
public class WikiController {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "wiki/list";
	}

	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(@RequestParam("tag") String tag) {
		return "wiki/content_" + tag;
	}
}
