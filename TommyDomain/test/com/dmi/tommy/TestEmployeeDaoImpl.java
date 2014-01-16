package com.dmi.tommy;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestEmployeeDaoImpl extends TestDao {
	Logger logger = Logger.getLogger(TestEmployeeDaoImpl.class.getName());

	
	@Before
	public void setup() {
		super.setup();
		// clear the employee table
		Session session = new HibernateUtilTest().getSessionFactory().openSession();
		session.beginTransaction();
		try {
			session.createSQLQuery("DELETE FROM employee").executeUpdate();
			logger.info("Deleted all employees.");
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "Can't delete all employees.", e);
			session.getTransaction().rollback();
		}
		finally {
			session.getTransaction().commit();
			if (session.isOpen()) {
				session.close();				
			}
		}
		
	}
	
	@Test
	public void testAll() {
		// assumes the DB is completely empty
		
		Employee ceo = new Employee("John", "Kenworth");
		ceo.setTitle("CEO");
		
		employeeDao.add(ceo);

		List<Employee> empList = employeeDao.findByName("John", "Kenworth");
		Assert.assertNotNull(empList);
		ceo = empList.get(0);
		Assert.assertEquals("John", ceo.getFirstname());
		
		Employee emp = employeeDao.findById(ceo.getEmployeeId());
		Assert.assertNotNull(emp);
		Assert.assertEquals("John", emp.getFirstname());
		
		emp = new Employee("Alec", "Berg");
		emp.setTitle("VP");
		emp.setBoss(ceo);
		
		empList.remove(0);
		empList.add(emp);
		
		ceo.setStaff(empList);
		
		employeeDao.update(ceo);
		
		Employee root = employeeDao.getRootEmployee();
		Assert.assertNotNull(root);
		Assert.assertNotNull(root.getStaff());
		Assert.assertTrue(root.getStaff().size() > 0);
		
	}	
}
