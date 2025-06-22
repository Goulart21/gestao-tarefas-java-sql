create database GerenciamentoDeTarefas;
use GerenciamentoDeTarefas;

create table if not exists tarefas(
id int auto_increment primary key not null,
descricao varchar(50),
concluida boolean

);
drop table tarefas;
select * from tarefas;