package com.sathyatech.app.validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.sathyatech.app.model.Item;
import com.sathyatech.app.util.ItemUtil;

@Component
public class ItemMultipartValidator {

	public Map<String,List<String>> processValidation(List<Item> items){
		
		Map<String,List<String>> errorMap = new LinkedHashMap<String,List<String>>();
		int row = 2;
		
		for(Item item : items){
			List<String> errorList = new ArrayList<String>();
			
			if(StringUtils.isEmpty(item.getItemCode())){
				errorList.add("Plz Enter ItemCode");
			}
			else if(!Pattern.compile("[a-zA-Z0-9]{3,20}").matcher(item.getItemCode()).matches()){
				errorList.add("3-20 chars and digits only");
			}
			
			if(item.getItemWidth() == null || item.getItemWidth() <= 0){
				errorList.add("Plz Enter Width Greater than 0");
			}
			
			if(item.getItemLength() == null || item.getItemLength() <= 0){
				errorList.add("Plz Enter Length Greater than 0");
			}
			
			if(item.getItemHeight() == null || item.getItemHeight() <= 0){
				errorList.add("Plz Enter Height Greater than 0");
			}
			
			if(item.getItemWidth() == null || item.getItemBaseCost() <= 0){
				errorList.add("Plz Enter Width Greater than 0");
			}
			
			if(StringUtils.isEmpty(item.getItemBaseCurrency())){
				errorList.add("Plz Choose Base Currency");
			}
			else if(!ItemUtil.baseCurrency().contains(item.getItemBaseCurrency())){
				errorList.add("Plz Choose From: "+ItemUtil.baseCurrency());
			}
			
			//Uom
			if(item.getUom()==  null || item.getUom().getUomId() == null){
				errorList.add("Plz Enter Uom from:");
			}
			
			//OrderSale
			if(item.getOrderSale() == null || item.getOrderSale().getOrderId() == null){
				errorList.add("Plz Enter Valid Order Sale Code");
			}
			
			if(item.getOrderPurchase() == null || item.getOrderPurchase().getOrderId() == null){
				errorList.add("Plz Enter Valid Order Purchase Code");
			}
			if(item.getVendors()==null || item.getVendors().isEmpty()){
				errorList.add("plaz enter valid Vendors");
			}
			
			if(item.getCustomers()==null || item.getCustomers().isEmpty()){
				errorList.add("plaz enter valid Customers");
			}
			
			if(!errorList.isEmpty()){
				errorMap.put("Error At Row#"+row, errorList);
			}
			row++;
		}
		return errorMap;
	}
}