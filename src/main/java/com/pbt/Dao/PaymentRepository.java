package com.pbt.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbt.Entities.Payment;
import com.pbt.Entities.Services;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
   List<Payment> findByService(Services service);
   
   String findByAmount(String amt);
   
}

