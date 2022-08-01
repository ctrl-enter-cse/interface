package com.techtree.userinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techtree.userinterface.service.userinterfaceService;

import com.techtree.userinterface.validation.Phonenumbervalidation;

@Controller
@RequestMapping("/home")
public class usercontroller {

	@Autowired
	Phonenumbervalidation phonevalidate;

	@Autowired
	userinterfaceService service;
	
	@GetMapping("/list")
	public ResponseEntity<Object> getlist() {
		return service.getlist();
		
	}

	@PostMapping("/regitration")
	public ResponseEntity<Object> Registration(@RequestBody String data) {
//		try {
//			phonevalidate.validate(data, null);
		// if(i=true) {
		return service.savedata(data);
	}
	// }else {
//				System.out.println("invalid format of phone");
//			}
//			
//		} catch (Exception e) {
//		
//			e.printStackTrace();
//		}
//		System.out.println(user);
////		System.out.println(phonenumber);
//		int i= checkreg.checkReg(user.phonenumber); 
//		
//		System.out.println(i);

//		
//		
//		System.out.println("Decrypted Value :: +new String(bytes));
//		

	@PutMapping("/setpin/{id}")
	public ResponseEntity<Object> setPin( @PathVariable("id") long id,@RequestParam String pwd,@RequestParam String confirmpass) {

		return service.setpin(id, pwd, confirmpass);

	}

	@PostMapping("/verfiy")
	public ResponseEntity<Object> verfiy(@RequestParam long id,@RequestParam String pwd ) {

		return service.verfiy(id, pwd);

	}

}
