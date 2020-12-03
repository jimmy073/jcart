package com.izasoft.jcart.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.*;
import org.springframework.security.core.authority.AuthorityUtils;

import com.izasoft.jcart.domain.User;
import com.izasoft.jcart.domain.Role;
import com.izasoft.jcart.domain.Permission;


public class AuthenticatedUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	private User user;
	public AuthenticatedUser(User user) {
		super(user.getEmail(), user.getPassword(), getAuthorities(user));
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(User user){
		Set<String> roleAndPermissions = new HashSet<String>();
		List<Role> roles = user.getRoles();
		
		for(Role role: roles) {
			List<Permission> permissions = role.getPermissions();
			
			for(Permission permission: permissions) {
				roleAndPermissions.add("ROLE_"+permission.getName());
			}
		}
		String [] roleNames = new String[roleAndPermissions.size()];
		Collection<GrantedAuthority> authorities = 
				AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
		return authorities;
	}
	

}
