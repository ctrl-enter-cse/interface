package com.techtree.messager.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techtree.messager.entity.User;
import com.techtree.messager.entity.userBean;
import com.techtree.messager.service.impl.serviceimpl;
import com.techtree.messager.validation.Passvalidation;
import com.techtree.messager.validation.phoneValidation;

@Controller
@RequestMapping("/home")
public class MessagerController {

	@Autowired
	Passvalidation passvalidation;

	@Autowired
	phoneValidation phoneValidation;

	@Autowired
	serviceimpl service;

	@PostMapping("/regitration")
	public ResponseEntity<Object> Registration(@RequestBody userBean user, BindingResult result) {
		try {
//			if (!user.getPhonenumber().isEmpty()|| user != null) {
				phoneValidation.validate(user, result);
				if (result.hasErrors()) {
					return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
				}
				return service.registation(user.getPhonenumber());
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/setpin")
	public ResponseEntity<Object> setPin(@RequestBody userBean userBean, BindingResult result)
			throws UnsupportedEncodingException {
		try {
//				if(pwd.getConfirmPassword().isEmpty()!=true ||!pwd.getPassword().isEmpty())
			passvalidation.validate(userBean, result);
			if (result.hasErrors()) {
				return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			}
			return service.setpin(userBean.getId(), userBean.getPassword(), userBean.getConfirmPassword());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping("/verfiy")
	public ResponseEntity<Object> verfiy(@RequestBody User login) {
		return service.verfiy(login.getOid(), login.getPassword());
		
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> update(@RequestBody userBean user) {
		return service.update(user);
		
	}
	
	@PostMapping("/view/{id}")
	public ResponseEntity<Object> view(@PathVariable("id") Long id){
		return service.view(id);
	}

}
