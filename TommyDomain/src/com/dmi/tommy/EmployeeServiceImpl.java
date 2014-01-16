package com.dmi.tommy;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.dmi.tommy.dao.EmployeeDao;


@WebService(name = "EmployeeService", targetNamespace = "http://tommy.dmi.com")
public class EmployeeServiceImpl extends SpringBeanAutowiringSupport implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	/* (non-Javadoc)
	 * @see com.dmi.tommy.EmployeeService#getEmployeeById(int)
	 */
	@Override
	public Employee getEmployeeById(int id) {
		return employeeDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.dmi.tommy.EmployeeService#getEmployeeByName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Employee> getEmployeeByName(String firstname, String lastname) {
		return employeeDao.findByName(firstname, lastname);
	}
	
	/* (non-Javadoc)
	 * @see com.dmi.tommy.EmployeeService#addEmployee(com.dmi.tommy.Employee)
	 */
	@Override
	public void addEmployee(Employee employee) {
		employeeDao.add(employee);
	}
	
	/* (non-Javadoc)
	 * @see com.dmi.tommy.EmployeeService#deleteEmployee(com.dmi.tommy.Employee)
	 */
	@Override
	public void deleteEmployee(Employee employee) {
		if (employee.getStaff() == null || employee.getStaff().size() == 0) {
			employeeDao.delete(employee);
		}
		else {
			throw new RuntimeException("Can't delete an employee who still manages staff.");
		}
	}

	@Override
	public Employee getEmployeeRoot() {
		// TODO Auto-generated method stub
		return employeeDao.getRootEmployee();
	}

}
