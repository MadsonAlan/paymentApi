package com.example.paymentapi.model;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.paymentapi.database.Payment;
import com.example.paymentapi.database.repository.PaymentRepository;

public class PaymentModel {
  private PaymentRepository repository;
  private ResponseMessageModel responseMessage;
  private StatusPaymentModel statusType;

  // Function to Add Payment
  public ResponseEntity<ResponseMessageModel> newPaymentModel(Payment payment) {
    String[] paymentMethods = {"boleto", "pix", "cartao_credito", "cartao_debito"};
    boolean contains = Arrays.stream(paymentMethods).anyMatch(payment.getPaymentMethod()::equals);
    
    // Verifica se é credito ou débito para exigir número do cartão caso não haja
    if ((payment.getPaymentMethod().equals(paymentMethods[2]) || payment.getPaymentMethod().equals(paymentMethods[3])) && (payment.getCardNumber() == null || payment.getCardNumber().length() != 16) ){

      responseMessage.setMessage("Cartão sem número válido");
      return new ResponseEntity<>(responseMessage,HttpStatus.BAD_REQUEST);
      
    }else

    // Verifica se a forma de pagamento é válida
    if(contains){
      
      payment.setPaymentStatus(statusType.pending);
      repository.save(payment);
      responseMessage.setMessage("Pagamento cadastrado. Pendente de processamento.");
      return new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }
    
    responseMessage.setMessage("Forma de pagamento não existe no sistema. Tente: boleto, pix, cartao_credito, cartao_debito");
    return new ResponseEntity<>(responseMessage,HttpStatus.BAD_REQUEST);
    
  }

  // Function to Update Payment
  public ResponseEntity<ResponseMessageModel> updatePayment( Long id, Payment payment){
    Payment newObj = repository.findById(id).get();  
    
    // Verifica se o status já é "Processado com Sucesso" para não permitir atualizar
    if(newObj.getPaymentStatus().equals(statusType.success)){
      responseMessage.setMessage("Não é possivel atualizar pagamento processado com sucesso");
      return new ResponseEntity<>(responseMessage,HttpStatus.BAD_REQUEST);
    }

    // Verifica se o status já é "Processado com Falha" para não permitir atualizar para "Processado com Sucesso" somente para "Pendente de Processamento"
    if(newObj.getPaymentStatus().equals(statusType.failed) && payment.getPaymentStatus().equals(statusType.success)){
      responseMessage.setMessage("É necessario torná-lo pendente de processamento primeiro");
      return new ResponseEntity<>(responseMessage,HttpStatus.BAD_REQUEST);
    }

    // Atualiza o status com a opção selecionada
    if (payment.getPaymentStatus().equals(statusType.failed) || payment.getPaymentStatus().equals(statusType.success) || payment.getPaymentStatus().equals(statusType.pending)) {
      newObj.setPaymentStatus(payment.getPaymentStatus());
      repository.save(newObj);
      responseMessage.setMessage("Status atualizado com sucesso.");
      return new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }else
    responseMessage.setMessage("Status invalido");
    return new ResponseEntity<>(responseMessage,HttpStatus.BAD_REQUEST);
  }

  // Function to Delete Payment
  public ResponseEntity<ResponseMessageModel> deletePayment(Long id){
    Payment paymentValidator = repository.findById(id).get();
    
    // Verifica se o status é "Pendente de Processamento" para poder excluir, caso contrário dá erro
    if (paymentValidator.getPaymentStatus().equals(statusType.pending)){
      repository.deleteById(id);
      responseMessage.setMessage("Registro excluído com sucesso.");
      return new ResponseEntity<ResponseMessageModel>(responseMessage,HttpStatus.OK);
    }else
      responseMessage.setMessage("Impossível excluir pagamentos que deixaram de ser pendentes");
    return new ResponseEntity<ResponseMessageModel>(responseMessage,HttpStatus.BAD_REQUEST);
  }
}
