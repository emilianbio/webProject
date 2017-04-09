package com.aykan.security.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.aykan.security.LoginAttemptService;
import com.aykan.security.aop.Loggable;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent>{

	
	
	@Autowired
	private LoginAttemptService loginAttampService;
	
	@Override
	@Loggable
	public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
		// TODO Auto-generated method stub
		WebAuthenticationDetails authenticationDetails = (WebAuthenticationDetails) authenticationSuccessEvent.getAuthentication().getDetails();
		if(authenticationDetails != null){
			loginAttampService.loginSucceded(authenticationDetails.getRemoteAddress());
		}
	}

}
