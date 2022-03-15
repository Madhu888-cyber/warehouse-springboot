package com.sathyatech.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sathyatech.app.model.SaleOrderDetails;

@Component
public class SaleOrderDetailValidator implements Validator{
	
	public boolean supports(Class<?> clazz) {
		return SaleOrderDetails.class.equals(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		
		SaleOrderDetails saleDetails = (SaleOrderDetails)target;
		
		if(saleDetails.getItemsQty()==null || saleDetails.getItemsQty()<=0){
			errors.rejectValue("itemsQty", "", "Quantity Must be Greater than 0");
		}
	}
}
