package com.izasoft.jcart.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izasoft.jcart.domain.Permission;

@Service
public class PermissionService {
	@Autowired PermissionRepository permissionRepository;
	
	public Permission findRole(Integer id) {
		return permissionRepository.findById(id).orElse(null);
	}
}
