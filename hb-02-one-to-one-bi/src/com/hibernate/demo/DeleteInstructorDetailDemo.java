package com.hibernate.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;


public class DeleteInstructorDetailDemo {

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
				int theId=3;
				InstructorDetail tempInstructorDetail = 
						session.get(InstructorDetail.class, theId);
				
			// print the instructor detail
			System.out.println("temp instructor object :"+tempInstructorDetail);
			//print the associated instructor
			System.out.println("The Associated Instructor :"+
					tempInstructorDetail.getInstructor());
			
			// now we need to delete the instructor detail
			System.out.println("Deleting tempInstructor detail :"+tempInstructorDetail);
			// remove the associated object reference
			// break bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(tempInstructorDetail);
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
