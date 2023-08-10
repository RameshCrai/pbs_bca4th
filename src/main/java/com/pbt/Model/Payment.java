package com.pbt.Model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentID;
	private String cost;
	private String duration;
	private String discount;
	private LocalDate paymentDate;
	
	@OneToMany(mappedBy = "payment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ParkingLocation> paidPayment = new LinkedList<ParkingLocation>();

	public Payment() {
		super();
	}

	

	public Payment(Long paymentID, String cost, String duration, String discount, LocalDate paymentDate,
			List<ParkingLocation> paidPayment) {
		super();
		this.paymentID = paymentID;
		this.cost = cost;
		this.duration = duration;
		this.discount = discount;
		this.paymentDate = paymentDate;
		this.paidPayment = paidPayment;
	}



	public Long getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(Long paymentID) {
		this.paymentID = paymentID;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	

	public LocalDate getPaymentDate() {
		return paymentDate;
	}



	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}



	public List<ParkingLocation> getPaidPayment() {
		return paidPayment;
	}

	public void setPaidPayment(List<ParkingLocation> paidPayment) {
		this.paidPayment = paidPayment;
	}
	
	
	

}
