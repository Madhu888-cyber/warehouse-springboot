package com.sathyatech.app.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import com.sathyatech.app.model.OrderMethod;

public class OrderXlsxView extends AbstractXlsxView {
	
	protected void buildExcelDocument(Map<String, Object> model, Workbook book,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//Set Book Name(Excel Name)
		res.setHeader("Content-Disposition", "attachment;filename=orderFile.xlsx");
		
		//1 Read Data s
		@SuppressWarnings("unchecked")
		List<OrderMethod> orderMethodList=(List<OrderMethod>) model.get("orderMethodList");
		
		//2 Create Sheet
		Sheet sheet = book.createSheet("orderFile");
		
		//Set Head To Sheet
		setHead(sheet);
		
		//Set Body To Sheet
		setBody(sheet,orderMethodList);
	}
	
	public void setHead(Sheet sheet){
		Row row = sheet.createRow(0);
		
		row.createCell(0).setCellValue("OrderId");
		
		row.createCell(1).setCellValue("Created On");
		
		row.createCell(2).setCellValue("Order Mode");
		
		row.createCell(3).setCellValue("Order Code");
		
		row.createCell(4).setCellValue("Order Method");
		
		row.createCell(5).setCellValue("Order Accept");
		
		row.createCell(6).setCellValue("Description");
		
		row.createCell(7).setCellValue("Modified On");
	}
	
	public void setBody(Sheet sheet,List<OrderMethod> orderMethodList){
		
		int rowNum = 1;
		
		//Get One-by-One Object From List
		for(OrderMethod orderMethod : orderMethodList){
			
			Row row= sheet.createRow(rowNum++);
			
			row.createCell(0).setCellValue(orderMethod.getOrderId());
			
			row.createCell(1).setCellValue(orderMethod.getCreatedOn().toString());
			
			row.createCell(2).setCellValue(orderMethod.getOrderMode());
			
			row.createCell(3).setCellValue(orderMethod.getOrderCode());
			
			row.createCell(4).setCellValue(orderMethod.getOrderMethd());
			
			row.createCell(5).setCellValue(orderMethod.getOrderAccept().toString());
			
			row.createCell(6).setCellValue(orderMethod.getDescription());
			
			row.createCell(7).setCellValue(orderMethod.getLastModefiedOn().toString());
		}
	}
}
