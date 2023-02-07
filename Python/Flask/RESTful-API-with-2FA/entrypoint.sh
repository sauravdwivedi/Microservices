#!/bin/bash

echo "Waiting for database to be ready"
echo "Sleeping for 30 seconds"
sleep 30

echo "Apply database init"
flask db init 
echo "Sleeping for 30 seconds"
sleep 30

echo "Apply database migrations"
flask db migrate -m "Initial migration"
echo "Sleeping for 30 seconds"
sleep 30

echo "Apply database upgrade"
flask db upgrade
echo "Sleeping for 30 seconds"
sleep 30

echo "Start flask application"
flask run --host=0.0.0.0 --port=8000