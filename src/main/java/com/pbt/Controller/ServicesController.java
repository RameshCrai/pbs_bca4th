package com.pbt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbt.Dao.ParkinglocationRepository;
import com.pbt.Dao.PaymentRepository;
import com.pbt.Dao.ServiceofpbtRepository;
import com.pbt.Dao.UserRepository;
import com.pbt.Dao.VehicleRepository;
import com.pbt.Model.ParkingLocation;
import com.pbt.Model.Payment;
import com.pbt.Model.Services;
import com.pbt.Model.User;
import com.pbt.Model.Vehicle;

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

//	save Services 
	@PostMapping("/save_services")
	@Transactional
	public String saveServices(@ModelAttribute Services service, @ModelAttribute Vehicle vehicle,
			@ModelAttribute ParkingLocation plocation, @ModelAttribute Payment payment, HttpSession session) {

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
					
					vehicle.getParkAtParkingLocation().add(plocation);
					plocation.setVehicle(vehicle);

					this.serviceRepo.save(service);
					this.vehicleRepo.save(vehicle);
					

					this.paymentRepo.save(payment);					
					this.parkinglocationRepo.save(plocation);
				}
			} else {
				System.out.println("user isEmpty ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An exception occurred");
		}

		return "pages/Dashboard/SuccessFile";
	}

}
