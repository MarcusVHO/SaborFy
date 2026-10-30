#Version 1.0 - 05/09/2026
# Escopo do Projeto — Saborfy

## 1. Visão Geral

O **Saborfy** é um sistema web destinado ao gerenciamento da operação de restaurantes, com foco no controle de mesas, comandas, pedidos, preparação dos pedidos pela cozinha, pagamentos, entregas e histórico das operações.

O sistema será desenvolvido como uma solução **SaaS**, permitindo que diferentes restaurantes utilizem a mesma aplicação, mantendo seus dados e operações isolados.

O objetivo principal é centralizar as operações do restaurante em uma única plataforma, reduzindo a dependência de processos manuais e melhorando a comunicação entre os funcionários envolvidos no atendimento, preparo e fechamento dos pedidos.

---

## 2. Objetivo do Projeto

O projeto tem como objetivos:

- Centralizar o gerenciamento dos pedidos do restaurante.
- Reduzir o uso de papel no processo de atendimento.
- Melhorar a comunicação entre garçons, caixa e cozinha.
- Permitir o acompanhamento do andamento dos pedidos.
- Facilitar o controle de comandas e mesas.
- Registrar os pagamentos realizados pelos clientes.
- Facilitar o gerenciamento de pedidos destinados à entrega.
- Manter um histórico das operações realizadas.
- Registrar ações relevantes dos funcionários para fins de auditoria.
- Permitir que diferentes restaurantes utilizem o sistema de forma independente.

---

## 3. Público-Alvo

O sistema será destinado principalmente a restaurantes, lanchonetes e estabelecimentos similares que necessitem controlar seus pedidos e operações internas.

Os principais usuários do sistema serão:

- **Gerente**
- **Garçom**
- **Caixa**
- **Cozinha**

Cada usuário terá acesso às funcionalidades compatíveis com seu cargo.

---

## 4. Escopo Funcional

Fazem parte do escopo inicial do projeto as seguintes funcionalidades:

### 4.1 Gerenciamento de Usuários

O sistema permitirá:

- Autenticação dos funcionários.
- Cadastro de funcionários.
- Definição do cargo do funcionário.
- Geração de identificador numérico.
- Consulta e gerenciamento dos funcionários.
- Controle de permissões de acordo com o cargo.

### 4.2 Gerenciamento do Restaurante

O sistema permitirá:

- Cadastro do restaurante.
- Identificação do restaurante.
- Configuração inicial do restaurante.
- Isolamento dos dados entre restaurantes.

O cadastro inicial do restaurante será realizado manualmente pelo responsável pelo Saborfy.

### 4.3 Gerenciamento de Mesas

O gerente poderá:

- Cadastrar mesas.
- Editar mesas.
- Desativar mesas.
- Consultar mesas cadastradas.

### 4.4 Gerenciamento de Categorias

O gerente poderá:

- Criar categorias.
- Editar categorias.
- Desativar categorias.
- Consultar categorias.

### 4.5 Gerenciamento de Produtos

O gerente poderá:

- Cadastrar produtos.
- Informar nome, descrição, preço, categoria e disponibilidade.
- Alterar produtos.
- Desativar produtos.
- Consultar produtos.

Produtos desativados permanecerão disponíveis nos registros históricos, mas não poderão ser adicionados a novos pedidos.

### 4.6 Gerenciamento de Comandas

O sistema permitirá:

- Abrir comandas vinculadas às mesas.
- Informar obrigatoriamente o nome do cliente.
- Visualizar as comandas abertas de uma mesa.
- Selecionar uma comanda existente.
- Manter uma comanda aberta sem pedidos.
- Associar vários pedidos à mesma comanda.
- Calcular o valor total da comanda.
- Fechar a comanda quando as condições de fechamento forem atendidas.

Uma mesa poderá possuir mais de uma comanda aberta simultaneamente.

### 4.7 Gerenciamento de Pedidos

O sistema permitirá:

- Criar pedidos.
- Vincular pedidos a comandas.
- Adicionar produtos e quantidades.
- Adicionar observações.
- Calcular valores automaticamente.
- Enviar pedidos para a cozinha.
- Alterar pedidos antes do início da preparação.
- Impedir alterações após o início da preparação.
- Criar novos pedidos para uma mesma comanda.
- Cancelar pedidos por funcionários autorizados.
- Registrar o motivo do cancelamento.
- Identificar pedidos destinados à entrega.

### 4.8 Operação da Cozinha

A cozinha terá uma interface própria para:

- Visualizar pedidos pendentes.
- Visualizar pedidos em preparação.
- Iniciar a preparação de um pedido.
- Finalizar a preparação.
- Organizar pedidos de acordo com seus status.
- Remover pedidos finalizados da tela operacional.

O sistema deverá destacar os pedidos que estiverem prontos para retirada ou entrega.

### 4.9 Notificações

O sistema permitirá:

- Notificar funcionários quando um pedido estiver pronto.
- Exibir notificações dentro da aplicação.
- Utilizar notificações do navegador quando disponíveis.

### 4.10 Pagamentos

O sistema permitirá:

- Registrar pagamentos.
- Informar o valor pago.
- Informar a forma de pagamento.
- Adicionar observações ao pagamento.
- Registrar múltiplos pagamentos para uma mesma comanda.
- Calcular o total pago.
- Controlar as condições necessárias para fechamento da comanda.

As formas de pagamento inicialmente suportadas serão:

