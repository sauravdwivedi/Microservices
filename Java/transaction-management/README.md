# RESTful API Java Spring Boot project transaction management

## Project description

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