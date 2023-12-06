package com.pbt.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbt.Entities.ParkingLocation;
import com.pbt.Entities.Vehicle;

@Repository
public interface ParkinglocationRepository extends JpaRepository<ParkingLocation, Long>{
	
	List<ParkingLocation> findByVehicle(Vehicle vehicle);


}
