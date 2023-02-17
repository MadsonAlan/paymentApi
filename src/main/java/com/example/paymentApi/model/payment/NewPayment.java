package com.example.paymentApi.model.payment;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.paymentApi.database.Payment;
import com.example.paymentApi.database.repository.PaymentRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class NewPayment {
  
  PaymentRepository repository;
  
  // Function to Add Payment
  public ResponseEntity<String> newPaymentModel(Payment payment) {
    String[] paymentMethods = {"boleto", "pix", "cartao_credito", "cartao_debito"};
    boolean contains = Arrays.stream(paymentMethods).anyMatch(payment.getPaymentMethod()::equals);
    Integer payerLength = Normalizer.normalize(payment.getPayer(), Normalizer.Form.NFD).replaceAll("[^0-9]", "").length();
    payment.setPayer(Normalizer.normalize(payment.getPayer(), Normalizer.Form.NFD).replaceAll("[^0-9]", ""));
    
    // Verifica se o CPF ou CNPJ é válido
    if (payerLength != 11 && payerLength != 14) {
      return new ResponseEntity<>("Adicione um CPF ou CNPJ válido.",HttpStatus.BAD_REQUEST);
      
    }
    
    // Verifica se o pagamento é válido
    if (payment.getPaymentValue().compareTo(BigDecimal.ZERO) <= 0) {
      return new ResponseEntity<>("Adicione um pagamento com valor maior que 0.",HttpStatus.BAD_REQUEST);
      
    }

    // Verifica se é credito ou débito para exigir número do cartão caso não haja
    if ((payment.getPaymentMethod().equals(paymentMethods[2]) || payment.getPaymentMethod().equals(paymentMethods[3])) && (payment.getCardNumber() == null || payment.getCardNumber().length() != 16) ){

      // responseMessage.setMessage("Cartão sem número válido");
      return new ResponseEntity<>("Cartão sem número válido",HttpStatus.BAD_REQUEST);
      
    }else

    // Verifica se a forma de pagamento é válida
    if(contains){
      
      payment.setPaymentStatus("Pendente de Processamento");
      repository.save(payment);
      // responseMessage.setMessage("Pagamento cadastrado. Pendente de processamento.");
      return new ResponseEntity<>("Pagamento cadastrado. Pendente de processamento.",HttpStatus.OK);
    }
    
    // responseMessage.setMessage("Forma de pagamento não existe no sistema. Tente: boleto, pix, cartao_credito, cartao_debito");
    return new ResponseEntity<>("Forma de pagamento não existe no sistema. Tente: boleto, pix, cartao_credito, cartao_debito",HttpStatus.BAD_REQUEST);
    
  }
}
