package com.covisint.wechat.weixin.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.covisint.wechat.account.service.AccountService;
import com.covisint.wechat.data.model.WxWechatAccount;
import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.SessionUtils;
import com.covisint.wechat.weixin.service.BasicWeChatService;
import com.covisint.wechat.weixin.util.AccessTokenUtils;
import com.covisint.wechat.weixin.util.HttpUtil;

@Service
public class BasicWeChatServiceImpl implements BasicWeChatService {
	@Autowired
	private AccountService accountService;
	@Value("${weixin.oauth_base}")
	private String oauthBase;
	@Value("${weixin.oauth_info}")
	private String oauthInfo;

	@Override
	public String uploadMedia(String type, File file, String accountId) throws WeChatException {
		try {
			String accessToken = AccessTokenUtils.getAccessToken(accountId);
			String url = UPLOAD_FILE_URL.concat(accessToken + "&type=" + type);
			String jsonStr = HttpUtil.postFile(url, file);
			return jsonStr;
		} catch (IOException e) {
			throw new WeChatException(e);
		}
	}

	@Override
	public String downloadMedia(String mediaId, String path, String accountId) throws WeChatException {
		try {
			String accessToken = AccessTokenUtils.getAccessToken(accountId);
			String url = DOWNLOAD_FILE_URL.concat(accessToken) + "&media_id=" + mediaId;
			return HttpUtil.getFile(url, path);
		} catch (IOException e) {
			throw new WeChatException(e);
		}
	}

	@Override
	public String uploadMediaByAccount(String type, MultipartFile file, String accountId) throws WeChatException {
		String accessToken = AccessTokenUtils.getAccessToken(accountId);
		return this.uploadMediaByToken(type, file, accessToken);
	}

	@Override
	public String uploadMediaByToken(String type, MultipartFile file, String accessToken) throws WeChatException {
		try {
			String url = UPLOAD_FILE_URL.concat(accessToken + "&type=" + type);
			return HttpUtil.postFile(url, file);
		} catch (ClientProtocolException e) {
			throw new WeChatException(e);
		} catch (IOException e) {
			throw new WeChatException(e);
		}
	}

	@Override
	public void downloadMediaByAccount(String mediaId, String accountId, HttpServletResponse response) throws WeChatException {
		String accessToken = AccessTokenUtils.getAccessToken(accountId);
		this.downloadMediaByToken(mediaId, accessToken, response);
	}

	@Override
	public void downloadMediaByToken(String mediaId, String accessToken, HttpServletResponse response) throws WeChatException {
		String url = DOWNLOAD_FILE_URL.concat(accessToken) + "&media_id=" + mediaId;
		CloseableHttpClient client = HttpUtil.createSSLInsecureClient();
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(25000).setConnectTimeout(25000).setSocketTimeout(25000).build();
		HttpGet request = new HttpGet(url);
		request.setConfig(requestConfig);
		request.addHeader("Content-Type", "text/xml; charset=UTF-8");
		CloseableHttpResponse wxResponse = null;
		try {
			wxResponse = client.execute(request);
			HttpEntity resEntity = wxResponse.getEntity();
			if (resEntity != null) {
				Header disposition = wxResponse.getFirstHeader("Content-disposition");
				Header length = wxResponse.getFirstHeader("Content-Length");
				Header type = wxResponse.getFirstHeader("Content-Type");
				if (disposition != null) {
					String fileName = disposition.getValue();
					response.setHeader("Content-disposition", fileName);
				}
				if (type != null) {
					response.setContentType(type.getValue());
				}
				if (length != null) {
					response.setContentLength(Integer.valueOf(length.getValue()));
				}
				OutputStream outStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
				resEntity.writeTo(outStream);
				outStream.flush();
				outStream.close();
				EntityUtils.consume(resEntity);
			}
		} catch (ClientProtocolException e) {
			throw new WeChatException(e);
		} catch (IOException e) {
			throw new WeChatException(e);
		} finally {
			request.releaseConnection();
			try {
				wxResponse.close();
			} catch (IOException e) {
			}
			try {
				client.close();
			} catch (IOException e) {
			}
		}
	}

	@Override
	public Map<String, String> getAccountInfo(String accountId) throws WeChatException {
		WxWechatAccount account = SessionUtils.getCurrentAccount(accountId);
		if (account == null) {
			account = accountService.info(accountId);
		}
		if (null != account) {
			Map<String, String> map = new HashMap<String, String>();
			map.put(KEY_APPID, account.getAppId());
			map.put(KEY_APPSECRET, account.getAppSecret());
			return map;
		} else {
			throw new WeChatException("60001");
		}
	}

	@Override
	public String getAccessToken(String accountId) throws WeChatException {
		Map<String, String> accountInfo = this.getAccountInfo(accountId);
		String appId = accountInfo.get(KEY_APPID);
		String appSecert = accountInfo.get(KEY_APPSECRET);
		return getAccessToken(appId, appSecert);
	}

	@Override
	public String getAccessToken(String appId, String appSecert) throws WeChatException {
		try {
			return HttpUtil.get(ACCESSTOKEN_URL.concat("&appid=") + appId + "&secret=" + appSecert);
		} catch (ClientProtocolException e) {
			throw new WeChatException(e);
		} catch (IOException e) {
			throw new WeChatException(e);
		}
	}

	@Override
	public String getOauthBaseUrl(String path, String accountId) throws WeChatException {
		Map<String, String> accountInfo = this.getAccountInfo(accountId);
		String appId = accountInfo.get(KEY_APPID);
		return MessageFormat.format(oauthBase, appId, path);
	}

	@Override
	public String getOauthInfoUrl(String path, String accountId) throws WeChatException {
		Map<String, String> accountInfo = this.getAccountInfo(accountId);
		String appId = accountInfo.get(KEY_APPID);
		return MessageFormat.format(oauthInfo, appId, path);
	}

}
