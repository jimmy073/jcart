package com.izasoft.jcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.izasoft.jcart.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);
	
}
