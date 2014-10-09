/************************************************************************************
 * @File name   :      TemplateServiceImpl.java
 *
 * @Author      :      马恩伟
 *
 * @Date        :      2014-4-30
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
 * 2014-4-30 下午03:01:36			马恩伟			1.0				Initial Version
 ************************************************************************************/
package com.covisint.wechat.content.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covisint.wechat.constant.DictCode;
import com.covisint.wechat.content.service.TemplateService;
import com.covisint.wechat.data.dao.WxNewsMessageDao;
import com.covisint.wechat.data.dao.WxReplymsgTemplateDao;
import com.covisint.wechat.data.model.WxLinkResource;
import com.covisint.wechat.data.model.WxNewsMessage;
import com.covisint.wechat.data.model.WxReplymsgTemplate;
import com.covisint.wechat.data.model.WxSystemUser;
import com.covisint.wechat.exception.AjaxException;
import com.covisint.wechat.page.Page;
import com.covisint.wechat.utils.IdentifierUtils;
import com.covisint.wechat.utils.SessionUtils;

/**
 *
 */

@Service
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private WxReplymsgTemplateDao wxReplymsgTemplateDao;
	@Autowired
	private WxNewsMessageDao wxNewsMessageDao;

	@Override
	public Page<WxReplymsgTemplate> pageTemplate(Map<String, Object> paramMap, Integer current, Integer pagesize) {
		String keyword = (String) paramMap.get("keyword");
		if (!StringUtils.isEmpty(keyword)) {
			paramMap.put("likeName", "%" + keyword + "%");
		}
		paramMap.put("except", WxReplymsgTemplate.RESOURCE_TYPE_ROBOTS);
		paramMap.put("messageTemplateStatus", DictCode.MESSAGE_TEMPLATE_STATUS);
		paramMap.put("msgResourcceType", DictCode.MSG_RESOURCE_TYPE);
		paramMap.put("messageType", DictCode.MESSAGE_TYPE);
		paramMap.put("status", WxLinkResource.LINK_STATUS_ENABLE);
		return wxReplymsgTemplateDao.pageDomain(paramMap, current, pagesize);
	}

	@Override
	public List<WxReplymsgTemplate> listTemplate(Map<String, Object> paramMap) {
		WxSystemUser current = SessionUtils.getCurrentUser();
		paramMap.put("accountId", current.getCurrentAccount());
		paramMap.put("status", WxReplymsgTemplate.STATUS_ENABLE);
		return wxReplymsgTemplateDao.findDomain(paramMap);
	}

	@Override
	public boolean saveDynamicMsg(WxReplymsgTemplate wxReplymsgTemplate, String userId, String accountId) throws AjaxException {
		int row = 0;
		if (this.validate_dynamic(wxReplymsgTemplate)) {
			String templateId = wxReplymsgTemplate.getTemplateId();
			if (StringUtils.isEmpty(templateId)) {
				wxReplymsgTemplate.setAccountId(accountId);
				wxReplymsgTemplate.setCreateBy(userId);
				wxReplymsgTemplate.setStatus(WxReplymsgTemplate.STATUS_ENABLE);
				wxReplymsgTemplate.setTemplateId(IdentifierUtils.getId().generate().toString());
				wxReplymsgTemplate.setResourceType(WxReplymsgTemplate.RESOURCE_TYPE_DYNAMIC);
				if (this.validate_name(wxReplymsgTemplate)) {
					row = wxReplymsgTemplateDao.insert(wxReplymsgTemplate);
				}
			} else {
				if (this.validate_name(wxReplymsgTemplate)) {
					row = wxReplymsgTemplateDao.update(wxReplymsgTemplate);
				}
			}
		}
		return row > 0;
	}

	@Override
	public boolean createRobotTemplate(String accountId, String userId) {
		WxReplymsgTemplate wxReplymsgTemplate = new WxReplymsgTemplate();
		wxReplymsgTemplate.setAccountId(accountId);
		wxReplymsgTemplate.setCreateBy(userId);
		wxReplymsgTemplate.setName("小i机器人");
		wxReplymsgTemplate.setResourceType(WxReplymsgTemplate.RESOURCE_TYPE_ROBOTS);
		wxReplymsgTemplate.setStatus(WxReplymsgTemplate.STATUS_ENABLE);
		wxReplymsgTemplate.setTemplateId(IdentifierUtils.getId().generate().toString());
		wxReplymsgTemplate.setType(WxReplymsgTemplate.TYPE_TEXT);
		return wxReplymsgTemplateDao.insert(wxReplymsgTemplate) > 0;
	}

	@Override
	public boolean saveStaticTemplate(WxReplymsgTemplate wxReplymsgTemplate, String userId, String accountId) throws AjaxException {
		int row = 0;
		if (this.validate_static(wxReplymsgTemplate)) {
			String templateId = wxReplymsgTemplate.getTemplateId();
			if (StringUtils.isEmpty(templateId)) {
				wxReplymsgTemplate.setCreateBy(userId);
				wxReplymsgTemplate.setAccountId(accountId);
				wxReplymsgTemplate.setResourceType(WxReplymsgTemplate.RESOURCE_TYPE_STATIC);
				wxReplymsgTemplate.setStatus(WxReplymsgTemplate.STATUS_ENABLE);
				wxReplymsgTemplate.setTemplateId(IdentifierUtils.getId().generate().toString());
				if (this.validate_name(wxReplymsgTemplate)) {
					row = wxReplymsgTemplateDao.insert(wxReplymsgTemplate);
				}
			} else {
				wxReplymsgTemplate.setAccountId(accountId);
				if (this.validate_name(wxReplymsgTemplate)) {
					row = wxReplymsgTemplateDao.update(wxReplymsgTemplate);
				}
			}
		}
		return row > 0;
	}

	@Override
	public WxReplymsgTemplate infoTemplate(String templateId) {
		WxReplymsgTemplate template = wxReplymsgTemplateDao.info(templateId);
		String type = template.getType();
		if (WxReplymsgTemplate.TYPE_NEWS.equals(type)) {
			List<WxNewsMessage> items = this.listNewsMsgItems(templateId);
			template.setItems(items);
		}
		return template;
	}

	@Override
	public boolean saveNewsMessage(WxReplymsgTemplate template, List<Map<String, Object>> items, String userId, String accountId) throws AjaxException {
		int row = 0;
		if (this.validate_static(template)) {
			if (this.validate_news(items)) {
				String templateId = template.getTemplateId();
				if (StringUtils.isEmpty(templateId)) {
					templateId = IdentifierUtils.getId().generate().toString();
					template.setCreateBy(userId);
					template.setAccountId(accountId);
					template.setResourceType(WxReplymsgTemplate.RESOURCE_TYPE_STATIC);
					template.setStatus(WxReplymsgTemplate.STATUS_ENABLE);
					template.setTemplateId(templateId);
					if (this.validate_name(template)) {
						List<WxNewsMessage> newsMessageItems = new ArrayList<WxNewsMessage>();
						for (Map<String, Object> item : items) {
							String attaId = (String) item.get("attaId");
							String title = (String) item.get("title");
							String url = (String) item.get("targetHref");
							String description = (String) item.get("description");
							String msgIndex = String.valueOf(item.get("msgIndex"));
							WxNewsMessage newsMessage = new WxNewsMessage();
							newsMessage.setDescription(description);
							newsMessage.setMsgIndex(msgIndex);
							newsMessage.setNewsMessageId(IdentifierUtils.getId().generate().toString());
							newsMessage.setTargetHref(url);
							newsMessage.setTemplateId(templateId);
							newsMessage.setTitle(title);
							newsMessage.setAttaId(attaId);
							newsMessageItems.add(newsMessage);
						}
						row = wxReplymsgTemplateDao.insert(template);
						wxNewsMessageDao.batchInsert(newsMessageItems);
					}
				}
			}
		}
		return row > 0;
	}

	@Override
	public boolean editNewsMessage(WxReplymsgTemplate template, List<Map<String, Object>> items, String[] delItemsArray, String userId, String accountId) {
		int row = 0;
		if (this.validate_static(template)) {
			if (this.validate_news(items)) {
				String templateId = template.getTemplateId();
				if (!StringUtils.isEmpty(templateId)) {
					List<WxNewsMessage> update = new ArrayList<WxNewsMessage>();
					List<WxNewsMessage> insert = new ArrayList<WxNewsMessage>();
					for (Map<String, Object> item : items) {
						String attaId = (String) item.get("attaId");
						String title = (String) item.get("title");
						String url = (String) item.get("targetHref");
						String description = (String) item.get("description");
						String msgIndex = String.valueOf(item.get("msgIndex"));
						String newsMessageId = (String) item.get("newsMessageId");
						WxNewsMessage newsMessage = new WxNewsMessage();
						newsMessage.setDescription(description);
						newsMessage.setMsgIndex(msgIndex);
						newsMessage.setTargetHref(url);
						newsMessage.setTemplateId(templateId);
						newsMessage.setTitle(title);
						newsMessage.setAttaId(attaId);
						if (StringUtils.isEmpty(newsMessageId)) {
							newsMessage.setNewsMessageId(IdentifierUtils.getId().generate().toString());
							insert.add(newsMessage);
						} else {
							newsMessage.setNewsMessageId(newsMessageId);
							update.add(newsMessage);
						}
					}
					if (this.validate_name(template)) {
						row = wxReplymsgTemplateDao.update(template);
						if (delItemsArray.length > 0)
							wxNewsMessageDao.batchDelete(delItemsArray);
						if (insert.size() > 0)
							wxNewsMessageDao.batchInsert(insert);
						if (update.size() > 0)
							wxNewsMessageDao.batchUpdate(update);
					}
				}
			}
		}
		return row > 0;
	}

	@Override
	public List<WxNewsMessage> listNewsMsgItems(String templateId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("templateId", templateId);
		return wxNewsMessageDao.findDomain(paramMap);
	}

	@Override
	public boolean changestatus(String templateId) throws AjaxException {
		WxReplymsgTemplate template = wxReplymsgTemplateDao.get(templateId);
		template.setStatus(WxReplymsgTemplate.STATUS_DISABLE);
		return wxReplymsgTemplateDao.update(template) > 0;
	}

	/**
	 * 后台验证数据合法性——验证模板名称是否存在
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_name(WxReplymsgTemplate wxReplymsgTemplate) throws AjaxException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String accountId = wxReplymsgTemplate.getAccountId();
		String name = wxReplymsgTemplate.getName();
		String status = WxReplymsgTemplate.STATUS_ENABLE;
		String templateId = wxReplymsgTemplate.getTemplateId();
		if (StringUtils.isNotEmpty(templateId)) {
			paramMap.put("except", templateId);
		}
		paramMap.put("accountId", accountId);
		paramMap.put("name", name);
		paramMap.put("status", status);
		int row = wxReplymsgTemplateDao.checkExists(paramMap);
		if (row > 0) {
			throw new AjaxException("该模板名称已存在");
		} else {
			return true;
		}
	}

	/**
	 * 后台验证数据合法性——动态模板非空验证
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_dynamic(WxReplymsgTemplate wxReplymsgTemplate) throws AjaxException {
		String name = wxReplymsgTemplate.getName();
		if (StringUtils.isEmpty(name)) {
			throw new AjaxException("请填写模板名称");
		}
		String resourceId = wxReplymsgTemplate.getResourceId();
		if (StringUtils.isEmpty(resourceId)) {
			throw new AjaxException("请选择资源地址");
		}
		return true;
	}

	/**
	 * 后台验证数据合法性——静态模板非空验证
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_static(WxReplymsgTemplate wxReplymsgTemplate) throws AjaxException {
		String name = wxReplymsgTemplate.getName();
		if (StringUtils.isEmpty(name)) {
			throw new AjaxException("请填写模板名称");
		}
		String type = wxReplymsgTemplate.getType();
		if (WxReplymsgTemplate.TYPE_TEXT.equals(type)) {
			String content = wxReplymsgTemplate.getContent();
			if (StringUtils.isEmpty(content)) {
				throw new AjaxException("请填写消息内容");
			}
			return true;
		} else if (WxReplymsgTemplate.TYPE_IMAGE.equals(type)) {
			String atta = wxReplymsgTemplate.getAttaId();
			if (StringUtils.isEmpty(atta)) {
				throw new AjaxException("请选择回复图片");
			}
			return true;
		} else if (WxReplymsgTemplate.TYPE_VOICE.equals(type)) {
			String atta = wxReplymsgTemplate.getAttaId();
			if (StringUtils.isEmpty(atta)) {
				throw new AjaxException("请选择回复音频");
			}
			return true;
		} else if (WxReplymsgTemplate.TYPE_VIDEO.equals(type)) {
			String atta = wxReplymsgTemplate.getAttaId();
			if (StringUtils.isEmpty(atta)) {
				throw new AjaxException("请选择回复视频");
			}
			return true;
		} else if (WxReplymsgTemplate.TYPE_NEWS.equals(type)) {
			return true;
		}
		return false;
	}

	/**
	 * 后台验证数据合法性——图文消息非空验证
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	private boolean validate_news(List<Map<String, Object>> items) throws AjaxException {
		if (items == null) {
			throw new AjaxException("请编辑图文项");
		}
		if (items.size() == 0) {
			throw new AjaxException("请编辑图文项");
		}
		if (items.size() > 8) {
			throw new AjaxException("你最多只可以加入8条图文消息");
		}
		for (Map<String, Object> item : items) {
			String attaId = (String) item.get("attaId");
			if (StringUtils.isEmpty(attaId)) {
				throw new AjaxException("图文消息必须包含图片");
			}
			String title = (String) item.get("title");
			if (StringUtils.isEmpty(title)) {
				throw new AjaxException("请输入图文消息的标题");
			}
			String url = (String) item.get("targetHref");
			if (StringUtils.isEmpty(url)) {
				throw new AjaxException("请输入图文消息的链接地址");
			}
		}
		return true;
	}

}
