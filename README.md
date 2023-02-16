# API Payment Spring Boot
API Desenvolvida usando Spring Boot voltada para pagamento usando Java 17 

## Running
Para rodar no [VS Code](https://code.visualstudio.com/) pode ir na opção de debugar ou somente apertar `f5` para rodar a aplicação.

## Features Roadmap

- [X] Create Payment
  - [X] Credit or debit with mandatory card number
  - [X] Limiting payment methods
  - [X] With status equal to pending
- [X] Read Payment
  - [X] Filtered for Payer
  - [X] Filtered for Debit Code
  - [X] Filtered for Status
  - [X] All Payments
- [X] Update Payment Status
  - [X] With different status of success
- [X] Delete Payment
  - [X] With status equal to pending

## Automated Testing

- [ ] Create Payment
  - [ ] Credit or debit with mandatory card number
  - [ ] Limiting payment methods
  - [ ] With status equal to pending
- [X] Read Payment
  - [X] Filtered for Payer
  - [X] Filtered for Debit Code
  - [X] Filtered for Status
  - [X] All Payments
- [ ] Update Payment Status
  - [ ] With different status of success
- [ ] Delete Payment
  - [ ] With status equal to pending

## Para usar as Rotas
A documentação das rotas do CRUD estão em src/docs (para melhor experiência use o JSON da data recente)

Para usá-las pode-se usar o [Insomnia](https://insomnia.rest/download). Ao baixar selecione o menu Application > Data > Import Data. 
E então selecione o arquivo `.json` que está no caminho já informado