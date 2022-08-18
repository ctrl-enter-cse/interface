package com.techtree.messager.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.techtree.messager.entity.userBean;

@Component
public class Passvalidation implements Validator {
//	private static final String pass_Format=;


	@Override
	public boolean supports(Class<?> clazz) {	
		return userBean.class.isAssignableFrom(clazz);
	}

	@Override
	public  void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors,"id", "id is empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "password is empty");
		ValidationUtils.rejectIfEmpty(errors,"ConfirmPassword", "ConfirmPassword is empty");
		userBean u= (userBean)target;
		if(!u.getPassword().matches("^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])|(?=.*[@&%])(?=.*[a-z])(?=.*[A-Z])|(?=.*[0-9])(?=.*[a-z])(?=.*[@$&-])|(?=.*[0-9])(?=.*[A-Z])(?=.*[@$&-]))(?=\\S+$).{8,20}$")){
			errors.rejectValue("password", " pattern not matching");
		}

	}

}
