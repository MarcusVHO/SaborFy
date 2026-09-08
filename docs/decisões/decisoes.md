# Registro de Decisões do Projeto — Saborfy

## 1. Objetivo

Este documento tem como objetivo registrar as principais decisões tomadas durante o desenvolvimento do Saborfy.

As decisões registradas neste documento servem como referência para orientar o desenvolvimento, esclarecer dúvidas futuras e manter um histórico das escolhas realizadas durante o projeto.

Este documento não substitui os requisitos funcionais, requisitos não funcionais ou regras de negócio. Ele registra **decisões de projeto e seus respectivos motivos**.

---

## 2. Como utilizar este documento

Sempre que uma decisão importante for tomada, deve ser adicionada uma nova entrada.

Uma decisão deve ser registrada principalmente quando:

- Existirem duas ou mais alternativas possíveis.
- A escolha afetar a arquitetura ou funcionamento do sistema.
- A decisão precisar ser lembrada no futuro.
- Uma funcionalidade for deliberadamente deixada para uma versão futura.
- A decisão tiver impacto em outros requisitos.
- For necessário registrar o motivo de uma escolha.

### Estrutura de uma decisão

Cada decisão deve possuir:

- **ID:** identificador único.
- **Data:** quando a decisão foi tomada.
- **Título:** descrição curta da decisão.
- **Contexto:** problema ou situação que motivou a decisão.
- **Decisão:** o que foi decidido.
- **Alternativas consideradas:** outras opções avaliadas.
- **Motivo:** por que a decisão foi escolhida.
- **Impacto:** o que essa decisão muda ou influencia no projeto.
- **Status:** situação atual da decisão.

---

# 3. Registro de Decisões

## DP01 — Sistema como aplicação Web

**Data:** 05/09/2026  
**Status:** Aprovada

### Contexto

O sistema será utilizado por diferentes funcionários do restaurante em dispositivos diferentes, como computadores, tablets e smartphones.

### Decisão

O Saborfy será desenvolvido como uma **aplicação web responsiva**.

### Alternativas consideradas

- Aplicação desktop.
- Aplicação mobile nativa.
- Aplicação web.

### Motivo

A aplicação web permite que diferentes funcionários utilizem o sistema em diferentes dispositivos sem a necessidade de instalar uma aplicação específica em cada dispositivo.

### Impacto

A interface deverá ser responsiva e compatível com computadores, tablets e smartphones.

---

## DP02 — Modelo SaaS Multi-Restaurante

**Data:** 05/09/2026  
**Status:** Aprovada

### Contexto

O Saborfy será utilizado por diferentes restaurantes.

### Decisão

O sistema será desenvolvido seguindo um modelo **SaaS**, permitindo que vários restaurantes utilizem a mesma aplicação.

Os dados de cada restaurante deverão permanecer isolados.

### Alternativas consideradas

- Desenvolver uma aplicação independente para cada restaurante.
- Desenvolver uma única aplicação multi-restaurante.

### Motivo

O modelo multi-restaurante facilita a evolução e manutenção do produto e permite que o Saborfy seja utilizado por diferentes clientes.

### Impacto

As entidades relacionadas à operação deverão possuir uma associação com o restaurante correspondente, garantindo o isolamento dos dados.

---

## DP03 — Usuário pertence a apenas um restaurante

**Data:** 05/09/2026  
**Status:** Aprovada

### Contexto

Um funcionário pode trabalhar em mais de um restaurante que utilize o Saborfy.

### Decisão

Cada conta de usuário será vinculada exclusivamente a um restaurante.

Caso a mesma pessoa trabalhe em dois restaurantes, deverá possuir uma conta independente em cada um deles.

### Alternativas consideradas

- Uma única conta podendo acessar vários restaurantes.
- Uma conta independente para cada restaurante.

### Motivo

A separação simplifica o controle de permissões e reduz o risco de acesso indevido aos dados de outro restaurante.

### Impacto

O usuário não poderá utilizar a mesma conta para acessar diferentes restaurantes.

---

## DP04 — Cadastro inicial do restaurante

**Data:** 05/09/2026  
**Status:** Aprovada

### Contexto

É necessário definir como um novo restaurante será inserido no Saborfy.

### Decisão

O cadastro inicial do restaurante será realizado manualmente pelo responsável pelo Saborfy.

Após o cadastro, o gerente do restaurante poderá realizar a configuração inicial, incluindo funcionários, mesas, categorias e produtos.

### Alternativas consideradas

- Cadastro público realizado pelo próprio restaurante.
- Cadastro realizado manualmente pelo responsável pelo Saborfy.

### Motivo

O cadastro manual permite maior controle durante a fase inicial do produto.

### Impacto

O sistema não precisará possuir inicialmente um fluxo público de criação de novos restaurantes.

---

## DP05 — Separação entre Mesa, Comanda e Pedido

**Data:** 05/09/2026  
**Status:** Aprovada

### Contexto

É necessário definir como será representada a operação de atendimento do restaurante.

### Decisão

O sistema terá três conceitos distintos:

**Mesa:** representa o local físico onde o cliente está.

**Comanda:** representa o consumo de um cliente associado a uma mesa.

**Pedido:** representa uma solicitação de produtos enviada para preparação.

Uma mesa poderá possuir várias comandas abertas e uma comanda poderá possuir vários pedidos.

