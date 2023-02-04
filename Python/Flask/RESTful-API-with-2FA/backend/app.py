#!/usr/bin/python3

from flask import Flask
from flask_migrate import Migrate
from sqlalchemy_utils import database_exists
from flask_smorest import Api
from sqlalchemy import create_engine, text
from flask_cors import CORS
from backend import Config, blp_user_regist, blp_payment, blp_transactions
from backend.db import db
import os


app = Flask(__name__)
CORS(app, supports_credentials=True)
app.config.from_object(Config)

if not database_exists(Config.SQLALCHEMY_DATABASE_URI):
    with create_engine(
        f"postgresql://{os.getenv('POSTGRES_USER')}:{os.getenv('POSTGRES_PASSWORD')}@{os.getenv('PG_HOST')}:5432",
        isolation_level="AUTOCOMMIT",
    ).connect() as connection:
        connection.execute(text("CREATE DATABASE flask_app"))

db.init_app(app)
migrate = Migrate(app, db)


@app.route("/")
def hello():
    return "RESTful API with 2FA using Google Authenticator app"


api = Api(app)

api.register_blueprint(blp_user_regist)
api.register_blueprint(blp_payment)
api.register_blueprint(blp_transactions)

if __name__ == "__main__":
    app.run(debug=True, port=8000, host="localhost")
