package com.sathyatech.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sathyatech.app.model.WHUserType;

@Component
public class WHUserTypeUtil {

	@Autowired
	private static WHUserType wh;

	//Get WHUserTypes
	public static final List<String> getUserTypes(){
		return Arrays.asList("VENDOR","CUSTOMER");
	}

	//Get whUserIdNumber
	public static final List<String> getWHUserIdTypes(){
		return Arrays.asList("PAN CARD","AADHAR","VOTERID","OTHER");
	}

	//Get whUserCode
	public static final String getUserCode(){
		StringBuilder sb = new StringBuilder();
		String s = UUID.randomUUID().toString().replace("-","").substring(0, 7);
		
		/*if(wh.getUserType()!=null){

			if("VENDOR".equals(wh.getUserType())){
				 sb.append("VEN"+s).toString();
			}
			else{
				 sb.append("CUST"+s).toString();
			}
		}*/
		return sb.append(s).toString();
		
	}

	//Convert MutipartFile To List
	public List<WHUserType> convertMultipartToList(MultipartFile userFile){
		List<WHUserType> whUserTypes = new ArrayList<WHUserType>();
		try {
			//Take IS from FIle
			InputStream is = userFile.getInputStream();

			//Create Workbook by adding InputStreams
			XSSFWorkbook book =new XSSFWorkbook(is);

			//Get Sheet Using name 
			XSSFSheet sheet= book.getSheet("userFile");

			if(sheet != null){

				//Get One-by-One Row from Sheet Using Iterator
				Iterator<Row> rows= sheet.iterator();

				while(rows.hasNext()){
					Row row= rows.next();
					//Skip Row 0 coz contains Heading
					if(row.getRowNum()==0)	continue;

					DataFormatter formatter = new DataFormatter();

					WHUserType whUserType = new WHUserType(formatter.formatCellValue(row.getCell(0)), 
							formatter.formatCellValue(row.getCell(1)), 
							formatter.formatCellValue(row.getCell(2)), 
							formatter.formatCellValue(row.getCell(3)), 
							formatter.formatCellValue(row.getCell(4)), 
							formatter.formatCellValue(row.getCell(5)), 
							formatter.formatCellValue(row.getCell(6)), 
							formatter.formatCellValue(row.getCell(7)));

					//Add Object To List
					whUserTypes.add(whUserType);
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		return whUserTypes;
	}
}
