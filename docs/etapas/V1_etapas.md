# Saborfy — Estrutura de Desenvolvimento

## 🗄️ 01 — Banco de Dados

### Backend
- [ ] Implementar entidades conforme modelo lógico
- [ ] Configurar relacionamentos JPA/ORM
- [ ] Criar migrations
- [ ] Implementar constraints
- [ ] Criar índices necessários
- [ ] Implementar isolamento por `restaurante_id`
- [ ] Validar estrutura do banco

### Testes
- [ ] Validar migrations
- [ ] Validar relacionamentos
- [ ] Validar constraints
- [ ] Validar isolamento entre restaurantes

---

# 🔐 02 — Autenticação e Usuários

## Backend

### Usuários
- [ ] Implementar CRUD de usuários
- [ ] Gerar identificador numérico do usuário
- [ ] Associar usuário ao restaurante
- [ ] Implementar alteração de dados do usuário
- [ ] Implementar desativação de usuário

### Autenticação
- [ ] Implementar login
- [ ] Implementar geração/validação de sessão ou token
- [ ] Implementar autenticação das rotas
- [ ] Implementar logout/invalidação quando aplicável

### Autorização
- [ ] Implementar permissões por cargo
- [ ] Implementar acesso do Gerente
- [ ] Implementar acesso do Garçom
- [ ] Implementar acesso do Caixa
- [ ] Implementar acesso da Cozinha
- [ ] Garantir isolamento entre restaurantes

## Frontend

- [ ] Criar tela de login
- [ ] Implementar gerenciamento de sessão
- [ ] Implementar proteção das rotas
- [ ] Implementar controle de acesso por cargo
- [ ] Criar tela de gerenciamento de usuários

## Testes

- [ ] Testar login
- [ ] Testar autenticação
- [ ] Testar permissões
- [ ] Testar isolamento entre restaurantes

---

# 🏪 03 — Restaurante

## Backend

- [ ] Implementar cadastro do restaurante
- [ ] Implementar consulta do restaurante
- [ ] Implementar edição do restaurante
- [ ] Implementar associação de usuários
- [ ] Implementar permissões de gerenciamento

## Frontend

- [ ] Criar tela de informações do restaurante
- [ ] Criar formulário de edição
- [ ] Criar gerenciamento básico do restaurante

## Testes

- [ ] Testar cadastro
- [ ] Testar consulta
- [ ] Testar edição
- [ ] Testar permissões

---

# 🪑 04 — Mesas

## Backend

- [ ] Implementar CRUD de mesas
- [ ] Implementar listagem de mesas
- [ ] Implementar edição
- [ ] Implementar desativação
- [ ] Implementar consulta da situação das mesas

## Frontend

- [ ] Criar tela de mesas
- [ ] Criar cadastro de mesa
- [ ] Criar edição de mesa
- [ ] Criar desativação de mesa
- [ ] Exibir situação das mesas

## Testes

- [ ] Testar CRUD
- [ ] Testar desativação
- [ ] Testar permissões
- [ ] Testar situação das mesas

---

# 🏷️ 05 — Categorias

## Backend

- [ ] Implementar CRUD de categorias
- [ ] Implementar listagem
- [ ] Implementar edição
- [ ] Implementar desativação
- [ ] Validar associação com restaurante

## Frontend

- [ ] Criar tela de categorias
- [ ] Criar cadastro
- [ ] Criar edição
- [ ] Criar desativação

## Testes

- [ ] Testar CRUD
- [ ] Testar desativação
- [ ] Testar permissões
- [ ] Testar isolamento entre restaurantes

---

# 🍔 06 — Produtos

## Backend

- [ ] Implementar CRUD de produtos
- [ ] Implementar associação com categoria
- [ ] Implementar preço
- [ ] Implementar disponibilidade
- [ ] Implementar desativação
- [ ] Implementar snapshot de nome/preço no `PedidoItem`

## Frontend

- [ ] Criar tela de produtos
- [ ] Criar cadastro de produto
- [ ] Criar edição
- [ ] Criar controle de disponibilidade
- [ ] Criar desativação
- [ ] Criar filtro por categoria

## Testes

- [ ] Testar CRUD
- [ ] Testar disponibilidade
- [ ] Testar desativação
- [ ] Testar preço
- [ ] Testar snapshot do produto

---

# 📋 07 — Comandas

## Backend

### Abertura
- [ ] Implementar abertura de comanda
- [ ] Validar nome obrigatório do cliente
- [ ] Associar comanda à mesa

### Consulta
- [ ] Listar comandas abertas da mesa
- [ ] Consultar detalhes da comanda
- [ ] Calcular total da comanda

### Transferência
- [ ] Implementar transferência de comanda
- [ ] Alterar apenas `mesa_id`
- [ ] Preservar pedidos
- [ ] Preservar pagamentos
- [ ] Preservar demais informações
- [ ] Registrar transferência na auditoria

