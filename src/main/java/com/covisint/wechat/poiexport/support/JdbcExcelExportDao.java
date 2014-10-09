package com.covisint.wechat.poiexport.support;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.covisint.wechat.poiexport.handler.JdbcExcelHandler;
import com.covisint.wechat.poiexport.model.ExcelSheet;
import com.covisint.wechat.poiexport.parse.ExcelExportXmlParse;

/**
 * JDBC方式 excel导出
 * 
 * @author Administrator
 * 
 */
public class JdbcExcelExportDao {
	private NamedParameterJdbcTemplate jdbcTemplate;

	private ExcelExportXmlParse parse;

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setParse(ExcelExportXmlParse parse) {
		this.parse = parse;
	}

	/**
	 * excel导出方法
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public void export(String sql, Map<String, Object> paramMap, String templateId, OutputStream outstream) throws IOException {
		ExcelSheet sheet = parse.getSheet(templateId);
		JdbcExcelHandler rowHandler = new JdbcExcelHandler(sheet);
		jdbcTemplate.query(sql, paramMap, rowHandler);
		rowHandler.write(outstream);
	}
}
