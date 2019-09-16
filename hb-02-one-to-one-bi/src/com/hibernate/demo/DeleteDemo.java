package com.hibernate.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;


public class DeleteDemo {

	public static void main(String[] args) {
		
		//Create Session factory
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects

						
			//Start a transaction
			session.beginTransaction();
			// get the instructor by primary key
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class,theId);
			
			System.out.println("Found instructor :"+tempInstructor);
			// delete the instructor
			if(tempInstructor!=null) {
				System.out.println("Deleting :"+tempInstructor);
				session.delete(tempInstructor);
			}
			//Commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
		}
	}

}
