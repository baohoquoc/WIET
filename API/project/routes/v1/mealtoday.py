# -*- coding: utf-8 -*-
# Author: Ned
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import mealtoday_controller


@app.route('/api/v1.0/mealtoday', methods=['GET'])
@jwt_required
def mealtoday():
    return mealtoday_controller.mealtoday_controller(request)
