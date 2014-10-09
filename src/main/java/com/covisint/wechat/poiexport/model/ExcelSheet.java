package com.covisint.wechat.poiexport.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * POI导出模板xml配置文件解析类
 */
@XStreamAlias("template")
public class ExcelSheet implements Serializable {
	private static final long serialVersionUID = 8752340895098882283L;

	@XStreamAlias("id")
	@XStreamAsAttribute
	private String id;

	@XStreamAlias("name")
	@XStreamAsAttribute
	private String name;

	@XStreamImplicit(itemFieldName = "column")
	private List<ExcelRow> rows;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExcelRow> getRows() {
		return rows;
	}

	public void setRows(List<ExcelRow> rows) {
		this.rows = rows;
	}

}
