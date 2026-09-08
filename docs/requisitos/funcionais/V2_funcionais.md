# Version 1.1 - 06/09/2026

# Requisitos Funcionais — Saborfy

## 1. Autenticação e Usuários

| ID | Requisito | Prioridade |
|---|---|---|
| RF-AUTH-001 | O sistema deve permitir que funcionários realizem login utilizando suas credenciais. | Essencial |
| RF-AUTH-002 | O sistema deve permitir que o gerente cadastre funcionários informando nome, senha e cargo. | Essencial |
| RF-AUTH-003 | O sistema deve gerar um identificador numérico único para cada funcionário dentro do restaurante. | Essencial |
| RF-AUTH-004 | O sistema deve permitir que o gerente consulte, edite, desative e gerencie os funcionários cadastrados. | Essencial |
| RF-AUTH-005 | O sistema deve controlar o acesso às funcionalidades de acordo com o cargo do funcionário. | Essencial |

## 2. Gerenciamento do Restaurante

| ID | Requisito | Prioridade |
|---|---|---|
| RF-REST-001 | O sistema deve permitir o cadastro e identificação do restaurante. | Essencial |
| RF-REST-002 | O sistema deve manter os dados de cada restaurante separados dos dados dos demais restaurantes. | Essencial |
| RF-REST-003 | O sistema deve permitir a configuração inicial do restaurante após seu cadastro. | Essencial |

## 3. Gerenciamento de Mesas

| ID | Requisito | Prioridade |
|---|---|---|
| RF-MESA-001 | O sistema deve permitir que o gerente cadastre mesas dinamicamente. | Essencial |
| RF-MESA-002 | O sistema deve permitir que o gerente altere os dados de uma mesa. | Essencial |
| RF-MESA-003 | O sistema deve permitir que o gerente desative uma mesa. | Importante |
| RF-MESA-004 | O sistema deve permitir a consulta das mesas cadastradas. | Essencial |

## 4. Gerenciamento de Categorias

| ID | Requisito | Prioridade |
|---|---|---|
| RF-CAT-001 | O sistema deve permitir que o gerente cadastre categorias de produtos. | Essencial |
| RF-CAT-002 | O sistema deve permitir que o gerente edite categorias cadastradas. | Essencial |
| RF-CAT-003 | O sistema deve permitir que o gerente desative categorias. | Importante |
| RF-CAT-004 | O sistema deve permitir a consulta das categorias cadastradas. | Essencial |

## 5. Gerenciamento de Produtos

| ID | Requisito | Prioridade |
|---|---|---|
| RF-PROD-001 | O sistema deve permitir que o gerente cadastre produtos informando nome, descrição, preço, categoria e disponibilidade. | Essencial |
| RF-PROD-002 | O sistema deve permitir que o gerente edite os dados dos produtos. | Essencial |
| RF-PROD-003 | O sistema deve permitir que o gerente desative produtos. | Essencial |
| RF-PROD-004 | O sistema deve permitir a consulta dos produtos cadastrados. | Essencial |
| RF-PROD-005 | O sistema deve identificar visualmente produtos indisponíveis e impedir que sejam adicionados a novos pedidos. | Essencial |

## 6. Gerenciamento de Comandas

| ID | Requisito | Prioridade |
|---|---|---|
| RF-COM-001 | O sistema deve permitir a abertura de uma comanda vinculada a uma mesa. | Essencial |
| RF-COM-002 | O sistema deve exigir o nome do cliente durante a abertura da comanda. | Essencial |
| RF-COM-003 | O sistema deve permitir visualizar as comandas abertas de uma determinada mesa. | Essencial |
| RF-COM-004 | O sistema deve permitir selecionar uma comanda existente para registrar novos pedidos. | Essencial |
| RF-COM-005 | O sistema deve permitir que uma comanda permaneça aberta mesmo sem possuir pedidos. | Importante |
| RF-COM-006 | O sistema deve permitir o fechamento de uma comanda por funcionários autorizados. | Essencial |
| RF-COM-007 | O sistema deve calcular e apresentar o valor total da comanda. | Essencial |
| RF-COM-008 | O sistema deve permitir que uma comanda seja transferida de uma mesa para outra. | Essencial |

## 7. Gerenciamento de Pedidos

| ID | Requisito | Prioridade |
|---|---|---|
| RF-PED-001 | O sistema deve permitir a criação de pedidos vinculados a uma comanda. | Essencial |
| RF-PED-002 | O sistema deve permitir adicionar produtos e suas respectivas quantidades ao pedido. | Essencial |
| RF-PED-003 | O sistema deve permitir adicionar uma observação ao pedido. | Importante |
| RF-PED-004 | O sistema deve calcular automaticamente o subtotal dos itens e o total do pedido. | Essencial |
| RF-PED-005 | O sistema deve permitir o envio do pedido para a cozinha. | Essencial |
| RF-PED-006 | O sistema deve permitir consultar os pedidos vinculados a uma comanda. | Essencial |
| RF-PED-007 | O sistema deve permitir alterar um pedido enquanto sua preparação ainda não tiver sido iniciada pela cozinha. | Essencial |
| RF-PED-008 | O sistema deve impedir alterações em pedidos cuja preparação já tenha sido iniciada. | Essencial |
| RF-PED-009 | O sistema deve permitir criar um novo pedido para a mesma comanda quando novos itens forem solicitados após o início da preparação de um pedido anterior. | Essencial |
| RF-PED-010 | O sistema deve permitir o cancelamento de pedidos por funcionários autorizados. | Essencial |
| RF-PED-011 | O sistema deve exigir o registro de um motivo para o cancelamento de um pedido. | Essencial |
| RF-PED-012 | O sistema deve identificar pedidos de entrega por meio do atributo `is_delivery`. | Essencial |

