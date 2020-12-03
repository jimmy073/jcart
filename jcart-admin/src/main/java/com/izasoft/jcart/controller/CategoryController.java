package com.izasoft.jcart.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

@Controller
@Secured("ROLE_MANAGE_CATEGORIES")
public class CategoryController extends JCartAdminBaseContoller {

}
