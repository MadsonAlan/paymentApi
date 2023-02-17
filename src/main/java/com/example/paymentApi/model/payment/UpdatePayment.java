package com.example.paymentApi.model.payment;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.paymentApi.database.Payment;
import com.example.paymentApi.database.repository.PaymentRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class UpdatePayment {
  
  PaymentRepository repository;
  
  // Function to Update Payment
  public ResponseEntity<String> updatePaymentModel( Long id, Payment payment){
    Payment newObj = repository.findById(id).get();  
    
    // Verifica se o status já é "Processado com Sucesso" para não permitir atualizar
    if(newObj.getPaymentStatus().equals("Processado com Sucesso")){
      // responseMessage.setMessage("Não é possivel atualizar pagamento processado com sucesso");
      return new ResponseEntity<>("Não é possivel atualizar pagamento processado com sucesso",HttpStatus.BAD_REQUEST);
    }

    // Verifica se o status já é "Processado com Falha" para não permitir atualizar para "Processado com Sucesso" somente para "Pendente de Processamento"
    if(newObj.getPaymentStatus().equals("Processado com Falha") && payment.getPaymentStatus().equals("Processado com Sucesso")){
      // responseMessage.setMessage("É necessario torná-lo pendente de processamento primeiro");
      return new ResponseEntity<>("É necessario torná-lo pendente de processamento primeiro",HttpStatus.BAD_REQUEST);
    }

    // Atualiza o status com a opção selecionada
    if (payment.getPaymentStatus().equals("Processado com Falha") || payment.getPaymentStatus().equals("Processado com Sucesso") || payment.getPaymentStatus().equals("Pendente de Processamento")) {
      newObj.setPaymentStatus(payment.getPaymentStatus());
      repository.save(newObj);
      // responseMessage.setMessage("Status atualizado com sucesso.");
      return new ResponseEntity<>("Status atualizado com sucesso.",HttpStatus.OK);
    }else
    // responseMessage.setMessage("Status invalido");
    return new ResponseEntity<>("Status invalido",HttpStatus.BAD_REQUEST);
  }
}
