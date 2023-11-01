# Bem vindo ao projeto AppContacts

Este projeto tem como finalidade demonstrar os conhecimentos obtidos durante o curso de capacitação em Java/Spring da Minsait. Nele foi construido uma API Rest que permite o usuário criar, deletar, resgatar ou atualizar pessoas e contatos em um banco de dados.

## Tecnologias

- Java 17
- Spring Boot
- Spring Framework
- Spring Data JPA
- Hibernate
- Banco de Dados (MySQL)
- Swagger-ui

## Pré-requisitos

Para iniciar o projeto é necessario ter instalado em sua máquina as seguintes ferramentas:

- Java 17
- Maven 3.9.5
- O banco de dados MySQL:8.0.16 configurado

## Instalação

1. Clone o repositório:
	- `git clone https://github.com/itaji-create/AppContacts.git`

2. Acesse o diretório do projeto:
	- `cd AppContacts`

3. Compile o projeto com o Maven:
	- `mvn clean install`
	
4. Execute o projeto:
	- `mvn spring-boot:run`
	

## Configuração

Configure o arquivo application.properties para definir as configurações do banco de dados e outras propriedades da aplicação. Certifique-se de configurar corretamente as informações de banco de dados, como URL, usuário e senha.

<strong>Exemplo de application.properties:</strong>

	spring.datasource.url=jdbc:mysql://localhost:3306/meu-database
	spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
	spring.datasource.username=root
	spring.datasource.password=senha-aqui
	spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
	spring.jpa.hibernate.ddl-auto=create
	spring.jpa.show-sql=true

## Endpoints disponíveis:

	- Obs. Exemplos com aplicação rodando no localhost porta 8080.

<details>
	<summary><strong>Pessoa</strong></summary>

	- POST: "http://localhost:8080/api/pessoas"
		(Cria uma nova Pessoa)
	- GET: "http://localhost:8080/api/pessoas/{pessoaId}"
		(Retorna os dados de uma Pessoa por ID)
	- GET: "http://localhost:8080/api/pessoas/maladireta/{pessoaId}"
		(Retorna os dados de uma Pessoa por ID para mala direta)
	- GET: "http://localhost:8080/api/pessoas"
		(Lista todas as Pessoas)
	- PUT http://localhost:8080/api/pessoas/{pessoaId}"
		(Atualiza uma Pessoa existente)
	- DELETE: "http://localhost:8080/api/pessoas/{pessoaId}"
		(Remove uma Pessoa por ID)

</details>

<details>
	<summary><strong>Contato</strong></summary>

	- POST http://localhost:8080/api/pessoas/{pessoaId}/contatos"
		(Adiciona um novo Contato a uma Pessoa)
	- GET: "http://localhost:8080/api/contatos/{contatoId}
		(Retorna os dados de um Contato por ID)
	- GET: "http://localhost:8080/api/pessoas/{pessoaId}/contatos"
		(Lista todos os Contatos de uma Pessoa)
	- PUT: "http://localhost:8080/api/contatos/{contatoId}"
		(Atualiza um Contato existente por ID)
	- DELETE http://localhost:8080/api/contatos/{contatoId}
		(Remove um Contato por ID)

</details>
	
