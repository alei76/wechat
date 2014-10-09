/************************************************************************************
 * @File name   :      StringTemplate.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-30
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
 * 2014-5-30 上午11:09:55			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.message.freemarker;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * freemark文本模板处理类
 */
@Component
public class StringTemplateProcess {

	private Configuration freemarkerConfiguration;
	private StringTemplateLoader templateLoader;

	@Autowired
	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
		this.freemarkerConfiguration = freemarkerConfiguration;
		templateLoader = new StringTemplateLoader();
		freemarkerConfiguration.setTemplateLoader(templateLoader);
	}

	public String getContent(String templateId, String template, Map<String, Object> data) throws IOException, TemplateException {
		templateLoader.putTemplate(templateId, template);
		Template temp = freemarkerConfiguration.getTemplate(templateId);
		StringWriter writer = new StringWriter();
		temp.process(data, writer);
		String content = writer.toString();
		freemarkerConfiguration.removeTemplateFromCache(templateId);
		return content;
	}
}
