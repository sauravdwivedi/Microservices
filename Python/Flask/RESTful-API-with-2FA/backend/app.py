#!/usr/bin/python3

from flask import Flask
from flask_migrate import Migrate
from flask_smorest import Api
from flask_cors import CORS
from backend import Config, blp_user_regist, blp_payment, blp_transactions
from backend.db import db


app = Flask(__name__)
CORS(app, supports_credentials=True)
app.config.from_object(Config)

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
