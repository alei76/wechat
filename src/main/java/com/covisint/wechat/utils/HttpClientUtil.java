/************************************************************************************
 * @File name   :      IDMServerClientUtil.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-28
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
 * 2014-5-28 上午09:07:17			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 */
public class HttpClientUtil {
	/**
	 * 提交数据
	 * 
	 * @author 马恩伟
	 * @date 2014-9-1
	 */
	public static String postForString(String url, List<NameValuePair> nvps, Map<String, String> header) throws ClientProtocolException, IOException {
		CloseableHttpClient client = createSSLInsecureClient();
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(25000).setConnectTimeout(25000).setSocketTimeout(25000).build();
		HttpPost request = new HttpPost(url);
		if (header != null) {
			Iterator<String> it = header.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = header.get(key);
				request.addHeader(key, value);
			}
		}
		request.setConfig(requestConfig);
		if (nvps != null) {
			request.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		}
		CloseableHttpResponse response = client.execute(request);
		try {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				String result = EntityUtils.toString(resEntity, Charset.forName("utf-8"));
				EntityUtils.consume(resEntity);
				return result;
			}
		} finally {
			request.releaseConnection();
			response.close();
			client.close();
		}
		return null;
	}

	/**
	 * 获取一个HttpClient，信任自签及所有证书
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-5-28
	 */
	private static CloseableHttpClient createSSLInsecureClient() {
		try {
			SSLContextBuilder builder = new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}
}
