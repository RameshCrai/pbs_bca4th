package com.pbt.Model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;




@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userID;


	private String fname;
	private String mName;
	private String lname;	
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	@Column(unique = true)
	private String email;


	@Column(unique = true)
	private String mobile;


	private String password;

	private String gender;
	
	private boolean agreement;

	
	@Column(length = 500)
	private String profile;
	
	private String userrolename;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Vehicle> hasVehicle = new LinkedList<Vehicle>();



	public User() {
		super();
	}



	


	public User(Long userID, String fname, String mName, String lname, LocalDate dob, String email, String mobile,
			String password, String gender, boolean agreement, String profile, String userrolename,
			List<Vehicle> hasVehicle) {
		super();
		this.userID = userID;
		this.fname = fname;
		this.mName = mName;
		this.lname = lname;
		this.dob = dob;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.gender = gender;
		this.agreement = agreement;
		this.profile = profile;
		this.userrolename = userrolename;
		this.hasVehicle = hasVehicle;
	}







	public Long getUserID() {
		return userID;
	}



	public void setUserID(Long userID) {
		this.userID = userID;
	}



	public String getFname() {
		return fname;
	}



	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getmName() {
		return mName;
	}



	public void setmName(String mName) {
		this.mName = mName;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



	public LocalDate getDob() {
		return dob;
	}



	public void setDob(LocalDate dob) {
		this.dob = dob;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public boolean isAgreement() {
		return agreement;
	}



	public void setAgreement(boolean agreement) {
		this.agreement = agreement;
	}



	public String getProfile() {
		return profile;
	}



	public void setProfile(String profile) {
		this.profile = profile;
	}






	public String getUserrolename() {
		return userrolename;
	}






	public void setUserrolename(String userrolename) {
		this.userrolename = userrolename;
	}






	public List<Vehicle> getHasVehicle() {
		return hasVehicle;
	}






	public void setHasVehicle(List<Vehicle> hasVehicle) {
		this.hasVehicle = hasVehicle;
	}



	



}
