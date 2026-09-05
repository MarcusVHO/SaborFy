# Requisitos Funcionais — Saborfy

## 1. Autenticação e Usuários

| ID    | Requisito | Prioridade |
|-------|---|---|
| RF001 | O sistema deve permitir que funcionários realizem login utilizando suas credenciais. | Essencial |
| RF002 | O sistema deve permitir que o gerente cadastre funcionários informando nome, senha e cargo. | Essencial |
| RF003 | O sistema deve gerar um identificador numérico único para cada funcionário dentro do restaurante. | Essencial |
| RF004 | O sistema deve permitir que o gerente consulte, edite, desative e gerencie os funcionários cadastrados. | Essencial |
| RF005 | O sistema deve controlar o acesso às funcionalidades de acordo com o cargo do funcionário. | Essencial |

## 2. Gerenciamento do Restaurante

| ID    | Requisito | Prioridade |
|-------|---|---|
| RF006 | O sistema deve permitir o cadastro e identificação do restaurante. | Essencial |
| RF007 | O sistema deve manter os dados de cada restaurante separados dos dados dos demais restaurantes. | Essencial |
| RF008 | O sistema deve permitir a configuração inicial do restaurante após seu cadastro. | Essencial |

## 3. Gerenciamento de Mesas

| ID    | Requisito | Prioridade |
|-------|---|---|
| RF009 | O sistema deve permitir que o gerente cadastre mesas dinamicamente. | Essencial |
| RF010 | O sistema deve permitir que o gerente altere os dados de uma mesa. | Essencial |
| RF011 | O sistema deve permitir que o gerente desative uma mesa. | Importante |
| RF012 | O sistema deve permitir a consulta das mesas cadastradas. | Essencial |

## 4. Gerenciamento de Categorias

| ID    | Requisito | Prioridade |
|-------|---|---|
| RF013 | O sistema deve permitir que o gerente cadastre categorias de produtos. | Essencial |
| RF014 | O sistema deve permitir que o gerente edite categorias cadastradas. | Essencial |
| RF015 | O sistema deve permitir que o gerente desative categorias. | Importante |
| RF016 | O sistema deve permitir a consulta das categorias cadastradas. | Essencial |

## 5. Gerenciamento de Produtos

| ID    | Requisito | Prioridade |
|-------|---|---|
| RF017 | O sistema deve permitir que o gerente cadastre produtos informando nome, descrição, preço, categoria e disponibilidade. | Essencial |
| RF018 | O sistema deve permitir que o gerente edite os dados dos produtos. | Essencial |
| RF019 | O sistema deve permitir que o gerente desative produtos. | Essencial |
| RF020 | O sistema deve permitir a consulta dos produtos cadastrados. | Essencial |
| RF021 | O sistema deve identificar visualmente produtos indisponíveis e impedir que sejam adicionados a novos pedidos. | Essencial |

## 6. Gerenciamento de Comandas

| ID    | Requisito | Prioridade |
|-------|---|---|
| RF022 | O sistema deve permitir a abertura de uma comanda vinculada a uma mesa. | Essencial |
| RF023 | O sistema deve exigir o nome do cliente durante a abertura da comanda. | Essencial |
| RF024 | O sistema deve permitir visualizar as comandas abertas de uma determinada mesa. | Essencial |
| RF025 | O sistema deve permitir selecionar uma comanda existente para registrar novos pedidos. | Essencial |
| RF026 | O sistema deve permitir que uma comanda permaneça aberta mesmo sem possuir pedidos. | Importante |
| RF027 | O sistema deve permitir o fechamento de uma comanda por funcionários autorizados. | Essencial |
| RF028 | O sistema deve calcular e apresentar o valor total da comanda. | Essencial |

## 7. Gerenciamento de Pedidos

| ID    | Requisito | Prioridade |
|-------|---|---|
| RF029 | O sistema deve permitir a criação de pedidos vinculados a uma comanda. | Essencial |
| RF030 | O sistema deve permitir adicionar produtos e suas respectivas quantidades ao pedido. | Essencial |
| RF031 | O sistema deve permitir adicionar uma observação ao pedido. | Importante |
| RF032 | O sistema deve calcular automaticamente o subtotal dos itens e o total do pedido. | Essencial |
| RF033 | O sistema deve permitir o envio do pedido para a cozinha. | Essencial |
| RF034 | O sistema deve permitir consultar os pedidos vinculados a uma comanda. | Essencial |
| RF035 | O sistema deve permitir alterar um pedido enquanto sua preparação ainda não tiver sido iniciada pela cozinha. | Essencial |
| RF036 | O sistema deve impedir alterações em pedidos cuja preparação já tenha sido iniciada. | Essencial |
| RF037 | O sistema deve permitir criar um novo pedido para a mesma comanda quando novos itens forem solicitados após o início da preparação de um pedido anterior. | Essencial |
| RF038 | O sistema deve permitir o cancelamento de pedidos por funcionários autorizados. | Essencial |
| RF039 | O sistema deve exigir o registro de um motivo para o cancelamento de um pedido. | Essencial |
| RF040 | O sistema deve identificar pedidos de entrega por meio do atributo `is_delivery`. | Essencial |

