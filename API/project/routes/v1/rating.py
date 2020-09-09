# -*- coding: utf-8 -*-
# Author: Ned, Rowan
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import rating_controller


@app.route('/api/v1.0/rating', methods=['POST'])
@jwt_required
def rating():
    return rating_controller.rating_controller(request)


@app.route('/api/v1.0/rating/top', methods=['GET'])
@jwt_required
def get_top_rating():
    return rating_controller.get_top_rating_controller(request)
