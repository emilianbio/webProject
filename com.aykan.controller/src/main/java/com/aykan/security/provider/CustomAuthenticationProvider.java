package com.aykan.security.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;



public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

	// Spring Security üretim kalitesinde içerir AuthenticationProviderdenilen
	// uygulama DaoAuthenticationProvider.
	// Bu kimlik doðrulama saðlayýcýsý oluþturmak kimlik doðrulama mekanizmalarý
	// ile uyumlu,
	// UsernamePasswordAuthenticationTokenve muhtemelen çerçevesinde en sýk
	// kullanýlan saðlayýcýsýdýr.
	/*
	 * @Autowired private UserDetailsService userDetailsService;
	 * 
	 * @Autowired private UserService userService;
	 */

	/*
	 * Spring Security, kimlik doðrulamayý gerçekleþtirmek için çeþitli
	 * seçenekler sunar - hepsi basit bir sözleþmeyi takiben - bir Kimlik
	 * Doðrulama isteði bir AuthenticationProvider tarafýndan iþlenir ve tam
	 * kimlik bilgilerine sahip tamamen kimliði doðrulanmýþ bir nesne
	 * döndürülür.
	 * 
	 * Standart ve en yaygýn uygulama DaoAuthenticationProvider - kullanýcý
	 * ayrýntýlarýný basit, salt okunur kullanýcý DAO - UserDetailsService'den
	 * alýr.
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
	 * authenticationDetails.getSessionId(); Kullanýcýnýn Session Id sini
	 * authenticationDetails.getRemoteAddress(); Kullanýcýn ip'sini dönüyorur
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
	 * super.authenticate(auth); ilk olarak gönderdigimiz auth nesnensini
	 * Spring'in bize sagladýgý Assert classýnýn instancerOf methodunu
	 * kullanarak gönderdigimiz parametrenin UsernamePasswordToken.class türünde
	 * olup olmadýgýný kontrol ediyor.
	 * 
	 * Code: Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class,
	 * authentication, messages.getMessage(
	 * "AbstractUserDetailsAuthenticationProvider.onlySupports",
	 * "Only UsernamePasswordAuthenticationToken is supported")); Benzer code:
	 * if(auth instanceOf UsernamePasswordToken.class){ throw new
	 * IllegalArgumentException("error message"); } Sonra parametre olarak
	 * gönderdigimiz auth nesnesinden String username = auth.getPrincipal();
	 * yaparak kullanýcýn giriþ yaptýgý email'i username nesnesine yüklüyor.
	 * 
	 * Daha sonra 'username' kullanarak UserDetails nesnesini cekiyor
	 * 
	 * UserDetails loadedUser =
	 * this.getUserDetailsService().loadUserByUsername(username); burada
	 * bulamazsa 'UsernameNotFoundException' fýrlatýyor.
	 * 
	 * loadUserByUsername(String email) throws UsernameNotFoundException
	 * 
	 * Herhangi bir hata cýkmaz ise loadedUser nesnesini check metodunu
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
	 * Username ile suan User nesnesinin verilerini yükledik. Daha sonrada
	 * password kontorülünü yapýyor hata varsa BadCredetionalException
	 * fýrlýtýyor.
	 * 
	 * Ýstersek super.authenticate(auth); methodunu kullanmayýz tüm kontrülü
	 * burada biz tanýmlarýz. O zaman userDetailsServiceImpl clasýný
	 * kullanmamýza gerek kalmaz. request gelirse buradan direk kontrol
	 * Spring'in yaptýgý gibi kontrülü yaparýz. hata varsa exception fýrlatýrýz.
	 * yoksa UserNamePasswordToken'i doldurup return ederiz.
	 */

	// Assert.instanceOf()

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
