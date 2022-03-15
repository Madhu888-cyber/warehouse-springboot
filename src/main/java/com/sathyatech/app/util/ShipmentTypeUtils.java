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
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sathyatech.app.model.ShipmentType;

@Component
public class ShipmentTypeUtils {

	public static final List<String> listShipMode(){
		return Arrays.asList("AIR","TRUCK","SHIP","TRAIN");
	}

	public static final List<String> generateGrade(){
		List<String> listGrade= Arrays.asList("A","B","C");
		return listGrade;
	}

	//Code Generator
	public static String commonStr(int length){

		return UUID.randomUUID()
				.toString()
				.replace("-", "")
				.substring(0, length);
	}

	//Shipment Code
	public static String getShipmentCode(){
		return commonStr(14);
	}

	//Multipart
	public List<ShipmentType> processMutilpart(MultipartFile shipmentFile){
		List<ShipmentType> shipList = new ArrayList<ShipmentType>();
		if(shipmentFile != null){
			try {
				InputStream is= shipmentFile.getInputStream();

				XSSFWorkbook book = new XSSFWorkbook(is);

				XSSFSheet sheet= book.getSheet("shipments");

				if(sheet != null){

					//From Sheet Get Row One By One using Iterator & Store in Row
					Iterator<Row> rows= sheet.iterator();
					while(rows.hasNext()){
						Row row= rows.next();
						//If 1st Row Skip
						if(row.getRowNum()==0)
							continue;

						/*TO Convert TO String*/
						DataFormatter formatter = new DataFormatter();
						//Read the Cells from Row & store in Object
						ShipmentType shipType = new ShipmentType(
								formatter.formatCellValue(row.getCell(0)),
								formatter.formatCellValue(row.getCell(1)),
								formatter.formatCellValue(row.getCell(2)),
								formatter.formatCellValue(row.getCell(3)));
						//Set Shipment Code
						shipType.setShipmentCode(getShipmentCode());

						//Store Object To List
						shipList.add(shipType);
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return shipList;
	}
}
