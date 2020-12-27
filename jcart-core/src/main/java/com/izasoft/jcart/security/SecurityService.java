package com.izasoft.jcart.security;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izasoft.jcart.JCartException;
import com.izasoft.jcart.domain.Permission;
import com.izasoft.jcart.domain.Role;
import com.izasoft.jcart.domain.User;
import com.izasoft.jcart.repository.PermissionRepository;
import com.izasoft.jcart.repository.RoleRepository;
import com.izasoft.jcart.repository.UserRepository;
import com.izasoft.jcart.repositoryService.PermissionService;

@Service
@Transactional
public class SecurityService {
	
	@Autowired UserRepository userRepository;
	@Autowired PermissionRepository permissionRepository;
	@Autowired RoleRepository roleRepository;
	@Autowired PermissionService permissionService;
	
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
	
	public List<Role> getAllRoles(){
		return roleRepository.findAll();
	}

	public Role getRoleByName(String name) {
		return roleRepository.findByName(name);
	}
	
	public Role createRole(Role role) {
		Role roleByName = getRoleByName(role.getName());
		
		if(roleByName != null) {
			throw new JCartException("Role "+role.getName()+" already exist"); 
		}
		
		List<Permission> persistedPermission = new ArrayList<Permission>();
		List<Permission> permissions = role.getPermissions();
		
		if(permissions !=null) {
			for(Permission permission: permissions) {
				if(permission.getId() != null) {
					Permission p = permissionService.findPermission(permission.getId());
					persistedPermission.add(p);
				}
			}
		}
		role.setPermissions(persistedPermission);
		return role;
	}
	
	public Role updateRole(Role role) {
		Role persistedRole = getRoleById(role.getId());
		if(persistedRole == null) {
			throw new JCartException("Role "+role.getId()+" doesn't exist");
		}
		
		persistedRole.setDescription(role.getDescription());
		List<Permission> updatedPermissions = new ArrayList<Permission>();
		List<Permission> permissions = role.getPermissions();
		
		if(permissions != null) {
			for(Permission permission: permissions) {
				if(permission.getId()!=null) {
					updatedPermissions.add(permissionService.findPermission(permission.getId()));
				}
			}
		}
		persistedRole.setPermissions(updatedPermissions);
		return roleRepository.save(persistedRole);
	}

	public Role getRoleById(Integer id) {
		return this.roleRepository.findById(id).orElse(null);
	}
	

}
