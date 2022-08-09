package com.techtree.messager.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="userProfile")
public class UserProfile {
	@Id
	@GenericGenerator(name = "ProfileIdGenerator", strategy = "foreign",parameters = @Parameter(name = "property",value = "User"))
	@GeneratedValue(generator = "ProfileIdGenerator")
	@Column(name="OID")
	private Long id;
	
	@Column(name="FIRST_NAME")
	private String First_name;
	
	@Column(name="LAST_NAME")
	private String Last_name;

	@Column(name="Email")
	private String email;
	
	@OneToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
