package com.sathyatech.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sathyatech.app.model.Uom;
import com.sathyatech.app.service.IUomService;
import com.sathyatech.app.util.UomTypeUtil;

@Component
public class UomValidator implements Validator {
	
	@Autowired
	private IUomService service;
	
	private boolean isEditMode;
	
	public boolean isEditMode() {
		return isEditMode;
	}

	public void setEditMode(boolean isEditMode) {
		this.isEditMode = isEditMode;
	}

	public boolean supports(Class<?> arg0) {
		return Uom.class.equals(arg0);
	}

	public void validate(Object target, Errors errors) {	
		Uom uom = (Uom)target;
		
		if(!UomTypeUtil.listType().contains(uom.getUomType())){
			errors.rejectValue("uomType", "", "Plz Choose From -PACKING,NO PACKING,NA- Only");
		}
		
		if(!Pattern.compile("[A-Z]{4,8}").matcher(uom.getUomModel()).matches()){
			errors.rejectValue("uomModel", "", "PLZ Enter 4-8 Upper Case Letters Only");
		}
		
		if(!Pattern.compile("[a-zA-Z]{10,250}").matcher(uom.getDescription()).matches()){
			errors.rejectValue("description", "", "Only Characters 10-250 Accepted");
		}
		
		//Checks In DB Whether UomModel Already Exist With Same UomType
		if(!isEditMode && service.isUomModelAndUomTypeExist(uom.getUomModel(), uom.getUomType())){
			errors.rejectValue("uomModel", "", "UomModel '" +uom.getUomModel()+"' Already Exist With Type '"+uom.getUomType()+"'");
		}
	}
}
