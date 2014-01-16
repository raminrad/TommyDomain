package com.dmi.tommy.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.dmi.tommy.Employee;

public class HibernateUtilImpl implements HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtilImpl() {
    	sessionFactory = buildSessionFactory();
    }
    
    private SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	return new AnnotationConfiguration()
        		.addAnnotatedClass(Employee.class)
        		.configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /* (non-Javadoc)
	 * @see com.dmi.tommy.util.HibernateUtil#getSessionFactory()
	 */
    @Override
	public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}