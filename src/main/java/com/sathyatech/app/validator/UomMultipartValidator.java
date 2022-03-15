package com.sathyatech.app.validator;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sathyatech.app.model.Uom;
import com.sathyatech.app.service.IUomService;
import com.sathyatech.app.util.UomTypeUtil;



@Component
public class UomMultipartValidator {
	
	@Autowired
	private IUomService service;
	
	public Map<String,List<String>> validateListUoms(List<Uom> uoms){
		
		Map<String,List<String>> errorMap = new LinkedHashMap<String, List<String>>();
		
		int row = 1;
		
		//Iterate List<Uom> to get One Uom Obj
		for(Uom uom:uoms){
			//One Uom can Contains List of Errors so Create List Object to Store Errors
			List<String> errorList = new ArrayList<String>();
			
			//Check UomType Empty or Not
			if(StringUtils.isEmpty(uom.getUomType())){
				errorList.add("UomType Cannot be Empty");
			}		//If Not Empty Then Check
			else if(!UomTypeUtil.listType().contains(uom.getUomType())){
				errorList.add("Plz Choose UomType From "+UomTypeUtil.listType().toString());
			}
			
			if(StringUtils.isEmpty(uom.getUomModel())){
				errorList.add("UomModel Cannot be Empty");
			}
			else if(!Pattern.compile("[A-Z]{4,8}").matcher(uom.getUomModel()).matches()){
				errorList.add("Plz Enter 4-8 Upper Case Letters Only");
			}
			
			if(StringUtils.isEmpty(uom.getDescription())){
				errorList.add("Description Cannot be Empty");
			}
			else if(!Pattern.compile("[a-zA-Z]{10,250}").matcher(uom.getDescription()).matches()){
				errorList.add("Only Characters 10-250 Accepted");
			}
			
			//Check UomModel Exist With UomTye or Not
			if(service.isUomModelAndUomTypeExist(uom.getUomModel(), uom.getUomType())){
				errorList.add("UomModel "+uom.getUomModel()+" Already Exist With "+uom.getUomType());
			}
			
			
			//Error List not Empty Then add to errorMap
			if(!errorList.isEmpty()){
				//errorList Not Empty Then put in errorMap
				errorMap.put("Errors At Row#"+row, errorList);
			}
			row++;
		}
		return errorMap;
	}
}
