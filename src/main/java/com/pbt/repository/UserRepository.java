package com.pbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.pbt.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	 
	
	public User findEmailAndPassword(String email, String pass);
	
}
