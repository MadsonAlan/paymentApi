package com.example.paymentApi.model.payment;

import java.text.Normalizer;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.example.paymentApi.database.Payment;
import com.example.paymentApi.database.repository.PaymentRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class GetPayment {
  
  PaymentRepository repository;

  // Function to get all Payment
  public List<Payment> getAllPayments() {
    return repository.findAll();
  }

  public List<Payment> getPaymentByDebitCode(Integer debitCode) {
    return repository.findByDebitCode(debitCode);
  }

  public List<Payment> getPaymentByPayer( String payer) {
    return repository.findByPayer(Normalizer.normalize(payer, Normalizer.Form.NFD).replaceAll("[^0-9]", ""));
  }

  public List<Payment> getPaymentByPaymentStatus(String paymentStatus) {
    return repository.findByPaymentStatus(paymentStatus);
  }
  
}
