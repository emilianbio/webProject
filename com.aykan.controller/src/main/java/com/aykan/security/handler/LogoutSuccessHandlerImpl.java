package com.aykan.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler{

	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session!=null){
			//HttpSessionBindirListener remove ettigimizi dinleyecek unbound methodu calýscak
			session.removeAttribute("user");
		}
		handle(request, response,authentication);
	}

	private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		// TODO Auto-generated method stub
		String targetUrl = "/";
		
		if(response.isCommitted()){
			//Response has already been committed. Unable to redirect to " + url
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
}
