package com.dmi.tommy.util;

import org.hibernate.SessionFactory;

public interface HibernateUtil {

	public abstract SessionFactory getSessionFactory();

}