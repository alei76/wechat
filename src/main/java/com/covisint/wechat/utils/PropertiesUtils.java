package com.covisint.wechat.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.stereotype.Component;

/**
 * Properties工具类
 * 
 * @author maew
 * @version 2012-9-11
 */
@Component
public class PropertiesUtils {
	@Autowired
	private PropertyPlaceholderConfigurer propertyResourceConfigurer;
	private static PropertiesUtils piu;
	private Properties properties;

	/**
	 * 初始化工具类，将配置在spring context中的properties文件注入到对象中
	 */
	@PostConstruct
	public void init() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		piu = this;
		properties = new Properties();
		Method mergeProperties = PropertiesLoaderSupport.class.getDeclaredMethod("mergeProperties");
		mergeProperties.setAccessible(true);
		Properties props = (Properties) mergeProperties.invoke(propertyResourceConfigurer);
		Method convertProperties = PropertyResourceConfigurer.class.getDeclaredMethod("convertProperties", Properties.class);
		convertProperties.setAccessible(true);
		convertProperties.invoke(propertyResourceConfigurer, props);
		properties.putAll(props);
	}

	/**
	 * 获取配置文件中的值
	 * 
	 * @author 马恩伟
	 * @date 2014-9-1
	 */
	public static String getValue(String key) {
		return piu.properties.getProperty(key, null);
	}

}
