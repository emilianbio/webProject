package com.aykan.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.domain.user.Privilege;
import com.aykan.domain.user.Role;
import com.aykan.domain.user.User;
import com.aykan.security.LoginAttemptService;
import com.aykan.service.user.UserService;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserService userService;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		User user = userService.findUserByUsername(username);
		
		if (loginAttemptService.isBlocked(getClientIp())) {
			throw new RuntimeException("Ip Blocked one munite.");
		}
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), 
					getAuthorities(user.getRole()));

	}
		// Biz ayrýcalýklarýmýza(privilege) göre siteden iþlemler yapýcaz.
		// Biz user'ýn role_admin yada role_user olduguna bakmayacagýz. Buradan
		// verdigimiz yetkilere göre bakýcaz.
		// Buradan biz user'a write_privilege yada read_privilege yetkileri
		// veriyoruz. Databaseden gelen veriye göre.

		/*
		 * public User(String username, String password, boolean enabled,
		 * boolean accountNonExpired, boolean credentialsNonExpired, boolean
		 * accountNonLocked, Collection<? extends GrantedAuthority> authorities)
		 */


	// eger biz privilege göre degilde role_user yada role_admine göre sitemizin
	// kontrolunu yapsaydýk o zaman :
	// Kullanmýyoruz. göstermelik
	@SuppressWarnings("unused")
	private Collection<? extends GrantedAuthority> getAuthorities2(List<Role> roles) {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
		// TODO Auto-generated method stub
		List<String> privileges = getPrivileges(role);
		return getGrantedAuthorities(privileges);
	}

	private List<String> getPrivileges(Role role) {
		// TODO Auto-generated method stub

		List<String> privileges = new ArrayList<String>();
		List<Privilege> collection = new ArrayList<Privilege>();

		collection.addAll(role.getPrivileges());

		for (Privilege privilege : collection) {
			privileges.add(privilege.getName());
		}

		return privileges;
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String privilege : privileges) {

			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
	
	private String getClientIp() {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}

}
