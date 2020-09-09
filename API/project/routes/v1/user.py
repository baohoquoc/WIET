# -*- coding: utf-8 -*-
# Author: Rowan
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import user_controller


@app.route('/api/v1.0/user', methods=['PUT'])
@jwt_required
def update_user():
    return user_controller.update_user_controller(request)


@app.route('/api/v1.0/save_last_location', methods=['PATCH'])
@jwt_required
def save_last_location():
    return user_controller.last_location_controller(request)
