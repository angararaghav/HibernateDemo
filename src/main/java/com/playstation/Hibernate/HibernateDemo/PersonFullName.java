package com.playstation.Hibernate.HibernateDemo;

import javax.persistence.*;


@Entity
@Embeddable
public class PersonFullName {

	@Column(name="FirstName")
	private String fname;
	@Column(name="LastName")
	private String lname;
	@Column(name="MiddleName")
	private String mname;
	@Id
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}

	
}
