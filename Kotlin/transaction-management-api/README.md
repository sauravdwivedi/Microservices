# Kotlin Spring Boot application Transaction Management API

## Project description
Web service provides endpoints to execute CRUD operations.

<img src=pic.PNG alt="Swagger UI">

## Architecture

```mermaid
sequenceDiagram
    participant C as Client
    participant B as Backend
    C->>B: POST
    alt malformed payload
        B-->>C: 400
    else payload OK
        B->>C: 201
    end
```

## Usage

### Create account
Backend creates random uuid for each new account.
#### Payload
```json
{
  "balance": 500000
}
```

#### Response
```json
{
    "id": "36d28dba-df34-47da-8d32-ede47edac57e",
    "balance": 500000.0
}
```

#### POST using terminal

```bash
curl -X 'POST' \
  'http://localhost:8080/api/v1/accounts' \
  -H 'accept: text/plain' \
  -H 'Content-Type: application/json' \
  -d '{
  "balance": 500000
}'
```

## Execution

### Run MySQL instance on docker

```bash
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql
```
 
### Run spring boot application

```bash 
gh repo clone sauravdwivedi/Microservices
cd Microservices && cd Kotlin && cd transaction-management-api
./gradlew --debug bootRun
```

### Swagger UI

- http://localhost:8080/swagger-ui/index.html

### Tutorials

- https://spring.io/guides/tutorials/spring-boot-kotlin
- https://kotlinlang.org/docs/jvm-get-started-spring-boot.html
- https://kotlinlang.org/docs/jvm-spring-boot-using-crudrepository.html