### Motivo

Essa estrutura representa melhor situações em que diferentes clientes utilizam a mesma mesa ou quando novos pedidos são realizados durante o atendimento.

### Impacto

A estrutura do banco de dados e os fluxos de atendimento deverão respeitar essa separação.

---

## DP06 — Histórico de preço dos produtos

**Data:** 05/09/2026  
**Status:** Aprovada

### Contexto

O preço de um produto pode ser alterado ao longo do tempo.

Por exemplo:

> X-Burger custa R$ 20,00 hoje e passa a custar R$ 25,00 futuramente.

Pedidos antigos não podem ter seus valores históricos alterados.

### Decisão

No momento da criação do pedido, o sistema deverá armazenar uma cópia do nome e do preço do produto utilizado naquele pedido.

### Alternativas consideradas

- Consultar sempre o preço atual do produto.
- Armazenar o preço utilizado no momento da criação do pedido.

### Motivo

O histórico do restaurante precisa preservar os valores que realmente foram utilizados em pedidos anteriores.

### Impacto

O item do pedido deverá possuir seus próprios dados históricos, independentemente das alterações futuras realizadas no cadastro do produto.

---

## DP07 — Desativação em vez de exclusão de produtos

**Data:** 05/09/2026  
**Status:** Aprovada

### Contexto

A exclusão física de um produto poderia comprometer o histórico de pedidos que utilizaram esse produto.

### Decisão

Produtos não serão excluídos fisicamente. Quando deixarem de ser comercializados, serão **desativados**.

### Motivo

A desativação preserva o histórico e impede que o produto seja utilizado em novos pedidos.

### Impacto

Produtos desativados continuarão aparecendo em pedidos históricos, mas não poderão ser adicionados a novos pedidos.

---

## DP08 — Alteração de pedidos após início da preparação

**Data:** 05/09/2026  
**Status:** Aprovada

### Contexto

Depois que a cozinha começa a preparar um pedido, alterações nesse pedido podem causar inconsistências entre o que foi solicitado e o que está sendo preparado.

### Decisão

Um pedido poderá ser alterado somente antes do início da preparação pela cozinha.

Após o início da preparação, o pedido ficará bloqueado para alterações.

Caso o cliente queira novos produtos, deverá ser criado um novo pedido para a mesma comanda.

### Motivo

Essa abordagem mantém o pedido original consistente com o que está sendo preparado pela cozinha.

### Impacto

O sistema deverá controlar o status do pedido e impedir alterações após o início da preparação.

---

## DP09 — Pagamento apenas como registro

**Data:** 05/09/2026  
**Status:** Aprovada

### Contexto

O restaurante precisa registrar os pagamentos realizados, mas o processamento financeiro não fará parte da primeira versão.

### Decisão

O Saborfy apenas registrará:

- Valor pago.
- Forma de pagamento.
- Observação do pagamento.

O sistema não realizará a transação financeira.

### Alternativas consideradas

- Integrar um gateway de pagamento.
- Apenas registrar o pagamento realizado externamente.

### Motivo

A integração financeira aumenta a complexidade do projeto e não é necessária para o funcionamento inicial do sistema.

### Impacto

O fechamento da comanda dependerá dos registros de pagamento, mas o processamento efetivo continuará sendo realizado externamente.

---

## DP10 — Integração com WhatsApp como evolução futura

**Data:** 05/09/2026  
**Status:** Aprovada

### Contexto

Existe a possibilidade de permitir que clientes realizem pedidos através do WhatsApp.

### Decisão

A integração automática com WhatsApp não fará parte da primeira versão.

Inicialmente, pedidos recebidos pelo WhatsApp serão registrados manualmente por um funcionário no Saborfy.

### Alternativas consideradas

- Implementar o bot de WhatsApp na primeira versão.
- Registrar inicialmente os pedidos manualmente e implementar a integração posteriormente.

### Motivo

A integração com WhatsApp adicionaria complexidade ao MVP e não é necessária para validar o funcionamento principal do sistema.

### Impacto

A arquitetura deverá permitir uma futura integração com serviços externos sem exigir uma reconstrução completa do sistema.

---

# 4. Status das Decisões

As decisões podem possuir os seguintes status:

| Status | Significado |
|---|---|
| **Proposta** | Decisão ainda está sendo discutida. |
| **Aprovada** | Decisão definida e deve ser seguida no projeto. |
| **Em revisão** | A decisão está sendo reconsiderada. |
| **Substituída** | Uma nova decisão substituiu esta decisão. |
| **Cancelada** | A decisão deixou de ser válida. |

---

# 5. Modelo para Novas Decisões

Utilize o modelo abaixo sempre que uma nova decisão precisar ser registrada:

## DPXX — Título da decisão

**Data:** DD/MM/AAAA  
**Status:** Proposta / Aprovada / Em revisão / Substituída / Cancelada

### Contexto

Descreva o problema ou situação que levou à necessidade de tomar uma decisão.

### Decisão

Descreva claramente o que foi decidido.

### Alternativas consideradas

- Alternativa 1.
- Alternativa 2.
- Alternativa 3.

### Motivo

Explique por que a alternativa escolhida foi considerada a melhor.

### Impacto

Descreva quais partes do projeto serão afetadas pela decisão.