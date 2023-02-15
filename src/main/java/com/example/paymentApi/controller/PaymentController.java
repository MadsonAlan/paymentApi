package com.example.paymentapi.controller;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.paymentapi.database.Payment;
import com.example.paymentapi.database.repository.PaymentRepository;

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
    return repository.findById(id).get();
  }

  @PostMapping("/payment/new")
  public Payment savePayment(@RequestBody Payment payment) {
    return PaymentModel(payment);
  }

    private Payment PaymentModel(Payment payment) {
      String[] paymentMethods = {"boleto", "pix", "cartao_credito", "cartao_debito"};
      boolean contains = Arrays.stream(paymentMethods).anyMatch(payment.getPaymentMethod()::equals);
      System.out.println(payment.getPaymentMethod().equals(paymentMethods[2]));
      if ((payment.getPaymentMethod().equals(paymentMethods[2]) || payment.getPaymentMethod().equals(paymentMethods[3])) && (payment.getCardNumber() == null || payment.getCardNumber().length() != 16) ){

        System.out.println("falha de cartão");
        // payment.setPaymentStatus("Processado com Falha");
        // return repository.save(payment);
        throw new Error("Cartão sem número válido");

      }else
      if(contains){
        System.out.println(contains);
        payment.setPaymentStatus("Pendente de Processamento");
        return repository.save(payment);
      }
      return repository.save(payment);
    }

  @DeleteMapping("/payment/{id}")
  public void deletePayment(@PathVariable Long id) {
    Payment paymentValidator = repository.findById(id).get();
    if (paymentValidator.getPaymentStatus().equals("Pendente de Processamento")){
      repository.deleteById(id);
    }
  }
}
