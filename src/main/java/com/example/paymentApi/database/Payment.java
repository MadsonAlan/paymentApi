package com.example.paymentApi.database;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "payment")
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

  String cardNumber;

  @NotNull
  BigDecimal paymentValue;
  
  @Column(columnDefinition = "varchar(90) default 'Pendente de Processamento'")
  @NotNull
  String paymentStatus;
}
