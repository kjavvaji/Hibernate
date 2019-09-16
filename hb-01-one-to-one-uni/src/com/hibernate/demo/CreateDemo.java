package com.hibernate.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;


public class CreateDemo {

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
//			Instructor tempInstructor = new Instructor("Baugh","John","johnbaugh@umich.edu");
//			
//			InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com/JPBaugh","Sarcasm");
			Instructor tempInstructor = new Instructor("Jinhua","JGuo","jguo@umich.edu");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com/JGuo","Teaching");
			//associate the objects
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
			factory.close();
		}
	}

}