## 8. Operação da Cozinha

| ID    | Requisito | Prioridade |
|-------|---|---|
| RF041 | O sistema deve disponibilizar uma tela específica para a cozinha visualizar os pedidos pendentes. | Essencial |
| RF042 | O sistema deve disponibilizar uma área específica para os pedidos em preparação. | Essencial |
| RF043 | O sistema deve permitir que a cozinha marque o início da preparação de um pedido. | Essencial |
| RF044 | O sistema deve permitir que a cozinha marque um pedido como pronto após finalizar sua preparação. | Essencial |
| RF045 | O sistema deve organizar os pedidos da cozinha de acordo com seus respectivos status. | Essencial |
| RF046 | O sistema deve remover da tela operacional da cozinha os pedidos que já foram finalizados. | Importante |

## 9. Pedidos Prontos e Notificações

| ID    | Requisito | Prioridade |
|-------|---|---|
| RF047 | O sistema deve permitir que funcionários autorizados visualizem os pedidos que estão prontos. | Essencial |
| RF048 | O sistema deve notificar os funcionários responsáveis quando um pedido estiver pronto. | Essencial |
| RF049 | O sistema deve utilizar notificações do navegador para informar sobre pedidos prontos. | Importante |
| RF050 | O sistema deve disponibilizar uma central de notificações dentro da aplicação. | Importante |
| RF051 | O sistema deve permitir que o funcionário registre que o pedido foi entregue à mesa. | Essencial |

## 10. Pagamentos

| ID    | Requisito | Prioridade |
|-------|---|---|
| RF052 | O sistema deve permitir registrar pagamentos vinculados a uma comanda. | Essencial |
| RF053 | O sistema deve permitir informar a forma de pagamento utilizada. | Essencial |
| RF054 | O sistema deve permitir informar o valor do pagamento. | Essencial |
| RF055 | O sistema deve permitir adicionar uma observação ao pagamento. | Desejável |
| RF056 | O sistema deve permitir registrar mais de um pagamento para a mesma comanda. | Essencial |
| RF057 | O sistema deve calcular o valor total dos pagamentos registrados na comanda. | Essencial |
| RF058 | O sistema deve permitir o fechamento da comanda somente quando o total dos pagamentos for igual ao valor total da comanda. | Essencial |
| RF059 | O sistema deve permitir o fechamento de comandas cujo valor total seja R$ 0,00 sem a necessidade de registrar pagamento. | Essencial |
| RF060 | O sistema deve disponibilizar as formas de pagamento inicialmente suportadas: dinheiro, PIX, cartão de crédito e cartão de débito. | Essencial |

## 11. Gerenciamento de Entregas

| ID    | Requisito | Prioridade |
|-------|---|---|
| RF061 | O sistema deve permitir o registro de pedidos destinados à entrega. | Essencial |
| RF062 | O sistema deve identificar visualmente os pedidos que são destinados à entrega. | Importante |
| RF063 | O sistema deve permitir acompanhar o fluxo de pedidos de entrega. | Essencial |
| RF064 | O sistema deve permitir atualizar o status de uma entrega entre `NOVO`, `EM_PREPARO`, `PRONTO`, `SAIU_PARA_ENTREGA` e `ENTREGUE`. | Essencial |

## 12. Histórico e Auditoria

| ID    | Requisito | Prioridade |
|-------|---|---|
| RF065 | O sistema deve disponibilizar uma tela de histórico de pedidos. | Essencial |
| RF066 | O sistema deve permitir filtrar o histórico pelo número do pedido. | Importante |
| RF067 | O sistema deve permitir filtrar o histórico por período/data. | Importante |
| RF068 | O sistema deve permitir filtrar o histórico pelo nome do cliente. | Importante |
| RF069 | O sistema deve permitir filtrar o histórico pela mesa. | Importante |
| RF070 | O sistema deve permitir filtrar o histórico pelo status do pedido. | Importante |
| RF071 | O sistema deve permitir filtrar o histórico por pedidos de entrega. | Importante |
| RF072 | O sistema deve registrar ações relevantes realizadas pelos funcionários no sistema. | Essencial |
| RF073 | O sistema deve identificar o funcionário responsável por cada ação registrada. | Essencial |
| RF074 | O sistema deve registrar a data e o horário das ações realizadas. | Essencial |

## 13. Definição de Prioridades

### Essencial
Funcionalidades indispensáveis para que o Saborfy cumpra sua finalidade principal e possa ser utilizado no funcionamento diário do restaurante.

### Importante
Funcionalidades relevantes para melhorar a operação, organização e experiência de uso, mas que não impedem o funcionamento básico do sistema.

### Desejável
Funcionalidades que agregam valor ao sistema, mas que podem ser implementadas posteriormente sem comprometer o funcionamento principal do Saborfy.