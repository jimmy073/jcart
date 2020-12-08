package com.izasoft.jcart.web.contoller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.izasoft.jcart.domain.Permission;
import com.izasoft.jcart.domain.Role;
import com.izasoft.jcart.security.SecurityService;
import com.izasoft.jcart.security.SecurityUtils;
import com.izasoft.jcart.web.validators.RoleValidator;

@Controller
@Secured(SecurityUtils.MANAGE_ROLES)
public class RoleController extends JCartAdminBaseContoller {

	public static final String viewPrefix ="roles/";
	
	@Autowired SecurityService securityService;
	@Autowired RoleValidator roleValidator;
	
	@Override
	protected String getHeaderTitle() {
		return "Manage Roles";
	}
	
	@ModelAttribute("permissionList")
	public List<Permission> permissionList(){
		return securityService.getAllPermissions();
	}
	
	@RequestMapping(value = "/roles", method=RequestMethod.GET)
	public String listRoles(Model model) {
		List<Role> list = securityService.getAllRoles();
		model.addAttribute("roles",list);
		return viewPrefix+"roles";
	}
	
	@RequestMapping(value = "/roles/new", method=RequestMethod.GET)
	public String createRoleForm(Model model) {
		Role role = new Role();
		model.addAttribute("role",role);
		return viewPrefix+"create_role";
	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public String createRole(@Valid @ModelAttribute("role") Role role, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		roleValidator.validate(role, result);
		if(result.hasErrors()) {
			return viewPrefix+"create_role";
		}
		Role persistedRole = securityService.createRole(role);
	//din't find this object	logger.debug("Created new Role with Id : {} and name: {} ", persistedRole.getId(), persistedRole.getName());
		redirectAttributes.addAttribute("info", "Role Created Successfully");
		return "redirect:/roles";
	}

}
