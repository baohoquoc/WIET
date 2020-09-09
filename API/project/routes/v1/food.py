# -*- coding: utf-8 -*-
# Author: Rowan
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import food_controller


@app.route('/api/v1.0/foods/<food_id>', methods=['GET'])
@jwt_required
def food_detail(food_id):
    return food_controller.food_detail_controller(food_id)


@app.route('/api/v1.0/foods', methods=['GET'])
@jwt_required
def search_limited_food():
    return food_controller.search_limited_food_controller(request)
