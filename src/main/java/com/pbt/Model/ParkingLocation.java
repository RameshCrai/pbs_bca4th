package com.pbt.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class ParkingLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long parkingID;
	private String state;
	private String street;
	private String city;
	private String spot;
	private LocalDate enterDate;
	private LocalDate exitDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Vehicle vehicle;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Payment payment;
	
	public ParkingLocation() {
		super();
	}

	

	public ParkingLocation(long parkingID, String state, String street, String city, String spot, LocalDate enterDate,
			LocalDate exitDate, Vehicle vehicle, Payment payment) {
		super();
		this.parkingID = parkingID;
		this.state = state;
		this.street = street;
		this.city = city;
		this.spot = spot;
		this.enterDate = enterDate;
		this.exitDate = exitDate;
		this.vehicle = vehicle;
		this.payment = payment;
	}



	public long getParkingID() {
		return parkingID;
	}

	public void setParkingID(long parkingID) {
		this.parkingID = parkingID;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSpot() {
		return spot;
	}

	public void setSpot(String spot) {
		this.spot = spot;
	}


	public LocalDate getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(LocalDate enterDate) {
		this.enterDate = enterDate;
	}

	public LocalDate getExitDate() {
		return exitDate;
	}

	public void setExitDate(LocalDate exitDate) {
		this.exitDate = exitDate;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
	
	
	
	
	
}
