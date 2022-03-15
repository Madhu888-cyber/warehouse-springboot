package com.sathyatech.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.sathyatech.app.model.OrderMethod;

@Component
public class OrderMethodUtils {

	//----------Get orderMethd List----------
	public static final List<String> getOrderMethdsList(){
		return Arrays.asList("FIFO","LIFO","FCFO","FEFO");

	}

	//----------Get List of orderMode----------
	public static final List<String> getOrderModeList(){
		return Arrays.asList("SALE","PURCHASE");
	}

	//----------Get List of orderAccept----------
	public static final List<String> getOrderAcceptList(){
		return Arrays.asList("MULTI_MODEL","ACCEPT_RETURN");
	}

	//----------Generate orderCode----------
	public static final String commonStr(int length){
		return UUID.randomUUID()
				.toString()
				.replace("-", "")
				.substring(0, length);
	}

	public static final String generateOrderCode(){
		return commonStr(11);
	}

	//----------Convert MultipartFile To List<OrderMethod>----------

	public List<OrderMethod> convertMultipartToList(MultipartFile orderFile){
		List<OrderMethod> listOrderMethods = new ArrayList<OrderMethod>();

		try {
			//Get InputStreams From MultipartFile Obj
			InputStream is = orderFile.getInputStream();

			//Create XSSFWorkbook & Pass InputStreams to its Constructor
			XSSFWorkbook book = new XSSFWorkbook(is);

			//Get the sheets from Workbook using sheet name 
			XSSFSheet sheet = book.getSheet("orderFile");

			if(sheet != null){
				//Get One-by-One Rows from Sheets Using iterator
				Iterator<Row> rows= sheet.iterator();

				while(rows.hasNext()){
					Row row = rows.next();

					//Skip 1st Row coz of Heading
					if(row.getRowNum() == 0)
						continue;

					//Get The Cells From Row & Set To OrderMethod Object
					//Use DataFormatter To Convert Row Cells of Excel to String Format
					DataFormatter formatter = new DataFormatter();

					OrderMethod orderMethod = new OrderMethod(formatter.formatCellValue(row.getCell(0)).trim().toUpperCase(),
							formatter.formatCellValue(row.getCell(1)).trim().toUpperCase(), 
							getOrderAcceptTokens(formatter.formatCellValue(row.getCell(2))), 
							formatter.formatCellValue(row.getCell(3)).trim().toUpperCase(),
							formatter.formatCellValue(row.getCell(4)).trim().toUpperCase());

					//Add OrderMethod To List
					listOrderMethods.add(orderMethod);
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return listOrderMethods;
	}

	//Split orderAccept Cell String To Tokens Using StringTokenizer
	public List<String> getOrderAcceptTokens(String orderAcceptCell){
		//Create StringTokenizer
		StringTokenizer st = new StringTokenizer(orderAcceptCell, ",");
		List<String> orderAcceptTokens = new ArrayList<String>();
		while(st.hasMoreTokens()){
			orderAcceptTokens.add(st.nextToken().trim());
		}
		return orderAcceptTokens;
	}
}