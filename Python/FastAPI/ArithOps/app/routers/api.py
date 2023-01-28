from fastapi import Body, APIRouter
from pydantic import BaseModel, root_validator
from fastapi.responses import JSONResponse, HTMLResponse
from app import ArithmeticOperation


router = APIRouter()


class RequestSchema(BaseModel):
    first_number: float
    second_number: float
    operation: str

    @root_validator()
    def valid_operation(cls, values):
        if values["operation"] not in ["ADD", "SUBTRACT", "MULTIPLY", "DIVIDE"]:
            raise ValueError(
                f"Operation must be one of 'ADD', 'SUBTRACT', 'MULTIPLY', 'DIVIDE'"
            )
        elif values["operation"] == "DIVIDE" and values["second_number"] == 0:
            raise ValueError(f"Division by zero in not possible!")

        return values


class ResponseSchema(BaseModel):
    result: float


@router.post("/api/v1/arithops", response_model=ResponseSchema)
async def do_arithmetic_operation(
    payload: RequestSchema = Body(
        example={"first_number": 100, "second_number": 200, "operation": "ADD"},
    )
):
    arithop = ArithmeticOperation(
        payload.first_number, payload.second_number, payload.operation
    )
    result = arithop.arithmetic_operation()

    return JSONResponse(content={"result": result})


@router.get("/", response_class=HTMLResponse)
async def read_items():
    return """
    <html>
        <head>
            <title>RESTful API with python microservice FastAPI</title>
        </head>
        <body>
            <h1>RESTful API with python microservice FastAPI</h1>
            <p>Microservice implements basic arithmetic operations such as "ADD", 
            "SUBTRACT", "MULTIPLY", and "DIVIDE" to be executed on an endpoint using 
            POST method. 
            
            <br><br>Make API call to endpoint:<br><br>
            
            http://localhost:8000/api/v1/arithops
            
            <br><br>with example payload:<br><br>
            
            {<br>
                &emsp;"first_number": 100, <br>
                &emsp;"second_number": 200, <br>
                &emsp;"operation": "ADD" <br> 
            }
            </p>
        </body>
    </html>
    """
