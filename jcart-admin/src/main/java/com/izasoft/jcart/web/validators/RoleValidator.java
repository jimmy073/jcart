package com.izasoft.jcart.web.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.izasoft.jcart.domain.Role;
import com.izasoft.jcart.security.SecurityService;
@Component
public class RoleValidator implements Validator{

	@Autowired protected MessageSource messageSource;
	@Autowired protected SecurityService securityService;
	
	public boolean supports(Class<?> clazz) {
		return Role.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Role role = (Role) target;
		String name = role.getName();
		
		Role roleByName = securityService.getRoleByName(name);
		
		if(roleByName != null) {
			errors.rejectValue("name", "error.exists", new Object[] {name},
			"Role "+name+" already exists");
		}
	}

}
