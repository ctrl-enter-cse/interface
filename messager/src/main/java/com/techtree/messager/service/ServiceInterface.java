package com.techtree.messager.service;

import org.springframework.http.ResponseEntity;


import com.techtree.messager.entity.userBean;

public interface ServiceInterface {

	public ResponseEntity<Object> registation(String phonenumber) ;
	public ResponseEntity<Object> setpin(long id, String password, String confirmPassword);
	public ResponseEntity<Object> verfiy(long id, String password);
	public ResponseEntity<Object> view(Long id); 
	public ResponseEntity<Object> update(userBean user);
	
}
