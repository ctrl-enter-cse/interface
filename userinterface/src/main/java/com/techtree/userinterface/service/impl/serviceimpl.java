package com.techtree.userinterface.service.impl;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.techtree.userinterface.model.User;
import com.techtree.userinterface.repository.userRepository;
import com.techtree.userinterface.service.userinterfaceService;

@Service
public class serviceimpl implements userinterfaceService {

	@Autowired
	userRepository repo;

	@Override
	public ResponseEntity<Object> savedata(String data) {
		User u = new User();
		Encoder encoder = Base64.getEncoder();
		String encodedString = encoder.encodeToString(data.getBytes());
		u.setPhonenumber(encodedString);
		User i = repo.save(u);
		return new ResponseEntity<Object>(i.getUser_id(), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> setpin(long id, String pwd, String confirmpass) {
		User u = repo.getById(id);
		if (u.getPassword()==null && pwd == confirmpass) {
			u.setPassword(pwd);
			User saved =repo.save(u);
			return new ResponseEntity<Object>(saved, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("already regitrated", HttpStatus.FOUND);
		}

	}

	@Override
	public ResponseEntity<Object> verfiy(long id, String pwd) {
		User u = repo.getById(id);
		String encoded= u.getPassword();
		Decoder decoder=Base64.getDecoder();
		byte[] decodedbytespass = decoder.decode(encoded);
		String 	Dbpass=decodedbytespass.toString();
		if(Dbpass.equals(pwd)) {
			return new ResponseEntity<Object>("login succefully", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>(" pass is invalid login fails", HttpStatus.OK);
		}	
	
	}

	@Override
	public ResponseEntity<Object> getlist() {
		repo.findAll();
		return  new ResponseEntity<Object>(repo.findAll(), HttpStatus.OK);
	}

}
