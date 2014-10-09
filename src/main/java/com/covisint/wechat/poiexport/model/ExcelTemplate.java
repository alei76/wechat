package com.covisint.wechat.poiexport.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * POI导出模板xml配置文件解析类
 */
@XStreamAlias("export")
public class ExcelTemplate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -123705566913167969L;

	@XStreamImplicit(itemFieldName = "template")
	private List<ExcelSheet> template;

	public List<ExcelSheet> getTemplate() {
		return template;
	}

	public void setTemplate(List<ExcelSheet> template) {
		this.template = template;
	}

}
