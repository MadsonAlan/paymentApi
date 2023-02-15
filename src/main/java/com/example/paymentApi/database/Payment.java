package com.example.paymentapi.database;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
  
  @NotNull
  Integer debitCode;

  @NotBlank
  String payer;

  @NotNull
  String paymentMethod;

  Integer cardNumber;

  @NotNull
  BigDecimal paimentValue;
  
  @Column(columnDefinition = "varchar(90) default 'Pendente de Processamento'")
  String paymentStatus;
}
