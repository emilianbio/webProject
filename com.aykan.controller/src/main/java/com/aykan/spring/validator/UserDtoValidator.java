package com.aykan.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aykan.web.dto.UserDto;

@Component
public class UserDtoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", null,"Firstname is requeired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", null,"Firstname is requeired");
		UserDto userDto = (UserDto)target;
		if(userDto.getFirstName().trim().length() < 3){
			errors.rejectValue("firstName", null, "Firstname en az 5 karakter olabilir.");
		}
		if(userDto.getLastName().trim().length() < 3){
			errors.rejectValue("firstName", null, "Lastname en az 5 karakter olabilir.");
		}
	}

}
