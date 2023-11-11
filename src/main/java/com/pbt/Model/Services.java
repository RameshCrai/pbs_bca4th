package com.pbt.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Services {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serviceID;
	private String serviceType;
	
	private LocalDate enterDate;
    private LocalDate exitDate;
	
	
	private LocalTime enterTime;
	private LocalTime exitTime;
	
	@ManyToOne
	@JoinColumn(name = "user_userid")
	private User user;

	
	@OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Payment> hasPayment = new LinkedList<Payment>();
	
	public Services() {}
	


	public Long getServiceID() {
		return serviceID;
	}

	public void setServiceID(Long serviceID) {
		this.serviceID = serviceID;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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

   

	public LocalTime getEnterTime() {
		return enterTime;
	}



	public void setEnterTime(LocalTime enterTime) {
		this.enterTime = enterTime;
	}



	public LocalTime getExitTime() {
		return exitTime;
	}



	public void setExitTime(LocalTime exitTime) {
		this.exitTime = exitTime;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Payment> getHasPayment() {
		return hasPayment;
	}

	public void setHasPayment(List<Payment> hasPayment) {
		this.hasPayment = hasPayment;
	}

	

}
