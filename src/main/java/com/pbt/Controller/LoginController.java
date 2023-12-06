package com.pbt.Controller;

import java.io.File;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pbt.Dao.UserRepository;
import com.pbt.Dao.VehicleRepository;
import com.pbt.Entities.User;
import com.pbt.Entities.Vehicle;
import com.pbt.ExceptionHandler.MessageMaster;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pbt")
public class LoginController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private VehicleRepository vehicleRepo;

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
					session.setAttribute("user", user);

					int totalVehicle = 0;
					int car = 0;
					int bike = 0;
					int absoAvailable = 0;

					User userinfo = this.userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
					if (userinfo != null) {
						List<Vehicle> vehicledata = this.vehicleRepo.findByUser(userinfo);
						for (Vehicle vt : vehicledata) {
							totalVehicle++;

							String vehicleType = vt.getVehicleType();
							if ("bike".equalsIgnoreCase(vehicleType)) {
								bike++;
							} else {
								car++;
							}
						}
						int available = 20 - totalVehicle;
						if (available != -1) {
							absoAvailable = Math.abs(available);
							model.addAttribute("available", absoAvailable);
						} else {
							absoAvailable = 0;
							model.addAttribute("available", absoAvailable);
						}

						model.addAttribute("car", car);
						model.addAttribute("bike", bike);

						model.addAttribute("totalVehicle", totalVehicle);

					}

					model.addAttribute("title", "System Dashboard ");
					model.addAttribute("user", userNameAndPassword);

					File profile = new File("src/main/resources/static/profile/");
					String pname = userNameAndPassword.getProfile();

					if (profile.exists() && profile.isDirectory()) {
						String parentPath = profile.getParent();

						String[] fileNames = profile.list();

						if (fileNames != null && fileNames.length > 0) {
							for (String fileName : fileNames) {
								if (fileName.equalsIgnoreCase(pname)) {
									System.out.println("File name: " + fileName);
									model.addAttribute("imgProfile", fileName);
								} else {
									System.out.println("profile name Not match ?");
								}
							}
						} else {
							System.out.println("No files found in the directory.");
						}
					} else {
						System.out.println("Directory does not exist or is not a directory.");
					}

					session.setAttribute("log", new MessageMaster("Login Successfully ??", "alert-success"));
					return "/pages/Dashboard/SystemDashboard";
				} else if ("NORMAL".equals(userRole)) {
					session.setAttribute("user", user);
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
