package com.izasoft.jcart.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izasoft.jcart.domain.User;
import com.izasoft.jcart.repository.UserRepository;

@Service
@Transactional
public class SecurityService {
	
	@Autowired UserRepository userRepository;
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
