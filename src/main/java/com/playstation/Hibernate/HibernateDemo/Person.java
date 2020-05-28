package com.playstation.Hibernate.HibernateDemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="tblperson")
public class Person {

	@Column(name="personname")
	private String personName = "";
	private int age = 0;
	@Id
	private int id = 1;
	private PersonFullName personfullname = null;
	
	public PersonFullName getPersonfullname() {
		return personfullname;
	}
	public void setPersonfullname(PersonFullName personfullname) {
		this.personfullname = personfullname;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
