# RESTful API with Rust microservice Actix-web

## Project description

Microservice implements basic arithmetic operations such as "ADD", "SUBTRACT", "MULTIPLY", and "DIVIDE" to be executed on an endpoint using POST method.

```mermaid
sequenceDiagram
    participant C as Client
    participant B as Backend
    C->>B: POST
    alt malformed payload
        B-->>C: 400
    else payload OK
        B->>C: 200
    end
```

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
gh repo clone sauravdwivedi/Microservices
```

### Run code
  
```bash
cd Microservices && cd Rust && cd arithops
cargo run
```

<!-- #### Run on Docker

```bash
docker build -t arithops .
docker run -d --name microservice -p 8000:8000 arithops
``` -->

### Test endpoint

```bash
curl http://localhost:8000/api/v1/arithops \
    --include \
    --header "Content-Type: application/json" \
    --request "POST" \
    --data '{"first_number": 100, "second_number": 5, "operation": "DIVIDE"}'
```

<!-- ### API documentation

- http://127.0.0.1:8000/docs
- http://127.0.0.1:8000/redoc -->

### Open backend in browser

- http://localhost:8000/
