from flask.views import MethodView
from flask_smorest import Blueprint
import marshmallow as ma
import requests
from werkzeug.exceptions import Unauthorized, InternalServerError
import logging
import json
from backend import Config, Users, Transactions
from backend.db import db


logging.basicConfig(
    format="%(asctime)s - %(levelname)s - %(message)s", level=logging.INFO
)

blp_user_regist = Blueprint(
    "user_registration",
    "user_registration",
    url_prefix="/api/v1/user",
    description="User Registration for Okay backend",
)

blp_payment = Blueprint(
    "schedule_payment",
    "schedule_payment",
    url_prefix="/api/v1/schedule",
    description="Payment info uses Okay app for auth",
)

blp_transactions = Blueprint(
    "transactions",
    "transactions",
    url_prefix="/api/v1/",
    description="Lists transactions both scheduled and approved",
)


class UserRegistrationSchema(ma.Schema):
    username = ma.fields.String()
    password = ma.fields.String()
    name = ma.fields.String()


class SchedulePaymentSchema(ma.Schema):
    user = ma.fields.String()
    amount = ma.fields.Float()
    transaction_type = ma.fields.String(
        validate=ma.validate.OneOf(["deposit", "withdrawal"])
    )
    status = ma.fields.String(validate=ma.validate.OneOf(["scheduled", "approved"]))
    pin = ma.fields.String()


class SchedulePaymentResSchema(ma.Schema):
    code = ma.fields.Integer()
    status = ma.fields.String()


@blp_user_regist.route("/register")
class UserRegistration(MethodView):
    @blp_user_regist.arguments(UserRegistrationSchema)
    @blp_user_regist.response(201)
    def post(self, payload):
        try:
            user_dict = {
                "username": payload["username"],
                "password": payload["password"],
                "name": payload["name"],
            }
            data = Users(**user_dict)
            db.session.add(data)
            db.session.commit()
            logging.info("User saved in database!")
        except Exception as e:
            logging.error("Error while trying to save user to database! %s", e)
            db.session.rollback()
            raise InternalServerError("An error occurred!")

        return {"code": 201, "status": "User successfully created!"}


@blp_payment.route("/payment")
class SchedulePayment(MethodView):
    @blp_payment.arguments(SchedulePaymentSchema)
    @blp_payment.response(201, SchedulePaymentResSchema)
    def post(self, payload):

        payload_auth_req = {
            "Pin": payload["pin"],
            "SecretCode": Config.SECRET,
        }
        url = "https://www.authenticatorApi.com/Validate.aspx"
        response_auth_req = requests.post(url, params=payload_auth_req)

        if response_auth_req.content.decode("utf-8") != "True":
            logging.warning("Google authentication failed!")
            raise Unauthorized("transaction declined!")
        else:
            logging.info("Google authentication successful. Transaction processed!")
            try:
                transaction_dict = {
                    "amount": payload["amount"],
                    "transaction_type": payload["transaction_type"],
                    "status": payload["status"],
                    "user": payload["user"],
                }
                data = Transactions(**transaction_dict)
                db.session.add(data)
                db.session.commit()
                logging.info("Transaction saved in database!")
            except Exception as e:
                logging.error(
                    "Error while trying to save transaction to database! %s", e
                )
                db.session.rollback()
                raise InternalServerError("An error occurred!")

        return {
            "code": 201,
            "status": "Google 2FA authentication successful. Transaction processed!",
        }


@blp_transactions.route("/transactions")
class TransactionsAPI(MethodView):
    def get(self):
        try:
            data = db.session.query(Transactions).all()
            transactions = []
            for d in data:
                trans_dict = {
                    "id": 0,
                    "amount": 0,
                    "transaction_type": "",
                    "status": "",
                    "user": "",
                }
                d = d.__dict__
                trans_dict.update((k, v) for k, v in d.items() if k in trans_dict)
                transactions.append(json.loads(json.dumps(trans_dict)))
        except Exception as e:
            logging.error("No transaction created yet! %s", e)
            raise InternalServerError("No transaction created yet!")

        return transactions
