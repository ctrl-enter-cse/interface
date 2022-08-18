package com.techtree.manytoone.service;

import org.springframework.http.ResponseEntity;


import com.techtree.manytoone.model.requestbody;

public interface service {

	public ResponseEntity<Object> getlist();

	public ResponseEntity<Object> save(requestbody items);

	
	
}
