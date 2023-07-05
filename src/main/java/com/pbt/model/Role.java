package com.pbt.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long roleID;
	private String roleName;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<UserRole> setUserRolename = new HashSet<>();

	public Role() {
		super();
		
	}

// Parameter Object of Role
	public Role(Long roleID, String roleName, Set<UserRole> setUserRole) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
		this.setUserRolename = setUserRole;
	}

	public Long getRoleID() {
		return roleID;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<UserRole> getSetUserRole() {
		return setUserRolename;
	}

	public void setSetUserRole(Set<UserRole> setUserRole) {
		this.setUserRolename = setUserRole;
	}
	
	
	
   
}
