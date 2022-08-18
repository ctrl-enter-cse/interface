package com.techtree.manytoone.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="custname")
	private String custname;
	
//this is child like only one customer 
	@JsonIgnore 								 // so u will put ignore in the child 
	@OneToMany(mappedBy ="customer")
	private List<Items> item;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCustname() {
		return custname;
	}


	public void setCustname(String custname) {
		this.custname = custname;
	}


	public List<Items> getItem() {
		return item;
	}


	public void setItem(List<Items> item) {
		this.item = item;
	}


	
	

}
