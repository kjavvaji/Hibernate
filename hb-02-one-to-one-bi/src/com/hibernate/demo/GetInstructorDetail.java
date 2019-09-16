package com.hibernate.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;


public class GetInstructorDetail {

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
			// get the instructor detail object
				int theId=1;
				InstructorDetail tempInstructorDetail = 
						session.get(InstructorDetail.class, theId);
				
			// print the instructor detail
			System.out.println("temp instructor object :"+tempInstructorDetail);
			//print the associated instructor
			System.out.println("The Associated Instructor :"+
					tempInstructorDetail.getInstructor());
			//Commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			//handle connection leak issue
			
			session.close();
			factory.close();
		}
	}

}
