package com.dmi.tommy.dao;

import java.util.List;

import com.dmi.tommy.Employee;

public interface EmployeeDao {

	public abstract Employee findById(int id);

	public abstract List<Employee> findByName(String firstname, String lastname);

	public abstract void add(Employee employee);

	public abstract void update(Employee employee);
	
	public abstract void delete(Employee employee);
	
	public abstract Employee getRootEmployee();

}