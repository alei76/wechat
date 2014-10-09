/************************************************************************************
 * @File name   :      MessageHisAspect.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-3
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2014-6-3 下午04:54:57			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.weixin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covisint.wechat.exception.MessageException;
import com.covisint.wechat.member.service.MessageHisService;
import com.covisint.wechat.stat.service.EventHisService;
import com.covisint.wechat.utils.ThreadPoolUtils;
import com.covisint.wechat.weixin.model.InMessage;
import com.covisint.wechat.weixin.thread.EventMsgThread;
import com.covisint.wechat.weixin.thread.TextMsgThread;

/**
 * 消息事件处理拦截器
 */
@Aspect
@Component
public class MessageHisAspect {
	private static final Logger logger = LoggerFactory.getLogger(MessageHisAspect.class);
	@Autowired
	private EventHisService eventHisService;
	@Autowired
	private MessageHisService messageHisService;

	/**
	 * 事件消息方法拦截器
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@Around(value = "execution(* com.covisint.wechat.weixin.service.MessageProcessingHandler.eventTypeMsg(..))")
	public String eventMessage(ProceedingJoinPoint joinPoint) throws Throwable {
		Long start = System.currentTimeMillis();
		Object[] args = joinPoint.getArgs();
		InMessage inMessage = null;
		if (args != null && args.length > 0 && args[0].getClass() == InMessage.class) {
			inMessage = (InMessage) args[0];
			boolean exists = eventHisService.checkExists(inMessage);// 查询是否重复处理
			if (exists) {
				try {
					String obj = (String) joinPoint.proceed();// 执行目标方法
					Long end = System.currentTimeMillis();
					if (start - end <= 4500) {// 判断是否超时处理，超时不保存记录
						EventMsgThread task = new EventMsgThread(eventHisService, inMessage);
						ThreadPoolUtils.execute(task);
					}
					if (logger.isInfoEnabled()) {
						logger.info("事件消息处理完毕，共耗时：" + (start - end) + "毫秒");
					}
					return obj;
				} catch (MessageException e) {
					throw e;
				}
			} else {
				if (logger.isInfoEnabled()) {
					logger.info("该消息已处理过");
				}
			}
		}
		return null;
	}

	/**
	 * 文本消息拦截器
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@Around(value = "execution(* com.covisint.wechat.weixin.service.MessageProcessingHandler.textTypeMsg(..))")
	public String textMessage(ProceedingJoinPoint joinPoint) throws Throwable {
		Long start = System.currentTimeMillis();
		Object[] args = joinPoint.getArgs();
		if (args != null && args.length > 0 && args[0].getClass() == InMessage.class) {
			InMessage inMessage = (InMessage) args[0];
			Long msgId = inMessage.getMsgId();
			boolean exists = messageHisService.checkExists(String.valueOf(msgId));// 判断该消息是否重复处理
			if (exists) {
				try {
					String outMessage = (String) joinPoint.proceed();// 执行目标方法
					Long end = System.currentTimeMillis();
					if (start - end <= 4500) {// 判断是否超时处理，超时不保存记录
						TextMsgThread task = new TextMsgThread(messageHisService, inMessage, outMessage);
						ThreadPoolUtils.execute(task);
					}
					if (logger.isInfoEnabled()) {
						logger.info("文本消息处理完毕，共耗时：" + (start - end) + "毫秒");
					}
					return outMessage;
				} catch (MessageException e) {
					throw e;
				}
			} else {
				if (logger.isInfoEnabled()) {
					logger.info("该消息已处理过");
				}
			}
		}
		return null;
	}
}
