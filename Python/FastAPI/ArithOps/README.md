# RESTful API with python microservice FastAPI

## Project description

Microservice implements basic arithmetic operations such as "ADD", "SUBTRACT", "MULTIPLY", and "DIVIDE" to be executed on an endpoint using POST method.

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
$ gh repo clone sauravdwivedi/RESTful-API
```

### Run code
  
```bash
$ cd RESTful-API && cd Python && cd FastAPI && cd ArithOps
$ python3 -m venv arithops
$ source arithops/bin/activate
$ pip3 install -r requirements.txt
$ uvicorn main:app --reload
```

### Test endpoint

```bash
$ curl http://localhost:8000/api/v1/arithops \
    --include \
    --header "Content-Type: application/json" \
    --request "POST" \
    --data '{"first_number": 100, "second_number": 5, "operation": "DIVIDE"}'
```

### API documentation

- http://127.0.0.1:8000/docs
- http://127.0.0.1:8000/redoc

### Open backend in browser

- http://localhost:8000/
