# Bem vindo ao projeto AppContacts

Este projeto tem como finalidade demonstrar os conhecimentos obtidos durante o curso de capacitação em Java/Spring da Minsait. Nele foi construido uma API Rest que permite o usuário criar, deletar, resgatar ou atualizar pessoas e contatos em um banco de dados.

## Tecnologias

- Java 17
- Spring Boot
- Spring Framework
- Spring Data JPA
- Hibernate
- Banco de Dados (MySQL)
- OpenAPI (Swagger)

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


## Variáveis de Ambiente e Inicialização

As configurações do projeto estão pré-definidas no arquivo application.properties, este arquivo não deve ser alterado, mas para que a aplicação rode localmente você deverá definir as variáveis de ambiente junto ao comando de run do mvn conforme o exemplo a seguir:

	DB_HOSTNAME=localhost DB_PORT=3306 DB_NAME=my-db DB_USER=root DB_PASSWORD=my-password ./mvnw spring-boot:run

 Caso você esteja usando uma IDE como o Eclipse, é possível definir as variáveis de ambiente clicando em Run Configurations na seção Enviroment.

 ## Interface da API
A API é documentada usando Swagger e está hospedada no serviço de clound Railway. Você pode acessar a documentação interativa da API no seguinte link: [Swagger Documentation](https://appcontacts-production.up.railway.app/swagger-ui/index.html)
Obs. Para testar os endpoints é necessário que o projeto esteja rodando em sua máquina, a disponibilidação dessa interface serve apenas para demonstrar de maneira mais visual os endpoints e como cada requisição deve ser feita.

## Endpoints disponíveis:

Exemplos com aplicação rodando no localhost porta 8080:

<details>
	<summary><strong>Pessoa</strong></summary>

	- POST: "http://localhost:8080/api/pessoas"
		(Cria uma nova Pessoa)
	- GET: "http://localhost:8080/api/pessoas/{pessoaId}"
		(Retorna os dados de uma Pessoa por ID)
	- GET: "http://localhost:8080/api/pessoas/maladireta/{pessoaId}"
		(Retorna os dados de uma Pessoa por ID para mala direta)
	- GET: "http://localhost:8080/api/pessoas"
		(Retornar todas as pessoas)
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
