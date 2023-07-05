package com.pbt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userroleID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;

	public UserRole() {
		super();
		
	}

	public UserRole(Long userroleID, User user, Role role) {
		super();
		this.userroleID = userroleID;
		this.user = user;
		this.role = role;
	}

	public Long getUserroleID() {
		return userroleID;
	}

	public void setUserroleID(Long userroleID) {
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
