package com.u1.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginPageController {
	
	@RequestMapping("/login")
	protected String loginPage(){
		//nothing to do

		return "login";
	}
}
