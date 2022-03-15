package com.sathyatech.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import com.sathyatech.app.model.PurchaseOrder;
import com.sathyatech.app.model.ShipmentType;
import com.sathyatech.app.model.WHUserType;
import com.sathyatech.app.service.IItemService;
import com.sathyatech.app.service.IPurchaseOrderService;
import com.sathyatech.app.service.IShipmentService;
import com.sathyatech.app.service.IWHUserTypeService;


@Component
public class PurchaseOrderUtil {

	@Autowired
	private IWHUserTypeService whUserService;
	@Autowired
	private IShipmentService shipMentService;
	@Autowired
	private IItemService itemService;

	@Autowired
	private IPurchaseOrderService poService;

	public List<String> getQualityChecks(){
		List<String> list=new ArrayList<String>();
		list.add("Required");
		list.add("Not Required");
		return list;
	}

	public void addUiComponents(ModelMap map) {
		map.addAttribute("qualityChecks", getQualityChecks());
		map.addAttribute("vendorsList",whUserService.findByUserType("VENDOR"));
		map.addAttribute("shipmentTypes",shipMentService.findByEnableShipment("YES"));
	}

	public List<Item> getVendorItems(Long vendorId){
		return itemService.findItemsByVendor(vendorId);
	}

	//Multi

	public List<PurchaseOrder> convertFileToList(MultipartFile poFile){

		List<PurchaseOrder> listPo = new ArrayList<PurchaseOrder>();
		if(poFile != null){
			//Get IS From poFile
			try {
				InputStream is= poFile.getInputStream();

				//Create BOOK
				XSSFWorkbook book = new XSSFWorkbook(is);

				//Get Sheet Using sheet name
				XSSFSheet sheet= book.getSheet("po");

				if(sheet !=null){
					//Get One by One Row From Sheet
					Iterator<Row> rows= sheet.iterator();

					DataFormatter formatter = new DataFormatter();

					while(rows.hasNext()){
						Row row = rows.next();

						if(row.getRowNum()==0)continue;

						PurchaseOrder po = new PurchaseOrder(formatter.formatCellValue(row.getCell(0)),
								getShipmentCode(formatter.formatCellValue(row.getCell(1))),
								getVendor(formatter.formatCellValue(row.getCell(2))), 
								formatter.formatCellValue(row.getCell(3)),
								formatter.formatCellValue(row.getCell(4)),
								formatter.formatCellValue(row.getCell(5)),
								formatter.formatCellValue(row.getCell(6)));

						//Add To List
						listPo.add(po);
					}
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listPo;
	}

	public ShipmentType getShipmentCode(String cell){
		return shipMentService.findByShipmentCode(cell);
	}

	public WHUserType getVendor(String cell){
		return whUserService.findByWhUserCode(cell);
	}
}