# -*- coding: utf-8 -*-
# Author: Rowan
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import city_controller


@app.route('/api/v1.0/city', methods=['GET'])
@jwt_required
def get_city():
    return city_controller.get_city_controller(request)
