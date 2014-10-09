/************************************************************************************
 * @File name   :      IRobotServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-6-5
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
 * 2014-6-5 下午03:27:30			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.irobot.service.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.exception.WebServiceException;
import com.covisint.wechat.irobot.model.Ask;
import com.covisint.wechat.irobot.model.AskE;
import com.covisint.wechat.irobot.model.AskResponse;
import com.covisint.wechat.irobot.model.AskResponseE;
import com.covisint.wechat.irobot.model.RobotCommand;
import com.covisint.wechat.irobot.model.RobotRequest;
import com.covisint.wechat.irobot.model.RobotResponse;
import com.covisint.wechat.irobot.model.UserAttribute;
import com.covisint.wechat.irobot.remoting.AskService;
import com.covisint.wechat.irobot.service.IRobotService;

/**
 *
 */
@Service
public class IRobotServiceImpl implements IRobotService {
	@Autowired
	private AskService askService;

	@Override
	public Map<String, String> getAnswer(String keyword, String openId, String brand) throws WebServiceException {
		AskE ask = new AskE();
		Ask param = new Ask();
		RobotRequest request = new RobotRequest();
		request.setUserId(openId);
		request.setQuestion(keyword);
		UserAttribute _platform = new UserAttribute();
		_platform.setName("platform");
		_platform.setValue("weixin");
		UserAttribute _brand = new UserAttribute();
		_brand.setName("brand");
		_brand.setValue(brand);
		UserAttribute[] attributes = { _platform, _brand };
		request.setAttributes(attributes);
		param.setRobotRequestEx(request);
		ask.setAsk(param);
		try {
			AskResponseE responseE = askService.ask(ask);
			AskResponse response = responseE.getAskResponse();
			RobotResponse robotResponse = response.getRobotResponseEx();
			int type = robotResponse.getType();
			if (type == 101) {
				keyword = robotResponse.getContent();
				return this.getAnswer(keyword, openId, brand);
			} else {
				RobotCommand[] commands = robotResponse.getCommands();
				String msgType = WxReplymsgTemplate.TYPE_TEXT;// 默认消息为文本消息
				if (commands != null) {
					for (RobotCommand command : commands) {
						if (command.getName().equals("imgtxtmsg")) {
							msgType = WxReplymsgTemplate.TYPE_NEWS;// 消息为图文消息
							break;
						}
					}
				}
				String content = robotResponse.getContent();// 小i机器人返回的消息内容
				Map<String, String> result = new HashMap<String, String>();// 规定返回map对方必须包括如下两个参数
				result.put(IRobotService.MESSAGE_TYPE, msgType);// 消息类型
				result.put(IRobotService.MESSAGE_CONTENT, content);// 消息内容
				return result;
			}
		} catch (RemoteException e) {
			throw new WebServiceException(e);
		}
	}
}
