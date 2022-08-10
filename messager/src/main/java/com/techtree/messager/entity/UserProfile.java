package com.techtree.messager.entity;


import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="userProfile")
public class UserProfile {
	
	@Id
	@Column(name="OID")
	@GeneratedValue(generator = "ProfileIdGenerator")
	@GenericGenerator(name = "ProfileIdGenerator", strategy = "foreign",parameters = @Parameter(name ="property",value = "user"))
	private Long oid;
	
	@Column(name="FIRST_NAME")
	private String First_name;
	
	@Column(name="LAST_NAME")
	private String Last_name;

	@Column(name="Email")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private User user;
	
	@CreationTimestamp
	private Date creationtime; 
	
	@UpdateTimestamp
	private Date Updatation;

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirst_name() {
		return First_name;
	}

	public void setFirst_name(String first_name) {
		First_name = first_name;
	}

	public String getLast_name() {
		return Last_name;
	}

	public void setLast_name(String last_name) {
		Last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
