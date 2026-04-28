# 🍔 SaborFy API

Backend de um sistema de gestão para pedidos e cardápio, desenvolvido com foco em boas práticas de arquitetura, segurança e escalabilidade.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT (Autenticação)
- Maven
- Docker
- Banco de Dados (PostgreSQL/MySQL)

---

## 📌 Funcionalidades

- 🔐 Autenticação e autorização com JWT
- 👤 Gerenciamento de usuários
- 📋 Gestão de cardápio
- 🛒 Criação e gerenciamento de pedidos
- 📦 Itens de pedidos
- 💳 Controle de pagamentos
- 📊 Dashboard de pagamentos
- 🧩 Organização de pedidos (Kanban)

---

## 🏗️ Arquitetura

O projeto segue uma estrutura organizada por domínio:

- Separação clara de responsabilidades
- Camadas bem definidas
- Código preparado para escalabilidade

---

## 🔐 Segurança

- Autenticação baseada em JWT
- Filtros com Spring Security
- Configuração de CORS
- Proteção de rotas

---

## ⚙️ Como rodar o projeto

### Pré-requisitos

- Java 17+
- Maven
- Docker (opcional)

### Rodando localmente

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/saborfy.git

# Entre na pasta
cd saborfy

# Rode o projeto
./mvnw spring-boot:run