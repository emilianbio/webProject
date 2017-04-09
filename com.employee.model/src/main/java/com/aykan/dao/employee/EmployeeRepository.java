package com.aykan.dao.employee;

import java.util.List;

import com.aykan.domain.employee.Employee;

public interface EmployeeRepository {

	boolean saveEmployee(Employee employee); 
	boolean deleteEmployee(Employee employee); 
	Employee updateEmployee(Employee employee); 
	Employee findEmployeeById(Long employeeId); 
	List<Employee> findEmployees(); 
	List<Employee> findEmployeesEntiries(int firstResult, int maxResult); 
	Long countEmployee();
	List<Employee> betweenSalartEmployees(int minSalary, int maxSalary);
	
}
