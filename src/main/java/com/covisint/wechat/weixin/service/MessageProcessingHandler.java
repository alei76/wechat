package com.covisint.wechat.weixin.service;

import java.util.Map;

import com.covisint.wechat.exception.MessageException;
import com.covisint.wechat.weixin.model.InMessage;

/**
 * 接收消息处理器
 * 
 */
public interface MessageProcessingHandler {
	public final static String MSG_TYPE_TEXT = "text";
	public final static String MSG_TYPE_LOCATION = "location";
	public final static String MSG_TYPE_IMAGE = "image";
	public final static String MSG_TYPE_LINK = "link";
	public final static String MSG_TYPE_VOICE = "voice";
	public final static String MSG_TYPE_EVENT = "event";

	public final static String MSG_TYPE_NEWS = "news";
	public final static String MSG_TYPE_MUSIC = "music";

	/**
	 * 文字内容的消息处理
	 * 
	 * @param msg
	 * @return
	 */
	public String textTypeMsg(InMessage msg, String originalXml, Map<String, String> signature) throws MessageException;

	/**
	 * 地理位置类型的消息处理
	 * 
	 * @param msg
	 * @return
	 */
	public String locationTypeMsg(InMessage msg, String originalXml, Map<String, String> signature) throws MessageException;

	/**
	 * 图片类型的消息处理
	 * 
	 * @param msg
	 * @return
	 */
	public String imageTypeMsg(InMessage msg, String originalXml, Map<String, String> signature) throws MessageException;

	/**
	 * 链接类型的消息处理
	 * 
	 * @param msg
	 * @return
	 */
	public String linkTypeMsg(InMessage msg, String originalXml, Map<String, String> signature) throws MessageException;

	/**
	 * 语音类型的消息处理
	 * 
	 * @param msg
	 * @return
	 */
	public String voiceTypeMsg(InMessage msg, String originalXml, Map<String, String> signature) throws MessageException;

	/**
	 * 事件类型的消息处理。
	 * 
	 * @param msg
	 * @return
	 */
	public String eventTypeMsg(InMessage msg, String originalXml, Map<String, String> signature) throws MessageException;
}
