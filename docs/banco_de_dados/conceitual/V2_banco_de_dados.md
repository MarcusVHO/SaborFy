# Versão 2.0 - 06/09/2026
# Banco de Dados e Entidades — Saborfy

## 1. Objetivo

Este documento define a estrutura conceitual do banco de dados do Saborfy, identificando as principais entidades do sistema, seus atributos, relacionamentos e regras de integridade.

O banco de dados deverá armazenar as informações necessárias para o funcionamento da aplicação, garantindo:

- Persistência dos dados.
- Isolamento entre restaurantes.
- Integridade dos relacionamentos.
- Preservação do histórico das operações.
- Registro de auditoria.
- Suporte ao fluxo de mesas, comandas, pedidos, cozinha, pagamentos e entregas.

---

# 2. Visão Geral do Modelo

O Saborfy será uma aplicação SaaS multi-restaurante.

A estrutura principal do sistema será:

```text
Restaurante
   │
   ├── Usuários
   │
   ├── Mesas
   │     │
   │     └── Comandas
   │             │
   │             └── Pedidos
   │                    │
   │                    └── Itens do Pedido
   │
   ├── Categorias
   │     │
   │     └── Produtos
   │
   ├── Pagamentos
   │
   └── Auditoria
```

Os pedidos destinados à entrega também estarão relacionados à estrutura de pedidos, sendo identificados pelo atributo `is_delivery`.

As notificações relacionadas aos pedidos prontos não serão armazenadas no banco de dados. A estratégia de comunicação em tempo real deverá ser definida na arquitetura da aplicação.

---

# 3. Entidades

As principais entidades do banco de dados serão:

| Entidade | Objetivo |
|---|---|
| Restaurante | Representar cada estabelecimento que utiliza o Saborfy. |
| Usuário | Representar os funcionários do restaurante. |
| Mesa | Representar as mesas físicas do restaurante. |
| Comanda | Representar o consumo de um cliente associado a uma mesa. |
| Pedido | Representar uma solicitação de produtos. |
| PedidoItem | Representar os produtos e quantidades de um pedido. |
| Categoria | Organizar os produtos do restaurante. |
| Produto | Representar os produtos comercializados pelo restaurante. |
| Pagamento | Registrar pagamentos realizados para uma comanda. |
| Auditoria | Registrar ações relevantes realizadas no sistema. |

---

# 4. Restaurante

## Objetivo

Representar um estabelecimento que utiliza o Saborfy.

## Atributos

| Atributo | Descrição |
|---|---|
| `id` | Identificador único do restaurante. |
| `nome` | Nome do restaurante. |
| `ativo` | Indica se o restaurante está ativo. |
| `created_at` | Data e horário de criação. |
| `updated_at` | Data e horário da última alteração. |

## Relacionamentos

Um restaurante pode possuir:

- Vários usuários.
- Várias mesas.
- Várias categorias.
- Vários produtos.
- Várias comandas.
- Vários pedidos.
- Vários pagamentos.
- Vários registros de auditoria.

Cada uma dessas entidades pertence a um único restaurante.

---

# 5. Usuário

## Objetivo

Representar os funcionários que utilizam o Saborfy.

## Atributos

| Atributo | Descrição |
|---|---|
| `id` | Identificador interno do registro. |
| `identificador` | Identificador numérico utilizado pelo funcionário. |
| `nome` | Nome do funcionário. |
| `senha` | Senha armazenada de forma segura. |
| `cargo` | Cargo/perfil do funcionário. |
| `ativo` | Indica se o funcionário está ativo. |
| `restaurante_id` | Restaurante ao qual o funcionário pertence. |
| `created_at` | Data e horário de criação. |
| `updated_at` | Data e horário da última alteração. |

## Cargo

Os cargos inicialmente suportados serão:

```text
GERENTE
GARCOM
CAIXA
COZINHA
```

## Relacionamentos

Um usuário pertence a exatamente um restaurante.

Um usuário pode realizar:

