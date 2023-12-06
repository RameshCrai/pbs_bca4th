package com.pbt.Dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pbt.Entities.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String passw);

	User findByEmailAndMobile(String email, String mobile);	
	
	User findByUserid(Long uid);
	
//	@Query("select u From User u WHERE u.Email =:e and u.Password=:p")
//	public User getLogin(@Param("e") String Email, @Param("p") String Password);
	
	

	
}
