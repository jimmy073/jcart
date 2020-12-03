package com.izasoft.jcart.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.izasoft.jcart.domain.User;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired SecurityService securityService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = securityService.findUserByEmail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Email "+username+" Not Found");
		}
		
		return new AuthenticatedUser(user);
	}

}
