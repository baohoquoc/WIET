# -*- coding: utf-8 -*-
# Author: Ned
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import recommend_controller


@app.route('/api/v1.0/recommend', methods=['GET'])
@jwt_required
def recommend():
    return recommend_controller.food_recommend_controller()
