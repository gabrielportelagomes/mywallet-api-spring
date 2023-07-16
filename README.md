# MyWallet-api-spring

## Sobre
API que gerencia as finanças do mês, permitindo o registro de entradas e saídas. Projeto desenvolvido para praticar Java e Spring Boot.

## Tecnologias

- Java 17;
- Spring Boot 3.1.1;
- MySQL;
- Maven;
- Spring Security;
- JWT;


## Instalação

1. Clone este repositório:

```bash
git@github.com:gabrielportelagomes/mywallet-api-spring.git
```

2. Instale as dependências com Maven

## Como executar

1. Verifique as informações de conexão com o banco de dados MySQL em /src/main/resources/application.properties
2. Inicie a aplicação com o Maven
3. A API estará disponível em `http://localhost:8080`


## API Endpoints
A API apresenta os seguintes endpoints:

```
POST /api/auth/sign-up
Recebe um JSON no seguinte formato:
  {
	"name": "usuario",
	"email": "usuario@gmail.com",
	"password": "senhausuario",
	"role": "USER"                // pode receber "USER" ou "ADMIN"
  }
```

```
POST /api/auth/sign-in
Recebe um JSON no seguinte formato:
  {
	"email": "usuario@gmail.com",
	"password": "senhausuario",
  }
```

```
POST /api/records
Rota autenticada, deve receber um Bearer ${token}

Recebe um JSON no seguinte formato:
  {
	"value": 3500,                // valor armazenado em centavos
	"description": "almoço",
	"category": "EXPENSE"        // pode receber "EXPENSE" (saídas) ou "INCOME" (entradas)
  }
```

```
GET /api/records
Rota autenticada, deve receber um Bearer ${token}

Retorna um JSON com os registros do mês atual do usuário, no seguinte formato:
  [
    {
      "id": "23d82c17-e361-4729-ad4e-15b6f58ed338",
      "value": 3500,
      "description": "almoço",
      "category": "EXPENSE"
      "createdAt": "2023-07-16T11:56:31.63087",
      "updatedAt": "2023-07-16T11:56:31.630925"
    }
  ]
```

```
DELETE /api/records/:id
Rota autenticada, deve receber um Bearer ${token}
Substituir :id pelo id do registro que deseja excluir.
Ex: /api/records/23d82c17-e361-4729-ad4e-15b6f58ed338
```

```
PUT /api/records/:id
Rota autenticada, deve receber um Bearer ${token}
Substituir :id pelo id do registro que deseja excluir.
Ex: /api/records/23d82c17-e361-4729-ad4e-15b6f58ed338

Recebe um JSON no seguinte formato:
  {
	"value": 3500,               // valor armazenado em centavos
	"description": "almoço",
  }
```

## Repositório do front
- [MyWallet-front](https://github.com/gabrielportelagomes/mywallet-angular)
