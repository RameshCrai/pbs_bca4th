package com.pbt.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbt.Model.Payment;
import com.pbt.Model.Services;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
   List<Payment> findByService(Services service);
}
