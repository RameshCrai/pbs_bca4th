package com.pbt.Model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

import javax.validation.constraints.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String userID;

	@NotBlank(message = "FirstName is Required")
	private String fName;
	private String mName;
	@NotBlank(message = "LastName is Required")
	private String lName;	

	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate Dob;

	@Column(unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+$")
	private String Email;

	@Size(min = 0, max = 10)
	@Pattern(regexp = "(^$|[0-9]{10})")
	@Column(unique = true)
	private String Mobile;

	@NotBlank(message = "Password Can not be Empty !!")
	@Size(min = 10, max = 12, message = "Password must be between 10  12 character !!")
	private String Password;

	private String gender;
	@AssertTrue(message = "Must be agreed terms and condition !!")
	private boolean agreement;

	private String profileImage;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<UserRole> setUserRole = new HashSet<>();

	public User() {
		super();
	}

	public User(String userID, String fName, String mName, String lName, LocalDate dob, String email, String mobile,
			String password, String gender, boolean agreement, String profileImage, Set<UserRole> setUserRole) {
		super();
		this.userID = userID;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		Dob = dob;
		Email = email;
		Mobile = mobile;
		Password = password;
		this.gender = gender;
		this.agreement = agreement;
		this.profileImage = profileImage;
		this.setUserRole = setUserRole;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public LocalDate getDob() {
		return Dob;
	}

	public void setDob(LocalDate dob) {
		Dob = dob;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
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

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Set<UserRole> getSetUserRole() {
		return setUserRole;
	}

	public void setSetUserRole(Set<UserRole> setUserRole) {
		this.setUserRole = setUserRole;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", Dob=" + Dob
				+ ", Email=" + Email + ", Mobile=" + Mobile + ", Password=" + Password + ", gender=" + gender
				+ ", agreement=" + agreement + ", profileImage=" + profileImage + ", setUserRole=" + setUserRole + "]";
	}

}
