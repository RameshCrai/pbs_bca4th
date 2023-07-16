package com.pbt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/")
	public String Welcome() {
		
		return "index";
	}
	
	@GetMapping("/***")
	public String pageInvalid() {
		return "Layout/pagenotfound";		
	}

}