- Dinheiro.
- PIX.
- Cartão de crédito.
- Cartão de débito.

O Saborfy **não realizará o processamento financeiro** do pagamento.

### 4.11 Entregas

O sistema permitirá:

- Registrar pedidos destinados à entrega.
- Identificar pedidos de entrega.
- Acompanhar o status da entrega.
- Atualizar o status da entrega.

O fluxo inicial será:

`NOVO → EM_PREPARO → PRONTO → SAIU_PARA_ENTREGA → ENTREGUE`

### 4.12 Histórico e Auditoria

O sistema permitirá:

- Consultar pedidos antigos.
- Consultar pedidos finalizados.
- Consultar pedidos cancelados.
- Filtrar pedidos por diferentes critérios.
- Registrar ações relevantes dos funcionários.
- Identificar o funcionário responsável por cada ação.
- Registrar data e horário das ações.

---

## 5. Perfis de Usuário

### Gerente

Possui acesso completo ao sistema, incluindo:

- Gerenciamento de funcionários.
- Gerenciamento de mesas.
- Gerenciamento de categorias.
- Gerenciamento de produtos.
- Operação de pedidos.
- Comandas.
- Pagamentos.
- Entregas.
- Histórico.
- Auditoria.

### Garçom

Possui acesso às funcionalidades relacionadas à operação do atendimento, incluindo:

- Criação de pedidos.
- Alteração de pedidos antes da preparação.
- Cancelamento de pedidos.
- Gerenciamento de comandas.
- Fechamento de comandas.
- Registro de pagamentos.
- Gerenciamento de entregas.
- Consulta de histórico.

### Caixa

Possui as mesmas permissões operacionais do Garçom.

### Cozinha

Possui acesso às funcionalidades relacionadas à preparação dos pedidos, incluindo:

- Visualização de pedidos.
- Início da preparação.
- Finalização da preparação.
- Consulta do histórico.

A Cozinha não poderá:

- Criar pedidos.
- Registrar pagamentos.
- Fechar comandas.
- Gerenciar entregas.

---

## 6. Plataforma e Dispositivos

O Saborfy será desenvolvido como uma **aplicação web**.

O sistema deverá ser utilizável em:

- Computadores.
- Tablets.
- Smartphones.

A interface deverá ser responsiva, permitindo que cada funcionário utilize o dispositivo mais adequado à sua função.

Exemplo:

- Garçom → smartphone ou tablet.
- Caixa → computador.
- Cozinha → tablet ou computador.

O funcionamento inicial dependerá de conexão com a internet.

---

## 7. Isolamento entre Restaurantes

O Saborfy será uma aplicação multi-restaurante.

Cada restaurante deverá possuir seus próprios:

- Funcionários.
- Mesas.
- Categorias.
- Produtos.
- Comandas.
- Pedidos.
- Pagamentos.
- Registros de entrega.
- Históricos.
- Registros de auditoria.

Um restaurante não poderá acessar os dados de outro restaurante.

---

## 8. Escopo Inicial

A primeira versão do Saborfy terá como foco o funcionamento básico da operação interna do restaurante.

O escopo inicial contempla:

- Autenticação.
- Usuários e permissões.
- Restaurantes.
- Mesas.
- Categorias.
- Produtos.
- Comandas.
- Pedidos.
- Cozinha.
- Notificações.
- Registro de pagamentos.
- Entregas.
- Histórico.
- Auditoria.

---

## 9. Fora do Escopo Inicial

As seguintes funcionalidades **não fazem parte da primeira versão do Saborfy**:

### 9.1 Integração automática com WhatsApp

Inicialmente, pedidos recebidos pelo WhatsApp deverão ser registrados manualmente no Saborfy por um funcionário.

A integração com um bot de WhatsApp poderá ser desenvolvida futuramente para permitir que o pedido seja criado automaticamente no sistema.

### 9.2 Processamento de pagamentos

O sistema não realizará:

- Cobrança via cartão.
- Cobrança via PIX.
- Integração com máquinas de cartão.
- Processamento de transações financeiras.
- Estorno de pagamentos.

O Saborfy apenas registrará o pagamento informado pelo funcionário.

### 9.3 Adicionais e personalização de produtos

A primeira versão não contemplará:

- Adicionais.
- Ingredientes opcionais.
- Combos configuráveis.
- Personalização avançada de produtos.

Essas funcionalidades poderão ser adicionadas posteriormente.

### 9.4 Funcionamento offline

A primeira versão não terá suporte para operação sem internet.

---

## 10. Evolução Futura

O Saborfy deverá ser desenvolvido de maneira que permita a inclusão futura de novas funcionalidades sem necessidade de reestruturar completamente o sistema.

Entre as possíveis evoluções estão:

- Integração automática com WhatsApp.
- Processamento de pagamentos.
- Adicionais e personalização de produtos.
- Novos recursos de gerenciamento.
- Novos relatórios.
- Novas formas de integração com serviços externos.

Essas funcionalidades não fazem parte do escopo inicial e não devem ser consideradas obrigatórias para a primeira versão.

---

## 11. Limites do Projeto

O projeto inicial está limitado ao gerenciamento operacional do restaurante.

O Saborfy será responsável por **registrar, organizar e controlar as informações da operação**, mas não será responsável inicialmente por:

- Processar pagamentos.
- Emitir transações financeiras.
- Realizar pedidos automaticamente pelo WhatsApp.
- Operar sem conexão com a internet.
- Gerenciar funcionalidades avançadas de personalização de produtos.