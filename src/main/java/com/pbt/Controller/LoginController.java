package com.pbt.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pbt.Dao.ParkinglocationRepository;
import com.pbt.Dao.PaymentRepository;
import com.pbt.Dao.ServiceofpbtRepository;
import com.pbt.Dao.UserRepository;
import com.pbt.Dao.VehicleRepository;
import com.pbt.Entities.ParkingLocation;
import com.pbt.Entities.Payment;
import com.pbt.Entities.Services;
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
	private ParkinglocationRepository parkinglocationRepo;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private ServiceofpbtRepository serviceRepo;

	@Autowired
	private VehicleRepository vehicleRepo;

	@Autowired
	private EmailsenderService emailSend;

//	login page
	@GetMapping("/login")
	public String userSignup(Model model) {
		model.addAttribute("title", "Login Component");

		return "Layout/Login";
	}

//	Admin dashboard
	@GetMapping("/system_dashboard")
	public String getAdminDashboardSystem(HttpSession session, Model model) {
		User usersession = (User) session.getAttribute("user");

		User userinfo = this.userRepo.findByEmail(usersession.getEmail());

		if (userinfo == null) {
			System.out.println("User isEmpty ??");
		} else {

			model.addAttribute("user", userinfo);

			int totalVehicle = 0;
			int car = 0;
			int bike = 0;
			int absoAvailable = 0;

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

			File profile = new File("src/main/resources/static/profile/");
			String pname = userinfo.getProfile();

			if (profile.exists() && profile.isDirectory()) {
				String[] fileNames = profile.list();

				if (fileNames != null && fileNames.length > 0) {
					boolean found = false;

					for (String fileName : fileNames) {
						if (fileName.equalsIgnoreCase(pname)) {
							System.out.println("File name: " + fileName);
							model.addAttribute("imgProfile", fileName);
							found = true;
							break;
						}
					}

					if (!found) {
						System.out.println("Profile name not found.");

					}
				} else {
					System.out.println("No files found in the directory.");
				}
			} else {
				System.out.println("Directory does not exist or is not a directory.");
			}
		}

		return "/pages/Dashboard/SystemDashboard";
	}

//	normal dashboard
	@GetMapping("/normal_dashboard")
	public String getNormalDashboardSystem(HttpSession session, Model model) {
		User usersession = (User) session.getAttribute("user");

		User userinfo = this.userRepo.findByEmail(usersession.getEmail());

		if (userinfo == null) {
			System.out.println("User isEmpty ??");
		} else {
			model.addAttribute("user", userinfo);

//			space count
			int totalVehicle = 0;
			int car = 0;
			int bike = 0;
			int absoAvailable = 0;

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

//			Profile fetch 

			File profile = new File("src/main/resources/static/profile/");
			String pname = userinfo.getProfile();

			if (profile.exists() && profile.isDirectory()) {
				String[] fileNames = profile.list();

				if (fileNames != null && fileNames.length > 0) {
					boolean found = false;

					for (String fileName : fileNames) {
						if (fileName.equalsIgnoreCase(pname)) {
							System.out.println("File name: " + fileName);
							model.addAttribute("imgProfile", fileName);
							found = true;
							break;
						}
					}

					if (!found) {
						System.out.println("Profile name not found.");

					}
				} else {
					System.out.println("No files found in the directory.");
				}
			} else {
				System.out.println("Directory does not exist or is not a directory.");
			}

//			fetch services list 

			List<Services> services = this.serviceRepo.findByUser(userinfo);
			List<Vehicle> vehicles = this.vehicleRepo.findByUser(userinfo);

			List<ParkingLocation> locations = new ArrayList<>();

			for (Vehicle vehicle : vehicles) {
				locations.addAll(this.parkinglocationRepo.findByVehicle(vehicle));
			}

			List<Payment> payments = new LinkedList<>();
			for (Services service : services) {
				payments.addAll(this.paymentRepo.findByService(service));
			}

			model.addAttribute("serviceslist", services);
			model.addAttribute("vehiclelist", vehicles);
			model.addAttribute("locationlist", locations);
			model.addAttribute("paymentlist", payments);

		}

		return "/pages/Dashboard/NormalDashboard";
	}

//	login to normal and admin dashboard
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
				if ("ADMIN".equalsIgnoreCase(userRole)) {
					session.setAttribute("user", userNameAndPassword);

//					session.setAttribute("log", new MessageMaster("Login Successfully ??", "alert-success"));
//					this.emailSend.sendEmail("Login Successfully !", "PBTS Testing phase  for ADMIN USER!", "This is Parking Booking Ticketing System for online Booking ?");
					return "redirect:/pbt/system_dashboard";
				} else if ("NORMAL".equalsIgnoreCase(userRole)) {
					session.setAttribute("user", userNameAndPassword);
					model.addAttribute("title", "Normal Dashboard ");

//					this.emailSend.sendEmail("Login Successfully !", "PBTS Testing phase  for NORMAL USER!", "This is Parking Booking Ticketing System for online Booking ?");

					return "redirect:/pbt/normal_dashboard";
				} else {
					session.setAttribute("log", new MessageMaster("Maintain Authentication ??", "alert-danger"));
				}
			}
		}

		return "redirect:/pbt/login";
	}

//	user list
	@GetMapping("/userlist")
	public String getUsers(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {

			return "Layout/Login";
		}

		List<User> userinfo = this.userRepo.findAll();
		if (userinfo != null) {
			model.addAttribute("userlist", userinfo);
			System.out.println("user list ");

		} else {
			System.out.println("User is not availabel ?");
		}

		return "pages/Dashboard/Userlist";
	}

//	delete handle
	@GetMapping("/delete/{uid}")
	public String deleteUser(@PathVariable("uid") Long uid, HttpSession session) {
		this.userRepo.deleteById(uid);

		session.setAttribute("del", new MessageMaster("User Delete Successfully ?", "alert-danger"));

		return "redirect:/pbt/userlist";

	}

	long uidEdit = 0;

//	EDIT user 
	@GetMapping("/edituser/{uid}")
	public String editUser(@PathVariable("uid") Long uid, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {

			return "Layout/Login";
		}

		User userinfo = this.userRepo.findByUserid(uid);
		this.uidEdit = uid;
		model.addAttribute("user", userinfo);

		return "pages/Dashboard/UserEdit";
	}

//	save update data 
	@PostMapping("/update_user")
	public String updateUser(@ModelAttribute User user, HttpSession session) {

		User userinfo = this.userRepo.findByUserid(this.uidEdit);
		if (userinfo == null) {
			session.setAttribute("del", new MessageMaster("User Update Failed ?", "alert-danger"));

		} else {
			user.setUserID(this.uidEdit);
			user.setFname(userinfo.getFname());
			user.setmName(userinfo.getmName());
			user.setLname(userinfo.getLname());
			user.setDob(userinfo.getDob());
			user.setAgreement(true);
			user.setEmail(userinfo.getEmail());
			user.setPassword(userinfo.getPassword());
			user.setMobile(userinfo.getMobile());
			user.setUserrolename(userinfo.getUserrolename());			

			this.userRepo.save(user);

			session.setAttribute("del", new MessageMaster("User Update Successfully ?", "alert-success"));

		}

		return "redirect:/pbt/userlist";
	}

//	logout 
	@GetMapping("/logout")
	public String getLogout(HttpSession session) {

		session.invalidate();

		return "index";
	}

}
