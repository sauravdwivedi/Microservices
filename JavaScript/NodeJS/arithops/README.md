# RESTful API with with Node.js microservice Express.js

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
$ cd Microservices && cd JavaScript && cd NodeJS && cd arithops
$ DEBUG=myapp:* npm start
```

### Test endpoint

```bash
$ curl http://localhost:3000/api/v1/arithops \
    --include \
    --header "Content-Type: application/json" \
    --request "POST" \
    --data '{"first_number": 100, "second_number": 5, "operation": "DIVIDE"}'
```

### Open backend in browser

- http://localhost:3000/
