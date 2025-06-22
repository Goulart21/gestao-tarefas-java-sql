# gestao-tarefas-java-sql
Sistema de gerenciamento de tarefas em Java com JDBC e banco de dados (SQLite ou MySQL). Permite cadastrar, atualizar, concluir, excluir e filtrar tarefas via terminal.
# Gestão de Tarefas (Java + JDBC + MySQL)

Este é um sistema simples de linha de comando para gerenciamento de tarefas, desenvolvido em Java, utilizando JDBC para conexão com banco de dados relacional (MySQL).

## 📚 Funcionalidades

- ✅ Cadastrar uma nova tarefa
- ✅ Marcar tarefa como concluída
- ✅ Atualizar descrição e status de uma tarefa
- ✅ Excluir uma tarefa por ID
- ✅ Exibir todas as tarefas cadastradas
- ✅ Filtrar tarefas por status (Concluídas ou Em Andamento)
- ✅ Deletar todas as tarefas do sistema

## 💻 Tecnologias utilizadas

- Java (JDK 21)
- JDBC (Driver de conexão com banco de dados)
- MySQL (Banco de dados)
- IntelliJ IDEA

## 🧠 Organização do projeto

- `Main.java`: Interface via terminal e menu de opções.
- `Tarefas.java`: Classe modelo da tarefa (id, descrição, status).
- `TaskDAO.java`: Camada de persistência (CRUD via JDBC).
- `TarefaNaoEncontrada.java`: Exceção personalizada para tratamento de erros.
- `TarefasConn.java`: Classe responsável pela conexão com o banco.


