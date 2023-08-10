package com.pbt.Model;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long vehicleID;
	private String vehicleType;
	private String model;
	private String LicencePlateNumber;
	private String color;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ParkingLocation> parkAtParkingLocation = new LinkedList<ParkingLocation>();

	public Vehicle() {
		super();
	}

	

	public Vehicle(long vehicleID, String vehicleType, String model,  String licencePlateNumber,
			String color, User user, List<ParkingLocation> parkAtParkingLocation) {
		super();
		this.vehicleID = vehicleID;
		this.vehicleType = vehicleType;
		this.model = model;
		LicencePlateNumber = licencePlateNumber;
		this.color = color;
		this.user = user;
		this.parkAtParkingLocation = parkAtParkingLocation;
	}



	public long getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(long vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	

	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public String getLicencePlateNumber() {
		return LicencePlateNumber;
	}



	public void setLicencePlateNumber(String licencePlateNumber) {
		LicencePlateNumber = licencePlateNumber;
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ParkingLocation> getParkAtParkingLocation() {
		return parkAtParkingLocation;
	}

	public void setParkAtParkingLocation(List<ParkingLocation> parkAtParkingLocation) {
		this.parkAtParkingLocation = parkAtParkingLocation;
	}
	
	
	

	
}
