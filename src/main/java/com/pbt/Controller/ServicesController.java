package com.pbt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pbt")
public class ServicesController {
	
	@GetMapping("/paytopark_service")
	public String getPayToPark(Model model1) {	
		
		model1.addAttribute("title", "PayToPark Service");
		
		
		return "pages/Services/Paytopark";
	}

	@GetMapping("/Official_service")
	public String getOfficial(Model model2) {
		
		model2.addAttribute("title", "Official Service");
		
		
		return "pages/Services/Official";
	}
	
	@GetMapping("/Subscription_Service")
	public String getSubscription(Model model3) {	
		
		model3.addAttribute("title", "Subscription Service");
		
		return "pages/Services/Subscription";
	}
}
