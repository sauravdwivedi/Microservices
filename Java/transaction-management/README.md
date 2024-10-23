# Java Spring Boot application Transaction Management API

## Project description
Web service provides endpoints to execute CRUD operations.

<img src=pic.PNG alt="Swagger UI">

## Usage

### Create account
#### Payload
```json
{
  "Id": "ff41eeb4-ea40-4c85-b7f5-272ccb511527",
  "Balance": 500000
}
```

#### Response
```json
{
    "status": 201
}
```

#### POST using terminal

```bash
curl -X 'POST' \
  'http://localhost:8080/api/Accounts' \
  -H 'accept: text/plain' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": "ff41eeb4-ea40-4c85-b7f5-272ccb511527",
  "balance": 0
}'
```

## Execution

### Run MySQL instance on docker

```bash
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql
```
 
### Run spring boot application

```bash 
cd Java/transaction-management
./gradlew bootRun
```

### Swagger UI

- http://localhost:8080/swagger-ui/index.html