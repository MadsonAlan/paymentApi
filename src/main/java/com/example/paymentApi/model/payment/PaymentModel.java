package com.example.paymentapi.model.payment;

import java.text.Normalizer;
import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.paymentapi.database.Payment;
import com.example.paymentapi.database.repository.PaymentRepository;

import lombok.AllArgsConstructor;



@Configuration
@AllArgsConstructor
public class PaymentModel {
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
    if (payment.getPaymentValue().intValue() > 0) {
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

  // Function to Update Payment
  public ResponseEntity<String> updatePayment( Long id, Payment payment){
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

  // Function to Delete Payment
  public ResponseEntity<String> deletePayment(Long id){
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
