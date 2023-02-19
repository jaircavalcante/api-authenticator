# api-authenticator

# Auth with DataBase

The project is an example of we can use the authentication of spring-boot 3.0 using the database


## Documentação da API

#### Example of the API not authenticated

```http
  GET /api/v1/demo-controller
```

#### Example of the API Login

```http
  POST /api/v1/auth/authenticate
```

```
curl --location --request POST 'http://localhost:8080/api/v1/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName":"jair",
    "lastName":"mendes",
    "email":"jairmendes@teste.com",
    "password":"123"
}'
```


#### Example of the API Create New User

```http
  POST api/v1/auth/register
```

```
curl --location --request POST 'http://localhost:8080/api/v1/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName":"jair",
    "lastName":"mendes",
    "email":"jairmendes@teste.com",
    "password":"123"
}'
```

## Instalação

Execute the project with commands:

```bash
  mvn spring-boot:run
```

# Stack

##### 1. DevTools
##### 2. Spring Data JPA
##### 3. Spring Security
##### 4. Spring Web
##### 5. Flyway
##### 6. Lombok
##### 7. Mysql
##### 8. JsonWebToken
##### 9. Java 17
##### 10. Maven