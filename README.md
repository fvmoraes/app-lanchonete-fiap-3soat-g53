# Sistema de Pedidos para Lanchonete - FIAP 3SOAT (Grupo 53)
---
---
## Estrutura e tecnologias utilizadas
### Qual a proposta?
>Este projeto é uma aplicação de pedidos para uma lanchonete, desenvolvida como parte do curso de pós graduação em Software Architeture da FIAP.

### Tecnologias utilizadas:
- [Java](https://dev.java/learn/)
- [Docker](https://docs.docker.com/get-started/)
- [ShelScript](https://www.shellscript.sh/)
- [PostgreSQL](https://www.postgresql.org/about/)
- [PGAdmin](https://www.pgadmin.org/docs/)
- [Swagger](https://swagger.io/solutions/api-documentation/)

### Informações Básicas:
> Este projeto contém 21 endpoints de API, dos quais 20 estão diretamente vinculados a um processo CRUD em um banco de dados PostgreSQL.

> Para interação básica, você pode usar o Swagger (via navegador) ou o Postman (importando a collection, o arquivo FIAP.postman_collection.json que contém a configuração essencial para iniciar o uso).

> O banco de dados PostgreSQL e o pgAdmin já estão configurados e prontos para uso.

> O projeto possui boas praticas de programação com base em DDD e CleanCode.

---
---
## Como Usar
### O que preciso ter instalado no meu computador?
> Docker e Docker Compose instalados na sua máquina

> Qualquer sistema operacional Linux ou subsistema Linux, Windows ou MacOs

- [Docker](https://docs.docker.com/get-started/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Como executar o projeto no meu computador?
> Não é necessario criar databases, ou rodar o projeto de forma local, e sim apenas utilizar um docker-compose que existe neste repositorio.
> Dentro do repositorio da aplicação, o comando inicial pode ser o seguinte:
```sh
docker-compose up
``````
> Após a primeira execução, pode ser necessário realizar um start manual no container de database, pela ui do Docker Desktop ou utilizando o seguinte comando:
```sh
docker start postgres_fiap_3soat_g53
```
> Valide se a imagem de postgres utilizada foi a "postgres:15.5-alpine", caso não tenha sido esta, elimine as imagens de container diferentes e aplique novamente os comandos acima.

### Como acessar as funcionalidades disponíveis?
> Os seguintes endereços privados são fixos na configuração do Docker-Compose e proporcionam acesso às opções do projeto:

- ``` http://localhost:8080/``` concede acesso à API (/api/v1/*) e ao endpoint do Swagger.
- ``` http://localhost:54321/login``` concede acesso ao PGAdmin que já possui a configuração com o banco de dados. Use para fazer login no pgadmin: "3soatg53@fiap.com" e "3soatg53" e, para confirmação de acesso ao banco de dados, a senha é "postgres".
- ``` 172.34.0.2 ``` é o endereço IP do banco de dados, acessível com as credenciais fiap:postgres:postgres na porta 5432.
### Exemplo de acesso ao PGAdmin:
> O PgAdmin já está configurado para acesso, utilize o endereço: [pgadmin/login](http://localhost:54321/login)
![](/img/pgadmin1.png)
![](/img/pgadmin2.png)
### Exemplo de acesso com o Swagger:
> O Swagger possui os endpoints disponiveis na API, utilize o endereço: [swagger/index.html](http://localhost:8080/swagger-ui/index.html)
![](/img/swagger.png)
### Exemplo de uso com o Postman:
> No menu suspenso do Workspace, selecione Importar. Escolha uma opção para encontrar o arquivo e importe o arquivo "FIAP.postman_collection.json".
> Para mais informações: [Documentação do Postman](https://learning.postman.com/docs/introduction/overview/)
![](/img/postman.png)

---
---
## Arquitetura
### Kubernetes
> Segue a arquitetura atual desenvolvida com o Kubernetes.
![](/img/kubernetes.png)

> Neste vídeo do youtube explicamos mais sobre a arquitetura:
[![Assista ao vídeo](/img/youtube.png)](https://www.youtube.com/watch?v=E8MHnRbv1Y8)

---
---
## Mais informações sobre a API
### Lista de endpoints
> Produto
- GET http://localhost:8080/api/v1/produto
- PUT http://localhost:8080/api/v1/produto
- POST http://localhost:8080/api/v1/produto
- DELETE http://localhost:8080/api/v1/produto
- GET http://localhost:8080/api/v1/produto/{categoria}
- DELETE http://localhost:8080/api/v1/produto/{nome}
> Pedido
- GET http://localhost:8080/api/v1/pedido
- PUT http://localhost:8080/api/v1/pedido
- POST http://localhost:8080/api/v1/pedido
- PUT http://localhost:8080/api/v1/pedido/status/{id}/{statusRequest}
- POST http://localhost:8080/api/v1/pedido/pagamento/mercadopago/{topic}/{id}
- GET http://localhost:8080/api/v1/pedido/{id}
- GET http://localhost:8080/api/v1/pedido/status
- GET http://localhost:8080/api/v1/pedido/pagamento/{id}
> Cliente
- GET http://localhost:8080/api/v1/cliente
- PUT http://localhost:8080/api/v1/cliente
- POST http://localhost:8080/api/v1/cliente
- DELETE http://localhost:8080/api/v1/cliente
- GET http://localhost:8080/api/v1/cliente/{cpf}
- DELETE http://localhost:8080/api/v1/cliente/{cpf}
> Swagger
- GET http://localhost:8080/swagger-ui/index.html
---
---
## Referências
### Este projeto foi criado usando os seguintes pacotes Java
- [spring boot starter data jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [spring boot starter web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [springdoc openapi starter webmvc ui](https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui)
- [postgresql](https://mvnrepository.com/artifact/org.postgresql/postgresql)
- [hibernate validator](https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator)
- [spring boot](https://spring.io/projects/spring-boot/)

---
---
_fim do README.md_
