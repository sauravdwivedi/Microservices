# RESTful API with Go and Gin example

## Project description

Application implements basic arithmetic operations such as "ADD", "SUBTRACT", "MULTIPLY", and "DIVIDE" to be executed on an endpoint using POST method.

### Payload
```json
{
    "first_number": 100, 
    "second_number": 200, 
    "operation": "ADD"
}
```

### Response
```json
{
    "result": 300
}
```

## Execution

### Clone repository  

```bash
$ gh repo clone sauravdwivedi/Microservices
```

### Run code
  
```bash
$ cd Microservices && cd Go && cd ArithmeticOperations
$ go get .
$ swag init 
$ go run .
```

### Test endpoint

```bash
$ curl http://localhost:8080/arithops \
    --include \
    --header "Content-Type: application/json" \
    --request "POST" \
    --data '{"first_number": 100, "second_number": 5, "operation": "DIVIDE"}'
```

### Swagger documentation

- http://localhost:8080/docs/index.html