- Pedidos.
- Cancelamentos.
- Pagamentos.
- Fechamentos de comandas.
- Alterações de status.
- Ações de auditoria.

---

# 6. Mesa

## Objetivo

Representar uma mesa física do restaurante.

## Atributos

| Atributo | Descrição |
|---|---|
| `id` | Identificador da mesa. |
| `numero` | Número ou identificação da mesa. |
| `ativo` | Indica se a mesa está disponível para utilização. |
| `restaurante_id` | Restaurante ao qual a mesa pertence. |
| `created_at` | Data e horário de criação. |
| `updated_at` | Data e horário da última alteração. |

## Relacionamentos

Uma mesa pertence a um restaurante.

Uma mesa pode possuir várias comandas.

```text
Mesa 1 ───── N Comandas
```

Uma comanda poderá ser transferida de uma mesa para outra, alterando sua associação com a mesa sem criar uma nova comanda.

---

# 7. Comanda

## Objetivo

Representar o consumo de um cliente associado a uma mesa.

A comanda é responsável por centralizar os pedidos realizados durante o atendimento.

## Atributos

| Atributo | Descrição |
|---|---|
| `id` | Identificador da comanda. |
| `nome_cliente` | Nome do cliente. |
| `status` | Estado atual da comanda. |
| `mesa_id` | Mesa atualmente associada à comanda. |
| `restaurante_id` | Restaurante ao qual pertence. |
| `created_at` | Data e horário de abertura. |
| `closed_at` | Data e horário de fechamento. |

## Status

Inicialmente:

```text
ABERTA
FECHADA
```

## Relacionamentos

Uma comanda pertence a uma mesa.

Uma comanda pode possuir vários pedidos.

Uma comanda pode possuir vários pagamentos.

```text
Mesa 1 ───── N Comandas
Comanda 1 ───── N Pedidos
Comanda 1 ───── N Pagamentos
```

## Regras

- Uma comanda deve possuir obrigatoriamente o nome do cliente.
- Uma comanda pode permanecer aberta sem possuir pedidos.
- Uma comanda permanece aberta até ser fechada por um funcionário autorizado.
- Uma mesa pode possuir várias comandas abertas simultaneamente.
- Uma comanda pode ser transferida de uma mesa para outra.
- A transferência não cria uma nova comanda.
- A transferência mantém os pedidos, pagamentos e demais informações associadas à comanda.
- A transferência altera somente a mesa associada à comanda.
- A transferência deverá ser registrada na auditoria.

---

# 8. Categoria

## Objetivo

Organizar os produtos cadastrados no restaurante.

## Atributos

| Atributo | Descrição |
|---|---|
| `id` | Identificador da categoria. |
| `nome` | Nome da categoria. |
| `ativo` | Indica se a categoria está ativa. |
| `restaurante_id` | Restaurante ao qual pertence. |
| `created_at` | Data e horário de criação. |
| `updated_at` | Data e horário da última alteração. |

## Relacionamentos

Uma categoria pertence a um restaurante.

Uma categoria pode possuir vários produtos.

```text
Categoria 1 ───── N Produtos
```

---

# 9. Produto

## Objetivo

Representar os produtos comercializados pelo restaurante.

## Atributos

| Atributo | Descrição |
|---|---|
| `id` | Identificador do produto. |
| `nome` | Nome do produto. |
| `descricao` | Descrição do produto. |
| `preco` | Preço atual do produto. |
| `disponivel` | Indica se o produto pode ser utilizado em novos pedidos. |
| `ativo` | Indica se o produto está ativo no cadastro. |
| `categoria_id` | Categoria do produto. |
| `restaurante_id` | Restaurante ao qual pertence. |
| `created_at` | Data e horário de criação. |
| `updated_at` | Data e horário da última alteração. |

## Relacionamentos

Um produto pertence a uma categoria.

Um produto pertence a um restaurante.

Um produto pode aparecer em vários itens de pedidos.

```text
Produto 1 ───── N PedidoItens
```

## Regras

Produtos não serão excluídos fisicamente.

Quando um produto deixar de ser utilizado, deverá ser desativado.

