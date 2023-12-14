package com.pbt.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "googlemap")
public class GoogleMap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long googleMapID;
	private double latitude;
    private double longitude;
    
    @ManyToOne(fetch = FetchType.LAZY)
	private Vehicle vehicle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Payment payment;
	
	
    
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
	public long getGoogleMapID() {
		return googleMapID;
	}
	public void setGoogleMapID(long googleMapID) {
		this.googleMapID = googleMapID;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
    
    
	

}
