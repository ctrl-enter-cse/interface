package com.techtree.userinterface.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long user_id;
	
	
	private String fname;
	private String lname;
	
//	@OneToMany
//	private Address adresss;
	
	public String phonenumber;
	private String password;
	
//	private String confirmpassword;
//	
//	public String getConfirmpassword() {
//		return confirmpassword;
//	}
//	public void setConfirmpassword(String confirmpassword) {
//		this.confirmpassword = confirmpassword;
//	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
//	public Address getAdresss() {
//		return adresss;
//	}
//	public void setAdresss(Address adresss) {
//		this.adresss = adresss;
//	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String data) {
		this.phonenumber = data;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
