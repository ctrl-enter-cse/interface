package com.techtree.messager.service.impl;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.techtree.messager.Repository.MessagerRepoUser;
import com.techtree.messager.Repository.UserProfileRepo;
import com.techtree.messager.entity.User;
import com.techtree.messager.entity.UserProfile;
import com.techtree.messager.entity.UserProfileBean;
import com.techtree.messager.entity.Userdetail;
import com.techtree.messager.service.ServiceInterface;
 
@Service
public class serviceimpl implements ServiceInterface {
	Logger logger = LoggerFactory.getLogger(serviceimpl.class);

	@Autowired
	MessagerRepoUser userrepo;

	@Autowired
	UserProfileRepo uprofilerepo;

	@Override
	public ResponseEntity<Object> registation(String phonenumber) {
		JSONObject response = new JSONObject();
		try {
			List<User> list = userrepo.findByPhonenumber(phonenumber);
			if (list.isEmpty()) {
				logger.info("phonenumber error", phonenumber);
				User user = new User();
				user.setPhonenumber(phonenumber);
				UserProfile userpro = new UserProfile();
				user.setUserprofile(userpro);
				userpro.setUser(user);
				User i = userrepo.save(user);

//			;.debug("save exception ",i);
				response.put("user-id", i.getOid());
				response.put("status", "success");
//				String s = response.toJSONString();
//			System.out.println(s);
//			ssssssssssss	logger.info(s);
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			} else {
//				response.put("already regiterd","try with other number");
				response.put("status", "failed");
				response.put("message", "Already registered");
				return new ResponseEntity<Object>(response, HttpStatus.FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Object> setpin(long id, String password, String confirmPassword) {
		try {
			Optional<User> usercheck = userrepo.findById(id);
			if (usercheck.isPresent()) {
				User user = usercheck.get();
				System.out.println(user.getPassword());
				if (user.getPassword() == null && password.equals(confirmPassword.toString())) {
					Encoder encoder = Base64.getEncoder();
					String encodedString = encoder.encodeToString(password.getBytes("UTF-8"));
					user.setPassword(encodedString);
					userrepo.save(user);
					return new ResponseEntity<Object>(" succesfully set pin ", HttpStatus.OK);
				} else {
					return new ResponseEntity<Object>("already registered", HttpStatus.FOUND);
				}
			} else {
				return new ResponseEntity<Object>("Not registered yet ", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<Object> verfiy(long id, String password) {
		try {
			if (userrepo.findById(id).isPresent()) {
				User user = userrepo.getById(id);
				String encoded = user.getPassword();
//				System.out.println(encoded);
				Decoder decoder = Base64.getDecoder();
				byte[] decodedbytespass = decoder.decode(encoded);
				System.out.println("---------------");
//				System.out.println(decodedbytespass);
				String Dbpass = new String(decodedbytespass, "UTF-8");
				System.out.println(Dbpass);
				if (Dbpass.equals(password)) {
					return new ResponseEntity<Object>("login_Succefully", HttpStatus.OK);
				} else {
					return new ResponseEntity<Object>("pass_Is_Invalid_Login_fails", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<Object>("id_Not_Found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<Object> update(UserProfileBean user) {
		JSONObject json = new JSONObject();
		try {
			ObjectMapper obj = new ObjectMapper();
			logger.info("user data"+obj.writeValueAsString(user));

			Optional<User> userdata = userrepo.findById(user.getId());
			if (userdata.isPresent()) {
				if (user.getFirst_name() != null) {
					userdata.get().getUserprofile().setFirst_name(user.getFirst_name());
				}
				if (user.getLast_name() != null) {
					userdata.get().getUserprofile().setLast_name(user.getLast_name());
				}
				if (user.getEmail() != null) {
					userdata.get().getUserprofile().setEmail(user.getEmail());
				}
				
				UserProfile userpro =uprofilerepo.save(userdata.get().getUserprofile());
				JSONObject resp = new JSONObject(obj.writeValueAsString(userpro));
				resp.put("status:", "success");
				resp.put("message:", "updated");
					String s= resp.toString();

				
				logger.info(s);

				return new ResponseEntity<Object>(s, HttpStatus.OK);

			} else {
				json.put("user", "user_Not_Found");
				json.put("status", "fail");
				return new ResponseEntity<Object>(json, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<Object> view(Long id) {
		return null;
//		JSONObject json = new JSONObject();
//		try {
//			Optional<UserProfile> userdata = uprofilerepo.findById(id);
//			if (userdata.isPresent()) {
//				Userdetail response = new Userdetail();
//				UserProfile userprofiledata = userdata.get();
//				if (userprofiledata != null) {
////				response.setFirstName(userprofiledata.getFirst_name());
////				response.setLastName(userprofiledata.getLast_name());
////				response.setEmail(userprofiledata.getEmail());
//
//					json.put("First_Name", userprofiledata.getFirst_name());
//					json.put("Last_name", userprofiledata.getLast_name());
//					json.put("Email", userprofiledata.getEmail());
//
//					return new ResponseEntity<Object>(json, HttpStatus.OK);
//				} else {
//					json.put("user profile data", "not found");
//					return new ResponseEntity<Object>(json, HttpStatus.BAD_REQUEST);
//				}
//			} else {
//				json.put("user invalid id ", "not found");
////				return new ResponseEntity<Object><dependency>
////			    <groupId>org.json</groupId>
////			    <artifactId>json</artifactId>
////			    <version>20220320</version>
////			</dependency>
////(json, HttpStatus.BAD_REQUEST);
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//
//		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	}	
}
