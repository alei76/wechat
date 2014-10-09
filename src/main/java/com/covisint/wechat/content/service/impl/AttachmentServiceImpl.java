/************************************************************************************
 * @File name   :      AttachmentServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-5-7
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
 * 2014-5-7 下午02:47:00			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.covisint.wechat.constant.DictCode;
import com.covisint.wechat.content.service.AttachmentService;
import com.covisint.wechat.data.dao.WxFilesDao;
import com.covisint.wechat.data.dao.WxMediaAttaDao;
import com.covisint.wechat.data.model.WxFiles;
import com.covisint.wechat.data.model.WxMediaAtta;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.FileAttaUtils;
import com.covisint.wechat.utils.IdentifierUtils;

/**
 *
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	private WxMediaAttaDao wxMediaAttaDao;
	@Autowired
	private WxFilesDao wxFilesDao;

	@Override
	public Page<WxMediaAtta> pageMediaAtta(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		String keyword = (String) paramMap.get("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			paramMap.put("likeName", "%" + keyword + "%");
		}
		paramMap.put("mediaStatus", DictCode.MEDIA_STATUS);
		paramMap.put("mediaType", DictCode.MEDIA_TYPE);
		return wxMediaAttaDao.pageDomain(paramMap, current, pagesize);
	}

	@Override
	public boolean saveMedia(WxMediaAtta wxMediaAtta, String userId, String accountId, MultipartFile image, MultipartFile media) throws AjaxException {
		int row = 0;
		if (this.validate_resource(wxMediaAtta, image, media)) {
			String attaId = IdentifierUtils.getId().generate().toString();
			wxMediaAtta.setAccountId(accountId);
			wxMediaAtta.setAttaId(attaId);
			wxMediaAtta.setCreateBy(userId);
			wxMediaAtta.setStatus(WxMediaAtta.STATUS_ENABLE);
			List<WxFiles> files = new ArrayList<WxFiles>();
			WxFiles imageFile = FileAttaUtils.saveFile(attaId, image, accountId, WxFiles.TYPE_PIC);
			files.add(imageFile);
			if (!wxMediaAtta.getType().equals(WxMediaAtta.TYPE_PIC)) {// 不是图片类型，需要保存音频或视频文件
				WxFiles mediaFile = FileAttaUtils.saveFile(attaId, media, accountId, WxFiles.TYPE_MEDIA);
				files.add(mediaFile);
			}
			row = wxMediaAttaDao.insert(wxMediaAtta);
			wxFilesDao.batchInsert(files);
		}
		return row > 0;
	}

	@Override
	public boolean editMedia(WxMediaAtta wxMediaAtta, String accountId, MultipartFile image, MultipartFile media) throws AjaxException {
		String attaId = wxMediaAtta.getAttaId();
		int row = wxMediaAttaDao.update(wxMediaAtta);
		if (!image.isEmpty()) {
			this.changeFile(attaId, accountId, image, WxFiles.TYPE_PIC);
		}
		if (!media.isEmpty()) {
			this.changeFile(attaId, accountId, media, WxFiles.TYPE_MEDIA);
		}
		return row > 0;
	}

	/**
	 * 删除某个媒体文件中某个类型的文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean changeFile(String attaId, String accountId, MultipartFile file, String type) throws AjaxException {
		if (!file.isEmpty()) {
			this.updateFileStatus(attaId, type);
			WxFiles imageFile = FileAttaUtils.saveFile(attaId, file, accountId, type);
			int row = wxFilesDao.insert(imageFile);
			return row > 0;
		}
		return false;
	}

	/**
	 * 删除某个媒体文件中某个类型的文件
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean updateFileStatus(String attaId, String type) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("enable", WxFiles.STATUS_ENABLE);
		paramMap.put("disable", WxFiles.STATUS_DISABLE);
		paramMap.put("fileType", type);
		paramMap.put("attaId", attaId);
		int row = wxFilesDao.updateStatus(paramMap);
		return row > 0;
	}

	@Override
	public WxMediaAtta getAtta(String attaId) {
		return wxMediaAttaDao.get(attaId);
	}

	@Override
	public String saveImage(String userId, String accountId, MultipartFile image) {
		WxMediaAtta wxMediaAtta = new WxMediaAtta();
		String attaId = IdentifierUtils.getId().generate().toString();
		wxMediaAtta.setAccountId(accountId);
		wxMediaAtta.setAttaId(attaId);
		wxMediaAtta.setCreateBy(userId);
		wxMediaAtta.setStatus(WxMediaAtta.STATUS_ENABLE);
		wxMediaAtta.setType(WxMediaAtta.TYPE_PIC);
		wxMediaAtta.setName(image.getOriginalFilename());
		WxFiles imageFile = FileAttaUtils.saveFile(attaId, image, accountId, WxFiles.TYPE_PIC);
		wxMediaAttaDao.insert(wxMediaAtta);
		wxFilesDao.insert(imageFile);
		return attaId;
	}

	/**
	 * 后台验证数据合法性
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_resource(WxMediaAtta wxMediaAtta, MultipartFile image, MultipartFile media) throws AjaxException {
		String name = wxMediaAtta.getName();
		if (StringUtils.isEmpty(name)) {
			throw new AjaxException("请填写资源名称");
		}
		String type = wxMediaAtta.getType();
		if (StringUtils.isEmpty(type)) {
			throw new AjaxException("请指定资源类型");
		}
		if (image.isEmpty()) {
			throw new AjaxException("请上传图片文件");
		}
		if (!wxMediaAtta.getType().equals(WxMediaAtta.TYPE_PIC)) {// 如果不是图片类型，还需添加视频或音频文件
			if (media.isEmpty()) {
				throw new AjaxException("请上传媒体文件");
			}
		}
		return true;
	}

	@Override
	public WxFiles getFile(String attaId, String type) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("attaId", attaId);
		paramMap.put("fileType", type);
		paramMap.put("status", WxFiles.STATUS_ENABLE);
		return wxFilesDao.getFile(paramMap);
	}

}
