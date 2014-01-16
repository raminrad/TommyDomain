package com.dmi.tommy;

import java.util.logging.Logger;

import org.junit.Before;

import com.dmi.tommy.dao.EmployeeDaoImpl;

public class TestDao {
	Logger logger = Logger.getLogger(TestDao.class.getName());
	
	protected EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
	
	
	@Before
	public void setup() {
		logger.info("Setting hibernate util on DAO");
        employeeDao.setHibernateUtil(new HibernateUtilTest());
	}
}
