package com.aykan.security.handler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.aykan.security.LoggedUser;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		//Login olma zamaný,
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss dd.MM.yyyy ");
		Date date = new Date();
		String result = format.format(date);
		
		handle(request, response, authentication);
		
		//Eger session yoksa oluþturma. Eger true olsaydý ve session olmasaydý yeni bir session oluþtururdu.
		HttpSession session = request.getSession(false);
		
		if(session != null){
			//30*60 30 dk boyunca istek olmazsa session silinsin
			session.setMaxInactiveInterval(30*60);
			
			LoggedUser user = new LoggedUser(authentication.getName(), result);
			
			//HttpSessionBindingListener yakalayacak.
			session.setAttribute("user", user);
		}
		clearAuthenticationAttributes(request);
	}

	private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		// TODO Auto-generated method stub
		//Hedef urlimiz yetkilerimize göre alýcagýz
		//Örnegin adminse admin paneline yönlendir user'sa user paneline yönlendir
		//yetkilerimiz authenticationdan cekip kontrol edicez.
		//ve ona göre bir String url nesnesi dönderecegiz.
		String targetUrl = determineTargetUrl(authentication);
		
		//Eger yanýt gönderilmisse if'e girecek method sonlanacak.
		if(response.isCommitted()){
			return;
		}
		//Eger yanýt gönderilmediyse yönlendirme yapýlacýktýr.
		redirectStrategy.sendRedirect(request, response, targetUrl);
		
	}

	private String determineTargetUrl(Authentication authentication) {
		// TODO Auto-generated method stub
		boolean isUser = false;
		boolean isAdmin = false;
		
		//parametre olarak gönderilen authentication'da olan yetkileri cekecegiz.
		Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if(grantedAuthority.getAuthority().equals("ROLE_READ_PRIVILEGE")){
				isUser = true;
			}else if(grantedAuthority.getAuthority().equals("ROLE_WRITE_PRIVILEGE")){
				isAdmin = true;
				isUser = false;
				break;
			}
		}
		if(isAdmin)
			return "/admin";
		else if(isUser)
			return "/";
		else
			throw new IllegalArgumentException();
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session == null){
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
