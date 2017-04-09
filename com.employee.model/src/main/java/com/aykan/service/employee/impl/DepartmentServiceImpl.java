package com.aykan.service.employee.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.dao.employee.DepartmentRepository;
import com.aykan.domain.employee.Department;
import com.aykan.service.employee.DepartmentService;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Throwable.class})
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public boolean saveDepartment(Department department) {
		return departmentRepository.saveDepartment(department);
	}

	@Override
	public boolean deleteDepartment(Department department) {
		return departmentRepository.deleteDepartment(department);
	}

	@Override
	public Department updateDepartment(Department department) {
		return departmentRepository.updateDepartment(department);
	}

	@Override
	public Department findDerpatmentById(Long departmentId) {
		return departmentRepository.findDerpatmentById(departmentId);
	}

	@Override
	public List<Department> findAllDepartments() {
		return departmentRepository.findAllDepartments();
	}

	@Override
	public List<Department> findAllDepartmentsEntiries(int firstResult, int maxResult) {
		return departmentRepository.findAllDepartmentsEntiries(firstResult, maxResult);
	}

	@Override
	public List<String> findDepartmentNames() {
		return departmentRepository.findDepartmentNames();
	}
}
