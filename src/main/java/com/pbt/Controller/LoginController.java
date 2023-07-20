package com.pbt.Controller;



import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pbt.Dao.UserRepository;
import com.pbt.ExceptionHandler.MessageMaster;
import com.pbt.Model.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pbt")
public class LoginController {
	
	@Autowired
	private UserRepository userRepo;
	
	

	@GetMapping("/login")
	public String userSignup(Model model) {
		model.addAttribute("title", "Login Component");         
		
		return "Layout/Login";
	}
	
	
	@PostMapping("/login_username_password")
	public String getLoginuserDemographic(@ModelAttribute User user, HttpSession session, Model model) {
		
		
		user.setPassword(DigestUtils.md5Hex(user.getPassword().getBytes()));
		
		User emailAnpassword = this.userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if(emailAnpassword == null) {
			
			session.setAttribute("logfailed", new MessageMaster("Username and Password not Match ??", "alert-danger"));
			
			return "Layout/Login";
			
		}else {
			
			
			model.addAttribute("title","System Dashboard ");
			
			session.setAttribute("logsuccess", new MessageMaster("Login Successfully ??","alert-success"));
			
			
			
			return "/pages/Dashboard/SystemDashboard";
		}
		
		
	}
	
	
	

}
