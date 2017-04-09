package com.aykan.security.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;



public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

	// Spring Security �retim kalitesinde i�erir AuthenticationProviderdenilen
	// uygulama DaoAuthenticationProvider.
	// Bu kimlik do�rulama sa�lay�c�s� olu�turmak kimlik do�rulama mekanizmalar�
	// ile uyumlu,
	// UsernamePasswordAuthenticationTokenve muhtemelen �er�evesinde en s�k
	// kullan�lan sa�lay�c�s�d�r.
	/*
	 * @Autowired private UserDetailsService userDetailsService;
	 * 
	 * @Autowired private UserService userService;
	 */

	/*
	 * Spring Security, kimlik do�rulamay� ger�ekle�tirmek i�in �e�itli
	 * se�enekler sunar - hepsi basit bir s�zle�meyi takiben - bir Kimlik
	 * Do�rulama iste�i bir AuthenticationProvider taraf�ndan i�lenir ve tam
	 * kimlik bilgilerine sahip tamamen kimli�i do�rulanm�� bir nesne
	 * d�nd�r�l�r.
	 * 
	 * Standart ve en yayg�n uygulama DaoAuthenticationProvider - kullan�c�
	 * ayr�nt�lar�n� basit, salt okunur kullan�c� DAO - UserDetailsService'den
	 * al�r.
	 */

	@Override
	public Authentication authenticate(Authentication auth) {
		
		Authentication authentication = super.authenticate(auth);

		return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),
				authentication.getAuthorities());
	}
	// User user = userService.findUserByEmail(auth.getName());

	/*
	 * System.out.println("Auth name : " + auth.getName());
	 * System.out.println("Auth getPrincipal : " +
	 * auth.getPrincipal().toString());
	 * System.out.println("Auth getCredentials : "+ auth.getCredentials());
	 * 
	 * WebAuthenticationDetails authenticationDetails =
	 * (WebAuthenticationDetails) auth.getDetails();
	 * authenticationDetails.getSessionId(); Kullan�c�n�n Session Id sini
	 * authenticationDetails.getRemoteAddress(); Kullan�c�n ip'sini d�n�yorur
	 * Indicates the TCP/IP address the authentication request was received
	 * from.
	 * 
	 * System.out.println("Auth getDetails : " + auth.getDetails().toString());
	 * 
	 * User user = userService.findUserByEmail(auth.getName());
	 * 
	 * 
	 * if(user == null){ throw new BadCredentialsException("Invalid username");
	 * } if(!user.getPassword().equals(auth.getDetails())){ throw new
	 * BadCredentialsException("Invalid password"); }
	 */

	/*
	 * super.authenticate(auth); ilk olarak g�nderdigimiz auth nesnensini
	 * Spring'in bize saglad�g� Assert class�n�n instancerOf methodunu
	 * kullanarak g�nderdigimiz parametrenin UsernamePasswordToken.class t�r�nde
	 * olup olmad�g�n� kontrol ediyor.
	 * 
	 * Code: Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class,
	 * authentication, messages.getMessage(
	 * "AbstractUserDetailsAuthenticationProvider.onlySupports",
	 * "Only UsernamePasswordAuthenticationToken is supported")); Benzer code:
	 * if(auth instanceOf UsernamePasswordToken.class){ throw new
	 * IllegalArgumentException("error message"); } Sonra parametre olarak
	 * g�nderdigimiz auth nesnesinden String username = auth.getPrincipal();
	 * yaparak kullan�c�n giri� yapt�g� email'i username nesnesine y�kl�yor.
	 * 
	 * Daha sonra 'username' kullanarak UserDetails nesnesini cekiyor
	 * 
	 * UserDetails loadedUser =
	 * this.getUserDetailsService().loadUserByUsername(username); burada
	 * bulamazsa 'UsernameNotFoundException' f�rlat�yor.
	 * 
	 * loadUserByUsername(String email) throws UsernameNotFoundException
	 * 
	 * Herhangi bir hata c�kmaz ise loadedUser nesnesini check metodunu
	 * yolluyor.
	 * 
	 * public void check(UserDetails user) { if (!user.isAccountNonLocked()) {
	 * logger.debug("User account is locked");
	 * 
	 * throw new LockedException(messages.getMessage(
	 * "AbstractUserDetailsAuthenticationProvider.locked",
	 * "User account is locked")); }
	 * 
	 * if (!user.isEnabled()) { logger.debug("User account is disabled");
	 * 
	 * throw new DisabledException(messages.getMessage(
	 * "AbstractUserDetailsAuthenticationProvider.disabled",
	 * "User is disabled")); }
	 * 
	 * if (!user.isAccountNonExpired()) {
	 * logger.debug("User account is expired");
	 * 
	 * throw new AccountExpiredException(messages.getMessage(
	 * "AbstractUserDetailsAuthenticationProvider.expired",
	 * "User account has expired")); }
	 * 
	 * Username ile suan User nesnesinin verilerini y�kledik. Daha sonrada
	 * password kontor�l�n� yap�yor hata varsa BadCredetionalException
	 * f�rl�t�yor.
	 * 
	 * �stersek super.authenticate(auth); methodunu kullanmay�z t�m kontr�l�
	 * burada biz tan�mlar�z. O zaman userDetailsServiceImpl clas�n�
	 * kullanmam�za gerek kalmaz. request gelirse buradan direk kontrol
	 * Spring'in yapt�g� gibi kontr�l� yapar�z. hata varsa exception f�rlat�r�z.
	 * yoksa UserNamePasswordToken'i doldurup return ederiz.
	 */

	// Assert.instanceOf()

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
