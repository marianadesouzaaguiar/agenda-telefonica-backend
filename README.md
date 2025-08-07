📞 Sistema de Agendamento Telefônico

Este projeto foi desenvolvido como parte de um desafio técnico para a vaga de Desenvolvedor(a) Frontend. O objetivo é criar uma aplicação de agendamento telefônico com funcionalidades completas de gerenciamento de contatos, integrando frontend Angular com backend Java + Spring Boot.

✨ Funcionalidades

Cadastro de contatos com validação para evitar duplicidade por número de celular

Listagem e busca de contatos

Edição e inativação de contatos

Marcar/desmarcar como favorito

Proteção de rotas com Angular Guards

Comunicação com a API (REST)

UI responsiva com Bootstrap

Testes unitários básicos

Organização modular do projeto

🧰 Tecnologias Utilizadas

Frontend:

Angular (última versão)

TypeScript

Bootstrap

RxJS

Angular Router

Backend:

Java 17+

Spring Boot

PostgreSQL

🧪 Testes

Testes unitários simples implementados com Jasmine/Karma no frontend

Cobertura de componentes principais e serviços

🗃️ Banco de Dados

O backend utiliza um banco PostgreSQL com o seguinte schema e tabela:

create schema desafio;

create table desafio.contato(
contato_id serial primary key,
contato_nome varchar(100),
contato_email varchar(255),
contato_celular varchar(11),
contato_telefone varchar(10),
contato_sn_favorito character(1),
contato_sn_ativo character(1),
contato_dh_cad timestamp without time zone
);

🚀 Como Executar

Pré-requisitos

Node.js

Angular CLI

PostgreSQL (com banco configurado)
Java 17+

Maven

Frontend

npm install

ng serve


Backend (exemplo)

mvn spring-boot:run

📁 Estrutura de Diretórios

src/
├── app/
│   ├── components/        # Componentes reutilizáveis
│   ├── pages/             # Telas (listar, cadastrar, editar)
│   ├── services/          # Serviços de API
│   ├── guards/            # Rotas protegidas
│   ├── app.module.ts      # Módulo principal
│   └── app-routing.module.ts # Rotas

👩‍💻 Autora
Mariana Aguiar
Desenvolvedora Frontend