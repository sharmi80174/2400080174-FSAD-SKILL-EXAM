package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;

public class ClientDemo
{
	public static void main(String[] args)
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Shipment.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();

		// -------- INSERT OPERATION --------
		Transaction tx = session.beginTransaction();

		Shipment s = new Shipment();
		s.setName("Electronics Package");
		s.setDate(new Date());
		s.setStatus("Delivered");
		s.setOrigin("Hyderabad");
		s.setDestination("Delhi");

		session.save(s);

		System.out.println("Shipment Inserted Successfully");

		tx.commit();

		// -------- DELETE USING HQL --------
		Transaction tx2 = session.beginTransaction();

		Query q = session.createQuery("delete from Shipment where id=:sid");
		q.setParameter("sid", s.getId());

		int result = q.executeUpdate();

		System.out.println(result + " Record Deleted Using HQL");

		tx2.commit();

		session.close();
		sf.close();
	}
}