/************************************************************************************
 * @File name   :      AttaPrevController.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-26
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
 * 2014-5-26 下午03:17:11			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.kernel.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.covisint.wechat.content.service.AttachmentService;
import com.covisint.wechat.data.model.WxFiles;
import com.covisint.wechat.utils.FileAttaUtils;
import com.covisint.wechat.utils.ValidateUtils;

/**
 * 媒体文件控制器
 */
@Controller
@RequestMapping("/anon")
public class AttaPrevController {
	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 获取媒体预览图(视频，音频，图片对应的图片文件)
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping("/image/prev/{attaId}")
	public void previmg(@PathVariable("attaId") String attaId, HttpServletResponse response) {
		this.responseImage(attaId, response);
	}

	/**
	 * 获取媒体文件(视频，音频)
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	@RequestMapping("/media/prev/{attaId}")
	public void prevmedia(@PathVariable("attaId") String attaId, HttpServletResponse response, @RequestHeader HttpHeaders headers) throws IOException {
		this.responseMedia(attaId, response, headers);
	}

	private void responseMedia(String attaId, HttpServletResponse response, HttpHeaders headers) throws IOException {
		response.reset();
		response.resetBuffer();
		WxFiles mediaFile = attachmentService.getFile(attaId, WxFiles.TYPE_MEDIA);
		if (!ValidateUtils.isEmpty(mediaFile)) {
			File downloadFile = FileAttaUtils.getFile(mediaFile);
			if (!downloadFile.exists()) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			long fileLength = downloadFile.length();// 记录文件大小
			long pastLength = 0;// 记录已下载文件大小
			int rangeSwitch = 0;// 0：从头开始的全文下载；1：从某字节开始的下载（bytes=27000-）；2：从某字节开始到某字节结束的下载（bytes=27000-39000）
			long toLength = 0;// 记录客户端需要下载的字节段的最后一个字节偏移量（比如bytes=27000-39000，则这个值是为39000）
			long contentLength = 0;// 客户端请求的字节总量
			String rangeBytes = "";// 记录客户端传来的形如“bytes=27000-”或者“bytes=27000-39000”的内容
			RandomAccessFile raf = null;// 负责读取数据
			OutputStream os = null;// 写出数据
			OutputStream out = null;// 缓冲
			int bsize = 1024;// 缓冲区大小
			byte b[] = new byte[bsize];// 暂存容器
			String range = headers.getFirst("Range");
			int responseStatus = 206;
			if (range != null && range.trim().length() > 0 && !"null".equals(range)) {
				responseStatus = HttpServletResponse.SC_PARTIAL_CONTENT;
				rangeBytes = range.replaceAll("bytes=", "");
				if (rangeBytes.endsWith("-")) {
					rangeSwitch = 1;
					rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf('-'));
					pastLength = Long.parseLong(rangeBytes.trim());
					contentLength = fileLength - pastLength;
				} else {
					rangeSwitch = 2;
					String temp0 = rangeBytes.substring(0, rangeBytes.indexOf('-'));
					String temp2 = rangeBytes.substring(rangeBytes.indexOf('-') + 1, rangeBytes.length());
					pastLength = Long.parseLong(temp0.trim());
					toLength = Long.parseLong(temp2);
					contentLength = toLength - pastLength + 1;
				}
			} else {
				contentLength = fileLength;
			}
			response.reset();
			response.setHeader("Accept-Ranges", "bytes");
			if (rangeSwitch != 0) {
				response.setStatus(responseStatus);
				switch (rangeSwitch) {
				case 1: {
					String contentRange = new StringBuffer("bytes ").append(new Long(pastLength).toString()).append("-").append(new Long(fileLength - 1).toString()).append("/").append(new Long(fileLength).toString()).toString();
					response.setHeader("Content-Range", contentRange);
					break;
				}
				case 2: {
					String contentRange = range.replace("=", " ") + "/" + new Long(fileLength).toString();
					response.setHeader("Content-Range", contentRange);
					break;
				}
				default: {
					break;
				}
				}
			} else {
				String contentRange = new StringBuffer("bytes ").append("0-").append(fileLength - 1).append("/").append(fileLength).toString();
				response.setHeader("Content-Range", contentRange);
			}
			try {
				String suffix = mediaFile.getFileSuffix();
				if ("mp4".equals(suffix)) {
					response.setContentType("video/mp4");
				} else if ("mp3".equals(suffix)) {
					response.setContentType("audio/mpeg");
				}
				String fileName = downloadFile.getName();
				response.addHeader("Content-Length", String.valueOf(contentLength));
				response.addHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
				os = response.getOutputStream();
				out = new BufferedOutputStream(os);
				raf = new RandomAccessFile(downloadFile, "r");
				try {
					switch (rangeSwitch) {
					case 0: {
					}
					case 1: {
						raf.seek(pastLength);
						int n = 0;
						while ((n = raf.read(b)) != -1) {
							out.write(b, 0, n);
						}
						break;
					}
					case 2: {
						raf.seek(pastLength);
						int n = 0;
						long readLength = 0;
						while (readLength <= contentLength - bsize) {
							n = raf.read(b);
							readLength += n;
							out.write(b, 0, n);
						}
						if (readLength <= contentLength) {
							n = raf.read(b, 0, (int) (contentLength - readLength));
							out.write(b, 0, n);
						}
						break;
					}
					default: {
						break;
					}
					}
					out.flush();
				} catch (IOException ie) {
				}
			} catch (Exception e) {
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
					}
				}
				if (raf != null) {
					try {
						raf.close();
					} catch (IOException e) {
					}
				}
			}
			os.close();
		}
	}

	private void responseImage(String attaId, HttpServletResponse response) {
		WxFiles imageFile = attachmentService.getFile(attaId, WxFiles.TYPE_PIC);
		if (!ValidateUtils.isEmpty(imageFile)) {
			File image = FileAttaUtils.getFile(imageFile);
			if (image.length() > 0) {
				try {
					FileInputStream hFile = new FileInputStream(image);
					int i = hFile.available();// 得到文件大小
					byte data[] = new byte[i];
					hFile.read(data); // 读数据
					hFile.close();
					response.setContentType("image/png");
					response.setHeader("Content-disposition", "filename=" + new String(image.getName().getBytes("utf-8"), "iso8859-1"));
					response.setContentLength(i);
					OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
					toClient.write(data); // 输出数据
					toClient.flush();
					toClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
