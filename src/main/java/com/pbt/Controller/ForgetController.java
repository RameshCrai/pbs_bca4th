package com.pbt.Controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbt.Dao.UserRepository;
import com.pbt.Entities.User;
import com.pbt.ExceptionHandler.MessageMaster;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pbt")
public class ForgetController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/forgott_password")
	public String passwordForget() {

		return "Layout/Forget";
	}

	@PostMapping("/check_email_mobile")
	public String sendEmailAndMobile(@ModelAttribute User user, HttpSession session) {

		User userinfo = this.userRepo.findByEmailAndMobile(user.getEmail(), user.getMobile());
		if (userinfo == null) {
			System.out.println("dont match ");
		} else {
			String oldPassword = userinfo.getPassword();

			System.out.println("old pass word " + oldPassword);

		}
		return "Layout/newPassword";
	}

	@PostMapping("/Password_change")
	public String PasswordChange(@ModelAttribute User user, @RequestParam("newPassword") String newPassword,
			HttpSession session) {

		User userinfo = this.userRepo.findByEmail(user.getEmail());
		if (userinfo != null) {

			System.out.println("newPassw ===== " + newPassword);

			System.out.println("user === " + user.getEmail());

			String password = newPassword;
			String Encryptpass = DigestUtils.md5Hex(password);

			userinfo.setPassword(Encryptpass);

			this.userRepo.save(userinfo);

		} else {
			System.out.println("Email isEmpty");
		}

		return "Layout/Login";
	}

}
