from flask import Flask
from flask_migrate import Migrate
from sqlalchemy_utils import database_exists, create_database
from flask_smorest import Api
from api import blp_user_regist, blp_payment, blp_transactions
from config import Config
from db import db


app = Flask(__name__)
app.config.from_object(Config)

if not database_exists(Config.SQLALCHEMY_DATABASE_URI):
    create_database(Config.SQLALCHEMY_DATABASE_URI)

db.init_app(app)
migrate = Migrate(app, db)

api = Api(app)

api.register_blueprint(blp_user_regist)
api.register_blueprint(blp_payment)
api.register_blueprint(blp_transactions)

if __name__ == "__main__":
    app.run(debug=True)
