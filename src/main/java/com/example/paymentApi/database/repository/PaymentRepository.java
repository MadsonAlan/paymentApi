package com.example.paymentapi.database.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.paymentapi.database.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

  List<Payment> findAll();

  List<Payment> findByDebitCode(Integer debitCode);
  List<Payment> findByPayer(String payer);
  List<Payment> findByPaymentStatus(String paymentStatus);

}
