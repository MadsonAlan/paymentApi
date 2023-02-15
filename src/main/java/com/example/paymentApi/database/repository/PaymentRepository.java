package com.example.paymentapi.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.paymentapi.database.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
  
}
