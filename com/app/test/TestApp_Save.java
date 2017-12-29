package com.app.test;

import org.hibernate.Session;

import org.hibernate.Transaction;


import com.app.model.HallTicket;
import com.app.model.Student;
import com.app.utility.HibernateUtil;

public class TestApp_Save {

	public static void main(String[] args) {
		Session ses=null;
		//get the session
		ses=HibernateUtil.getSession();
			Transaction tx=null;
		//create the objects
				Student stud1=new Student();
				Student stud2=new Student();
					stud1.setStudName("Pratik");
					stud2.setStudName("Rohit");

				
				HallTicket hall1=new HallTicket();
				HallTicket hall2=new HallTicket();
					hall1.setTicketId(555);hall1.setExam("GATE");
					hall2.setTicketId(999);hall2.setExam("CAT");
					
			

				//save objs (parent to child)
					stud1.setHall(hall1);hall1.setStud(stud1);
					stud2.setHall(hall2);hall2.setStud(stud2);
					try {
						tx=ses.beginTransaction();
						ses.save(stud1);
						ses.save(stud2);

						tx.commit();
						System.out.println("\n\n\t\tObjects saved Successfully");
						
					} catch (Exception e2) {
						tx.rollback();
						e2.printStackTrace();
						System.out.println("\n\n\t\tObjects not saved Successfully");
					}
						//close the session and session factory
					HibernateUtil.closeSession(ses);
					HibernateUtil.closeFactory();
	}

}
