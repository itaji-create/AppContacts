# Bem vindo ao projeto AppContacts

Este projeto tem como finalidade demonstrar os conhecimentos obtidos durante o curso de capacitação em Java/Spring da Minsait. Nele foi uma API Rest que permite o usuário criar, deletar, resgatar ou atualizar pessoas e contatos em um banco de dados.

## Tecnologias

- Java 11
- Spring Boot
- Spring Framework
- Spring Data JPA
- Hibernate
- Banco de Dados (MySQL)

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

Exemplo de application.properties:

`spring.datasource.url=jdbc:mysql://localhost:3306/meu-database`
`spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver`
``spring.datasource.username=root``
``spring.datasource.password=senha-aqui``
``spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect``
``spring.jpa.hibernate.ddl-auto=create``
``spring.jpa.show-sql=true`

<details>
<summary>
	## Endpoints disponíveis:
</summary>
	Obs. Exemplos com aplicação rodando no localhost porta 8080.
### Pessoa
	- POST `http://localhost:8080/api/pessoas` (cria uma nova Pessoa)
	- GET http://localhost:8080/api/pessoas/{id} (retorna os dados de uma Pessoa por ID)
	- GET http://localhost:8080/api/pessoas/maladireta/{id} (retorna os dados de uma Pessoa por ID para mala direta)
	- GET http://localhost:8080/api/pessoas (lista todas as Pessoas)
	- PUT http://localhost:8080/api/pessoas/{id} (atualiza uma Pessoa existente)
	- DELETE http://localhost:8080/api/pessoas/{id} (remove uma Pessoa por ID)

### Contato
	- POST http://localhost:8080/api/pessoas/{id}/contatos (adiciona um novo Contato a uma Pessoa)
	- GET http://localhost:8080/api/contatos/{id} (retorna os dados de um Contato por ID)
	- GET http://localhost:8080/api/pessoas/{idPessoa}/contatos (lista todos os Contatos de uma Pessoa)
	- PUT http://localhost:8080/api/contatos/{id} (atualiza um Contato existente)
	- DELETE http://localhost:8080/api/contatos/{id} (remove um Contato por ID)

</details>
	