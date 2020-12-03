package com.izasoft.jcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeContoller extends JCartAdminBaseContoller {
	@RequestMapping("/home")
	public String home(Model model) {
		return "home";
	}
}
