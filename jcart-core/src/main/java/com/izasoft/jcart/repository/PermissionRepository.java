package com.izasoft.jcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.izasoft.jcart.domain.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
