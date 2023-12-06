package com.pbt.Entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class District {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long districtID;
	@Column(length = 200, unique = true)
	private String districtName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Province province;
	
	@OneToMany(mappedBy = "district",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<MunicipalityName> hasMunicipality = new HashSet<MunicipalityName>();

	public Long getDistrictID() {
		return districtID;
	}

	public void setDistrictID(Long districtID) {
		this.districtID = districtID;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Set<MunicipalityName> getHasMunicipality() {
		return hasMunicipality;
	}

	public void setHasMunicipality(Set<MunicipalityName> hasMunicipality) {
		this.hasMunicipality = hasMunicipality;
	}
	
   
	
	
}
