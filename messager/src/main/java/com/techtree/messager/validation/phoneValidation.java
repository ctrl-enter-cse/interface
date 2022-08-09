package com.techtree.messager.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.techtree.messager.entity.userBean;

@Component
public class phoneValidation implements Validator {

	private final static String phoneFormat = "[6-9][0-9]{9,11}";

	@Override
	public boolean supports(Class<?> clazz) {
		return userBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "Phonenumber", "Phonenumber is empty");
		userBean u = (userBean) target;
		if (!u.getPhonenumber().matches(phoneFormat)) {
			errors.rejectValue("Phonenumber", "not matching");
		}

	}

}
