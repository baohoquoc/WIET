# -*- coding: utf-8 -*-
# Author: Rowan
import atexit
import logging

import firebase_admin
from apscheduler.schedulers.background import BackgroundScheduler
from firebase_admin import credentials
from flask import Flask
from flask_admin import Admin
from flask_jwt_extended import JWTManager
from flask_login import LoginManager
from flask_sqlalchemy import SQLAlchemy

from config import config
from lib import celery_module
from project.utils.view_utils import MyHomeView

app = Flask('project')

# call config file
config(app)

# config database
db = SQLAlchemy(app)

# config login
login = LoginManager(app)

# config admin
admin = Admin(app, name='WIET', template_mode='bootstrap3', index_view=MyHomeView())

# config celery
celery = celery_module.make_celery(app)

# config logging
logging.basicConfig(level=logging.DEBUG)

# config jwt
jwt = JWTManager(app)

# config firebase
cred = credentials.Certificate(app.config['FIREBASE'])
default_app = firebase_admin.initialize_app(cred)

# config schedule
scheduler = BackgroundScheduler({'apscheduler.timezone': 'Asia/Ho_Chi_Minh'})

# import routes
from project.routes.v1 import *

# schedule init
from project.schedule import *

# start schedule
scheduler.start()

# Shut down the scheduler when exiting the app
atexit.register(lambda: scheduler.shutdown())
