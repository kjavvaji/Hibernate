package com.hibernate.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;


public class DeleteMaryStudentDemo {

	public static void main(String[] args) {
		
		//Create Session factory
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
						
			//Start a transaction
			session.beginTransaction();
			
			// get the student mary from database
			int studentId = 2;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\nLoaded Student: "+tempStudent);
			System.out.println("\nCourses: "+tempStudent.getCourses());
			
			// delete student
			System.out.println("Deleting student: "+tempStudent);
			session.delete(tempStudent);
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
