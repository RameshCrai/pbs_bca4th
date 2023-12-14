package com.pbt.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbt.Dao.GoogleMapRepository;
import com.pbt.Dao.ParkinglocationRepository;
import com.pbt.Dao.PaymentRepository;
import com.pbt.Dao.ServiceofpbtRepository;
import com.pbt.Dao.UserRepository;
import com.pbt.Dao.VehicleRepository;
import com.pbt.Entities.GoogleMap;
import com.pbt.Entities.ParkingLocation;
import com.pbt.Entities.Payment;
import com.pbt.Entities.Services;
import com.pbt.Entities.User;
import com.pbt.Entities.Vehicle;
import com.pbt.ExceptionHandler.MessageMaster;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/pbt")
public class ServicesController {

	@Autowired
	private ParkinglocationRepository parkinglocationRepo;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private ServiceofpbtRepository serviceRepo;

	@Autowired
	private VehicleRepository vehicleRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private GoogleMapRepository GoogleRepo;

	@Autowired
	private EmailsenderService emailSend;

//	paytopark  page
	@GetMapping("/paytopark_service")
	public String getPayToPark(Model model1) {

		model1.addAttribute("title", "PayToPark Service");

		return "pages/Services/Paytopark";
	}

//	official page
	@GetMapping("/Official_service")
	public String getOfficial(Model model2) {

		model2.addAttribute("title", "Official Service");

		return "pages/Services/Official";
	}

//subscription page
	@GetMapping("/Subscription_Service")
	public String getSubscription(Model model3) {

		model3.addAttribute("title", "Subscription Service");

		return "pages/Services/Subscription";
	}

