package com.sathyatech.app.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import com.sathyatech.app.model.SaleOrder;

public class SaleOrderXlsxView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook book,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		res.setHeader("Content-Disposition", "attachment;filename=saleOrder.xlsx");

		@SuppressWarnings("unchecked")
		List<SaleOrder> saleOrderList=(List<SaleOrder>) model.get("saleOrderList");

		Sheet sheet= book.createSheet();

		setHeader(sheet);

		setBody(sheet, saleOrderList);

	}

	public void setHeader(Sheet sheet){
		Row row= sheet.createRow(0);

		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Order Code");
		row.createCell(2).setCellValue("Mode");
		row.createCell(3).setCellValue("Customer");
		row.createCell(4).setCellValue("Ref Num");
		row.createCell(5).setCellValue("Stock Mode");
		row.createCell(6).setCellValue("Stock Sources");
		row.createCell(7).setCellValue("Status");
		row.createCell(8).setCellValue("Desc");
		row.createCell(9).setCellValue("Ct Date");
		row.createCell(10).setCellValue("Md Date");
	}

	public void setBody(Sheet sheet,List<SaleOrder> saleOrderList){
		int rowNum = 1;
		for(SaleOrder saleOrder:saleOrderList){
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(saleOrder.getSaleId());
			row.createCell(1).setCellValue(saleOrder.getOrderCode());
			row.createCell(2).setCellValue(saleOrder.getSaleShipmentMode().getShipmentCode());
			row.createCell(3).setCellValue(saleOrder.getSaleCustomers().getWhUserCode());
			row.createCell(4).setCellValue(saleOrder.getReferenceNumber());
			row.createCell(5).setCellValue(saleOrder.getStockMode());
			row.createCell(6).setCellValue(saleOrder.getStockSource());
			row.createCell(7).setCellValue(saleOrder.getStatus());
			row.createCell(8).setCellValue(saleOrder.getDescription());
			row.createCell(9).setCellValue(saleOrder.getCreatedOn().toString());
			row.createCell(10).setCellValue(saleOrder.getModifiedOn().toString());
		}
	}
}