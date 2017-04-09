package com.aykan.security.handler;

/*import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
*/

//Bu clasý kullanmýyoruz. göstermek için açtým
public class AuthenticationFailureHandlerImpl{

}

/*
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler{


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) throws IOException, ServletException {
	
		String errorMessage = "bad credetils";
	
		if(exception.getMessage().equalsIgnoreCase("blocked")){
			errorMessage = "blocked";
		}
		request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
}

}
*/