package com.pbt.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbt.Model.ParkingLocation;
import com.pbt.Model.Vehicle;

public interface ParkinglocationRepository extends JpaRepository<ParkingLocation, Long>{
	
	List<ParkingLocation> findByVehicle(Vehicle vehicle);


}
