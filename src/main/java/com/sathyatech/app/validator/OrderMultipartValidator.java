package com.sathyatech.app.validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sathyatech.app.model.OrderMethod;
import com.sathyatech.app.util.OrderMethodUtils;

@Component
public class OrderMultipartValidator {
	

	public Map<String,List<String>> doValidation(List<OrderMethod> orderList){
		Map<String,List<String>> errorMap = new LinkedHashMap<String, List<String>>();

		int row = 2;
		for(OrderMethod orderMethod : orderList){
			List<String> errorList = new ArrayList<String>();
			
			//----------Check orderMode In File----------
			if(StringUtils.isEmpty(orderMethod.getOrderMode())){
				errorList.add("Plz Select Order Mode");
			}
			else if(!OrderMethodUtils.getOrderModeList().contains(orderMethod.getOrderMode())){
				errorList.add("Plz Choose from:"+OrderMethodUtils.getOrderModeList());
			}

			//----------Check orderMethd In File----------
			if(StringUtils.isEmpty(orderMethod.getOrderMethd())){
				errorList.add("Plz Select Order Method");
			}
			else if(!OrderMethodUtils.getOrderMethdsList().contains(orderMethod.getOrderMethd())){
				errorList.add("Plz Choose from:"+OrderMethodUtils.getOrderMethdsList());
			}

			//----------Check orderAccept In File----------
			if(StringUtils.isEmpty(orderMethod.getOrderAccept())){
				errorList.add("Plz Check OrderAccept");
			}
			else if(!CollectionUtils.containsAny(OrderMethodUtils.getOrderAcceptList(), orderMethod.getOrderAccept())){
				errorList.add("Plz Select From"+OrderMethodUtils.getOrderAcceptList());
			}
			
			//----------Check Description In File----------
			if(StringUtils.isEmpty(orderMethod.getDescription())){
				errorList.add("Plz Write into Desc");
			}
			else if(!Pattern.compile("[a-zA-Z]{10,250}").matcher(orderMethod.getDescription()).matches()){
				errorList.add("UpperCase Chars 10-250 only");
			}
			
			
			//----------If errorList is not Empty Add to errorMap In File----------
			if(!errorList.isEmpty()){
				errorMap.put("Errors At Row#"+row, errorList);
			}
			row++;
		}
		return errorMap;
	}
}
