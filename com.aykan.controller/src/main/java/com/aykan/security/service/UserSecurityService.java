package com.aykan.security.service;

public interface UserSecurityService {

	String validatePasswordResetToken(String token, Long id);
	
}
