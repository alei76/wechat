/************************************************************************************
 * @File name   :      FileAttaUtils.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-9
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
 * 2014-5-9 上午11:03:25			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.covisint.wechat.data.model.WxFiles;

/**
 * 微信平台文件操作工具类
 */
public class FileAttaUtils {
	private static final String ROOT_PATH = PropertiesUtils.getValue("media.path");

	/**
	 * 获取文件
	 */
	public static File getFile(WxFiles files) {
		String path = files.getFilePath();
		String fileName = files.getFileName();
		String fileSuffix = files.getFileSuffix();
		String fullpath = ROOT_PATH + path + File.separator + fileName + "." + fileSuffix;
		return new File(fullpath);
	}

	/**
	 * 保存文件
	 * 配置根路径/accoundId/日期/时间戳_文件名.后缀
	 */
	public static WxFiles saveFile(String attaId, MultipartFile image, String accountId, String type) {
		try {
			WxFiles files = new WxFiles();
			files.setAttaId(attaId);
			files.setFileId(IdentifierUtils.getId().generate().toString());
			String timestamp = DateUtils.currentStringDate("HHmmss");
			String date = DateUtils.currentStringDate("yyyyMMdd");
			String mediaFileName = image.getOriginalFilename().toLowerCase();
			String outfilename = timestamp + "_" + mediaFileName;// 文件名称+后缀
			String path = File.separator + accountId + File.separator + date;// 存放文件夹的相对路径
			String outdir = ROOT_PATH + path;// 存放文件夹的绝对路径
			saveFile(image, outdir, outfilename);// 保存文件
			String fileSuffix = getFileSuffix(outfilename);// 获取文件后缀名
			String fileName = getFileName(outfilename);// 获取文件名称
			files.setFileName(fileName);
			files.setFilePath(path);
			files.setFileSuffix(fileSuffix);
			files.setFileType(type);
			files.setStatus(WxFiles.STATUS_ENABLE);
			return files;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取文件名
	 */
	private static String getFileName(String fileName) {
		return FilenameUtils.getBaseName(fileName).toLowerCase();
	}

	/**
	 * 获取文件后缀
	 */
	private static String getFileSuffix(String fileName) {
		return FilenameUtils.getExtension(fileName).toLowerCase();
	}

	/**
	 * 保存至文件
	 */
	private static void saveFile(MultipartFile image, String outdir, String outfilename) throws IllegalStateException, IOException {
		File f = new File(outdir);
		f.mkdirs();
		File dest = new File(f.getPath() + "/" + outfilename);
		image.transferTo(dest);
	}
}
