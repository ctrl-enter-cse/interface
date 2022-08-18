package com.techtree.manytoone.model;

import java.util.List;

public class requestbody {
	
	private Customer custmer;
	
	private List<itemBean> items;

	public Customer getCustmer() {
		return custmer;
	}

	public void setCustmer(Customer custmer) {
		this.custmer = custmer;
	}

	public List<itemBean> getItems() {
		return items;
	}

	public void setItems(List<itemBean> items) {
		this.items = items;
	}

	
	
	
	
}
