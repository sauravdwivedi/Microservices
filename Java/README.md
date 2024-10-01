# RESTful API

## Run MySQL instance on docker

```bash
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql
```
 
## Run spring boot application

```bash 
cd Java/transaction-management
./gradlew bootRun
```

