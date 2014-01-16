package com.dmi.tommy;

import java.util.List;

public interface EmployeeService {

	public abstract Employee getEmployeeRoot();

	public abstract Employee getEmployeeById(int id);
	
	public abstract List<Employee> getEmployeeByName(String firstname, String lastname);

	public abstract void addEmployee(Employee employee);

	public abstract void deleteEmployee(Employee employee);
}