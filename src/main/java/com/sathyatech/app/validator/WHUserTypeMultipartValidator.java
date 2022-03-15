package com.sathyatech.app.validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.sathyatech.app.model.WHUserType;
import com.sathyatech.app.util.WHUserTypeUtil;

@Component
public class WHUserTypeMultipartValidator {
	
	public Map<String,List<String>> doValidation(List<WHUserType> whUserTypes){
		
		Map<String,List<String>> errorMap = new LinkedHashMap<String, List<String>>();
		int row = 2;
		
		//Get One-by-One WHUserType from List
		for(WHUserType whUserType : whUserTypes){
			List<String> errorList = new ArrayList<String>();

			//Check For UserType
			if(StringUtils.isEmpty(whUserType.getUserType())){
				errorList.add("Plz Enter UserType");
			}
			else if(!WHUserTypeUtil.getUserTypes().contains(whUserType.getUserType())){
				errorList.add("Plz Enter Valid UserType From: "+WHUserTypeUtil.getUserTypes());
			}
			
			//Check For UserFor
			if(StringUtils.isEmpty(whUserType.getWhUserFor())){
				errorList.add("Plz Enter UserFor");
			}
			else if("VENDOR".equals(whUserType.getUserType()) && !"PURCHASE".equals(whUserType.getWhUserFor())   ||
					"CUSTOMER".equals(whUserType.getUserType()) && !"SALE".equals(whUserType.getWhUserFor())){
				errorList.add("For UserType \'VENDOR\' Select UserFor \'SALE\' & For UserType \'CUSTOMER\' Select UserFor \'PURCHASE\'");
			}
			
			//Check For Email
			if(StringUtils.isEmpty(whUserType.getWhUserEmail())){
				errorList.add("Plz Enter Email");
			}
			else if(!Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,63}$").matcher(whUserType.getWhUserEmail()).matches()){
				errorList.add("Plz Enter Valid Email");
			}
			
			//Check For Contacts
			if(StringUtils.isEmpty(whUserType.getWhUserContact())){
				errorList.add("Plz Enter Contact Number");
			}
			else if(!Pattern.compile("[0-9]{10}").matcher(whUserType.getWhUserContact()).matches()){
				errorList.add("Plz Enter Valid Contact Number");
			}
			
			//Check For UserIdType
			if(StringUtils.isEmpty(whUserType.getWhUserIdType())){
				errorList.add("Plz Enter UserIdType");
			}
			else if(!WHUserTypeUtil.getWHUserIdTypes().contains(whUserType.getWhUserIdType())){
				errorList.add("Plz Select Valid Id Type From: "+WHUserTypeUtil.getWHUserIdTypes());
			}
			
			//Check IfOther
			if(StringUtils.isEmpty(whUserType.getWhIfOther())){
				errorList.add("Plz Enter IfOther");
			}
			
			//Check Id Number
			if(StringUtils.isEmpty(whUserType.getWhUserIdNumber())){
				errorList.add("Plz Enter Id Number");
			}
			else if(!Pattern.compile("[a-zA-Z0-9]{4,20}").matcher(whUserType.getWhUserIdNumber()).matches()){
				errorList.add("Plz Enter Valid WHUserIdNumber");
			}
			
			//Add errorList To Map
			if(!errorList.isEmpty()){
				errorMap.put("Errors at Row#"+row,errorList);
				row++;
			}	
			
		}
		
		return errorMap;
	}
}