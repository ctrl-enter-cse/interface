package com.techtree.userinterface.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.techtree.userinterface.entity.UserBean;


@Component
public class phoneValidation  implements Validator{
	private static final String Phone_format="[0-9]{10,12}";
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserBean.class.isAssignableFrom(clazz);
		
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "phonenumber", "phonenumber.empty");
		UserBean p = (UserBean) target;
        if (!p.getPhonenumber().matches("[0-9]{10,12}")) {
        	errors.rejectValue("phonenumber", "not matching");
        } 
		
	}

	
//	public void validate(Object  target, Errors errors) {
//		ValidationUtils.rejectIfEmpty(errors, "phonenumber", "phonenumber.empty");
//        if (target..matches(Phone_format)) {
//        	errors.rejectValue("Phonenumber", "not matching");
//        } 
//	}
}
