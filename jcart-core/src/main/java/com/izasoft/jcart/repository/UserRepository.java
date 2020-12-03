package com.izasoft.jcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.izasoft.jcart.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
	
}
