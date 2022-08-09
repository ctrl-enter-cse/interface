package com.techtree.userinterface.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techtree.userinterface.entity.User;
import com.techtree.userinterface.entity.UserBean;
import com.techtree.userinterface.service.userinterfaceService;
import com.techtree.userinterface.validation.Passvalidation;
import com.techtree.userinterface.validation.phoneValidation;

@RestController
@RequestMapping("/home")
public class usercontroller {

	@Autowired
	Passvalidation passvalidate;
	@Autowired
	phoneValidation phonevalidate;

	@Autowired
	userinterfaceService service;

	@GetMapping("/list")
	public ResponseEntity<Object> getlist() {
		return service.getlist();
	}

	@PostMapping(value = "/regitration", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Object> Registration(@RequestBody User data, BindingResult result) {

			phonevalidate.validate(data, result);
			if (result.hasErrors()) {
				return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			}
		
			return	service.savedata(data.getPhonenumber());	
	}

	@PutMapping("/setpin")
	public ResponseEntity<Object> setPin( @RequestBody UserBean pwd,
			 BindingResult result) throws UnsupportedEncodingException {
			try {
			passvalidate.validate(pwd, result);
			if(result.hasErrors()) {
				return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
			}
//			if(id!=0||!pwd.isEmpty()||!confirmpass.isEmpty()) {
				return service.setpin(pwd.getId(), pwd.getPwd(),pwd.getConfirmpass());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@GetMapping("/verfiy")
	public ResponseEntity<Object> verfiy(@RequestBody User login) {
		
		return service.verfiy(login.getUser_id(), login.getPassword());
		
	}

}
