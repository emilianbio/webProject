package com.aykan.formatter.user;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.aykan.domain.user.Role;

@Component
public class RoleFormatter implements Formatter<Role> {

	@Override
	public String print(Role object, Locale locale) {
		// TODO Auto-generated method stub
		return object.getId().toString();
	}

	@Override
	public Role parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setId(Long.parseLong(text));
		return role;
	}

}
