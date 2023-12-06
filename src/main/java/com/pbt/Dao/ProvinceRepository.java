package com.pbt.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbt.Entities.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long>{
	
	Province findByState(String state);
	
	Province findByStateID(Long sid);

}
