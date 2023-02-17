package com.example.paymentApi.model.status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusPaymentModel {
  private String pending = "Pendente de Processamento";
  private String success = "Processado com Sucesso";
  private String failed = "Processado com Falha";

}
