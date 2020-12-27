package com.izasoft.jcart.repositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izasoft.jcart.domain.Permission;
import com.izasoft.jcart.repository.PermissionRepository;

@Service
public class PermissionService {
	@Autowired PermissionRepository permissionRepository;
	
	public Permission findPermission(Integer id) {
		return permissionRepository.findById(id).orElse(null);
	}
}
