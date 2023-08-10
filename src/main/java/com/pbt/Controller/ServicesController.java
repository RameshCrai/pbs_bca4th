package com.pbt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pbt/services")
public class ServicesController {
	
	@GetMapping("/paytopark_services")
	public String getPayToPark(HttpSession session, Model model) {	
		
		model.addAttribute("title", "PayToPark Service");
		
		
		return "pages/Services/Paytopark";
	}

	@GetMapping("/Official_service")
	public String getOfficial(HttpSession session, Model model) {
		
		model.addAttribute("title", "Official Service");
		
		
		return "pages/Services/Official";
	}
	
	@GetMapping("/Subscription_Services")
	public String getSubscription(HttpSession session, Model model) {	
		
		model.addAttribute("title", "Subscription Service");
		
		return "pages/Services/Subscription";
	}
}
