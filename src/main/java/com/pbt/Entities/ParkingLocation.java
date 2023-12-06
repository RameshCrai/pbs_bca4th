package com.pbt.Entities;


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
	private String district;
	private String municipality;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Vehicle vehicle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Payment payment;
	
	public ParkingLocation() {
		super();
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

	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}



	public String getMunicipality() {
		return municipality;
	}



	public void setMunicipality(String municipality) {
		this.municipality = municipality;
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