### Fechamento
- [ ] Implementar fechamento da comanda
- [ ] Validar pagamentos
- [ ] Validar valor restante
- [ ] Permitir fechamento com total `R$ 0,00`

## Frontend

- [ ] Criar tela de seleção de mesa
- [ ] Exibir comandas abertas da mesa
- [ ] Criar abertura de comanda
- [ ] Criar tela de detalhes da comanda
- [ ] Exibir pedidos da comanda
- [ ] Exibir total
- [ ] Criar transferência de comanda
- [ ] Criar fechamento da comanda

## Testes

- [ ] Testar abertura
- [ ] Testar nome obrigatório
- [ ] Testar associação com mesa
- [ ] Testar múltiplas comandas
- [ ] Testar transferência
- [ ] Testar preservação dos dados
- [ ] Testar fechamento
- [ ] Testar fechamento com total zero

---

# 🧾 08 — Pedidos

## Backend

### Criação
- [ ] Implementar criação de pedido
- [ ] Gerar número único do pedido
- [ ] Associar pedido à comanda
- [ ] Adicionar produtos
- [ ] Validar quantidade
- [ ] Adicionar observação
- [ ] Calcular subtotal dos itens
- [ ] Calcular total do pedido

### Cozinha
- [ ] Implementar envio do pedido para cozinha
- [ ] Implementar status do pedido

### Alteração
- [ ] Permitir alteração antes do preparo
- [ ] Bloquear alteração após início do preparo
- [ ] Permitir criação de novo pedido para itens adicionais

### Cancelamento
- [ ] Implementar cancelamento
- [ ] Exigir motivo do cancelamento
- [ ] Registrar responsável pelo cancelamento

### Delivery
- [ ] Implementar identificação de pedido delivery

## Frontend

- [ ] Criar tela de criação de pedido
- [ ] Selecionar mesa
- [ ] Selecionar comanda
- [ ] Selecionar categoria
- [ ] Selecionar produtos
- [ ] Definir quantidades
- [ ] Adicionar observação
- [ ] Exibir resumo do pedido
- [ ] Enviar pedido
- [ ] Permitir alteração antes do preparo
- [ ] Exibir bloqueio após início do preparo
- [ ] Criar cancelamento de pedido
- [ ] Criar seleção de delivery

## Testes

- [ ] Testar criação
- [ ] Testar cálculo de subtotal
- [ ] Testar cálculo do total
- [ ] Testar múltiplos pedidos na mesma comanda
- [ ] Testar alteração
- [ ] Testar bloqueio após preparo
- [ ] Testar cancelamento
- [ ] Testar motivo obrigatório
- [ ] Testar delivery

---

# 👨‍🍳 09 — Cozinha

## Backend

- [ ] Implementar status `NOVO`
- [ ] Implementar status `EM_PREPARO`
- [ ] Implementar status `PRONTO`
- [ ] Implementar início do preparo
- [ ] Implementar finalização do preparo
- [ ] Criar endpoint de pedidos pendentes
- [ ] Criar endpoint de pedidos em preparo

## Frontend

- [ ] Criar tela da cozinha
- [ ] Criar área de pedidos novos
- [ ] Criar área de pedidos em preparo
- [ ] Criar ação "Iniciar preparo"
- [ ] Criar ação "Finalizar preparo"
- [ ] Exibir informações do pedido
- [ ] Exibir observações
- [ ] Destacar delivery

## Testes

- [ ] Testar transição de status
- [ ] Testar pedidos pendentes
- [ ] Testar pedidos em preparo
- [ ] Testar finalização

---

# 🔔 10 — Pedidos Prontos e Comunicação em Tempo Real

## Backend

- [ ] Definir mecanismo de comunicação em tempo real
- [ ] Implementar evento de pedido pronto
- [ ] Publicar evento quando pedido ficar pronto
- [ ] Implementar atualização dos clientes conectados

## Frontend

- [ ] Implementar recebimento de atualização em tempo real
- [ ] Destacar pedidos prontos
- [ ] Atualizar automaticamente a tela
- [ ] Permitir marcar pedido como entregue à mesa

## Testes

- [ ] Testar evento de pedido pronto
- [ ] Testar comunicação em tempo real
- [ ] Testar atualização automática
- [ ] Testar múltiplos usuários conectados

---

# 💰 11 — Pagamentos

## Backend

- [ ] Implementar registro de pagamento
- [ ] Implementar formas de pagamento
- [ ] Permitir múltiplos pagamentos
- [ ] Calcular valor pago
- [ ] Calcular valor restante
- [ ] Validar pagamento contra total da comanda
- [ ] Permitir fechamento com total `R$ 0,00`
- [ ] Registrar observação do pagamento
- [ ] Registrar usuário responsável

