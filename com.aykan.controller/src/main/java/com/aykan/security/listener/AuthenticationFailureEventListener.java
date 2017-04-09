package com.aykan.security.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.aykan.security.LoginAttemptService;

@Component
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent>{

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent auth) {
		// TODO Auto-generated method stub
		WebAuthenticationDetails authenticationDetails = (WebAuthenticationDetails) auth.getAuthentication().getDetails();
		if(auth != null){
			loginAttemptService.loginFailed(authenticationDetails.getRemoteAddress());
		}
	}

}
