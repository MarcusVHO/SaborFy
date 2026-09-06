#Version 2.0 - 06/09/2026
# Regras de Negócio — Saborfy

| ID    | Regra de Negócio                                                                                                                                                                         |
|-------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| RN001 | Os dados de um restaurante não podem ser acessados por funcionários pertencentes a outro restaurante.                                                                                    |
| RN002 | Cada funcionário deve estar associado exclusivamente a um restaurante.                                                                                                                   |
| RN003 | Caso uma mesma pessoa trabalhe em restaurantes diferentes, deverá possuir uma conta independente para cada restaurante.                                                                  |
| RN004 | Cada funcionário deve possuir um identificador numérico único dentro do restaurante.                                                                                                     |
| RN005 | Somente funcionários com cargo de Gerente podem cadastrar, editar, desativar e gerenciar funcionários.                                                                                   |
| RN006 | Somente funcionários com cargo de Gerente podem gerenciar mesas, categorias e produtos.                                                                                                  |
| RN007 | Cada comanda deve estar vinculada a exatamente uma mesa.                                                                                                                                 |
| RN008 | Uma mesma mesa pode possuir várias comandas abertas simultaneamente.                                                                                                                     |
| RN009 | Toda comanda deve possuir obrigatoriamente o nome do cliente.                                                                                                                            |
| RN010 | Uma comanda pode permanecer aberta mesmo sem possuir pedidos.                                                                                                                            |
| RN011 | Uma comanda permanece aberta até que um funcionário autorizado realize seu fechamento.                                                                                                   |
| RN012 | Uma comanda pode possuir vários pedidos.                                                                                                                                                 |
| RN013 | Cada pedido deve possuir um número identificador único dentro do restaurante.                                                                                                            |
| RN014 | Um pedido pode ser alterado somente enquanto sua preparação não tiver sido iniciada pela cozinha.                                                                                        |
| RN015 | Após o início da preparação, o pedido não pode mais ser alterado.                                                                                                                        |
| RN016 | Caso sejam necessários novos itens após o início da preparação de um pedido, deverá ser criado um novo pedido vinculado à mesma comanda.                                                 |
| RN017 | Os cargos de Gerente, Garçom e Caixa podem realizar o cancelamento de pedidos.                                                                                                           |
| RN018 | Todo pedido cancelado deve possuir um motivo registrado.                                                                                                                                 |
| RN019 | A cozinha é responsável por registrar o início e a conclusão da preparação dos pedidos.                                                                                                  |
| RN020 | Após a conclusão da preparação, o pedido deve ficar disponível para os funcionários responsáveis pela entrega à mesa ou pela operação de entrega.                                        |
| RN021 | O funcionário responsável deve registrar quando um pedido destinado à mesa foi entregue.                                                                                                 |
| RN022 | O Saborfy não realiza o processamento financeiro do pagamento; apenas registra as informações fornecidas pelo restaurante.                                                               |
| RN023 | Uma mesma comanda pode possuir vários registros de pagamento.                                                                                                                            |
| RN024 | A soma dos pagamentos registrados deve ser exatamente igual ao valor total da comanda para que ela possa ser fechada.                                                                    |
| RN025 | O sistema não deve permitir o fechamento de uma comanda quando o valor pago for superior ao valor total da comanda.                                                                      |
| RN026 | Comandas com valor total de R$ 0,00 podem ser fechadas sem registro de pagamento.                                                                                                        |
| RN027 | O valor e o nome do produto utilizados em um pedido devem ser armazenados como uma cópia no momento da criação do pedido, garantindo a preservação do histórico.                         |
| RN028 | Produtos não devem ser excluídos fisicamente do sistema; devem ser desativados quando deixarem de ser utilizados.                                                                        |
| RN029 | Produtos desativados não podem ser adicionados a novos pedidos, mas devem continuar disponíveis nos registros históricos em que foram utilizados.                                        |
| RN030 | Pedidos destinados à entrega devem ser identificados pelo atributo `is_delivery`.                                                                                                        |
| RN031 | Pedidos finalizados ou cancelados devem permanecer disponíveis no histórico.                                                                                                             |
| RN032 | Ações relevantes realizadas no sistema devem possuir registro de auditoria contendo o funcionário responsável, data e horário.                                                           |
| RN033 | Todos os valores monetários do sistema devem utilizar o Real brasileiro (BRL).                                                                                                           |
| RN034 | O funcionamento inicial do sistema depende de conexão com a internet.                                                                                                                    |
| RN035 | O Gerente possui acesso a todas as funcionalidades do sistema.                                                                                                                           |
| RN036 | O Garçom pode criar, alterar antes da preparação, cancelar pedidos, fechar comandas, registrar pagamentos, gerenciar entregas e consultar histórico.                                     |
| RN037 | O Caixa possui as mesmas permissões operacionais do Garçom.                                                                                                                              |
| RN038 | A Cozinha pode consultar o histórico e operar a preparação dos pedidos, mas não pode criar pedidos, registrar pagamentos, fechar comandas ou gerenciar entregas.                         |
| RN039 | O cadastro inicial de um restaurante será realizado manualmente pelo responsável pelo Saborfy.                                                                                           |
| RN040 | A integração automática com WhatsApp, processamento de pagamentos e personalização de produtos com adicionais não fazem parte do escopo inicial e poderão ser implementados futuramente. |
| RN041 | Uma comanda poderá ser transferida de uma mesa para outra pelo funcionário autorizado, mantendo todos os pedidos, pagamentos e demais informações já associados à comanda.               |