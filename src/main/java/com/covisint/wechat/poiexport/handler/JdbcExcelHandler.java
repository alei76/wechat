package com.covisint.wechat.poiexport.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.covisint.wechat.poiexport.model.ExcelRow;
import com.covisint.wechat.poiexport.model.ExcelSheet;

/**
 * POI Excel结合JDBC回调方法导出处理类
 */
public class JdbcExcelHandler implements RowCallbackHandler {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private CellStyle cellBorder;
	private List<ExcelRow> rows;

	/**
	 * 构造方法
	 */
	public JdbcExcelHandler(ExcelSheet template) {
		rows = template.getRows();
		workbook = new XSSFWorkbook();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平布局：居中
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		Font headFont = workbook.createFont();
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 11);// 设置字体大小
		headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
		cellStyle.setFont(headFont);

		cellBorder = workbook.createCellStyle();
		cellBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		cellBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		Font bodyFont = workbook.createFont();
		bodyFont.setFontName("宋体");
		bodyFont.setFontHeightInPoints((short) 11);// 设置字体大小
		cellBorder.setFont(bodyFont);
		String sheetName = template.getName();
		sheet = workbook.createSheet(sheetName);
		row = sheet.createRow(0);
		for (int i = 0; i < rows.size(); i++) {
			ExcelRow r = rows.get(i);
			XSSFCell cell = row.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(r.getTitle());
			cell.setCellStyle(cellStyle);
		}
	}

	/**
	 * 数据结果集处理
	 */
	@Override
	public void processRow(ResultSet resultSet) throws SQLException {
		int rowNum = resultSet.getRow();
		row = sheet.createRow(rowNum);
		for (int i = 0; i < rows.size(); i++) {
			ExcelRow r = rows.get(i);
			XSSFCell cell = row.createCell(i);
			cell.setCellStyle(cellBorder);
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			String key = r.getKey();
			cell.setCellValue(resultSet.getString(key));// 填充值
		}
	}

	/**
	 * 文件输出
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public void write(OutputStream outstream) throws IOException {
		workbook.write(outstream);
		outstream.flush();
		outstream.close();
	}

}
