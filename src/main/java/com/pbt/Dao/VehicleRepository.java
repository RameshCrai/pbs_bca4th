package com.pbt.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbt.Model.User;
import com.pbt.Model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

   List<Vehicle> findByUser(User user);
    

   
}

