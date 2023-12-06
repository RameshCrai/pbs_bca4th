package com.pbt.Entities;

import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Province {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stateID;
	@SuppressWarnings("deprecation")
	@Column(length = 100, unique = true)
	@NotNull
	private String state;
	private String country;
	
	@OneToMany(mappedBy = "province",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<District> stateHash = new HashSet<District>();

	public Long getStateID() {
		return stateID;
	}

	public void setStateID(Long stateID) {
		this.stateID = stateID;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<District> getStateHash() {
		return stateHash;
	}

	public void setStateHash(Set<District> stateHash) {
		this.stateHash = stateHash;
	}
	
	

}
