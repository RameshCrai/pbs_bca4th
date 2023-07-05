package com.pbt.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;
	
	private String fName;
	private String mName;
	private String lName;
	@Column(unique = true)
	
	private String Email;
	@Column(unique = true, length = 10)
	private String Mobile;
	@Column(length = 12)
	private String Password;
	private String gender;
	private boolean isActive = true;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<UserRole> setUserRole = new HashSet<>();
	
	@OneToMany(mappedBy = "userProfile",fetch = FetchType.LAZY)
	private List<Profile> setProfile = new  LinkedList<>();

	public User() {
		super();
	}

//	Parameter Object of User
	public User(Long userID, String fName, String mName, String lName, String email, String mobile, String password,
			String gender, boolean isActive, Set<UserRole> setUserRole, List<Profile> setProfile) {
		super();
		this.userID = userID;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		Email = email;
		Mobile = mobile;
		Password = password;
		this.gender = gender;
		this.isActive = isActive;
		this.setUserRole = setUserRole;
		this.setProfile = setProfile;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<UserRole> getSetRole() {
		return setUserRole;
	}

	public void setSetRole(Set<UserRole> setRole) {
		this.setUserRole = setRole;
	}

	public List<Profile> getSetProfile() {
		return setProfile;
	}

	public void setSetProfile(List<Profile> setProfile) {
		this.setProfile = setProfile;
	}
	
	
	
	
	
}