## Frontend

- [ ] Criar tela de pagamentos
- [ ] Exibir total da comanda
- [ ] Exibir valor pago
- [ ] Exibir valor restante
- [ ] Criar registro de pagamento
- [ ] Selecionar forma de pagamento
- [ ] Adicionar observação
- [ ] Exibir pagamentos realizados
- [ ] Criar fechamento da comanda

## Testes

- [ ] Testar pagamento único
- [ ] Testar múltiplos pagamentos
- [ ] Testar cálculo do valor restante
- [ ] Testar formas de pagamento
- [ ] Testar pagamento acima do total
- [ ] Testar fechamento com valor pendente
- [ ] Testar fechamento com total zero

---

# 🛵 12 — Delivery

## Backend

- [ ] Implementar criação de pedido delivery
- [ ] Implementar fluxo de status
- [ ] Implementar `SAIU_PARA_ENTREGA`
- [ ] Implementar `ENTREGUE`
- [ ] Validar transições de status

## Frontend

- [ ] Identificar pedidos delivery
- [ ] Criar visualização de delivery
- [ ] Exibir status da entrega
- [ ] Permitir atualizar status
- [ ] Exibir pedidos aguardando entrega

## Testes

- [ ] Testar criação de delivery
- [ ] Testar fluxo de status
- [ ] Testar transições inválidas
- [ ] Testar entrega finalizada

---

# 📚 13 — Histórico

## Backend

- [ ] Criar consulta de pedidos finalizados
- [ ] Criar consulta de pedidos cancelados
- [ ] Implementar filtro por número
- [ ] Implementar filtro por data
- [ ] Implementar filtro por cliente
- [ ] Implementar filtro por mesa
- [ ] Implementar filtro por status
- [ ] Implementar filtro por delivery

## Frontend

- [ ] Criar tela de histórico
- [ ] Criar filtros
- [ ] Criar listagem de pedidos
- [ ] Criar visualização dos detalhes
- [ ] Exibir informações do pedido
- [ ] Exibir informações de cancelamento

## Testes

- [ ] Testar filtros
- [ ] Testar pedidos finalizados
- [ ] Testar pedidos cancelados
- [ ] Testar histórico de delivery

---

# 📝 14 — Auditoria

## Backend

- [ ] Criar serviço de auditoria
- [ ] Registrar criação
- [ ] Registrar alterações
- [ ] Registrar cancelamentos
- [ ] Registrar fechamento de comanda
- [ ] Registrar transferência de comanda
- [ ] Registrar pagamentos
- [ ] Registrar usuário responsável
- [ ] Registrar data e hora
- [ ] Implementar consulta de auditoria

## Frontend

- [ ] Criar tela de auditoria
- [ ] Criar filtros
- [ ] Exibir responsável
- [ ] Exibir ação
- [ ] Exibir entidade
- [ ] Exibir data e hora
- [ ] Exibir descrição

## Testes

- [ ] Testar geração dos registros
- [ ] Testar responsável
- [ ] Testar data/hora
- [ ] Testar consulta
- [ ] Testar filtros

---

# 🧪 15 — Testes e Qualidade

## Backend

- [ ] Criar estratégia de testes
- [ ] Criar testes unitários
- [ ] Criar testes de integração
- [ ] Criar testes de autorização
- [ ] Criar testes de isolamento entre restaurantes
- [ ] Criar testes dos principais fluxos

## Frontend

- [ ] Testar principais telas
- [ ] Testar navegação
- [ ] Testar permissões
- [ ] Testar formulários
- [ ] Testar tratamento de erros
- [ ] Testar responsividade

## E2E

- [ ] Testar login
- [ ] Testar abertura de comanda
- [ ] Testar criação de pedido
- [ ] Testar preparo na cozinha
- [ ] Testar pedido pronto
- [ ] Testar pagamento
- [ ] Testar fechamento
- [ ] Testar delivery

---

# 🚀 16 — Deploy e Produção

## Infraestrutura

- [ ] Configurar Docker do backend
- [ ] Configurar Docker do frontend
- [ ] Configurar banco de produção
- [ ] Configurar variáveis de ambiente
- [ ] Configurar volumes
- [ ] Configurar rede dos containers

## CI/CD

- [ ] Criar pipeline de build
- [ ] Criar pipeline de testes
- [ ] Criar pipeline de publicação
- [ ] Configurar deploy automático

## Produção

- [ ] Configurar servidor
- [ ] Configurar domínio
- [ ] Configurar HTTPS
- [ ] Configurar proxy reverso
- [ ] Configurar logs
- [ ] Validar aplicação em produção
- [ ] Realizar teste completo do sistema

📋 Backlog
🔵 A Fazer
🟡 Em Desenvolvimento
🟣 Em Revisão
🟠 Em Testes
🔴 Bloqueado
🟢 Concluído