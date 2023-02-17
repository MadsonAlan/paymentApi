package com.example.paymentApi.model.payment;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.paymentApi.database.Payment;
import com.example.paymentApi.database.repository.PaymentRepository;

import lombok.AllArgsConstructor;



@Configuration
@AllArgsConstructor
public class DeletePayment {
  PaymentRepository repository; 

  // Function to Delete Payment
  public ResponseEntity<String> deletePaymentModel(Long id){
    Payment paymentValidator = repository.findById(id).get();
    
    // Verifica se o status é "Pendente de Processamento" para poder excluir, caso contrário dá erro
    if (paymentValidator.getPaymentStatus().equals("Pendente de Processamento")){
      repository.deleteById(id);
      // responseMessage.setMessage("Registro excluído com sucesso.");
      return new ResponseEntity<String>("Registro excluído com sucesso.",HttpStatus.OK);
    }else
      // responseMessage.setMessage("Impossível excluir pagamentos que deixaram de ser pendentes");
    return new ResponseEntity<String>("Impossível excluir pagamentos que deixaram de ser pendentes",HttpStatus.BAD_REQUEST);
  }
}
