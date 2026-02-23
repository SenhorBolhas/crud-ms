# Sistema de Gerenciamento de Clientes - Spring Boot

Microserviço responsável por criar, alterar, excluir e consultar Clientes.

## Tecnologias

- Java 11
- Spring Boot
- Spring Data JPA
- H2 Database (em memória)
- Spring Security + OAuth2 (Password Flow)
- RabbitMQ
- JUnit 5
- Docker
- GitLab CI

---

## Arquitetura

- API REST protegida com OAuth2
- Banco H2 em memória
- Mensageria com RabbitMQ
- Testes unitários
- Container Docker

---

## Como rodar local

### 1) Subir RabbitMQ

```bash
docker run -d --hostname rabbit \
-p 5672:5672 -p 15672:15672 \
rabbitmq:3-management
```

Console: ```http://localhost:15672```
```
user: guest
password: guest
```
### 2) Rodar aplicação
```
mvn clean install
mvn spring-boot:run
```
Ou pela sua IDE (IntelliJ / Eclipse).

Aplicação: ```http://localhost:8080```

Console H2: ```http://localhost:8080/h2-console```

JDBC URL: ```jdbc:h2:mem:clientesdb```

### Autenticação (Password Flow)

POST: ```http://localhost:8080/oauth/token```

Header 
```Authorization: Basic Y2xpZW50OnNlY3JldA==```

Body (x-www-form-urlencoded):
```
grant_type=password
username=admin
password=123
```

Resposta:
```
{
    "access_token": "KDxk8...",
    "token_type": "bearer",
    "refresh_token": "kUNHg...",
    "expires_in": 3599,
    "scope": "read write"
}
```

### Consumindo API

Header: 
```
Authorization: Bearer access_token
```

Endpoints:
```
POST /clientes 

Body:
{
  "nome": "Giovanni",
  "email": "gio@email.com"
}

--

GET /clientes

--

PUT /clientes/{id}

Body:
{
  "nome": "Giovanni Atualizado",
  "email": "gio@email.com"
}

--

DELETE /clientes/{id}
```
### Validações

Email é único (constraint unique)

ID é gerado automaticamente

Testes utilizam banco H2 em memória

### Docker

Build:
```
mvn clean package
docker build -t ms-clientes .
docker run -p 8080:8080 ms-clientes
```

### CI/CD (GitLab)

Arquivo .gitlab-ci.yml já configurado para build Maven.

### Licença

Projeto para fins educacionais.
Desenvolvido para estudo de Spring Boot e testes de integração (legado).

### Autor

Giovanni Pelloso Gasparetto