package com.aykan.service.employee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.dao.employee.EmployeeRepository;
import com.aykan.domain.employee.Employee;
import com.aykan.service.employee.EmployeeService;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Throwable.class})
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public boolean saveEmployee(Employee employee) {
		return employeeRepository.saveEmployee(employee);
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		return employeeRepository.deleteEmployee(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.updateEmployee(employee);
	}

	@Override
	public Employee findEmployeeById(Long employeeId) {
		return employeeRepository.findEmployeeById(employeeId);
	}

	@Override
	public List<Employee> findEmployees() {
		return employeeRepository.findEmployees();
	}

	@Override
	public List<Employee> findEmployeesEntiries(int firstResult, int maxResult) {
		return employeeRepository.findEmployeesEntiries(firstResult, maxResult);
	}

	@Override
	public Long countEmployee() {
		return employeeRepository.countEmployee();
	}

	@Override
	public List<Employee> betweenSalartEmployees(int minSalary, int maxSalary) {
		return employeeRepository.betweenSalartEmployees(minSalary, maxSalary);
	}

}
