package com.sathyatech.app.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.sathyatech.app.model.Item;
import com.sathyatech.app.model.WHUserType;

public class ItemXlsxView extends AbstractXlsxView{
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//Set Book name Excel Name
		response.setHeader("Content-Disposition", "attachment;filename=itemFile.xlsx");
		
		//Read Data From Map
		@SuppressWarnings("unchecked")
		List<Item> items= (List<Item>) model.get("items");
		
		//Create Sheet
		Sheet sheet= workbook.createSheet("itemFile");
		
		//SetHeader To Sheet
		setHeader(sheet);
		
		//Set Body
		setBody(sheet, items);
		
		
	}
	
	public void setHeader(Sheet sheet){
		//Create Row
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("itemId");
		row.createCell(1).setCellValue("ItemCode");
		row.createCell(2).setCellValue("itemWidth");
		row.createCell(3).setCellValue("itemLength");
		row.createCell(4).setCellValue("itemHeight");
		row.createCell(5).setCellValue("itemBaseCost");
		row.createCell(6).setCellValue("itemBaseCurrency");
		row.createCell(7).setCellValue("uom");
		row.createCell(8).setCellValue("orderSale");
		row.createCell(9).setCellValue("orderPurchase");
		row.createCell(10).setCellValue("vendors");
		row.createCell(11).setCellValue("customers");
		row.createCell(12).setCellValue("description");
		row.createCell(13).setCellValue("CreateOn");
		row.createCell(14).setCellValue("ModifiedOn");
	}
	
	public void setBody(Sheet sheet, List<Item> items){
		int rowNum = 1;
		for(Item item : items){
			Row row = sheet.createRow(rowNum++);
			
			row.createCell(0).setCellValue(item.getItemId());
			row.createCell(1).setCellValue(item.getItemCode());
			row.createCell(2).setCellValue(item.getItemWidth());
			row.createCell(3).setCellValue(item.getItemLength());
			row.createCell(4).setCellValue(item.getItemHeight());
			row.createCell(5).setCellValue(item.getItemBaseCost());
			row.createCell(6).setCellValue(item.getItemBaseCurrency());
			row.createCell(7).setCellValue(item.getUom().getUomModel());
			row.createCell(8).setCellValue(item.getOrderSale().getOrderCode());
			row.createCell(9).setCellValue(item.getOrderPurchase().getOrderCode());
			
			StringBuilder ven = new StringBuilder();		
			for(WHUserType whUserType: item.getVendors()){
				String s = whUserType.getWhUserCode();
				
				ven.append(s+" ");
			}
			row.createCell(10).setCellValue(ven.toString().trim());
			
			StringBuilder cust = new StringBuilder();	
			for(WHUserType whUserType: item.getCustomers()){
				String s = whUserType.getWhUserCode();
				cust.append(s+" ");
			}
			row.createCell(11).setCellValue(cust.toString().trim());
			
			
			row.createCell(12).setCellValue(item.getDescription());
			
			row.createCell(13).setCellValue(item.getCreatedOn().toString());
			
			row.createCell(14).setCellValue(item.getModifiedOn().toString());
		}
	}

	
}