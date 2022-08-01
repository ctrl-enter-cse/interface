package com.techtree.userinterface.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.techtree.userinterface.model.User;

public class passValidation   implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
		User p = (User) target;
        if (p.getPhonenumber().matches("[0-9]{10,12}")) {
        	errors.rejectValue("Phonenumber", " not matching");
        } 
		
	}

}
