package com.aykan.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aykan.web.dto.PasswordDto;

@Component
public class PasswordDtoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return PasswordDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", null,"Old Password is requeired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matchingNewPassword", null,"Matching New Password is requeired");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", null,"New Password Password is requeired");
	}

}
