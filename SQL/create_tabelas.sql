CREATE TABLE endereco(
  id_endereco integer PRIMARY key not null,
  numero  integer not null,
  rua varchar (30) not null,
  bairro varchar (30),
  cidade varchar (30) not null,
  uf varchar(2) not null);
  
 CREATE table cinema (
   id_cinema integer PRIMARY key not null,
   nome_cinema varchar (30) not null,
   id_endereco integer not null,
   FOREIGN key (id_endereco) REFERENCES endereco(id_endereco));
   
 create table filme (
   id_filme integer PRIMARY key not null,
   nome_filme varchar(30) not null,
   preco numeric (4,2) not null);
   
 CREATE table secao(
   id_secao integer primary key not null,
   horario timestamp not null,
   id_cinema integer not null,
   id_filme integer not null,
   qtd_assentos integer not null,
   FOREIGN key (id_cinema) REFERENCES cinema(id_cinema),
   FOREIGN key (id_filme) REFERENCES filme(id_filme));
   
  create table venda(
    id_venda integer primary key not null,
    nome_cliente varchar (50) not null,
    assento integer not null,
    forma_pagamento varchar(30) not null,
    id_secao integer not null,
    FOREIGN key (id_secao) REFERENCES secao(id_secao));
  
