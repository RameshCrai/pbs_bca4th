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

	@SuppressWarnings("unused")
	@PostMapping("/login_username_password")
	public String getLoginuserDemographic(@ModelAttribute User user, HttpSession session, Model model) {
		user.setPassword(DigestUtils.md5Hex(user.getPassword().getBytes()));

		User userNameAndPassword = this.userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());

		if (userNameAndPassword == null) {
			model.addAttribute("title", "Not Found !!");
			session.setAttribute("log", new MessageMaster("Username and Password not Match ??", "alert-danger"));
			return "Layout/Login";
		} else {
			String userRole = userNameAndPassword.getUserrolename();

			if (userRole == null) {
				session.setAttribute("log", new MessageMaster("Invalid User Role ??", "alert-danger"));
				return "Layout/Login";
			} else {
				if ("ADMIN".equals(userRole)) {
					model.addAttribute("title", "System Dashboard ");
					model.addAttribute("user", userNameAndPassword);
					session.setAttribute("log", new MessageMaster("Login Successfully ??", "alert-success"));
					return "/pages/Dashboard/SystemDashboard";
				} else if ("NORMAL".equals(userRole)) {
					model.addAttribute("title", "Normal Dashboard ");
					
					model.addAttribute("user", userNameAndPassword);
					return "/pages/Dashboard/NormalDashboard";
				} else {
					session.setAttribute("log", new MessageMaster("Maintain Authentication ??", "alert-danger"));
				}
			}
		}

		return "Layout/Login";
	}


	@GetMapping("/normal_dashboard")
	public String getNormalDashboardSystem() {

		return "/pages/Dashboard/NormalDashboard";
	}
	



	@GetMapping("/logout")
	public String getLogout(HttpSession session) {

		session.invalidate();

		return "index";
	}

}
