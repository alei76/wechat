package com.covisint.wechat.weixin.service;

import java.util.List;

import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.PropertiesUtils;
import com.covisint.wechat.weixin.model.ArticleItem;

/**
 * 发送消息接口支持
 * 
 * @author mæw
 * @date 2014-1-3
 */
public interface SendMessageService {
	public static final String MESSAGE_URL = PropertiesUtils.getValue("weixin.api_address") + "/cgi-bin/message/custom/send?access_token=";

	/**
	 * 客服发送文本消息
	 * 
	 * @param toUser
	 *            关注者的微信号
	 * @param content
	 *            发送文本
	 * @return
	 */
	public String sendText(String toUser, String content, String accountId) throws WeChatException;

	/**
	 * 客服发送图片消息
	 * 
	 * @param toUser
	 *            关注者的微信号
	 * @param mediaId
	 *            发送的图片的媒体ID
	 */
	public String sendImage(String toUser, String mediaId, String accountId) throws WeChatException;

	/**
	 * 客服发送语音消息
	 * 
	 * @param toUser
	 *            关注者的微信号
	 * @param mediaId
	 *            发送的语音的媒体ID
	 */
	public String sendVoice(String toUser, String mediaId, String accountId) throws WeChatException;

	/**
	 * 客服发送视频消息
	 * 
	 * @param toUser
	 *            普通用户openid
	 * @param mediaId
	 *            发送的视频的媒体ID
	 * @param title
	 *            视频消息的标题
	 * @param description
	 *            视频消息的描述
	 */
	public String sendVideo(String toUser, String mediaId, String title, String description, String accountId) throws WeChatException;

	/**
	 * 客服发送音乐消息
	 * 
	 * @param toUser
	 *            普通用户openid
	 * @param title
	 *            音乐标题
	 * @param description
	 *            音乐描述
	 * @param musicurl
	 *            音乐链接
	 * @param hqmusicurl
	 *            高品质音乐链接，wifi环境优先使用该链接播放音乐
	 * @param thumb_media_id
	 *            缩略图的媒体ID
	 */
	public String sendMusic(String toUser, String title, String description, String musicUrl, String hqmusicUrl, String thumbMediaId, String accountId) throws WeChatException;

	/**
	 * 客服发送图文消息
	 * 
	 * @param toUser
	 *            普通用户openid
	 * @param articlesList
	 *            多图文消息内容
	 */
	public String sendNews(String toUser, List<ArticleItem> articlesList, String accountId) throws WeChatException;

	/**
	 * 发送消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String sendByAccount(String body, String accountId) throws WeChatException;

	/**
	 * 发送消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String sendByToken(String body, String accessToken) throws WeChatException;
}
