package com.sathyatech.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.sathyatech.app.model.OrderMethod;
import com.sathyatech.app.service.IOrderMethodService;
import com.sathyatech.app.util.OrderMethodUtils;
@Component
public class OrderValidator implements Validator {
	
	@Autowired
	private IOrderMethodService service;
	
	private boolean isEditMode;

	public boolean isEditMode() {
		return isEditMode;
	}

	public void setEditMode(boolean isEditMode) {
		this.isEditMode = isEditMode;
	}

	public boolean supports(Class<?> arg0) {
		return OrderMethod.class.equals(arg0);
	}

	public void validate(Object target, Errors errors) {
		OrderMethod orderMethod = (OrderMethod)target;

		if(!OrderMethodUtils.getOrderModeList().contains(orderMethod.getOrderMode())) {
			errors.rejectValue("orderMode", "", "Please choose one of Order Mode!");
		}
		
		if(!Pattern.compile("[0-9a-zA-Z \\t\\n\\x0B\\f\\r]{4,12}").matcher(orderMethod.getOrderCode()).matches()) {
			errors.rejectValue("orderCode", "", "Order Code must be between 4-12 Characters only!");
		}
		
		if(!OrderMethodUtils.getOrderMethdsList().contains(orderMethod.getOrderMethd())) {
			errors.rejectValue("orderMethd", "", "Please select valid Order Method");
		}
		
		if(!Pattern.compile("[A-Za-z \\t\\n\\x0B\\f\\r]{8,255}").matcher(orderMethod.getDescription()).matches()){
			errors.rejectValue("description", "", "Description must between 8-255 characters only!");
		}
		
		if(!isEditMode && service.isOrderCodeAndMethodExist(orderMethod.getOrderCode(), orderMethod.getOrderMethd())) {
			errors.rejectValue("orderCode", "", "Order '"+orderMethod.getOrderCode()+"' with '"+orderMethod.getOrderMethd()+"' exist");
		}
	}
}