	private boolean sessionTimecounter = false;

//	Admin Dashboard page
	@GetMapping("/systemdashboard")
	public String getSystemDashboardPage(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {

			return "Layout/Login";
		}

//		session Encounter
		if (this.sessionTimecounter) {
			session.setMaxInactiveInterval(10);

			model.addAttribute("redirectScript",
					"setTimeout(function(){ window.location.href = '/pbt/login'; }, 10000);");

		}
		int totalVehicle = 0;
		int car = 0;
		int bike = 0;
		int absoAvailable = 0;

		User userinfo = this.userRepo.findByEmail(user.getEmail());
		if (userinfo != null) {
			model.addAttribute("user", userinfo);
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

		return "pages/Dashboard/SystemDashboard";
	}

//	Normal Dashboard page
	@GetMapping("/normalDashboard")
	public String getNormalDashboardPage(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {

			return "Layout/Login";
		}

//		session Encounter
		if (this.sessionTimecounter) {
			session.setMaxInactiveInterval(10);

			model.addAttribute("redirectScript",
					"setTimeout(function(){ window.location.href = '/pbt/login'; }, 10000);");

		}

//		count vehicle
		int totalVehicle = 0;
		int car = 0;
		int bike = 0;
		int absoAvailable = 0;

		User userinfo = this.userRepo.findByEmail(user.getEmail());
		if (userinfo != null) {
			model.addAttribute("user", userinfo);
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

//		profile fetch 
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

//		fetch services list 

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

		return "pages/Dashboard/NormalDashboard";
	}

//	save Services 
	@PostMapping("/save_services")
	@Transactional
	public String saveServices(@Validated @ModelAttribute Services service, @ModelAttribute Vehicle vehicle,
			@ModelAttribute ParkingLocation plocation, @ModelAttribute Payment payment,
			@ModelAttribute GoogleMap googleMap, HttpSession session, Model model) {

		try {
			User user = (User) session.getAttribute("user");

			if (user != null) {
				String email = (String) user.getEmail();
				System.out.println("User email = " + email);

				User userinfo = this.userRepo.findByEmail(email);
				if (userinfo != null) {
					System.out.println("user details " + userinfo.getFname());

					userinfo.getChoseService().add(service);
					service.setUser(userinfo);

					userinfo.getHasVehicle().add(vehicle);
					vehicle.setUser(userinfo);

					service.getHasPayment().add(payment);
					payment.setService(service);

					payment.getPaidPayment().add(plocation);
					plocation.setPayment(payment);

					payment.getSetmapforpark().add(googleMap);
					googleMap.setPayment(payment);

					vehicle.getSetMap().add(googleMap);
					googleMap.setVehicle(vehicle);

					vehicle.getParkAtParkingLocation().add(plocation);
					plocation.setVehicle(vehicle);

					this.serviceRepo.save(service);
					this.vehicleRepo.save(vehicle);

					this.paymentRepo.save(payment);
					this.parkinglocationRepo.save(plocation);

					this.GoogleRepo.save(googleMap);

					this.emailSend.sendEmail(email, "PBTS service ",
							"pbts service have been verified " + service.getServiceType());

					session.setAttribute("mes",
							new MessageMaster("Parking Booking Ticketing System Applied Succesfully", "alert-success"));

					this.sessionTimecounter = true;

					return "redirect:/pbt/systemdashboard";
				}
			} else {
				session.setAttribute("mes",
						new MessageMaster("Parking Booking Ticketing System ERROR", "alert-danger"));
				System.out.println("user isEmpty ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An exception occurred");
		}

		return "redirect:/pbt/systemdashboard";
	}

//	save Normal Services 
	@PostMapping("/normal_save_services")
	@Transactional
	public String saveNormalServices(@Validated @ModelAttribute Services service, @ModelAttribute Vehicle vehicle,
			@ModelAttribute ParkingLocation plocation, @ModelAttribute Payment payment,
			@ModelAttribute GoogleMap googleMap, HttpSession session, Model model) {

		try {
			User user = (User) session.getAttribute("user");

			if (user != null) {
				String email = (String) user.getEmail();
				System.out.println("User email = " + email);

				User userinfo = this.userRepo.findByEmail(email);
				if (userinfo != null) {
					System.out.println("user details " + userinfo.getFname());

					userinfo.getChoseService().add(service);
					service.setUser(userinfo);

					userinfo.getHasVehicle().add(vehicle);
					vehicle.setUser(userinfo);

					service.getHasPayment().add(payment);
					payment.setService(service);

					payment.getPaidPayment().add(plocation);
					plocation.setPayment(payment);

					payment.getSetmapforpark().add(googleMap);
					googleMap.setPayment(payment);

					vehicle.getSetMap().add(googleMap);
					googleMap.setVehicle(vehicle);

					vehicle.getParkAtParkingLocation().add(plocation);
					plocation.setVehicle(vehicle);

					this.serviceRepo.save(service);
					this.vehicleRepo.save(vehicle);

					this.paymentRepo.save(payment);
					this.parkinglocationRepo.save(plocation);

					this.GoogleRepo.save(googleMap);

					this.emailSend.sendEmail(email, "PBTS service ",
							"pbts service have been verified " + service.getServiceType());

					session.setAttribute("mes",
							new MessageMaster("Parking Booking Ticketing System Applied Succesfully", "alert-success"));

					this.sessionTimecounter = true;

					return "redirect:/pbt/normalDashboard";
				}
			} else {
				session.setAttribute("mes",
						new MessageMaster("Parking Booking Ticketing System ERROR", "alert-danger"));
				System.out.println("user isEmpty ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An exception occurred");
		}

		return "redirect:/pbt/normalDashboard";
	}

//	get services details 
	@GetMapping("fetch_services_details")
	public String fetchServices(HttpSession session, Model model) throws Exception {
		User user = (User) session.getAttribute("user");
		if (user == null) {

			return "Layout/Login";
		} else {
			User userinfo = this.userRepo.findByEmail(user.getEmail());

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

			return "pages/Dashboard/SuccessFile";
		}
	}

//	get Payment history 
	@GetMapping("/payments/{uid}")
	public String paymentHistory(@PathVariable("uid") Long uid, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {

			return "Layout/Login";
		}
		User userinfo = this.userRepo.findByUserid(uid);
		if (userinfo != null) {
			List<Services> serviceinfo = this.serviceRepo.findByUser(userinfo);

			int amount = 0;
			int times = 0;
			serviceinfo.forEach(se -> {
				List<Payment> listofPayment = this.paymentRepo.findByService(se);

//				amount = this.paymentRepo.findByAmount();
//				times++;

				model.addAttribute("paymentlist", listofPayment);
			});

			List<Payment> listofPayment = new ArrayList<>();
			for (Services serve : serviceinfo) {

				listofPayment.addAll(this.paymentRepo.findByService(serve));
			}

			model.addAttribute("paymentlist", listofPayment);

		} else {
			System.out.println("payment isInvalid ??");
		}

		return "pages/Dashboard/PaymentsHistory";
	}

}
