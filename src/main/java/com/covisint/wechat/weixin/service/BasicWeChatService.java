package com.covisint.wechat.weixin.service;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.covisint.wechat.exception.WeChatException;
import com.covisint.wechat.utils.PropertiesUtils;

/**
 * 微信接口基础支持
 * 
 * @author mæw
 * @date 2014-1-3
 */
public interface BasicWeChatService {
	public static final String KEY_APPID = "APP_ID";
	public static final String KEY_APPSECRET = "APP_SECRET";

	public static final String ACCESSTOKEN_URL = PropertiesUtils.getValue("weixin.api_address") + "/cgi-bin/token?grant_type=client_credential";
	public static final String UPLOAD_FILE_URL = PropertiesUtils.getValue("weixin.file_address") + "/cgi-bin/media/upload?access_token=";
	public static final String DOWNLOAD_FILE_URL = PropertiesUtils.getValue("weixin.file_address") + "/cgi-bin/media/get?access_token=";

	/**
	 * 获取微信账号接口信息
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public Map<String, String> getAccountInfo(String accountId) throws WeChatException;

	/**
	 * 获取access_token
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getAccessToken(String accountId) throws WeChatException;

	/**
	 * 获取access_token
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getAccessToken(String appId, String appSecert) throws WeChatException;

	/**
	 * 上传多媒体文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String uploadMedia(String type, File file, String accountId) throws WeChatException;

	/**
	 * 上传多媒体文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String uploadMediaByAccount(String type, MultipartFile file, String accountId) throws WeChatException;

	/**
	 * 上传多媒体文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String uploadMediaByToken(String type, MultipartFile file, String accessToken) throws WeChatException;

	/**
	 * 下载多媒体文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String downloadMedia(String mediaId, String path, String accountId) throws WeChatException;

	/**
	 * 下载多媒体文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public void downloadMediaByAccount(String mediaId, String accountId, HttpServletResponse response) throws WeChatException;

	/**
	 * 下载多媒体文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public void downloadMediaByToken(String mediaId, String accessToken, HttpServletResponse response) throws WeChatException;

	/**
	 * 获取OAuth2.0 基础scope的路径
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getOauthBaseUrl(String path, String accountId) throws WeChatException;

	/**
	 * 获取OAuth2.0 高级scope的路径
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public String getOauthInfoUrl(String path, String accountId) throws WeChatException;

}
