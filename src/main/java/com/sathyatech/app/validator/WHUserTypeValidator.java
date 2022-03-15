package com.sathyatech.app.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.sathyatech.app.model.WHUserType;
import com.sathyatech.app.util.WHUserTypeUtil;

@Component
public class WHUserTypeValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return WHUserType.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		WHUserType whUserType = (WHUserType)target;
	
		if(StringUtils.isEmpty(whUserType.getUserType())){
			errors.rejectValue("userType", "", "Plz Choose userType");
		}
		/*
		if(StringUtils.isEmpty(whUserType.getWhUserCode())){
			errors.rejectValue("whUserCode", "", "Plz Provide Code");
		}*/
		//else if(Pattern.compile("[a-zA-Z]"))
		
		if(StringUtils.isEmpty(whUserType.getWhUserEmail())){
			errors.rejectValue("whUserEmail", "", "Plz Enter Email");
		}
		else if(!Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,63}$").matcher(whUserType.getWhUserEmail()).matches()){
			errors.rejectValue("whUserEmail", "", "Plz Enter Valid Email");
		}
		
		if(StringUtils.isEmpty(whUserType.getWhUserContact())){
			errors.rejectValue("whUserContact", "", "Plz Enter Contact Number");
		}
		else if(!Pattern.compile("[0-9]{10}").matcher(whUserType.getWhUserContact()).matches()){
			errors.rejectValue("whUserContact", "", "Plz Enter Valid Mobile Num..");
		}
		
		if(StringUtils.isEmpty(whUserType.getWhUserIdType())){
			errors.rejectValue("whUserIdType", "", "Plz Select UserIdTypes");
		}
		else if(!WHUserTypeUtil.getWHUserIdTypes().contains(whUserType.getWhUserIdType())){
			errors.rejectValue("whUserIdType", "", "Plz Select Valid whUserIdType");
		}
		
		if(StringUtils.isEmpty(whUserType.getWhUserIdNumber())){
			errors.rejectValue("whUserIdNumber", "", "Plz Enter whUserIdNumber");
		}
	}
}
