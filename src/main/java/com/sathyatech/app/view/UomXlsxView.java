package com.sathyatech.app.view;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import com.sathyatech.app.model.Uom;

public class UomXlsxView extends AbstractXlsxView{

	protected void buildExcelDocument(Map<String, Object> model, Workbook book,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//Set Book Name
		res.setHeader("Content-Disposition", "attachment;filename=uomFile.xlsx");
			
		//Read Data From Map
		@SuppressWarnings("unchecked")
		List<Uom> uomList = (List<Uom>) model.get("uomList");
		
		//Create Sheet
		Sheet sheet= book.createSheet("uomFile");
		
		//Set Sheet Header 
		setHeader(sheet);
		
		//Set Sheet Body
		setBody(sheet,uomList);
	}
	
	public void setHeader(Sheet sheet){
		//Create Row To set Heading
		Row row = sheet.createRow(0);
		
		//Create Cells and Set Heading Value
		row.createCell(0).setCellValue("UomId");
		
		row.createCell(1).setCellValue("Created On");
		
		row.createCell(2).setCellValue("Uom Type");
		
		row.createCell(3).setCellValue("Uom Model");
		
		row.createCell(4).setCellValue("Description");
		
		row.createCell(5).setCellValue("Modified On");
	}
	
	public void setBody(Sheet sheet,List<Uom> uomList){
		int rowNum = 1;
		for(Uom uom : uomList){
			//Create Row 
			Row row = sheet.createRow(rowNum++);
			
			//Create Cell & Set Value
			row.createCell(0).setCellValue(uom.getUomId());
			
			row.createCell(1).setCellValue(uom.getCreatedOn());
			
			row.createCell(2).setCellValue(uom.getUomType());
			
			row.createCell(3).setCellValue(uom.getUomModel());
			
			row.createCell(4).setCellValue(uom.getDescription());
			
			row.createCell(5).setCellValue(uom.getModifiedOn());
			
		}
	}
}
