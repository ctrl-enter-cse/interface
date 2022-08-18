package com.techtree.userinterface.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.techtree.userinterface.entity.User;
import com.techtree.userinterface.repository.userRepository;
import com.techtree.userinterface.service.userinterfaceService;

@Service
public class serviceimpl implements userinterfaceService {

	@Autowired
	userRepository repo;

	@Override
	public ResponseEntity<Object> savedata(String data) {
		try {
			User u = new User();
			Encoder encoder = Base64.getEncoder();
			String encodedString = encoder.encodeToString(data.getBytes());
			u.setPhonenumber(encodedString);
			User i = repo.save(u);
			return new ResponseEntity<Object>(i.getUser_id(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Object> setpin(long id, String pwd, String Confirmpass) throws UnsupportedEncodingException {
		try {
			if (repo.findById(id).isPresent()) {
				User u = repo.getById(id);
				System.out.println(u.getPassword());
				if (u.getPassword() == null && pwd.equals(pwd)) {
					Encoder encoder = Base64.getEncoder();
					String encodedString = encoder.encodeToString(pwd.getBytes("UTF-8"));
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
	public ResponseEntity<Object> verfiy(long id, String pwd) {
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
				if (Dbpass.equals(pwd)) {
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
	public ResponseEntity<Object> getlist() {
		JSONObject json= new JSONObject();
		try {
			List<User> u = repo.findAll();
			if (!u.isEmpty()) {
				json.put("value", u);
			
				return new ResponseEntity<Object>(json, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("empty", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
