package com.izasoft.jcart.web.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.izasoft.jcart.domain.Permission;
import com.izasoft.jcart.security.SecurityService;
import com.izasoft.jcart.security.SecurityUtils;

@Controller
@Secured(SecurityUtils.MANAGE_PERMISSIONS)
public class PermissionController extends JCartAdminBaseContoller {

	public static final String viewPrefix = "permissions/";
	
	@Autowired private SecurityService securityService;
	
	@Override
	public String getHeaderTitle() {
		return "Manage Permission";
	}
	
	@RequestMapping(value="/permissions", method = RequestMethod.GET)
	public String listPermissions(Model model) {
		List<Permission> list = securityService.getAllPermissions();
		model.addAttribute("permissions", list);
		return viewPrefix+
				"permissions";
	}
	
	
}
