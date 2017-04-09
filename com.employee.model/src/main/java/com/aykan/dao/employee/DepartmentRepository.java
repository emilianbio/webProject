package com.aykan.dao.employee;

import java.util.List;

import com.aykan.domain.employee.Department;

public interface DepartmentRepository {

	boolean saveDepartment(Department department);
	boolean deleteDepartment(Department department); 
	Department updateDepartment(Department department);
	Department findDerpatmentById(Long departmentId); 
	List<Department> findAllDepartments(); 
	List<Department> findAllDepartmentsEntiries(int firstResult, int maxResult);
	
	List<String> findDepartmentNames();
}
