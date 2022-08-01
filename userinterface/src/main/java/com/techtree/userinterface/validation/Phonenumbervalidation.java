package com.techtree.userinterface.validation;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.techtree.userinterface.model.User;

@Component
public class Phonenumbervalidation implements Validator{


	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
		User p = (User) target;
        if (p.getPhonenumber().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@$&-])(?=\\\\S+$).{8,20}$")) {
        	errors.rejectValue("pass not matching", " not matching");
        } 
		
	}

//	 public boolean  phonevalidate(String dbdata);

	
	
}
