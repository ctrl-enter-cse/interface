package com.techtree.messager.service.impl;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.techtree.messager.Repository.MessagerRepo;
import com.techtree.messager.entity.User;
import com.techtree.messager.entity.userBean;
import com.techtree.messager.service.ServiceInterface;

@Service
public class serviceimpl implements ServiceInterface {

	@Autowired
	MessagerRepo repo;

	@Override
	public ResponseEntity<Object> registation(String phonenumber) {
		try {
			User u = new User();
			Encoder encoder = Base64.getEncoder();
			String encodedString = encoder.encodeToString(phonenumber.getBytes());
			u.setPhonenumber(encodedString);
			User i = repo.save(u);
			return new ResponseEntity<Object>(i.getOid(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<Object> setpin(long id, String password, String confirmPassword) {
		try {
			if (repo.findById(id).isPresent()) {
				User u = repo.getById(id);
				System.out.println(u.getPassword());
				if (u.getPassword() == null && password.equals(password)) {
					Encoder encoder = Base64.getEncoder();
					String encodedString = encoder.encodeToString(password.getBytes("UTF-8"));
					u.setPassword(encodedString);
					User saved = repo.save(u);
					return new ResponseEntity<Object>(" succesfully set pin ", HttpStatus.OK);
				} else {
					return new ResponseEntity<Object>("already regitrated", HttpStatus.FOUND);
				}
			} else {
				return new ResponseEntity<Object>("Not regitrated yet ", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<Object> verfiy(long id, String password) {
		try {
			if (repo.findById(id).isPresent()) {
				User u = repo.getById(id);
				String encoded = u.getPassword();
				System.out.println(encoded);
				Decoder decoder = Base64.getDecoder();
				byte[] decodedbytespass = decoder.decode(encoded);
				System.out.println("---------------");
				System.out.println(decodedbytespass);
				String Dbpass = new String(decodedbytespass, "UTF-8");
				System.out.println(Dbpass);
				if (Dbpass.equals(password)) {
					return new ResponseEntity<Object>("login succefully", HttpStatus.OK);
				} else {
					return new ResponseEntity<Object>(" pass is invalid login fails", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<Object>(" id not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<Object> update(userBean user) {
		try {
		User userbean=repo.getById(user.getId());
		if(userbean.setOid(use));
			
			

		return null;
	}

	@Override
	public ResponseEntity<Object> view(Long id) {
		User userdata = repo.findById(id).get();
		try {
			if (userdata != null) {
				return new ResponseEntity<Object>(userdata, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
