package com.pbt.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbt.Entities.Services;
import com.pbt.Entities.User;


@Repository
public interface ServiceofpbtRepository extends JpaRepository<Services, Long>{

	List<Services> findByUser(User user);
	
	public Services findByServiceID(Long id);

}