Produtos desativados não poderão ser adicionados a novos pedidos.

Produtos que estejam temporariamente indisponíveis poderão permanecer cadastrados, mas não poderão ser adicionados a novos pedidos enquanto estiverem indisponíveis.

---

# 10. Pedido

## Objetivo

Representar uma solicitação de produtos realizada durante o atendimento.

## Atributos

| Atributo | Descrição |
|---|---|
| `id` | Identificador interno do pedido. |
| `numero` | Número do pedido apresentado aos usuários. |
| `observacao` | Observação geral do pedido. |
| `status` | Estado atual do pedido. |
| `is_delivery` | Indica se o pedido é destinado à entrega. |
| `total` | Valor total do pedido. |
| `comanda_id` | Comanda à qual o pedido pertence. |
| `restaurante_id` | Restaurante ao qual pertence. |
| `created_by` | Usuário responsável pela criação. |
| `created_at` | Data e horário de criação. |
| `updated_at` | Data e horário da última alteração. |
| `cancelled_at` | Data e horário do cancelamento, quando aplicável. |
| `cancelled_by` | Usuário responsável pelo cancelamento. |
| `motivo_cancelamento` | Motivo informado para o cancelamento. |

## Status

O fluxo principal do pedido será:

```text
NOVO
   ↓
EM_PREPARO
   ↓
PRONTO
   ↓
EM_MESA
```

Para pedidos de entrega:

```text
NOVO
   ↓
EM_PREPARO
   ↓
PRONTO
   ↓
SAIU_PARA_ENTREGA
   ↓
ENTREGUE
```

Também deverá existir um estado para pedidos cancelados:

```text
CANCELADO
```

## Relacionamentos

Um pedido pertence a uma comanda.

Um pedido possui vários itens.

Um pedido pode ter sido criado por um usuário.

Um pedido pode ser cancelado por um usuário autorizado.

```text
Comanda 1 ───── N Pedidos
Pedido 1 ───── N PedidoItens
```

## Regras

- Um pedido deverá possuir pelo menos um item para ser enviado à cozinha.
- Um pedido poderá ser alterado enquanto a preparação ainda não tiver sido iniciada.
- Após o início da preparação, o pedido não poderá ser alterado.
- Caso o cliente solicite novos produtos após o início da preparação, deverá ser criado um novo pedido para a mesma comanda.
- Pedidos cancelados deverão possuir um motivo de cancelamento.
- O número do pedido deverá ser único dentro do restaurante.

---

# 11. PedidoItem

## Objetivo

Representar individualmente cada produto que faz parte de um pedido.

Essa entidade também será responsável por preservar o histórico do produto no momento em que o pedido foi criado.

## Atributos

| Atributo | Descrição |
|---|---|
| `id` | Identificador do item. |
| `pedido_id` | Pedido ao qual pertence. |
| `produto_id` | Produto utilizado na criação do item. |
| `nome_produto` | Cópia do nome do produto no momento da criação. |
| `preco_unitario` | Cópia do preço do produto no momento da criação. |
| `quantidade` | Quantidade solicitada. |
| `subtotal` | Valor total do item. |
| `observacao` | Observação específica do item, quando aplicável. |

## Regra de histórico

O sistema deverá armazenar no `PedidoItem` o nome e o preço utilizados no momento da criação.

Exemplo:

```text
Produto atual

X-Burger
Preço atual: R$ 25,00
```

Pedido realizado anteriormente:

```text
PedidoItem

nome_produto: X-Burger
preco_unitario: R$ 20,00
quantidade: 2
subtotal: R$ 40,00
```

Se posteriormente o produto passar de R$ 20,00 para R$ 25,00, o pedido antigo continuará registrando R$ 20,00.

---

# 12. Pagamento

## Objetivo

Registrar os pagamentos realizados para uma comanda.

O Saborfy não realizará o processamento financeiro do pagamento.

## Atributos

