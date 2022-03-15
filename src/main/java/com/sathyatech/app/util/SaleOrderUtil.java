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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.sathyatech.app.model.Item;
import com.sathyatech.app.model.SaleOrder;
import com.sathyatech.app.model.ShipmentType;
import com.sathyatech.app.model.WHUserType;
import com.sathyatech.app.service.IItemService;
import com.sathyatech.app.service.IShipmentService;
import com.sathyatech.app.service.IWHUserTypeService;

@Component
public class SaleOrderUtil {

	@Autowired
	private IShipmentService shipService;

	@Autowired
	private IWHUserTypeService userService;

	@Autowired
	private IItemService itemService;

	public static List<String> getStockMode(){
		return Arrays.asList("GRADE","MARGIN");
	}

	public static List<String> getStockSource(){
		return Arrays.asList("OPEN","AVAIL","REFUND");
	}

	public void addUi(ModelMap map){
		map.addAttribute("saleOrderCode", GeneratorsUtil.codeGenerator());
		map.addAttribute("stockModes", getStockMode());
		map.addAttribute("stockSources", getStockSource());
		map.addAttribute("shipModes", shipService.findByEnableShipment("YES"));
		map.addAttribute("customersList",userService.findByUserType("CUSTOMER"));
	}

	//Fetch Items Based On Cust ..id
	public List<Item> findItemByCustomer(Long id){
		System.out.println(itemService.findItemByCustomer(id));
		return itemService.findItemByCustomer(id);
	}

	public List<SaleOrder> convertMultipartToList(MultipartFile saleFile){
		List<SaleOrder> listSaleOrders = new ArrayList<SaleOrder>();
		if(saleFile != null){
			try {
				InputStream is= saleFile.getInputStream();

				XSSFWorkbook book = new XSSFWorkbook(is);

				XSSFSheet sheet= book.getSheet("sale");

				if(sheet != null){
					
					DataFormatter formatter = new DataFormatter();
					
					Iterator<Row> rows= sheet.iterator();

					while(rows.hasNext()){
						Row row= rows.next();

						if(row.getRowNum() == 0) continue;
						
						SaleOrder saleOrder = new SaleOrder(GeneratorsUtil.codeGenerator().trim(),
								findByShipmentCode(formatter.formatCellValue(row.getCell(0)).trim()),
								findByWhUserCode(formatter.formatCellValue(row.getCell(1)).trim()),
								formatter.formatCellValue(row.getCell(2)).trim(),
								formatter.formatCellValue(row.getCell(3)).trim(),
								formatter.formatCellValue(row.getCell(4)).trim(),
								formatter.formatCellValue(row.getCell(5)).trim());
						
						saleOrder.setStatus("SALE-OPEN");
						
						//Add SaleOrder To List
						listSaleOrders.add(saleOrder);
					}
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listSaleOrders;
	}
	
	//Find By Ship. Code
	public ShipmentType findByShipmentCode(String cell){
		return shipService.findByShipmentCode(cell);
	}
	
	
	//Find By WHUserType Code
	public WHUserType findByWhUserCode(String cell){
		return userService.findByWhUserCode(cell);
	}
}
