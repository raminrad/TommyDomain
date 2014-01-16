package com.dmi.tommy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.dmi.tommy.util.HibernateUtil;

public class HibernateUtilTest implements HibernateUtil {

	private static final SessionFactory sessionFactory = new AnnotationConfiguration().
    		addAnnotatedClass(Employee.class)
    		.configure("com/dmi/tommy/hibernate-test.cfg.xml")
    		.buildSessionFactory();
	
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