| Atributo | Descrição |
|---|---|
| `id` | Identificador do pagamento. |
| `comanda_id` | Comanda relacionada ao pagamento. |
| `valor` | Valor registrado. |
| `forma_pagamento` | Forma de pagamento utilizada. |
| `observacao` | Observação opcional. |
| `registrado_por` | Usuário que registrou o pagamento. |
| `created_at` | Data e horário do registro. |

## Formas de pagamento

Inicialmente:

```text
DINHEIRO
PIX
CARTAO_CREDITO
CARTAO_DEBITO
```

## Relacionamento

Uma comanda pode possuir vários pagamentos.

```text
Comanda 1 ───── N Pagamentos
```

## Regra de fechamento

A comanda somente poderá ser fechada quando:

```text
Soma dos pagamentos = Total da comanda
```

Exceção:

```text
Total da comanda = R$ 0,00
```

Nesse caso, a comanda poderá ser fechada sem pagamento.

---

# 13. Auditoria

## Objetivo

Registrar ações relevantes realizadas pelos funcionários no sistema.

A auditoria permitirá identificar:

- Quem realizou determinada ação.
- Qual ação foi realizada.
- Quando a ação aconteceu.
- Qual entidade foi afetada.

## Atributos

| Atributo | Descrição |
|---|---|
| `id` | Identificador do registro. |
| `usuario_id` | Usuário responsável pela ação. |
| `restaurante_id` | Restaurante relacionado. |
| `acao` | Ação realizada. |
| `entidade` | Entidade afetada. |
| `entidade_id` | Identificador do registro afetado. |
| `descricao` | Informações adicionais sobre a ação. |
| `created_at` | Data e horário da ação. |

## Exemplos de ações

```text
CRIAR_PEDIDO
ALTERAR_PEDIDO
CANCELAR_PEDIDO
ABRIR_COMANDA
FECHAR_COMANDA
TRANSFERIR_COMANDA
REGISTRAR_PAGAMENTO
INICIAR_PREPARACAO
FINALIZAR_PREPARACAO
ALTERAR_PRODUTO
DESATIVAR_PRODUTO
CRIAR_USUARIO
DESATIVAR_USUARIO
```

---

# 14. Relacionamentos Principais

A estrutura principal de relacionamentos será:

```text
Restaurante
│
├── 1:N ── Usuário
│
├── 1:N ── Mesa
│            │
│            └── 1:N ── Comanda
│                         │
│                         ├── 1:N ── Pedido
│                         │            │
│                         │            └── 1:N ── PedidoItem
│                         │
│                         └── 1:N ── Pagamento
│
├── 1:N ── Categoria
│            │
│            └── 1:N ── Produto
│
└── 1:N ── Auditoria
```

---

# 15. Relacionamentos e Cardinalidades

| Origem | Relação | Destino |
|---|---|---|
| Restaurante | 1:N | Usuário |
| Restaurante | 1:N | Mesa |
| Restaurante | 1:N | Categoria |
| Restaurante | 1:N | Produto |
| Restaurante | 1:N | Comanda |
| Restaurante | 1:N | Pedido |
| Restaurante | 1:N | Pagamento |
| Restaurante | 1:N | Auditoria |
| Mesa | 1:N | Comanda |
| Categoria | 1:N | Produto |
| Comanda | 1:N | Pedido |
| Comanda | 1:N | Pagamento |
| Pedido | 1:N | PedidoItem |
| Produto | 1:N | PedidoItem |
| Usuário | 1:N | Pedido |
| Usuário | 1:N | Pagamento |
| Usuário | 1:N | Auditoria |

A transferência de uma comanda entre mesas não representa um novo relacionamento ou uma nova entidade. A operação deverá apenas alterar o valor de `mesa_id` da comanda existente.

---

# 16. Isolamento Multi-Restaurante

O banco de dados deverá garantir que os dados de um restaurante não sejam acessados por outro.

As entidades operacionais deverão possuir uma referência ao restaurante quando necessário.

Exemplo:

```text
restaurante_id
```

Esse identificador deverá ser utilizado para garantir que:

