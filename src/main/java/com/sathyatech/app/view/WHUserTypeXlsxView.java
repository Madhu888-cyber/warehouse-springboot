package com.sathyatech.app.view;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import com.sathyatech.app.model.WHUserType;

public class WHUserTypeXlsxView extends AbstractXlsxView{
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment;filename=userdata.xlsx");
		
		@SuppressWarnings("unchecked")
		List<WHUserType> whUserType = (List<WHUserType>)model.get("whUserTypes");
		
		Sheet sheet= workbook.createSheet("userFile");
		
		setHead(sheet);
		
		setBody(sheet,whUserType);
	}
	
	private void setHead(Sheet sheet){
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("whUserId");
		row.createCell(1).setCellValue("userType");
		row.createCell(2).setCellValue("whUserFor");
		row.createCell(3).setCellValue("whUserEmail");
		row.createCell(4).setCellValue("whUserContact");
		row.createCell(5).setCellValue("whUserIdType");
		row.createCell(6).setCellValue("whIfOther");
		row.createCell(7).setCellValue("whUserIdNumber");
		row.createCell(8).setCellValue("createdOn");
		row.createCell(9).setCellValue("modifiedOn");
	}
	
	private void setBody(Sheet sheet,List<WHUserType> whUserTypes){
		int rowNum = 1;
		for(WHUserType whUserType : whUserTypes){
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(whUserType.getWhUserId());
			row.createCell(1).setCellValue(whUserType.getUserType());
			row.createCell(2).setCellValue(whUserType.getWhUserFor());
			row.createCell(3).setCellValue(whUserType.getWhUserEmail());
			row.createCell(4).setCellValue(whUserType.getWhUserContact());
			row.createCell(5).setCellValue(whUserType.getWhUserIdType());
			row.createCell(6).setCellValue(whUserType.getWhIfOther());
			row.createCell(7).setCellValue(whUserType.getWhUserIdNumber());
			row.createCell(8).setCellValue(whUserType.getCreatedOn().toString());
			row.createCell(9).setCellValue(whUserType.getModeifiedOn().toString());
		}
	}
}
