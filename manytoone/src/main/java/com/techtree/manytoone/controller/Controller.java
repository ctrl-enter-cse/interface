package com.techtree.manytoone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.techtree.manytoone.model.Customer;
import com.techtree.manytoone.model.Items;
import com.techtree.manytoone.model.requestbody;
import com.techtree.manytoone.service.service;

@org.springframework.stereotype.Controller
public class Controller {
	
	@Autowired
	service service;
	@GetMapping("/getlist")
	public ResponseEntity<Object> get(){
		return service.getlist();
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody requestbody  items){
		System.out.println(items);
		return service.save(items);
		
	}

}
