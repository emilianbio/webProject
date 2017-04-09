package com.aykan.formatter.employee;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.aykan.domain.employee.Department;

@Component
public class DepartmentFormatter implements Formatter<Department>{

	// interface Converter
	//package org.springframework.core.convert.converter
	//spring configration dosyasýnda tanýmlamayý unutma
	
	@Override
	public Department parse(String departmentId, Locale arg1) throws ParseException {
		// TODO Auto-generated method stub
		Department department = new Department();
		department.setDepartmentId(Long.parseLong(departmentId));
		return department;
	}

	@Override
	public String print(Department department, Locale arg1) {
		// TODO Auto-generated method stub
		return department.getDepartmentId().toString();
	}

	
}
