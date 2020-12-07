package com.izasoft.jcart.security;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izasoft.jcart.domain.Permission;
import com.izasoft.jcart.domain.User;
import com.izasoft.jcart.repository.PermissionRepository;
import com.izasoft.jcart.repository.UserRepository;

@Service
@Transactional
public class SecurityService {
	
	@Autowired UserRepository userRepository;
	@Autowired PermissionRepository permissionRepository;
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public String resetPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean verifyPasswordResetToken(String email, String token) {
		// TODO Auto-generated method stub
		return false;
	}

	public void updatePassword(String email, String token, String encodedPwd) {
		// TODO Auto-generated method stub
		
	}

	public List<Permission> getAllPermissions(){
		return permissionRepository.findAll();
	}
	

}
