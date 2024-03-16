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
> Qualquer sistema operacional Linux ou subsistema Linux, Windows ou MacOs

> Docker, Docker Desktop e Docker Compose instalados na sua máquina

> Kubernetes, Minikube e Lens instalados na sua máquina

- [Docker](https://docs.docker.com/get-started/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Kubenetes](https://kubernetes.io/pt-br/docs/setup/)
- [MiniKube](https://minikube.sigs.k8s.io/docs/start/)
- [Lens](https://k8slens.dev/)

### Como executar o projeto no meu computador?
> Não é necessario criar databases, ou rodar o projeto de forma local, e sim apenas utilizar um docker-compose que existe neste repositorio.
> Dentro do repositorio da aplicação, o comando inicial pode ser o seguinte para uso com Docker Compose:
```sh
docker-compose up
``````
> Após a primeira execução, pode ser necessário realizar um start manual no container de database, pela ui do Docker Desktop ou utilizando o seguinte comando:
```sh
docker start postgres_fiap_3soat_g53
```
> Criamos também dois scripts que sobem a arquitetura com Kubernetes, através do MiniKube, para tal execute:
```sh
./k8s.sh
``````
> Após validar o "UP" dos PODs Kubernetes, utilize o seguinte script para fazer Port Forward:
```sh
./pt-fwd.sh
``````
> Resultado esperado utilizando Docker-Compose:
![](/img/withdc.png)

> Resultado esperado utilizando Kustomize do Kubernetes:
![](/img/withk8s.png)

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


## ADR: Modelo de Dados para Sistema de Pedidos

### Contexto

Estamos desenvolvendo um sistema de pedidos para uma empresa que oferece serviços de entrega de alimentos. Precisamos definir um modelo de dados que permita armazenar informações sobre clientes, pedidos e produtos de forma eficiente e escalável.

### Decisão

Decidimos adotar um modelo de dados relacional, visto que o tamanho dos dados e o volume de transações esperados não são extremamente altos, e a escalabilidade vertical oferecida por bancos de dados relacionais é suficiente para nossas necessidades.

Optamos pelo banco de dados Postgres devido às seguintes razões:

1 - Suporte a colunas em formato JSON: O Postgres disponibiliza suporte nativo para colunas em formato JSON, o que nos permite armazenar a lista de produtos de cada pedido de forma flexível na tabela Pedidos.

2 - ACID (Atomicidade, Consistência, Isolamento e Durabilidade): O Postgres oferece garantias ACID, o que é essencial para manter a integridade dos dados em um ambiente transacional como o nosso sistema de pedidos.

### Tabela Cliente

    **CPF**: Chave primária em formato String. Armazena o CPF do cliente.
    **Nome**: Em formato String. Armazena o nome completo da pessoa.
    **Email**: Em formato String. Armazena o endereço de e-mail para contato.

### Tabela Pedidos

    **Id**: Chave primária em formato numérico. Armazena o identificador único do pedido.
    **Lista_produtos_pedido**: Em formato JSON. Armazena uma lista de objetos contendo informações dos produtos relacionados ao pedido, como nome, quantidade e preço unitário.
    **Status_pagamento**: Em formato String. Armazena o status de pagamento do pedido, podendo ser "Pago", "Esperando Confirmação" ou "Cancelado".
    **Status_pedido**: Em formato String. Armazena o status do pedido, podendo ser "Recebido", "Em Preparação", "Pronto" ou "Finalizado".
    **Valor_total**: Em formato numérico. Armazena o valor total do pedido.

Tabela Produtos

    **Nome**: Chave primária em formato String. Armazena o nome único do produto.
    **Categoria**: Em formato String. Armazena a categoria do produto, podendo ser "Lanche", "Bebida", "Acompanhamento" ou "Sobremesa".
    **Valor**: Em formato numérico. Armazena o preço do produto.

### Justificativa

A escolha do modelo de dados relacional e do banco de dados Postgres é adequada para o tamanho e complexidade do nosso sistema de pedidos. Essa decisão nos permite manter a integridade dos dados, garantir a consistência das transações e aproveitar os recursos oferecidos pelo Postgres, como suporte a JSON e ACID.


### Consequências

1- A utilização do modelo relacional e do Postgres facilita o desenvolvimento, manutenção e operação do sistema de pedidos.

2 - É necessário garantir o bom dimensionamento do banco de dados Postgres para lidar com o crescimento esperado do sistema ao longo do tempo.

3 - Podemos explorar recursos avançados oferecidos pelo Postgres, como índices e otimizações de consulta, para melhorar o desempenho do sistema conforme necessário.

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
