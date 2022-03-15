package com.sathyatech.app.validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sathyatech.app.model.SaleOrder;
import com.sathyatech.app.service.IShipmentService;
import com.sathyatech.app.util.SaleOrderUtil;

@Component
public class SaleOrderMultipartValidator {
	
	@Autowired
	private IShipmentService shipService;
	
	public Map<String,List<String>> multipartValidator(List<SaleOrder> saleOrders){
		
		Map<String,List<String>> errorMap = new LinkedHashMap<String, List<String>>();
		int row = 1;
		for(SaleOrder saleOrder: saleOrders){
			List<String> errorList = new ArrayList<String>(0);
			
			if(saleOrder.getSaleShipmentMode()==null || saleOrder.getSaleShipmentMode().getShipmentId()==null){
				errorList.add("Plz Provide Valid ShipmentCode");
			}
			else if(!shipService.findByEnableShipment("YES").contains(saleOrder.getSaleShipmentMode())){
				errorList.add("ShipmentType \'"+saleOrder.getSaleShipmentMode().getShipmentMode()+"\' With Code \'"+saleOrder.getSaleShipmentMode().getShipmentCode()+"\' is Temporarly Disabled..");
			}
			
			if(saleOrder.getSaleCustomers() == null || saleOrder.getSaleCustomers().getWhUserId() == null){
				errorList.add("Plz Provide Valid Customer");
			}
			
			if(StringUtils.isEmpty(saleOrder.getReferenceNumber())){
				errorList.add("Plz Enter Ref Number");
			}
			else if(!Pattern.compile("[0-9]{4,8}").matcher(saleOrder.getReferenceNumber()).matches()){
				errorList.add("4-8 Digits Only");
			}
			
			if(saleOrder.getStockMode()== "" || saleOrder.getStockMode() == null){
				errorList.add("Plz Select StockMode From:"+SaleOrderUtil.getStockMode());
			}
			else if(!SaleOrderUtil.getStockMode().contains(saleOrder.getStockMode().toUpperCase())){
				errorList.add("Plz Select StockMode From:"+SaleOrderUtil.getStockMode());
			}
			
			if(saleOrder.getStockSource()=="" || saleOrder.getStockSource()==null){
				errorList.add("Plz Provide stockSource From:"+SaleOrderUtil.getStockSource());
			}
			else if(!SaleOrderUtil.getStockSource().contains(saleOrder.getStockSource().toUpperCase())){
				errorList.add("Plz Provide stockSource From:"+SaleOrderUtil.getStockSource());
			}
			
			if(StringUtils.isEmpty(saleOrder.getDescription())){
				errorList.add("Plz Write Description");
			}
			else if(!Pattern.compile("[a-zA-Z \\t\\n\\x0B\\f\\r]{10,255}").matcher(saleOrder.getDescription()).matches()){
				errorList.add("Chars only range of 10-255 accepted");
			}
			
			if(!errorList.isEmpty()){
				errorMap.put("Errors At Row#"+row, errorList);
			}
			row++;
		}
		return errorMap;
	}
}
