package com.techtree.userinterface.service;

import org.springframework.http.ResponseEntity;

public interface userinterfaceService {

	public ResponseEntity<Object>  savedata ( String data);

	public ResponseEntity<Object> setpin(long id, String pwd, String confirmpass);

	public ResponseEntity<Object> verfiy(long id, String pwd);

	public ResponseEntity<Object> getlist();
}
