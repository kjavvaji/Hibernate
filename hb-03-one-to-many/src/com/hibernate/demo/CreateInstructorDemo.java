package com.hibernate.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;


public class CreateInstructorDemo {

	public static void main(String[] args) {
		
		//Create Session factory
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects
			Instructor tempInstructor = new Instructor("Susan","Public","susan.public@umich.edu");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com/Susan","VideoGames");

			tempInstructor.setInstructorDetail(tempInstructorDetail);
						
			//Start a transaction
			session.beginTransaction();
			
			// save the instructor
			// this statement will save instructor and instructordetail object as they are associated
			// because we gave cascade.all
			System.out.println("Saving Instructor: "+tempInstructor);
			session.save(tempInstructor);
									
			//Commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
			
		}
		finally {
			//add clean up code
			session.close();
			factory.close();
		}
	}

}
