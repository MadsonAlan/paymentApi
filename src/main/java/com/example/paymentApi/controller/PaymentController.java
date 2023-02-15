package com.example.paymentapi.controller;

import com.example.paymentapi.database.repository.PaymentRepository;

import lombok.AllArgsConstructor;

import com.example.paymentapi.database.Payment;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PaymentController {

  PaymentRepository repository;
  
  @GetMapping("/payment/all")
  public List<Payment> getAllPayments() {
    return (List<Payment>) repository.findAll();
  }

  @GetMapping("/payment/{id}")
  public Payment getPaymentByID(@PathVariable Long id) {
    return repository.findByID(id).get();
  }

  @PostMapping("/payment/new")
  public Payment savePayment(@RequestBody Payment payment) {
    return PaymentModel(payment);
  }

  @DeleteMapping("/payment/{id}")
  public Payment deletePayment(@PathVariable Long id) {
    Payment paymentValidator = repository.findByID(id).get();
    if (paymentValidator.getPaymentStatus() == "Pendante de Processamento"){
      repository.deleteById(id);
    }
  }
}
