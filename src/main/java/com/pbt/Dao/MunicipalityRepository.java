package com.pbt.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbt.Entities.District;
import com.pbt.Entities.MunicipalityName;

@Repository
public interface MunicipalityRepository extends JpaRepository<MunicipalityName, Long>{
	
	MunicipalityName findByNameofMunicipality(String nm);

	List<MunicipalityName> findByDistrict(District district);
	
}
