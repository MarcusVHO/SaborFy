# 🍔 SaborFy API

API REST desenvolvida em **Java 21** utilizando **Spring Boot** para gerenciamento de restaurantes, permitindo o controle de usuários, cardápio, pedidos e pagamentos.

O projeto foi desenvolvido com foco em boas práticas de desenvolvimento backend, utilizando autenticação com JWT, arquitetura modular por domínio e persistência de dados com Spring Data JPA/Hibernate.

---

## 🚀 Funcionalidades

- Cadastro e autenticação de usuários utilizando JWT
- Gerenciamento do cardápio
- Cadastro e gerenciamento de clientes
- Criação e acompanhamento de pedidos
- Controle dos itens de cada pedido
- Registro de pagamentos
- Proteção de rotas utilizando Spring Security
- Persistência de dados utilizando Spring Data JPA/Hibernate

---

## 🛠️ Stack Tecnológica

| Categoria | Tecnologias |
|-----------|-------------|
| Linguagem | Java 21 |
| Framework | Spring Boot |
| Segurança | Spring Security, JWT |
| Persistência | Spring Data JPA, Hibernate |
| Banco de Dados | PostgreSQL |
| Build | Maven |
| Containerização | Docker |
| Frontend | React |

---

## 🏗️ Arquitetura

A aplicação foi organizada utilizando uma **arquitetura modular por domínio (Feature-Based Architecture)**.

Cada funcionalidade da aplicação possui seu próprio módulo, contendo todas as camadas necessárias para seu funcionamento, como controllers, services, repositories, entidades e DTOs. Essa abordagem reduz o acoplamento entre funcionalidades e facilita a manutenção e evolução do sistema.

### Estrutura simplificada

```text
src
└── main
    └── java
        └── com.br.marcus.saborfy
            ├── domain
            │
            ├── auth
            ├── customer
            │   ├── controller
            │   ├── dto
            │   ├── entity
            │   ├── factory
            │   ├── mapper
            │   ├── query
            │   ├── repository
            │   ├── service
            │   └── utils
            │
            ├── finance
            ├── menu
            └── order
            │
            └── infra
```

### Principais conceitos utilizados

- Arquitetura modular por domínio
- Arquitetura em camadas dentro de cada módulo
- Single Responsibility Principle (SRP)
- DTOs para comunicação entre cliente e API
- Mappers para conversão entre entidades e DTOs
- Factories para criação de objetos
- Camada de consultas (`query`)
- Tratamento centralizado de exceções
- Autenticação baseada em JWT

---

## 📌 Principais Endpoints

| Método | Endpoint | Descrição |
|---------|----------|-----------|
| POST | `/auth/login` | Autenticação de usuários |
| POST | `/customers` | Cadastro de clientes |
| GET | `/customers` | Listagem de clientes |
| POST | `/menu` | Cadastro de itens do cardápio |
| GET | `/menu` | Listagem do cardápio |
| POST | `/orders` | Criação de pedidos |
| GET | `/orders/{id}` | Consulta de pedido |
| POST | `/payments` | Registro de pagamentos |

> Os endpoints podem variar conforme a evolução do projeto.

---

## 🔐 Segurança

A autenticação é realizada utilizando **JSON Web Token (JWT)**.

Após realizar o login, o cliente recebe um token que deve ser enviado nas requisições para acessar os endpoints protegidos.

```http
Authorization: Bearer <token>
```

O controle de autenticação e autorização é realizado pelo Spring Security.

---

## 🐳 Executando com Docker

### Clone o repositório

```bash
git clone https://github.com/MarcusVHO/SaborFy.git

cd SaborFy
```

### Execute utilizando Docker Compose

```bash
docker compose up --build
```

---

## 💻 Executando localmente

### Pré-requisitos

- Java 21
- Maven
- PostgreSQL

### Executar

Utilizando o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Ou utilizando Maven instalado:

```bash
mvn spring-boot:run
```

---

## 📚 O que foi praticado

Durante o desenvolvimento deste projeto foram aplicados conceitos como:

- Desenvolvimento de APIs REST
- Spring Boot
- Spring Security
- Autenticação utilizando JWT
- Spring Data JPA e Hibernate
- Modelagem de banco de dados relacional
- Docker
- Arquitetura modular por domínio
- Arquitetura em camadas
- Clean Code
- Single Responsibility Principle (SRP)

---

## 📈 Roadmap

- [x] Autenticação com JWT
- [x] CRUD de clientes
- [x] CRUD do cardápio
- [x] CRUD de pedidos
- [x] Persistência utilizando JPA/Hibernate
- [x] Docker
- [ ] Testes automatizados
- [ ] Documentação OpenAPI/Swagger
- [ ] Deploy da aplicação

---

## 👨‍💻 Autor

**Marcus Vinicius Hilario de Oliveira**

- GitHub: https://github.com/MarcusVHO
- LinkedIn: https://linkedin.com/in/marcus-vinicius-hilario
