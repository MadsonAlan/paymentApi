package com.example.paymentApi.database;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;
  
  
  Integer debitCode;

  String payer;

  String paymentMethod;

  Integer cardNumber;

  BigDecimal paimentValue;
  
  @Column(columnDefinition = "varchar(90) default 'Pendente de Processamento'")
  String paymentStatus;
}
