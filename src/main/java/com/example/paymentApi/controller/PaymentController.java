package com.example.paymentApi.controller;

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

import com.example.paymentApi.database.Payment;
import com.example.paymentApi.model.payment.DeletePayment;
import com.example.paymentApi.model.payment.GetPayment;
import com.example.paymentApi.model.payment.NewPayment;
import com.example.paymentApi.model.payment.UpdatePayment;

@RestController
@AllArgsConstructor
public class PaymentController {

  private DeletePayment deletePayment;
  private NewPayment newPaymentModels;
  private UpdatePayment updatePayment;
  private GetPayment getPayment;
  
  @GetMapping("/payment/all")
  public List<Payment> getAllPayments() {
    return getPayment.getAllPayments();
  }

  @GetMapping("/payment/filter/debitCode/{debitCode}")
  public List<Payment> getPaymentByDebitCode(@PathVariable Integer debitCode) {
    return getPayment.getPaymentByDebitCode(debitCode);
  }

  @GetMapping("/payment/filter/payer/{payer}")
  public List<Payment> getPaymentByPayer(@PathVariable String payer) {
    return getPayment.getPaymentByPayer(payer);
  }

  @GetMapping("/payment/filter/paymentStatus/{paymentStatus}")
  public List<Payment> getPaymentByPaymentStatus(@PathVariable String paymentStatus) {
    return getPayment.getPaymentByPaymentStatus(paymentStatus);
  }

  @PostMapping("/payment/new")
  public ResponseEntity<String> savePayment(@RequestBody Payment payment) {
    return newPaymentModels.newPaymentModel(payment);
  }    

  @PutMapping("/payment/{id}")
  public ResponseEntity<String> UpdatePaymentStatus(@PathVariable Long id, @RequestBody Payment payment) {    
    return updatePayment.updatePaymentModel(id, payment);
  }

  @DeleteMapping("/payment/{id}")
  public ResponseEntity<String> deletePayment(@PathVariable Long id) {
    return deletePayment.deletePaymentModel(id);
  }
}