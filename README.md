<h1 align="center">Gerenciador de Tarefas</h1>

<br>

## 💻 Sobre o projeto

API simples para Gerenciamento de Tarefas criado com Java e Spring Boot.

### 🚀 Tecnologias

- **Gerenciador de dependências**: Maven
- **Linguagem de programação**: Java 21
- **Framework**: Spring Boot 3.4.0
- **Gerenciador de banco de dados**: [H2](http://localhost:8080/banco-de-dados)
- **Documentação da API**: [Swagger](http://localhost:8080/swagger-ui/index.html)
- **Teste unitário**: JUnit e Mockito

### 📝 Funcionalidades

- Criar uma tarefa
- Atualizar uma tarefa
- Listar todas as tarefas (com paginação)
- Excluir uma tarefa

<br>

## 👩‍💻 Instalação e execução

1. Clone este projeto na sua máquina
2. Instale as dependências necessárias:
- Java 21
3. Executando o projeto:
```
mvn spring-boot:run
```

## ✅ Como utilizar a API

### Para criar uma tarefa:
- Requisição: POST
- URL: ```http://localhost:8080/tarefas```
- Headers:
  - header: ```Content-Type```
  - value: ```application/json```
- Body:
```
{
	"titulo": "Ajustar formatação de hora e data",
	"descricao": "Retornar a data e hora no padrão ISO",
	"status": "PENDENTE",
	"dataConclusao": "05/01/2025 18:00:00"
}
```

### Para atualizar uma tarefa:
- Requisição: PUT
- URL: ```http://localhost:8080/tarefas/1```
- Headers:
  - header: ```Content-Type```
  - value: ```application/json```
- Body:
```
{
	"titulo": "Limpar o código",
	"descricao": "Remover linhas desnecessárias",
	"status": "EM_ANDAMENTO",
	"dataConclusao": null
}
```

### Para excluir uma tarefa:
- Requisição: DELETE
- URL: ```http://localhost:8080/tarefas/1```

### Para listar as tarefas:
- Requisição: GET
- URL: ```http://localhost:8080/tarefas/```
- Query params (opcionais):
  - name: ```name```
  - value: ```0``` (padrão)
  - name: ```tamanhoPagina```
  - value: ```10``` (padrão)

## 📖 Wiki

[🤝 Regras de negócio](https://github.com/karinasasaki/gerenciador-de-tarefas/wiki/%F0%9F%A4%9D-Regras-de-neg%C3%B3cio)

[🔨 Teste Unitário](https://github.com/karinasasaki/gerenciador-de-tarefas/wiki/%F0%9F%94%A8-Teste-Unit%C3%A1rio)
