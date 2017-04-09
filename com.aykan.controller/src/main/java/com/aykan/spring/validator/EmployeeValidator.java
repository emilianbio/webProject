package com.aykan.spring.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aykan.domain.employee.Employee;

@Component
public class EmployeeValidator implements Validator{
	
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Employee.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Employee employee = (Employee)target;
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(employee.getEmail());
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", null, "FirstName is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", null, "FirstName is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", null, "FirstName is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", null, "FirstName is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salary", null, "FirstName is required.");

		//String utils araþtýrabilirsiniz.
		if(employee.getFirstName().trim().length() < 3){
			errors.rejectValue("firstName", null, "FirstName 3 karakterden kücük olamaz");
		}
		if(employee.getLastName().length() < 3){
			errors.rejectValue("lastName", null, "FirstName 3 karakterden kücük olamaz");
		}
		if(matcher.matches()){
			errors.rejectValue("email", null, "Düzgün bir email girmelisiniz.");
		}
		
	}

	
	
}
