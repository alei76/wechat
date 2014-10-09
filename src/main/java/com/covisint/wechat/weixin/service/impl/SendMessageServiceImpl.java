package com.covisint.wechat.weixin.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.JacksonJsonMapper;
import com.covisint.wechat.weixin.model.ArticleItem;
import com.covisint.wechat.weixin.service.SendMessageService;
import com.covisint.wechat.weixin.util.AccessTokenUtils;
import com.covisint.wechat.weixin.util.HttpUtil;

@Service
public class SendMessageServiceImpl implements SendMessageService {

	@Override
	public String sendText(String toUser, String content, String accountId) throws WeChatException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("touser", toUser);
		param.put("msgtype", "text");
		Map<String, Object> text = new HashMap<String, Object>();
		text.put("content", content);
		param.put("text", text);
		return this.send(param, accountId);
	}

	@Override
	public String sendImage(String toUser, String mediaId, String accountId) throws WeChatException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("touser", toUser);
		param.put("msgtype", "image");
		Map<String, Object> image = new HashMap<String, Object>();
		image.put("media_id", mediaId);
		param.put("image", image);
		return this.send(param, accountId);
	}

	@Override
	public String sendVoice(String toUser, String mediaId, String accountId) throws WeChatException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("touser", toUser);
		param.put("msgtype", "voice");
		Map<String, Object> voice = new HashMap<String, Object>();
		voice.put("media_id", mediaId);
		param.put("voice", voice);
		return this.send(param, accountId);
	}

	@Override
	public String sendVideo(String toUser, String mediaId, String title, String description, String accountId) throws WeChatException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("touser", toUser);
		param.put("msgtype", "video");
		Map<String, Object> video = new HashMap<String, Object>();
		video.put("media_id", mediaId);
		video.put("title", title);
		video.put("description", description);
		param.put("video", video);
		return this.send(param, accountId);
	}

	@Override
	public String sendMusic(String toUser, String title, String description, String musicUrl, String hqmusicUrl, String thumbMediaId, String accountId) throws WeChatException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("touser", toUser);
		param.put("msgtype", "music");
		Map<String, Object> music = new HashMap<String, Object>();
		music.put("title", title);
		music.put("description", description);
		music.put("musicurl", musicUrl);
		music.put("hqmusicurl", hqmusicUrl);
		music.put("thumb_media_id", thumbMediaId);
		param.put("music", music);
		return this.send(param, accountId);
	}

	@Override
	public String sendNews(String toUser, List<ArticleItem> articlesList, String accountId) throws WeChatException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("touser", toUser);
		param.put("msgtype", "news");
		Map<String, Object> articles = new HashMap<String, Object>();
		articles.put("articles", articlesList);
		param.put("news", articles);
		return this.send(param, accountId);
	}

	@Override
	public String sendByAccount(String body, String accountId) throws WeChatException {
		String accessToken = AccessTokenUtils.getAccessToken(accountId);
		return this.sendByToken(body, accessToken);
	}

	@Override
	public String sendByToken(String body, String accessToken) throws WeChatException {
		try {
			return HttpUtil.post(MESSAGE_URL.concat(accessToken), body);
		} catch (ClientProtocolException e) {
			throw new WeChatException(e);
		} catch (IOException e) {
			throw new WeChatException(e);
		}
	}

	private String send(Map<String, Object> param, String accountId) throws WeChatException {
		String body = JacksonJsonMapper.objectToJson(param, true);
		return this.sendByAccount(body, accountId);
	}
}
