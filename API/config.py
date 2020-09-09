# -*- coding: utf-8 -*-
# Author: Rowan

DEBUG_MODE = False
PORT = 8080
API_ENDPOINT = 'http://3.23.64.136/api/v1.0'

SECRET_KEY = 'bc13grp2'

SQLALCHEMY_TRACK_MODIFICATIONS = True

ENGINE = 'postgresql+psycopg2'
DB_ENDPOINT = 'wiet.cmyc8njmx8fz.us-east-2.rds.amazonaws.com'
DB_PORT = '5432'
DB_USERNAME = 'postgres'
DB_PASSWORD = '101doogS'
DB_DBNAME = 'postgres'

JWT_SECRET_KEY = 'bc13grp2'

FIREBASE = {
    'type': 'service_account',
    'project_id': 'bc13-wiet',
    'private_key_id': 'ff432a47c33fb369a10f074a1e250052e33552d0',
    'private_key': '-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC5kYbIKWzjTeMM\n9G'
                   '/CT/G+ZqJ90RRsxXdOCwp/OZI9a8jmv5dhTc1fjUZMf9Obp4u+5h5iqaAGOTX6'
                   '\nxYhLil5to2MjGKnr72P9eAQzf0Zo2fJDIzXWwV3WDgpw24CJ0FvyKGjLuq7yGMZs'
                   '\nNTCdP4xtjbl3XLM8Ug9pqq6nmU63MGLBENtMEgZqYf9lMCm7ofYZ5tKNUNq7TLJZ\nJHh0EFeCHZv5vF'
                   '+XOZbnVhZbjTY2seB3PhWbT3lFO/UwS+PbXwDH/4lHAEqOhpop\nk+d'
                   '+gOVI1chQhfbyHh3zTXz1OZVIn5BzEiylmXrXBKyxUs+ey2MfFOGJWlCZh1aI\n/duPaPYjAgMBAAECggEAA37YxZt2KB'
                   '/2928BTe04lggT5n/SPSxJD4jItVXNyedm\nXOZeASC4jiS4nsdHoqZ4zOtDxkMAty1LtYIUC6w7FCCgRPB5fu6'
                   '++O7SUXAD0QTN\n+fLHWFjDHj2cd7zCwpXQrZKSSsgk9H1/+1jTJhsk14l0ZWmzHJ9FGA31hUWvhGbW\nKoODm62mk'
                   '/Ceez2mNEpEGbqUWlEXy5mFtsK1nys7OMx7m+CXtEpL0tghYq28CcYD'
                   '\nO6sCim66kNWje3KsMHSlv59bChn8mBizKHvSOStFMzTU0h7F+pgU9A6EZOXjsw6m\nPzI'
                   '+Y4ocgb9UKEM7hn04PGm3y1KxTOD8qMSbyMgC5QKBgQDzV5H4GMwtZ/zPsPkp'
                   '\nx3UZAT7XmKXswJJALlrVQxkask2YiBuGL469zJjsabJEPdG0g3j9qmsO0X/EWbtI'
                   '\n1ghYR7Qqnu2YwMXBIoEf2t90cvD2QIZnm875x2Eg1+qVj+MksgNDdygr8v5cQ/CM\n5RyAP+VMLy'
                   '+BPpSrAfXD8RCrbwKBgQDDOJ9Q3B+I1220c2JaEbtZukPNkBqTa/2s\nRrHq943qYBnAzY5Fx'
                   '+M6v0wz1aC7iReJ2qCoE3emXop1quGOPax3toAcuKhFvvgP'
                   '\nN5pBDqK2T02kI8hg6ddf3aWNfIl1GtqiUydCZWVf7KooSC767clHhewKgjFRDR/D\n8cW4+fEWjQKBgQDXJxEns'
                   '/0rFGtB0y6qUvFk12YRx6TFDIsC9jlXoAjdQtM6zvTP\nEtN5WyHGvblhfusgWLIsTnZz1qXKHLBruNoN++//Ux'
                   '+uVJtEpVZXrPSviEUm5Www\nxWEeFbv6Bw4hiOQDeOVLzFoXabSeLW/Nk3E8r8H0NzAgbQ2TmZsL0OO1PwKBgQCJ'
                   '\nWidNXvKY9ulq0tvZ/yvP36ugz8jC/HIIadz4MqCYzHCSJI37Lr4DMWDN/3oNOFFl\nXns2hivi1BWa4FW+HEpiF'
                   '/dfPdS27bveCQLwzvlQR8+asspi1WQBQRMfzwVvbSty\nlgrDPk/rA7pciRz006SQO/ttEVjda9xzooG4ubIUaQKBgH9k2cMH'
                   '+IYLMqhuKl6q\nj+Pvp4FxO3QX3tmYBsRZ7INkZ1ccSS/AClWhgL675KdATEbybUPqpDpRJV+2oEUQ\nIwvZUyv'
                   '+pssgtToGTkFZLhrGk3R2PMEoL5wMjzgCdv0P0WfKlx1gZQBWrjOyLEoa\nOJ2upU44k2Yd/aL2bCvySCCo\n-----END '
                   'PRIVATE KEY-----\n',
    'client_email': 'firebase-adminsdk-qwaq1@bc13-wiet.iam.gserviceaccount.com',
    'client_id': '118319996129157595879',
    'auth_uri': 'https://accounts.google.com/o/oauth2/auth',
    'token_uri': 'https://oauth2.googleapis.com/token',
    'auth_provider_x509_cert_url': 'https://www.googleapis.com/oauth2/v1/certs',
    'client_x509_cert_url': 'https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-qwaq1%40bc13-wiet'
                            '.iam.gserviceaccount.com '
}

REDIS_ENDPOINT = 'redis://127.0.0.1:6379'


def config(app):
    app.config.update(
        CELERY_BROKER_URL=REDIS_ENDPOINT,
        CELERY_RESULT_BACKEND=REDIS_ENDPOINT
    )

    app.config['API_ENDPOINT'] = API_ENDPOINT
    app.config['DEBUG'] = DEBUG_MODE
    app.config['PORT'] = PORT
    app.config['SECRET_KEY'] = SECRET_KEY

    # SQLALCHEMY DATABASE
    db_uri = ENGINE + '://' + DB_USERNAME + ':' + DB_PASSWORD + '@' + DB_ENDPOINT + '/' + DB_DBNAME
    app.config['SQLALCHEMY_DATABASE_URI'] = db_uri
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = SQLALCHEMY_TRACK_MODIFICATIONS

    # FLAK-JWT-EXTENDED
    app.config['JWT_SECRET_KEY'] = JWT_SECRET_KEY

    # FIREBASE
    app.config['FIREBASE'] = FIREBASE
