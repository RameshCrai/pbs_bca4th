package com.pbt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "User_Profile")
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long profileID;
	private byte[] image[];
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User userProfile;

	public Profile() {
		super();
	}

	public Profile(Long profileID, byte[][] image, User userProfile) {
		super();
		this.profileID = profileID;
		this.image = image;
		this.userProfile = userProfile;
	}

	public Long getProfileID() {
		return profileID;
	}

	public void setProfileID(Long profileID) {
		this.profileID = profileID;
	}

	public byte[][] getImage() {
		return image;
	}

	public void setImage(byte[][] image) {
		this.image = image;
	}

	public User getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(User userProfile) {
		this.userProfile = userProfile;
	}
	
	
	
	

}
