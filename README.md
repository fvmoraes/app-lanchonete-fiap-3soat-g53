# Sistema de Pedidos para Lanchonete - FIAP 3SOAT (Grupo 53)
---
---
## Estrutura e tecnologias utilizadas
### qual a proposta?
>Este projeto é uma aplicação de pedidos para uma lanchonete, desenvolvida como parte do curso de pós graduação em Software Architeture da FIAP.
.

### Tecnologias utilizadas:
- [Java](https://dev.java/learn/)
- [Docker](https://docs.docker.com/get-started/)
- [ShelScript](https://www.shellscript.sh/)
- [PostgreSQL](https://www.postgresql.org/about/)
- [PGAdmin](https://www.pgadmin.org/docs/)
- [Swagger](https://swagger.io/solutions/api-documentation/)

### Informações Básicas:
> Este projeto contém 7 endpoints de API, dos quais 6 estão diretamente vinculados a um processo CRUD em um banco de dados PostgreSQL.

> Para interação básica, você pode usar o Swagger (via navegador) ou o Postman (importando a collection, o arquivo colectionPostman.txt que contém a configuração essencial para iniciar o uso).

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
> O comando inicial pode ser o seguinte:
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

- ``` http://172.33.0.4:9000/api/v1/ ``` concede acesso à API e aos pontos finais do Swagger.
- ``` http://172.33.0.3/ ``` concede acesso ao PGAdmin que já possui a configuração com o banco de dados. Use para fazer login no pgadmin: "admin@foobar.com" e "123456" e, para confirmação de acesso ao banco de dados, a senha é "foobar".
- ``` 172.33.0.2 ``` é o endereço IP do banco de dados, acessível com as credenciais foobar:foobar:foobar na porta 5432.
### Exemplo de acesso ao PGAdmin:
> [pgadmin/login](http://172.33.0.3/login)
![](/img/pgadmin.png)
### Exemplo de uso com o Swagger:
> [/api/v1/swagger/index.html](http://172.33.0.4:9000/api/v1/swagger/index.html)
![](/img/swagger.png)
### Exemplo de uso com o Insomnia:
> Nas opções do aplicativo, no menu suspenso Nome do Documento ou Coleção, selecione Importar/Exportar. Escolha uma opção no menu suspenso Importar Dados. Importe o arquivo "Insomnia_api-go-gin.yaml".
> Para mais informações: [Documentação do Insomnia](https://docs.insomnia.rest/insomnia/import-export-data)
![](/img/insomnia.png)
### Exemplo de acesso ao endpoint "Mock":
> [/api/v1/foobar/mock](http://172.33.0.4:9000/api/v1/foobar/mock)
![](/img/mock.png)
### Exemplos de geração de testes
```sh
go test
```
![](/img/test.png)
---
---
## Mais informações sobre a API
### Lista de pontos finais
- ```GET http://172.33.0.4:9000/api/v1/foobar/mock``` Para mostrar o mock
- ```POST http://172.33.0.4:9000/api/v1/foobar/``` Para criar Foobar
- ```GET http://172.33.0.4:9000/api/v1/foobar/``` Para mostrar todos os Foobar
- ```GET http://172.33.0.4:9000/api/v1/foobar/:id``` Para mostrar Foobar por ID
- ```GET http://172.33.0.4:9000/api/v1/foobar/:reg``` Para mostrar Foobar por Registro
- ```PATCH http://172.33.0.4:9000/api/v1/foobar/:id``` Para editar Foobar por ID
- ```DELETE http://172.33.0.4:9000/api/v1/foobar/:id``` Para excluir Foobar por ID
- ```GET http://172.33.0.4:9000/api/v1/swagger/index.html``` Swagger
---
---
## Referências
### Este projeto foi criado usando os seguintes pacotes Golang
- [Gin](https://pkg.go.dev/github.com/gin-gonic/gin)
- [GORM](https://pkg.go.dev/gorm.io/gorm)
- [Logrus](https://pkg.go.dev/github.com/sirupsen/logrus)
- [Testify](https://pkg.go.dev/github.com/stretchr/testify)
- [Testing](https://pkg.go.dev/testing)
- [SwagGo](https://pkg.go.dev/github.com/swaggo/swag)
- [ValidatorV2](https://pkg.go.dev/gopkg.in/validator.v2@v2.0.1)
---
---
_fim do README.md_
