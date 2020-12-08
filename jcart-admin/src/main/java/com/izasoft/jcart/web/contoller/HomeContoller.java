package com.izasoft.jcart.web.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeContoller extends JCartAdminBaseContoller {
	
	@RequestMapping("/home")
	public String home(Model model) {
		return "home";
	}

	@Override
	protected String getHeaderTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}