## 8. Operação da Cozinha

| ID | Requisito | Prioridade |
|---|---|---|
| RF-COZ-001 | O sistema deve disponibilizar uma tela específica para a cozinha visualizar os pedidos pendentes. | Essencial |
| RF-COZ-002 | O sistema deve disponibilizar uma área específica para os pedidos em preparação. | Essencial |
| RF-COZ-003 | O sistema deve permitir que a cozinha marque o início da preparação de um pedido. | Essencial |
| RF-COZ-004 | O sistema deve permitir que a cozinha marque um pedido como pronto após finalizar sua preparação. | Essencial |
| RF-COZ-005 | O sistema deve organizar os pedidos da cozinha de acordo com seus respectivos status. | Essencial |
| RF-COZ-006 | O sistema deve remover da tela operacional da cozinha os pedidos que já foram finalizados. | Importante |

## 9. Pedidos Prontos e Notificações

| ID         | Requisito                                                                                    | Prioridade |
|------------|----------------------------------------------------------------------------------------------|---|
| RF-NOT-001 | O sistema deve permitir que funcionários autorizados visualizem os pedidos que estão prontos. | Essencial |
| RF-NOT-002 | O sistema deve manter atualizado a tela de pedidos.                                          | Essencial |
| RF-NOT-003 | O sistema deve permitir que o funcionário registre que o pedido foi entregue à mesa.         | Essencial |

## 10. Pagamentos

| ID | Requisito | Prioridade |
|---|---|---|
| RF-PAG-001 | O sistema deve permitir registrar pagamentos vinculados a uma comanda. | Essencial |
| RF-PAG-002 | O sistema deve permitir informar a forma de pagamento utilizada. | Essencial |
| RF-PAG-003 | O sistema deve permitir informar o valor do pagamento. | Essencial |
| RF-PAG-004 | O sistema deve permitir adicionar uma observação ao pagamento. | Desejável |
| RF-PAG-005 | O sistema deve permitir registrar mais de um pagamento para a mesma comanda. | Essencial |
| RF-PAG-006 | O sistema deve calcular o valor total dos pagamentos registrados na comanda. | Essencial |
| RF-PAG-007 | O sistema deve permitir o fechamento da comanda somente quando o total dos pagamentos for igual ao valor total da comanda. | Essencial |
| RF-PAG-008 | O sistema deve permitir o fechamento de comandas cujo valor total seja R$ 0,00 sem a necessidade de registrar pagamento. | Essencial |
| RF-PAG-009 | O sistema deve disponibilizar as formas de pagamento inicialmente suportadas: dinheiro, PIX, cartão de crédito e cartão de débito. | Essencial |

## 11. Gerenciamento de Entregas

| ID | Requisito | Prioridade |
|---|---|---|
| RF-ENT-001 | O sistema deve permitir o registro de pedidos destinados à entrega. | Essencial |
| RF-ENT-002 | O sistema deve identificar visualmente os pedidos que são destinados à entrega. | Importante |
| RF-ENT-003 | O sistema deve permitir acompanhar o fluxo de pedidos de entrega. | Essencial |
| RF-ENT-004 | O sistema deve permitir atualizar o status de uma entrega entre `NOVO`, `EM_PREPARO`, `PRONTO`, `SAIU_PARA_ENTREGA` e `ENTREGUE`. | Essencial |

## 12. Histórico e Auditoria

| ID | Requisito | Prioridade |
|---|---|---|
| RF-HIST-001 | O sistema deve disponibilizar uma tela de histórico de pedidos. | Essencial |
| RF-HIST-002 | O sistema deve permitir filtrar o histórico pelo número do pedido. | Importante |
| RF-HIST-003 | O sistema deve permitir filtrar o histórico por período/data. | Importante |
| RF-HIST-004 | O sistema deve permitir filtrar o histórico pelo nome do cliente. | Importante |
| RF-HIST-005 | O sistema deve permitir filtrar o histórico pela mesa. | Importante |
| RF-HIST-006 | O sistema deve permitir filtrar o histórico pelo status do pedido. | Importante |
| RF-HIST-007 | O sistema deve permitir filtrar o histórico por pedidos de entrega. | Importante |
| RF-HIST-008 | O sistema deve registrar ações relevantes realizadas pelos funcionários no sistema. | Essencial |
| RF-HIST-009 | O sistema deve identificar o funcionário responsável por cada ação registrada. | Essencial |
| RF-HIST-010 | O sistema deve registrar a data e o horário das ações realizadas. | Essencial |

## 13. Definição de Prioridades

### Essencial

Funcionalidades indispensáveis para que o Saborfy cumpra sua finalidade principal e possa ser utilizado no funcionamento diário do restaurante.

### Importante

Funcionalidades relevantes para melhorar a operação, organização e experiência de uso, mas que não impedem o funcionamento básico do sistema.

### Desejável

Funcionalidades que agregam valor ao sistema, mas que podem ser implementadas posteriormente sem comprometer o funcionamento principal do Saborfy.