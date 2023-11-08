package com.pbt.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbt.Model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
