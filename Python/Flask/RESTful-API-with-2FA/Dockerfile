FROM python

WORKDIR /code

COPY ./requirements.txt /code/requirements.txt
RUN pip3 install --no-cache-dir  --upgrade -r /code/requirements.txt

COPY ./backend /code/backend

COPY ./entrypoint.sh /code/entrypoint.sh
RUN chmod +x /code/entrypoint.sh
ENTRYPOINT ["sh", "/code/entrypoint.sh"]

EXPOSE 8000