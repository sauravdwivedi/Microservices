#!/usr/bin/python3

from fastapi import FastAPI
from app.routers import api

app = FastAPI()

app.include_router(
    api.router,
    prefix="",
    tags=["arithops"],
)
