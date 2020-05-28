package com.playstation.Hibernate.HibernateDemo;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class App {
	public static void main(String[] args) {
		
		System.out.println("Hello World!");

		JPAExample();
		
	}
	
	static void JPAExample()
	{
		System.out.println("Hello JPAExample!");
		
		EntityManagerFactory emFactory =Persistence.createEntityManagerFactory("JPADemo");
		EntityManager entityManager = emFactory.createEntityManager();
		
		Laptop objLaptop = entityManager.find(Laptop.class, 4);
		
		//objLaptop.setLid(5);
		//objLaptop.setLname("Thinkpad");
		//for insert
		//entityManager.getTransaction().begin();
		//entityManager.persist(entity);
		//entityManager.getTransaction().commit();
		System.out.println(objLaptop.getLname());
	}
	
	static void PersistanceExample()
	{
		System.out.println("Hello PersistanceExample!");
		Configuration objConfiguration = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Laptop.class);

		ServiceRegistry objServiceRegistry = new ServiceRegistryBuilder().applySettings(objConfiguration.getProperties()).buildServiceRegistry();
		SessionFactory objSessionFactory = objConfiguration.buildSessionFactory(objServiceRegistry);

		try {
			Session objSession = objSessionFactory.openSession();

			Transaction objTransaction = objSession.beginTransaction();
			
					
			Laptop laptop = new Laptop();
			laptop.setLid(4);
			laptop.setLname("Logitech");
			
			objSession.save(laptop);
			laptop.setLname("Logitech 3x");
			
			
			objTransaction.commit();
			
			//objSession.detach(laptop);
			//laptop.setLname("Logitech 3x");			
					
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace().toString());
		}
	}
	
	static void SQLQueryInHibernatte()
	{
		System.out.println("Hello SQLQueryInHibernatte!");
		Configuration objConfiguration = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class);

		ServiceRegistry objServiceRegistry = new ServiceRegistryBuilder().applySettings(objConfiguration.getProperties()).buildServiceRegistry();
		SessionFactory objSessionFactory = objConfiguration.buildSessionFactory(objServiceRegistry);

		try {
			Session objSession = objSessionFactory.openSession();

			Transaction objTransaction = objSession.beginTransaction();
			System.out.println("H6");
			SQLQuery query = objSession.createSQLQuery("Select * from tblperson where age > 60");
			query.addEntity(Person.class);
		
			final List<Person> persons = query.list();
			
			for(Person p: persons)
			{
				System.out.println(p.getPersonName());
			}
			
								
			objTransaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace().toString());
		}
	
	}
	
	static void AddPersonHQL()
	{
		//Insert
		
		System.out.println("Hello AddPerson!");
		Configuration objConfiguration = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class);

		ServiceRegistry objServiceRegistry = new ServiceRegistryBuilder().applySettings(objConfiguration.getProperties()).buildServiceRegistry();
		SessionFactory objSessionFactory = objConfiguration.buildSessionFactory(objServiceRegistry);

		try {
			Session objSession = objSessionFactory.openSession();

			Transaction objTransaction = objSession.beginTransaction();
			System.out.println("H6");
			//we need to use class name we masked class as tblperson in entity so using tblperson 
			Query query = objSession.createQuery("from tblperson where age >50");
			System.out.println(query.toString());
			final List<Person> persons = query.list();
			
			for(Person p: persons)
			{
				System.out.println(p.getPersonName());
			}
			
								
			objTransaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace().toString());
		}

	}
	
	
	static void MappingRelationExample()
	{
		//Insert
		System.out.println("Hello MappingRelationExample!");
		
		Laptop objLaptop = new Laptop();
		objLaptop.setLid(103);
		objLaptop.setLname("Lenovo");
		
		Student objStudent = new Student();
		objStudent.setMarks(88);
		objStudent.setName("Kapil Dev");
		objStudent.setRollno(3);
		//objStudent.setLaptop(objLaptop);
		objStudent.getLaptop().add(objLaptop);
		
		
		Configuration objConfiguration = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);

		ServiceRegistry objServiceRegistry = new ServiceRegistryBuilder().applySettings(objConfiguration.getProperties()).buildServiceRegistry();
		SessionFactory objSessionFactory = objConfiguration.buildSessionFactory(objServiceRegistry);

		try {
			Session objSession = objSessionFactory.openSession();

			Transaction objTransaction = objSession.beginTransaction();

			//objSession.save(objStudent);
			//objSession.save(objLaptop);
			
			Student oStudent = (Student) objSession.get(Student.class,3);
			
			System.out.println(oStudent.getName());
			//Lazy 
			List<Laptop> laps = oStudent.getLaptop();
			
			for(Laptop laptop : laps)
			{
				System.out.println(laptop.getLname());
			}
			
			
			objTransaction.commit();
			objSession.close();
			//Second level cache example
			
			Session objSession2 = objSessionFactory.openSession();

			Transaction objTransaction2 = objSession2.beginTransaction();
			
			Student oStudent2 = (Student) objSession2.get(Student.class,3);
			
			System.out.println(oStudent2.getName());
						
			
			objTransaction2.commit();
			
			objSession2.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace().toString());
		}
	}
	
	static void AddPerson()
	{
		//Insert
		System.out.println("Hello AddPerson!");
		Person objPerson = new Person();
		objPerson.setAge(50);
		objPerson.setPersonName("Rags");
		objPerson.setId(500);
		PersonFullName objFullName = new PersonFullName();
		objFullName.setFname("Raghav");
		objFullName.setMname("kumar");
		objFullName.setLname("Angara");
		objPerson.setPersonfullname(objFullName);

		Configuration objConfiguration = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class);

		ServiceRegistry objServiceRegistry = new ServiceRegistryBuilder().applySettings(objConfiguration.getProperties()).buildServiceRegistry();
		SessionFactory objSessionFactory = objConfiguration.buildSessionFactory(objServiceRegistry);

		try {
			Session objSession = objSessionFactory.openSession();

			Transaction objTransaction = objSession.beginTransaction();

			objSession.save(objPerson);
		
			objTransaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace().toString() + objPerson.toString());
		}

	}
	
	static void GetPerson()
	{
		
		//Get
		System.out.println("Hello GetPerson!");
		Person objPerson = new Person();
		
		Configuration objConfiguration = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Person.class);

		ServiceRegistry objServiceRegistry = new ServiceRegistryBuilder().applySettings(objConfiguration.getProperties()).buildServiceRegistry();
		SessionFactory objSessionFactory = objConfiguration.buildSessionFactory(objServiceRegistry);

		try {
			Session objSession = objSessionFactory.openSession();

			Transaction objTransaction = objSession.beginTransaction();

			objPerson = (Person) objSession.get(Person.class,800);
			
			objTransaction.commit();
			
			System.out.println(objPerson.getPersonName());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace().toString() + objPerson.toString());
		}
		
	}
	

}