```text
Restaurante A
   ├── Usuários A
   ├── Mesas A
   ├── Produtos A
   ├── Comandas A
   └── Pedidos A

Restaurante B
   ├── Usuários B
   ├── Mesas B
   ├── Produtos B
   ├── Comandas B
   └── Pedidos B
```

não tenham seus dados misturados.

Além da estrutura de banco, a aplicação deverá garantir que as consultas e operações realizadas pelos usuários sejam sempre limitadas ao restaurante ao qual pertencem.

---

# 17. Integridade dos Dados

O banco de dados deverá garantir, entre outras, as seguintes condições:

- Um usuário deve pertencer a um restaurante.
- Uma mesa deve pertencer a um restaurante.
- Uma categoria deve pertencer a um restaurante.
- Um produto deve pertencer a um restaurante.
- Uma comanda deve pertencer a uma mesa.
- Uma comanda deve pertencer a um restaurante.
- Um pedido deve pertencer a uma comanda.
- Um pedido deve pertencer a um restaurante.
- Um pedido deverá possuir pelo menos um item para ser enviado à cozinha.
- Um item de pedido deve estar associado a um pedido.
- Um pagamento deve estar associado a uma comanda.
- Um usuário não pode acessar dados de outro restaurante.
- Produtos desativados não podem ser adicionados a novos pedidos.
- Produtos indisponíveis não podem ser adicionados a novos pedidos.
- Pedidos iniciados pela cozinha não podem ser alterados.
- Pedidos cancelados devem possuir motivo.
- O histórico de preço dos pedidos não deve depender do preço atual do produto.
- A transferência de uma comanda deve manter seus pedidos e pagamentos.
- A transferência de uma comanda não deve criar uma nova comanda.
- Uma comanda somente poderá ser fechada quando atender às regras de pagamento definidas.

---

# 18. Exclusão e Desativação

O sistema deverá priorizar a **desativação** em vez da exclusão física de dados que possuam relevância histórica.

Principalmente:

- Usuários.
- Produtos.
- Categorias.
- Mesas.

Dados utilizados em pedidos históricos não deverão ser removidos de maneira que comprometa a integridade do histórico.

A desativação de uma entidade deverá impedir sua utilização em novas operações quando aplicável, mas deverá preservar os dados necessários para consultas históricas e auditoria.

---

# 19. Identificadores

Cada entidade deverá possuir um identificador único.

Além do identificador interno do banco, o pedido deverá possuir um número utilizado para identificação dentro do restaurante.

Exemplo:

```text
Pedido #1024
```

O número do pedido deverá ser único dentro do restaurante.

---

# 20. Pontos Ainda a Definir

Os seguintes detalhes não foram definidos durante a especificação inicial e deverão ser decididos antes da implementação definitiva do banco:

- Estratégia dos identificadores (`UUID`, `Long`, etc.).
- Tipos exatos das colunas.
- Estratégia de geração do número do pedido.
- Necessidade de índices específicos.
- Estratégia de exclusão/desativação de registros.
- Campos adicionais de endereço para pedidos de entrega.
- Necessidade de informações adicionais do cliente.
- Estratégia de comunicação das notificações em tempo real.
- Estratégia de auditoria detalhada.
- Tecnologia definitiva do banco de dados.
- Regras específicas de permissão para transferência de comandas.

Esses pontos devem ser registrados posteriormente no **Registro de Decisões do Projeto**, caso sejam tomadas decisões relevantes sobre eles.

---

# 21. Observação sobre Evolução

O modelo deverá permitir futuras extensões do Saborfy sem comprometer os dados existentes.

Funcionalidades futuras como:

- Integração com WhatsApp.
- Processamento de pagamentos.
- Adicionais de produtos.
- Personalização de produtos.

não deverão ser utilizadas como requisito para a primeira versão do banco, mas a estrutura deverá evitar decisões que dificultem sua implementação futura.

As notificações não serão persistidas no banco de dados nesta versão. Caso futuramente seja necessário armazenar histórico de notificações, essa decisão deverá ser registrada e uma entidade específica poderá ser adicionada posteriormente.