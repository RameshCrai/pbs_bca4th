package com.pbt.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String userroleID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

	public UserRole() {
		super();
		
	}

	public UserRole(String userroleID, User user, Role role) {
		super();
		this.userroleID = userroleID;
		this.user = user;
		this.role = role;
	}

	public String getUserroleID() {
		return userroleID;
	}

	public void setUserroleID(String userroleID) {
		this.userroleID = userroleID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
}
