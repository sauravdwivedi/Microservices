mod api;
mod models;
use actix_web::{App, HttpServer};
use api::{arithops, index};

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    HttpServer::new(|| App::new().service(index).service(arithops))
        .bind(("127.0.0.1", 8000))?
        .run()
        .await
}
