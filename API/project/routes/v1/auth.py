# -*- coding: utf-8 -*-
# Author: Rowan
from flask import request

from project import app
from project.controllers import user_controller


@app.route('/api/v1.0/auth', methods=['POST'])
def auth():
    return user_controller.auth_user_controller(request)
