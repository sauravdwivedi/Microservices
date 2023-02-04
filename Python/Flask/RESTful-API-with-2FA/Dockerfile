FROM python:latest

WORKDIR /code

COPY ./requirements.txt /code/requirements.txt
RUN pip3 install --no-cache-dir  --upgrade -r /code/requirements.txt

COPY ./backend /code/backend

ENV FLASK_APP=backend/app
ENV FLASK_DEBUG=1
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=password
ENV PG_HOST=host.docker.internal
# ENV PG_HOST=db
ENV SECRET=mJhslfdJSD9248445Mmndsasdm

RUN flask db init 
RUN flask db migrate -m "Initial migration"
RUN flask db upgrade

EXPOSE 8000

CMD [ "flask", "run", "--host", "0.0.0.0", "--port", "8000"]