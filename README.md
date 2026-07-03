# 🍔 SaborFy API

API REST desenvolvida em Java com Spring Boot para gerenciar pedidos de um restaurante. O projeto foi criado com o objetivo de praticar o desenvolvimento de aplicações backend utilizando autenticação, organização em camadas e persistência de dados.

## Funcionalidades

- Cadastro e autenticação de usuários utilizando JWT
- Gerenciamento do cardápio
- Criação e acompanhamento de pedidos
- Controle dos itens de cada pedido
- Registro de pagamentos
- Proteção das rotas da aplicação com Spring Security

## Tecnologias

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- MySQL
- Maven
- Docker

## Estrutura do projeto

O projeto foi organizado em camadas para separar as responsabilidades da aplicação.

```
src
├── config
├── controller
├── dto
├── entity
├── repository
├── security
├── service
└── exception
```

Essa organização facilita a manutenção do código e evita que regras de negócio fiquem misturadas com a camada responsável pelas requisições HTTP.

## Como executar

### Pré-requisitos

- Java 17 ou superior
- Maven
- MySQL
- Docker (opcional)

### Clonando o projeto

```bash
git clone https://github.com/seu-usuario/saborfy.git

cd saborfy
```

### Executando

```bash
./mvnw spring-boot:run
```

Ou, caso utilize Maven instalado na máquina:

```bash
mvn spring-boot:run
```

## Segurança

A autenticação é feita utilizando JWT. Após realizar o login, o cliente recebe um token que deve ser enviado nas requisições para acessar os endpoints protegidos.

## Objetivo

Este projeto faz parte do meu portfólio de backend e foi desenvolvido para colocar em prática conceitos como:

- APIs REST
- Spring Security
- Autenticação com JWT
- Persistência de dados com JPA
- Organização em camadas
- Tratamento de exceções