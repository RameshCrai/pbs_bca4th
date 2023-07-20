package com.pbt.Dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pbt.Model.User;




public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);
	
	public User findByMobile(String mobile);
	
	public User findByEmailAndPassword(String email, String passw);
	
	public User findByFname(String fname);
	
//	@Query("select u From User u WHERE u.Email =:e and u.Password=:p")
//	public User getLogin(@Param("e") String Email, @Param("p") String Password);

	
}
