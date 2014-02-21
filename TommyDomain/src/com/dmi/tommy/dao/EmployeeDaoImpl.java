package com.dmi.tommy.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.dmi.tommy.Employee;
import com.dmi.tommy.util.HibernateUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	Logger logger = Logger.getLogger(EmployeeDaoImpl.class.getName());
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	public Employee getRootEmployee() {
		Session session = hibernateUtil.getSessionFactory().openSession();
		try {
			List<Employee> empList = session.createSQLQuery("SELECT * FROM employee where id_boss is NULL").addEntity(Employee.class).list();
			if (empList != null && empList.size() > 0) {
				return empList.get(0);
			}
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "Can't find root employee.", e);
		}
		finally {
			if (session.isOpen()) {
				session.close();				
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.dmi.tommy.EmployeeDao#findById(int)
	 */
	@Override
	public Employee findById(int id) {
		Session session = hibernateUtil.getSessionFactory().openSession();
		try {
			return (Employee) session.get(Employee.class, id);
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "Can't find by id " + id, e);
		}
		finally {
			if (session.isOpen()) {
				session.close();				
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.dmi.tommy.EmployeeDao#findByName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Employee> findByName(String firstname, String lastname) {
		Session session = hibernateUtil.getSessionFactory().openSession();
		try {
			return (List<Employee>) session.createQuery("from Employee where firstname = :fn and lastname = :ln")
					.setParameter("fn", firstname)
					.setParameter("ln", lastname)
					.list();
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, 
					"exception thrown while trying to find by Name for " + firstname + " " + lastname, e);
		}
		finally {
			if (session.isOpen()) {
				session.close();				
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.dmi.tommy.EmployeeDao#add(com.dmi.tommy.Employee, com.dmi.tommy.Employee)
	 */
	@Override
	public void add(Employee employee) {
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(employee);
		}
		catch (Exception e) {
			session.getTransaction().rollback();
			// TODO - do something with exception
		}
		finally {
			session.getTransaction().commit();
			if (session.isOpen()) {
				session.close();				
			}
		}
	}

	@Override
	public void update(Employee employee) {
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(employee);
		}
		catch (Exception e) {
			session.getTransaction().rollback();
			// TODO - do something with exception
		}
		finally {
			session.getTransaction().commit();
			if (session.isOpen()) {
				session.close();				
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.dmi.tommy.EmployeeDao#delete(com.dmi.tommy.Employee)
	 */
	@Override
	public void delete(Employee employee) {
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.delete(employee);
		}
		catch (Exception e) {
			session.getTransaction().rollback();
			// TODO - do something with exception
		}
		finally {
			session.getTransaction().commit();
			if (session.isOpen()) {
				session.close();				
			}
		}
	}

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}
}
