package com.pbt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pbt")
public class LoginController {
	
	
//	User login from here
	@GetMapping("/login")
	public String userSignup(Model model) {
		model.addAttribute("title", "Login Component");         
		
		return "Layout/Login";
	}
	

}
