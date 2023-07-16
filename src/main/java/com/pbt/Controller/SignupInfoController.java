package com.pbt.Controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbt.Dao.UserRepository;
import com.pbt.ExceptionHandler.MessageMaster;
import com.pbt.Model.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pbt")
public class SignupInfoController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/signup")
	public String SingupInformation(Model model) {

		model.addAttribute("title", "Signup Form !");

		model.addAttribute("user", new User());

		return "Layout/Signup";
	}

//	User Demographic Info 
	@RequestMapping(value = "/signup_user", method = RequestMethod.POST)
	public String SingupUserDemographicInformation(@Valid @ModelAttribute("user") User user, BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {

		try {

			if (!agreement) {
		
				throw new Exception("You have not agreed the Terms and conditions ??");


			}

			if (result.hasErrors()) {
				System.out.println("Form binding error ");
				String er = result.toString();
				System.out.println("ERROR=" + er);			

				return "Layout/Signup";
			}
			
			
			String password = user.getPassword();
			String Encryptpass = DigestUtils.md2Hex(password);
			user.setPassword(Encryptpass);
			
			
			user.setProfileImage("default.png");

			User u = this.userRepository.save(user);
			System.out.printf("user details = ", u);

		
			session.setAttribute("mesg", new MessageMaster("Signup Successfully ", "alert-success"));

			return "Layout/Signup";

		} catch (Exception e) {
			System.out.println("Terms and condition isEmpty ??");
			e.printStackTrace();
			
			session.setAttribute("mesg", new MessageMaster("Something went wrong The terms and Conditions "+agreement, "alert-danger"));

			return "Layout/Signup";

		}

	}

}
