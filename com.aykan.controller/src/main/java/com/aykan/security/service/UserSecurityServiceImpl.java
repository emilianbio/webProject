package com.aykan.security.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.domain.user.PasswordResetToken;
import com.aykan.domain.user.User;
import com.aykan.service.user.PasswordResetTokenService;

@Service
@Transactional
public class UserSecurityServiceImpl implements UserSecurityService{

	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	@Autowired
	private UserDetailsService userDetailsService;
	
    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";
	
	@Override
	public String validatePasswordResetToken(String token, Long id) {

		PasswordResetToken passwordResetToken = passwordResetTokenService.findPasswordResetTokenByToken(token);
		
		if(passwordResetToken == null || passwordResetToken.getUser().getId() != id){
			return TOKEN_INVALID;
		}
		
		Calendar calendar = Calendar.getInstance();
		if(passwordResetToken.getExpiryDate().getTime() - calendar.getTime().getTime() <= 0){
			return TOKEN_EXPIRED;
		}
		User user = passwordResetToken.getUser();

		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		return TOKEN_VALID;
	}
}
