package com.sathyatech.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.sathyatech.app.model.Item;
import com.sathyatech.app.model.OrderMethod;
import com.sathyatech.app.model.Uom;
import com.sathyatech.app.model.WHUserType;
import com.sathyatech.app.service.IOrderMethodService;
import com.sathyatech.app.service.IUomService;
import com.sathyatech.app.service.IWHUserTypeService;

@Component
public class ItemUtil {
	
	@Autowired
	private IUomService service;
	
	@Autowired
	private IOrderMethodService orderService;
	
	@Autowired
	private IWHUserTypeService whUserService;
	
	@Autowired
	private IUomService uomService;
	
	public static final List<String> baseCurrency(){
		return Arrays.asList("INR","USD","AUS","EURO");
	}
	
	public void addUiComponents(ModelMap map){
		map.addAttribute("baseCurrencies", baseCurrency());
		map.addAttribute("uoms", service.getAllUom());
		map.addAttribute("orderSales", orderService.findByOrderMode("SALE"));
		map.addAttribute("orderPurchases", orderService.findByOrderMode("PURCHASE"));
		map.addAttribute("whUserVendors", whUserService.findByUserType("VENDOR"));
		map.addAttribute("whUserCustomers", whUserService.findByUserType("CUSTOMER"));
	}
	
	/* For Validation Purpose Fetch List of SALE or PURCHASE
	 * In Validator Checks Whether given code belongs to SALE or PURCHASE 
	 */
	public List<String> listOrderCode(String code){
		List<OrderMethod> orderMethods = orderService.findByOrderMode(code);
		ArrayList<String> codes= new ArrayList<String>();
		for(OrderMethod om :orderMethods){
			codes.add(om.getOrderCode());
		}
		return codes;
	}
	
	
	
	//Multipart
	public List<Item> processMultipart(MultipartFile itemFile){
		List<Item> items = new ArrayList<Item>();
		try {
			InputStream is = itemFile.getInputStream();
			
			XSSFWorkbook book = new XSSFWorkbook(is);
			
			XSSFSheet sheet = book.getSheet("itemFile");
			
			if(sheet == null) return null;
			
			Iterator<Row> rows = sheet.iterator();
			
			DataFormatter formatter = new DataFormatter();
			
			while(rows.hasNext()){
				Row row= rows.next();
				
				if(row.getRowNum()==0)continue;
				
				Item item = new Item(row.getCell(0).getStringCellValue(),
						Double.parseDouble(formatter.formatCellValue(row.getCell(1)).trim()), 
						Double.parseDouble(formatter.formatCellValue(row.getCell(2)).trim()), 
						Double.parseDouble(formatter.formatCellValue(row.getCell(3)).trim()), 
						Double.parseDouble(formatter.formatCellValue(row.getCell(4)).trim()), 
						row.getCell(5).getStringCellValue().trim(), 
						getUomModel(row.getCell(6).getStringCellValue().trim()),
						getOrderCode(row.getCell(7).getStringCellValue().trim()),
						getOrderCode(row.getCell(8).getStringCellValue().trim()),
						getUserCodes(formatter.formatCellValue(row.getCell(9)).trim()),
						getUserCodes(formatter.formatCellValue(row.getCell(10)).trim()),
						row.getCell(11).getStringCellValue().trim());
				
				//Add Item To List
				items.add(item);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	//Fetch Uom based On UomModel
	public Uom getUomModel(String uomModel){
		//System.out.println(uomService.findByUomModel(uomModel)+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		return uomService.findByUomModel(uomModel);
	}
	
	//Fetch OrderMethod based On OrderCode
	public OrderMethod getOrderCode(String orderCode){
		System.out.println(orderService.findByOrderCode(orderCode));
		return orderService.findByOrderCode(orderCode);
	}
	
	
	public List<WHUserType> getUserCodes(String cell){
		
		StringTokenizer st = new StringTokenizer(cell, ",");
		
		List<String> codesList = new ArrayList<String>();
		
		while(st.hasMoreTokens()){
			codesList.add(st.nextToken().trim());
		}
		//System.out.println(codesList+"******************************************************");
		
		//Fetch List of WHUserType based on List of whUserCode
		List<WHUserType> whUserTypes= whUserService.findByWhUserCodeIn(codesList);
		
		return whUserTypes;
	}
}
