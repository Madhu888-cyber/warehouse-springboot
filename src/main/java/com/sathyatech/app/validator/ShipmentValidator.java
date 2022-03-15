package com.sathyatech.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sathyatech.app.model.ShipmentType;
import com.sathyatech.app.service.IShipmentService;
import com.sathyatech.app.util.ShipmentTypeUtils;



@Component
public class ShipmentValidator implements Validator {

	@Autowired
	private IShipmentService service;

	private boolean isEditMode;

	public boolean isEditMode() {
		return isEditMode;
	}

	public void setEditMode(boolean isEditMode) {
		this.isEditMode = isEditMode;
	}

	public boolean supports(Class<?> arg0) {
		return ShipmentType.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ShipmentType shipmentType = (ShipmentType)target;

		if(!ShipmentTypeUtils.listShipMode().contains(shipmentType.getShipmentMode())) {
			errors.rejectValue("shipmentMode", "", "Please select one of Shipment Mode");
		}
		if(!Pattern.compile("[a-zA-Z0-9 \\t\\n\\x0B\\f\\r]{4,12}").matcher(shipmentType.getShipmentMode()).matches()) {
			errors.rejectValue("shipmentCode", "", "Shipment Code must between 4-12 characters only!");
		}
		if(!ShipmentTypeUtils.generateGrade().contains(shipmentType.getShipmentGrade())) {
			errors.rejectValue("shipmentGrade", "", "Please Choose one of Shipment Grade!");
		}
		if(!Pattern.compile("[a-zA-Z \\t\\n\\x0B\\f\\r]{8,255}").matcher(shipmentType.getDescription()).matches()) {
			errors.rejectValue("description", "", "Description must between 8-255 Characters Only!");
		}

		if(!isEditMode && service.isShipmentModeAndCodeExist(shipmentType.getShipmentMode(), shipmentType.getShipmentCode())) {
			errors.rejectValue("shipmentMode", "", "ShipmentType '"+shipmentType.getShipmentMode()+"' with '"+shipmentType.getShipmentCode()+"' exist");
		}
	}

}
