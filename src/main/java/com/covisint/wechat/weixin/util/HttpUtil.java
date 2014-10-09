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
package com.covisint.wechat.weixin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * http请求工具类
 */
public class HttpUtil {
	/**
	 * POST提交
	 */
	public static String post(String url, String body, Map<String, String> signature) throws ClientProtocolException, IOException {
		CloseableHttpClient client = createSSLInsecureClient();
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(25000).setConnectTimeout(25000).setSocketTimeout(25000).build();
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
		if (signature != null) {
			Iterator<String> _iname = signature.keySet().iterator();
			while (_iname.hasNext()) {
				String name = _iname.next();
				String value = signature.get(name);
				uriBuilder.queryParam(name, value);
			}
		}
		url = uriBuilder.build().encode().toUriString();
		HttpPost request = new HttpPost(url);
		request.setConfig(requestConfig);
		request.addHeader("Content-Type", "text/xml; charset=UTF-8");
		if (!StringUtils.isEmpty(body)) {
			EntityBuilder builder = EntityBuilder.create();
			builder.setContentEncoding("utf-8");
			builder.setContentType(ContentType.create("text/xml", Charset.forName("utf-8")));
			builder.setText(body);
			HttpEntity entity = builder.build();
			request.setEntity(entity);
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
	 * POST提交
	 */
	public static String post(String url, String body) throws ClientProtocolException, IOException {
		CloseableHttpClient client = createSSLInsecureClient();
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(25000).setConnectTimeout(25000).setSocketTimeout(25000).build();
		HttpPost request = new HttpPost(url);
		request.setConfig(requestConfig);
		request.addHeader("Content-Type", "text/xml; charset=UTF-8");
		if (!StringUtils.isEmpty(body)) {
			EntityBuilder builder = EntityBuilder.create();
			builder.setContentEncoding("utf-8");
			builder.setContentType(ContentType.create("text/xml", Charset.forName("utf-8")));
			builder.setText(body);
			HttpEntity entity = builder.build();
			request.setEntity(entity);
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
	 * GET
	 */
	public static String get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient client = createSSLInsecureClient();
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(25000).setConnectTimeout(25000).setSocketTimeout(25000).build();
		HttpGet request = new HttpGet(url);
		request.setConfig(requestConfig);
		request.addHeader("Content-Type", "text/xml; charset=UTF-8");
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
	 * 文件上传
	 */
	public static String postFile(String url, File file) throws ClientProtocolException, FileNotFoundException, IOException {
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		return postFile(url, file.getName(), new FileInputStream(file));
	}

	/**
	 * 文件上传
	 */
	public static String postFile(String url, MultipartFile file) throws ClientProtocolException, IOException {
		if (file.isEmpty()) {
			throw new IOException("文件不存在");
		}
		return postFile(url, file.getOriginalFilename(), file.getInputStream());
	}

	/**
	 * 文件上传
	 */
	public static String postFile(String url, String fileName, InputStream inputStream) throws ClientProtocolException, IOException {
		CloseableHttpClient client = createSSLInsecureClient();
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(25000).setConnectTimeout(25000).setSocketTimeout(25000).build();
		HttpPost request = new HttpPost(url);
		request.setConfig(requestConfig);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addPart("media", new InputStreamBody(inputStream, fileName));

		HttpEntity entity = builder.build();
		request.setEntity(entity);

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
	 * 下载文件
	 */
	public static String getFile(String url, String path) throws IOException {
		CloseableHttpClient client = createSSLInsecureClient();
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(25000).setConnectTimeout(25000).setSocketTimeout(25000).build();
		HttpGet request = new HttpGet(url);
		request.setConfig(requestConfig);
		request.addHeader("Content-Type", "text/xml; charset=UTF-8");
		CloseableHttpResponse response = client.execute(request);
		try {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				Header disposition = response.getFirstHeader("Content-disposition");
				String fileName = disposition.getValue();
				InputStream instream = resEntity.getContent();
				String filePath = path + fileName.substring(fileName.indexOf("\"") + 1, fileName.length() - 1);
				File file = new File(filePath);
				file.createNewFile();
				byte[] by = new byte[1024];
				int nread = 0;
				RandomAccessFile savefile = new RandomAccessFile(file, "rw");
				while ((nread = instream.read(by, 0, 1024)) > 0) {
					savefile.write(by, 0, nread);
				}
				savefile.close();
				EntityUtils.consume(resEntity);
				return filePath;
			}
		} finally {
			request.releaseConnection();
			response.close();
			client.close();
		}
		return null;
	}

	/**
	 * 信任自签及所有证书
	 */
	public static CloseableHttpClient createSSLInsecureClient() {
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
