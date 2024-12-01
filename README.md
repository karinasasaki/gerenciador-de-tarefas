# Gerenciador de Tarefas
API para Gerenciamento de Tarefas.

### Descrição do Problema:
Você foi contratado para desenvolver uma API simples de gerenciamento de tarefas. A API deve permitir que os usuários possam:
- Criar uma tarefa.
- Atualizar uma tarefa.
- Listar todas as tarefas.
- Excluir uma tarefa.

### Cada tarefa deve ter os seguintes campos:
- id (gerado automaticamente)
- titulo (string, obrigatório)
- descricao (string, opcional)
- status (string, obrigatório: "PENDENTE", "EM_ANDAMENTO", ou "CONCLUIDA")
- dataCriacao (data/hora, gerada automaticamente)
- dataConclusao (data/hora, opcional)

### Requisitos Técnicos:
- Use Java e o framework Spring Boot para desenvolver a API.
- Utilize banco de dados relacional (pode ser em memória, como H2).
- Documente a API usando Swagger.
- Implemente as operações usando os verbos HTTP adequados:
  - POST para criar.
  - PUT para atualizar.
  - GET para listar.
  - DELETE para excluir.
- Garanta que as validações necessárias sejam feitas, como campos obrigatórios.

#### Extras (não obrigatórios, mas serão considerados diferenciais):
- Implementação de paginação para listagem
- Logs básicos utilizando o framework de logging de sua escolha.
- Testes unitários simples para os serviços ou controladores.

### Entrega:
- Disponibilize o código em um repositório público no GitHub ou em um arquivo compactado.
- Inclua um arquivo README.md com instruções para rodar o projeto e testar a API.

<hr>

## Tecnologias:
- Maven
- Java 17
- Spring Boot 3.4.0
- [H2](http://localhost:8080/banco-de-dados)
- [Swagger](http://localhost:8080/swagger-ui/index.html)
- JUnit e Mockito
