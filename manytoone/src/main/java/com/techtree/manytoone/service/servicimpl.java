package com.techtree.manytoone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.techtree.manytoone.model.Customer;
import com.techtree.manytoone.model.Items;
import com.techtree.manytoone.model.itemBean;
import com.techtree.manytoone.model.requestbody;
import com.techtree.manytoone.repo.custrepo;
import com.techtree.manytoone.repo.repo;

@Service
public class servicimpl implements service {

	@Autowired
	repo repo;

	@Autowired
	custrepo cusrepo;

	public ResponseEntity<Object> getlist() {
		return new ResponseEntity<Object>(repo.findAll(), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> save(requestbody items) {

//		this this items coming is having 2 obj 
//		1) is Customer
//2)Items
//item controller 
//
//so first save this Customer then
//		wait 
//this whole is item controller only 
		Customer cus = cusrepo.save(items.getCustmer()); // this line will save obj of sutmer
		System.out.println(cus);
		List<itemBean> b = items.getItems(); // this is get the list of item fromthe request body
		List<Items> newitemList = new ArrayList<>(); // new list to save in item repo

		for (int i = 0; i < b.size(); i++) { // for loop for getting one object at a time
			itemBean itbean = b.get(i); // getting one object saving it into itbean
			Items item = new Items(); // new enity item that will b save in repo
			item.setId(itbean.getId()); // setting data to it
			item.setName(itbean.getName());
			item.setCustomer(cus); // setting the Customer object to the item
			newitemList.add(item); // adding this to the list back and save the item list in the repo
		}
		repo.saveAll(newitemList); // this is imp
//		see this  i am saving this through item repo mean item controller only right 
		return new ResponseEntity<Object>(newitemList, HttpStatus.OK);

	}

}
