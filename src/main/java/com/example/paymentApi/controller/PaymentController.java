package com.example.paymentapi.controller;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.paymentapi.database.Payment;
import com.example.paymentapi.database.repository.PaymentRepository;
import com.example.paymentapi.model.payment.PaymentModel;

@RestController
@AllArgsConstructor
public class PaymentController {

  private PaymentRepository repository;
  private PaymentModel paymentModels;
  
  @GetMapping("/payment/all")
  public List<Payment> getAllPayments() {
    return repository.findAll();
  }

  @GetMapping("/payment/{id}")
  public Payment getPaymentByID(@PathVariable Long id) {
    System.out.println(repository.findById(id).get());
    return repository.findById(id).get();
  }

  @PostMapping("/payment/new")
  public ResponseEntity<String> savePayment(@RequestBody Payment payment) {
    return paymentModels.newPaymentModel(payment);
  }    

  @PutMapping("/payment/{id}")
  public ResponseEntity<String> UpdatePaymentStatus(@PathVariable Long id, @RequestBody Payment payment) {    
    return paymentModels.updatePayment(id, payment);
  }

  @DeleteMapping("/payment/{id}")
  public ResponseEntity<String> deletePayment(@PathVariable Long id) {
    return paymentModels.deletePayment(id);
  }
}
