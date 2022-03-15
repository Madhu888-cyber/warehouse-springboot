package com.sathyatech.app.validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sathyatech.app.model.ShipmentType;
import com.sathyatech.app.util.ShipmentTypeUtils;



@Component
public class ShipmentMultipartValidator {
	
	public Map<String,List<String>> doValidation(List<ShipmentType> shipTypes){
		Map<String,List<String>> errorMap = new LinkedHashMap<String, List<String>>();
		int row = 2;
		if(shipTypes != null){
			//Get One-By-One Objects from shipTypes
			for(ShipmentType ship : shipTypes){
				List<String> errorList = new ArrayList<String>();
				if(StringUtils.isEmpty(ship.getShipmentMode())){
					errorList.add("Plz Select Shipment Mode");
				}
				else if(!ShipmentTypeUtils.listShipMode().contains(ship.getShipmentMode())){
					errorList.add("Plz Select From:"+ShipmentTypeUtils.listShipMode());
				}
				
				if(StringUtils.isEmpty(ship.getShipmentGrade())){
					errorList.add("Plz Select Grade");
				}
				else if(!ShipmentTypeUtils.generateGrade().contains(ship.getShipmentGrade())){
					errorList.add("Plz Select From:"+ShipmentTypeUtils.generateGrade());
				}
				
				if(StringUtils.isEmpty(ship.getDescription())){
					errorList.add("Plz Write Description");
				}
				else if(!Pattern.compile("[a-zA-Z]{10,250}").matcher(ship.getDescription()).matches()){
					errorList.add("Plz Write 10-250 UpperCase Letters ");
				}
				
				if(!errorList.isEmpty()){
					errorMap.put("Error At Row#"+row, errorList);
				}
				row++;
			}
		}
		return errorMap;
	}
}
