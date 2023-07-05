package com.pbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pbt.model.User;
import com.pbt.repository.UserRepository;


@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	

	
//	User information Register from here
	@PostMapping("/signup_user")
	public String userSignup(@ModelAttribute User user,Model model) {
		this.userRepository.save(user);
	
		model.addAttribute("title", "User Signup Successfully ?? ");
         
		
		return "signup";
	}
	
	
	
	
	
}
