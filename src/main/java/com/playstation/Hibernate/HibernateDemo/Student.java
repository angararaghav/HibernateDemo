package com.playstation.Hibernate.HibernateDemo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
//these two commented for JPA
//@Cacheable
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Student {

	@Id
	private int rollno;
	private String name;
	private int marks;
	//@OneToOne
	//private Laptop laptop;
	
	
	//@OneToMany(fetch=FetchType.EAGER)
	@OneToMany(fetch=FetchType.LAZY)
	private List<Laptop> laptop = new ArrayList<Laptop>();
	
//	public Laptop getLaptop() {
//		return laptop;
//	}

//	public void setLaptop(Laptop laptop) {
//		this.laptop = laptop;
//	}  


	public List<Laptop> getLaptop() {
		return laptop;
	}


	public void setLaptop(List<Laptop> laptop) {
		this.laptop = laptop;
	}


	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", name=" + name + ", marks=" + marks + "]";
	}

	
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	
}
