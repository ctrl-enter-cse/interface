package com.techtree.userinterface.validation;

	

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.techtree.userinterface.entity.Userbean;

@Component
public class Passvalidation implements Validator{
	private static final String pass_format="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\S+$).{8,20}$";

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Userbean.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "id", "id.empty");
		ValidationUtils.rejectIfEmpty(errors, "pwd", "pwd.empty");
		ValidationUtils.rejectIfEmpty(errors, "confirmpass", "confirmpass.empty");
		Userbean p = (Userbean) target;
        if (p.getPwd().matches(pass_format)!=true){
        	System.out.println("-------------------");
        	System.out.println(p.getPwd().matches(pass_format));
        	errors.rejectValue("pwd", " not matching"," one digit,one lower,one upper,one symbol,minimum 8 lenght");
        } 
		
	}
	
	
}
