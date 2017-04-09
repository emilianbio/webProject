package com.aykan.dao.employee.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.dao.employee.DepartmentRepository;
import com.aykan.domain.employee.Department;

@Repository
@Transactional(rollbackFor = {RuntimeException.class, Throwable.class})
public class DepartmentRepositoryImpl implements DepartmentRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean saveDepartment(Department department) {
		entityManager.persist(department);
		return true;
	}

	@Override
	public boolean deleteDepartment(Department department) {
		if(entityManager.contains(department)){
			entityManager.remove(department);
		}else{
			Department deleteDepartment = findDerpatmentById(department.getDepartmentId());
			entityManager.remove(deleteDepartment);
		}
		return true;
	}

	@Override
	public Department updateDepartment(Department department) {
		Department updatedDepartment = entityManager.merge(department);
		entityManager.flush();
		return updatedDepartment;
	}

	@Override
	@Transactional(readOnly = true)
	public Department findDerpatmentById(Long departmentId) {
		return entityManager.createNamedQuery("Department.findLocationAndEmployeesByDepartmentId", Department.class).setParameter("departmentId", departmentId).getSingleResult();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Department> findAllDepartments() {
		return entityManager.createNamedQuery("Department.findAll", Department.class).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Department> findAllDepartmentsEntiries(int firstResult, int maxResult) {
		return entityManager.createNamedQuery("Department.findAll", Department.class)
							.setFirstResult(firstResult)
							.setMaxResults(maxResult)
							.getResultList();
	}

	@Override
	public List<String> findDepartmentNames() {
		TypedQuery<String> typedQuery = entityManager.createNamedQuery("Department.findByDepartmentName", String.class);
		return typedQuery.getResultList();
	}
	
}
