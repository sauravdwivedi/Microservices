#!/bin/bash

echo "Apply database init"
flask db init 

echo "Apply database migrations"
flask db migrate -m "Initial migration"

echo "Apply database upgrade"
flask db upgrade

echo "Start flask application"
flask run --host=0.0.0.0 --port=8000