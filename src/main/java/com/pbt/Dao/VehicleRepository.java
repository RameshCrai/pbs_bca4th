package com.pbt.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbt.Entities.User;
import com.pbt.Entities.Vehicle;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

   List<Vehicle> findByUser(User user);
      
}

