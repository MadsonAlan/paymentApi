package com.example.paymentApi.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.paymentApi.database.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
  
}
