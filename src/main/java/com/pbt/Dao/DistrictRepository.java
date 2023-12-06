package com.pbt.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbt.Entities.District;
import com.pbt.Entities.Province;

import java.util.List;


@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{
	
	District findByDistrictID(Long did);
	
	District findByDistrictName(String dname);
	
	List<District> findByProvince(Province province);

}
