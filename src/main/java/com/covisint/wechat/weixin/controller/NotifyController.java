package com.covisint.wechat.weixin.controller;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.covisint.wechat.account.service.AccountService;
import com.covisint.wechat.data.model.WxWechatAccount;
import com.covisint.wechat.weixin.model.InMessage;
import com.covisint.wechat.weixin.model.TextOutMessage;
import com.covisint.wechat.weixin.service.MessageProcessingHandler;
import com.covisint.wechat.weixin.util.Tools;
import com.covisint.wechat.weixin.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;

/**
 * 腾讯微信消息接收处理控制器
 */
@Controller
@RequestMapping("/api-wechat")
public class NotifyController {
	private static final Logger logger = LoggerFactory.getLogger(NotifyController.class);
	@Autowired
	private MessageProcessingHandler processingHandler;
	@Autowired
	private AccountService accountService;

	/**
	 * 接收腾讯微信推送过来的用户消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/notify/{accountId}", method = RequestMethod.POST)
	@ResponseBody
	public HttpEntity<String> notifyMessage(@RequestBody String xmlMsg, @PathVariable("accountId") String accountId, @RequestParam Map<String, String> params) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/xml;charset=UTF-8");
		String signature = params.get("signature");// 微信加密签名
		String timestamp = params.get("timestamp");// 时间戳
		String nonce = params.get("nonce");// 随机数
		if (!StringUtils.isEmpty(signature) && !StringUtils.isEmpty(timestamp) && !StringUtils.isEmpty(nonce)) {
			WxWechatAccount account = accountService.info(accountId);
			String token = account.getToken();
			if (Tools.checkSignature(token, signature, timestamp, nonce)) {// 验证消息合法性
				String response = null;
				logger.debug("输入消息:[" + xmlMsg + "]");
				XStream xstream = XStreamFactory.getStream();
				xstream.alias("xml", InMessage.class);
				xstream.ignoreUnknownElements();
				xstream.processAnnotations(InMessage.class);
				InMessage msg = (InMessage) xstream.fromXML(xmlMsg);// 将传入的xml解析为对象
				try {
					msg.setAccountId(accountId);
					if (WxWechatAccount.ACCOUNT_STATUS_ENABLE.equals(account.getStatus())) {// 判断状态
						if (msg.getToUserName().equals(account.getAccountNo())) {// 判断配置路径是否为该微信号，以防配置错误。
							String type = msg.getMsgType();// 取得消息类型
							Method mt = processingHandler.getClass().getMethod(type + "TypeMsg", InMessage.class, String.class, Map.class);
							response = (String) mt.invoke(processingHandler, msg, xmlMsg, params);
						}
					}
					if (response == null) {
						response = getErrorMsg(msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
					response = getErrorMsg(msg);
				}
				return new HttpEntity<String>(response, headers);
			}
		}
		return null;
	}

	/**
	 * 验证路径合法性
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping(value = "/notify/{accountId}", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<String> checkSignature(@RequestParam Map<String, String> params, @PathVariable("accountId") String accountId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/plain;charset=utf-8");
		String signature = params.get("signature");// 微信加密签名
		String timestamp = params.get("timestamp");// 时间戳
		String nonce = params.get("nonce");// 随机数
		String echostr = params.get("echostr");// 随机字符串
		if (!StringUtils.isEmpty(signature) && !StringUtils.isEmpty(timestamp) && !StringUtils.isEmpty(nonce) && !StringUtils.isEmpty(echostr)) {
			WxWechatAccount account = accountService.info(accountId);
			String token = account.getToken();
			if (Tools.checkSignature(token, signature, timestamp, nonce)) {
				return new HttpEntity<String>(echostr, headers);
			}
		}
		return null;
	}

	/**
	 * 有异常返回默认空白消息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private String getErrorMsg(InMessage msg) {
		TextOutMessage out = new TextOutMessage();
		out.setContent("");
		out.setMsgType("text");
		out.setCreateTime(new Date().getTime());
		out.setToUserName(msg.getFromUserName());
		out.setFromUserName(msg.getToUserName());
		return XStreamFactory.toXml(out);
	}
}
