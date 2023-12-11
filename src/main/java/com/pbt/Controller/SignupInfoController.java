package com.pbt.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pbt.Dao.UserRepository;
import com.pbt.Entities.User;
import com.pbt.ExceptionHandler.MessageMaster;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/pbt")
public class SignupInfoController {
	private static final String UPLOAD_DIRECTORY = "src/main/resources/static/profile";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailsenderService Emailsend;

	@GetMapping("/signup")
	public String SingupInformation(Model model) {
		
		model.addAttribute("title", "Signup Form !");
		return "Layout/Signup";
	}

	@PostMapping("/signup_user")
	public String SingupUserDemographicInformation(@ModelAttribute User user,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement,
			@RequestParam("image") MultipartFile file, Model model, HttpSession session) {


		try {

			if (!file.isEmpty()) {

				StringBuilder fileNames = new StringBuilder();

				Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
				fileNames.append(file.getOriginalFilename());

				// Save the file to the upload directory
				Files.write(fileNameAndPath, file.getBytes());

				String profileName = file.getOriginalFilename();

				user.setProfile(profileName);

				model.addAttribute("msg", "Uploaded images: " + fileNames.toString());

			} else {
				user.setProfile("default.png");
			}

			if (!agreement) {

				throw new Exception("You have not agreed the Terms and conditions ??");
			}

			User local = this.userRepository.findByEmail(user.getEmail());
			if (local == null) {

				String password = user.getPassword();
				String Encryptpass = DigestUtils.md5Hex(password);
				user.setPassword(Encryptpass);

//				user.setUserrolename("NORMAL");

				this.userRepository.save(user);

//				Email send to user 
				session.setAttribute("mesg", new MessageMaster("Signup Successfully ", "alert-success"));
				this.Emailsend.sendEmail(user.getEmail(), "PBTS Singup successfuly ",
						"PBTS parking booking ticketing system is a park book system ");

				return "redirect:/pbt/signup";

			} else {
				session.setAttribute("mesg",
						new MessageMaster("Email Is Already over there please User other Email ??? ", "alert-danger"));
				return "redirect:/pbt/signup";
			}

		} catch (DuplicateKeyException e) {
			session.setAttribute("mesg",
					new MessageMaster("Email is already in use. Please use another email.", "alert-danger"));
			return "redirect:/pbt/signup";
		} catch (Exception e) {
			session.setAttribute("mesg", new MessageMaster("Something went wrong: " + e.getMessage(), "alert-danger"));
			return "redirect:/pbt/signup";
		}

	}

	    
}
