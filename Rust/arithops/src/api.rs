use crate::models::ArithmeticOperation;
use actix_web::{get, post, web, HttpResponse, Responder, Result};
use serde::{Deserialize, Serialize};
use validator::Validate;

#[derive(Deserialize, Validate)]
struct RequestSchema {
    first_number: f32,
    second_number: f32,
    operation: String,
}

#[derive(Serialize)]
struct ResponseSchema {
    result: f32,
}

#[get("/")]
async fn index() -> impl Responder {
    HttpResponse::Ok().body(
        "Microservice implements basic arithmetic operations such as 'ADD', 'SUBTRACT', 
        'MULTIPLY', and 'DIVIDE' to be executed on an endpoint using POST method.",
    )
}

#[post("api/v1/arithops")]
async fn arithops(payload: web::Json<RequestSchema>) -> Result<impl Responder> {
    let mut arithop = ArithmeticOperation {
        first_number: payload.first_number,
        second_number: payload.second_number,
        operation: payload.operation.to_string(),
    };
    let response = ResponseSchema {
        result: arithop.arithops(),
    };
    Ok(web::Json(response))
}
