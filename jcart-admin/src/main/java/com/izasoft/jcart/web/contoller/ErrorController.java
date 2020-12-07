package com.izasoft.jcart.web.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController extends JCartAdminBaseContoller {

	@RequestMapping("/403")
	public String accessDenied() {
		return "error/accessDenied";
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
