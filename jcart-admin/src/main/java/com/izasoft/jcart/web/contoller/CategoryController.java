package com.izasoft.jcart.web.contoller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

@Controller
@Secured("ROLE_MANAGE_CATEGORIES")
public class CategoryController extends JCartAdminBaseContoller {

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
