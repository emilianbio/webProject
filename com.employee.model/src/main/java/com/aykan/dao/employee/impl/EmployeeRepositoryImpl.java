package com.aykan.dao.employee.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.dao.employee.EmployeeRepository;
import com.aykan.domain.employee.Employee;

@Repository
@Transactional(rollbackFor = {Throwable.class, RuntimeException.class})
public class EmployeeRepositoryImpl implements EmployeeRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		entityManager.persist(employee);
		return true;
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		if(entityManager.contains(employee)){
			entityManager.remove(employee);
		}else{
			Employee deleteEmployee = findEmployeeById(employee.getEmployeeId());
			entityManager.remove(deleteEmployee);
		}
		return true;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Employee updatedEmployee = entityManager.merge(employee);
		return updatedEmployee;
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findEmployeeById(Long employeeId) {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("Employee.findFullById", Employee.class).setParameter("employeeId", employeeId).getSingleResult();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findEmployees() {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("Employee.findAll", Employee.class).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findEmployeesEntiries(int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("Employee.findAll", Employee.class)
							.setFirstResult(firstResult)
							.setMaxResults(maxResult)
							.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Long countEmployee() {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("Employee.count", Long.class).getSingleResult();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> betweenSalartEmployees(int minSalary, int maxSalary) {
		// TODO Auto-generated method stub
		return entityManager.createNamedQuery("Employee.betweenSalay", Employee.class)
								.setParameter("minSalary", minSalary)
								.setParameter("maxSalary", maxSalary)
								.getResultList();
	}
	
}
