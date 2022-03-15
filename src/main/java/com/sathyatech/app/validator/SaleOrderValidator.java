package com.sathyatech.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sathyatech.app.model.SaleOrder;
import com.sathyatech.app.service.IShipmentService;
import com.sathyatech.app.util.SaleOrderUtil;

@Component
public class SaleOrderValidator implements Validator {

	@Autowired
	private IShipmentService shipService;
	
	@Autowired
	private SaleOrderUtil util;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SaleOrder.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SaleOrder saleOrder = (SaleOrder)target;
		
		if(StringUtils.isEmpty(saleOrder.getSaleShipmentMode())){
			errors.rejectValue("saleShipmentMode", "", "Plz Choose ShipmentMode");
		}
		else if(saleOrder.getSaleShipmentMode() == null || saleOrder.getSaleShipmentMode().getShipmentId() == null){
			errors.rejectValue("saleShipmentMode", "", "Plz Provide ShipmentId");			
		}
		
		if(StringUtils.isEmpty(saleOrder.getSaleCustomers())){
			errors.rejectValue("saleCustomers", "", "Plz Choose Customer");
		}
		else if(saleOrder.getSaleCustomers() == null || saleOrder.getSaleCustomers().getWhUserId()==null){
			errors.rejectValue("saleCustomers", "", "Plz Provide Customer");
		}
		
		if(StringUtils.isEmpty(saleOrder.getReferenceNumber())){
			errors.rejectValue("referenceNumber", "", "Plz Enter Ref Num");
		}
		else if(!Pattern.compile("[0-9]{4,8}").matcher(saleOrder.getReferenceNumber()).matches()){
			errors.rejectValue("referenceNumber", "", "4-8 Digits Only");
		}
		
		if(saleOrder.getStockMode()=="" || saleOrder.getStockMode()==null){
			errors.rejectValue("stockMode", "", "Plz Select StockMode From:"+SaleOrderUtil.getStockMode());
		}
		else if(!SaleOrderUtil.getStockMode().contains(saleOrder.getStockMode().toUpperCase())){
			errors.rejectValue("stockMode", "", "Plz Select StockMode From:"+SaleOrderUtil.getStockMode());
		}
		
		if(saleOrder.getStockSource()=="" || saleOrder.getStockSource()==null){
			errors.rejectValue("stockSource", "", "Plz Select stockSource From:"+SaleOrderUtil.getStockSource());
		}
		else if(!SaleOrderUtil.getStockSource().contains(saleOrder.getStockSource().toUpperCase())){
			errors.rejectValue("stockSource", "", "Plz Select stockSource From:"+SaleOrderUtil.getStockSource());
		}
		
		if(StringUtils.isEmpty(saleOrder.getDescription())){
			errors.rejectValue("description", "", "Plz Write Description");
		}
		else if(!Pattern.compile("[a-zA-Z \\t\\n\\x0B\\f\\r]{10,255}").matcher(saleOrder.getDescription()).matches()){
			errors.rejectValue("description", "", "Chars only range of 10-255 accepted");
		}
	}

}