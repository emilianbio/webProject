package com.aykan.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;

import com.google.common.base.Joiner;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String>{

	@Override
	public void initialize(ValidPassword constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		List<Rule> rules = new ArrayList<>();
		/*	
		rules.add(new LengthRule(5, 30));
     	rules.add(new UppercaseCharacterRule(1));
		rules.add(new DigitCharacterRule(1));
		rules.add(new WhitespaceRule());
		rules.add(new SpecialCharacterRule(1));
		*/
		PasswordValidator validator = new PasswordValidator(rules);
		RuleResult result = validator.validate(new PasswordData(password));
		if(result.isValid()){
			return true;
		}
		
		/*
		 * List<String> list = validator.getMessages(result))
		 * Oluþturdugumuz kurallara uymayan mesajlarý listenisi dönüyor 
		 * Joiner.on("\n") ile her bir hatadan sonra alt satýra geciyor. Listeyi satýr satýr yazma istersek Joiner kullanýyoruz.
		 */
		
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(Joiner.on("\n").join(validator.getMessages(result))).addConstraintViolation();
		return false;
	}

}
