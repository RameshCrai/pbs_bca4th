package com.pbt.Entities;




import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class MunicipalityName {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long municipalityID;
	@SuppressWarnings("deprecation")
	@Column(length = 300,unique = true)
	@NotNull
	private String nameofMunicipality;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private District district;

	public Long getMunicipalityID() {
		return municipalityID;
	}

	public void setMunicipalityID(Long municipalityID) {
		this.municipalityID = municipalityID;
	}



	public String getNameofMunicipality() {
		return nameofMunicipality;
	}

	public void setNameofMunicipality(String nameofMunicipality) {
		this.nameofMunicipality = nameofMunicipality;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
	
	
 
}
