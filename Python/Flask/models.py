from db import db


class Users(db.Model):
    __tablename__ = "users"
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(128))
    password = db.Column(db.String(128))
    name = db.Column(db.String(128))


class Transactions(db.Model):
    __tablename__ = "transactions"
    id = db.Column(db.Integer, primary_key=True)
    ammount = db.Column(db.Float())
    transaction_type = db.Column(db.String(128))
    status = db.Column(db.String(128))
    user = db.Column(db.String(128))
