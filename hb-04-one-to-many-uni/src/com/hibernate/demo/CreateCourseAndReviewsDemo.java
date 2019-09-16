package com.hibernate.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;


public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		//Create Session factory
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.buildSessionFactory();
		
		// Create Session
		Session session = factory.getCurrentSession();
		
		try {
						
			//Start a transaction
			session.beginTransaction();
			
			//Create a course
			Course tempCourse = new Course("Pacman - Score high");
			// add some reviews
			tempCourse.addReview(new Review("Greate course !!"));
			tempCourse.addReview(new Review("Tough course."));
			tempCourse.addReview(new Review("I failed this course"));
			// Save the course ... and leverage the cascade all
			System.out.println("Saving the course");
			session.save(tempCourse);
			System.out.println(tempCourse.getReviews());
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
