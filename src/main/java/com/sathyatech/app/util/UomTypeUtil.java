package com.sathyatech.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.sathyatech.app.model.Uom;

@Component
public class UomTypeUtil {

	public static final List<String> listType(){
		List<String> listType= Arrays.asList("PACKING","NO PACKING","NA");
		return listType;
	}

	public List<Uom> processMultipart(MultipartFile uomFile){

		List<Uom> uomList = null;
		if(uomFile != null){
			try {

				uomList = new ArrayList<Uom>();

				InputStream is = uomFile.getInputStream();

				XSSFWorkbook book = new XSSFWorkbook(is);

				XSSFSheet sheet= book.getSheet("uomFile");

				if(sheet != null){
					
					//Get Rows from Sheet into Iterator
					Iterator<Row> rows= sheet.iterator();

					//Get Rows One-by-One from Iterator
					while (rows.hasNext()) {					
						Row row =  rows.next();

						if(row.getRowNum()==0)continue;	//Skip

						//Use DataFormatter to format Cell Value To String
						DataFormatter formatter = new DataFormatter();

						Uom uom = new Uom(formatter.formatCellValue(row.getCell(0)).trim().toUpperCase(),
								formatter.formatCellValue(row.getCell(1)).trim().toUpperCase(),
								formatter.formatCellValue(row.getCell(2)).trim().toUpperCase());

						//Save Uom To List
						uomList.add(uom);
					}
				}

			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return uomList;
	}
}
