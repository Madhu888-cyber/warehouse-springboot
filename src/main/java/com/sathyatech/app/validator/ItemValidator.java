package com.sathyatech.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sathyatech.app.model.Item;
import com.sathyatech.app.util.ItemUtil;

@Component
public class ItemValidator implements Validator {


	@Autowired
	private ItemUtil itemUtil;
	
	public boolean supports(Class<?> clazz) {
		return Item.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Item item = (Item)target;
		
		if(StringUtils.isEmpty(item.getItemCode())){
			errors.rejectValue("itemCode", "", "Plz Enter Item Code");
		}
		else if(!Pattern.compile("[a-zA-Z0-9]{4,20}").matcher(item.getItemCode()).matches()){
			errors.rejectValue("itemCode", "", "Item Code must be 4-20 Alpha Numeric");
		}
		
		if(item.getItemWidth() == null || item.getItemWidth() <= 0){
			errors.rejectValue("itemWidth", "", "Plz Enter Width Greater than 0");
		}
		
		if(item.getItemLength() == null || item.getItemLength() <= 0){
			errors.rejectValue("itemLength", "", "Plz Enter Length Greater than 0");
		}
		
		if(item.getItemHeight() == null || item.getItemHeight() <= 0){
			errors.rejectValue("itemHeight", "", "Plz Enter Height Greater than 0");
		}
		
		if(item.getItemBaseCost() == null || item.getItemBaseCost() <= 0){
			errors.rejectValue("itemBaseCost", "", "Plz Enter Cost Greater than 0");
		}
		
		
		if(!ItemUtil.baseCurrency().contains(item.getItemBaseCurrency())){
			errors.rejectValue("itemBaseCurrency", "", "Plz Choose One Currency");
		}
		
		if(item.getUom() == null || StringUtils.isEmpty(item.getUom().getUomModel())){
			errors.rejectValue("uom", "", "Plz Choose One Uom");
		}
		
		if(item.getOrderSale() == null || StringUtils.isEmpty(item.getOrderSale().getOrderCode())){
			errors.rejectValue("orderSale", "", "Plz Choose One Order Sale");
		}
		else if(!itemUtil.listOrderCode("SALE").contains(item.getOrderSale().getOrderCode())){
			errors.rejectValue("orderSale", "", "Given Code is Not for SALE");
		}
		
		if(item.getOrderPurchase() == null || StringUtils.isEmpty(item.getOrderPurchase().getOrderCode())){
			errors.rejectValue("orderPurchase", "", "Plz Choose One Order Purchase");
		}
		else if(!itemUtil.listOrderCode("PURCHASE").contains(item.getOrderPurchase().getOrderCode())){
			errors.rejectValue("orderPurchase", "", "Given Code is Not for PURCHASE");
		}
		/*else if(!orderService.findByOrderCodeAndOrderId(item.getOrderPurchase().getOrderCode(), item.getOrderPurchase().getOrderId())){
			errors.rejectValue("orderPurchase", "", "Plz Check OrderId for OrderCode");
		}*/
		
		if(item.getVendors() == null || item.getVendors().isEmpty()){
			errors.rejectValue("vendors", "", "Plz Choose Atleast One Vendor");
		}
		
		if(item.getCustomers() == null || item.getCustomers().isEmpty()){
			errors.rejectValue("customers", "", "Plz Choose Atleast One customers");
		}
		
		if(StringUtils.isEmpty(item.getDescription())){
			errors.rejectValue("description", "", "Plz Enter Description");
		}
		else if(!Pattern.compile("[a-zA-Z0-9]{4,250}").matcher(item.getDescription()).matches()){
			errors.rejectValue("description", "", "Enter 4-250 Chars..	");
		}
	}
	
	

}
