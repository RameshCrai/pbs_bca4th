package com.pbt.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbt.Model.Services;
import com.pbt.Model.User;


public interface ServiceofpbtRepository extends JpaRepository<Services, Long>{

	List<Services> findByUser(User user);

}
