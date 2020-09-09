# -*- coding: utf-8 -*-
# Author: Rowan
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import store_controller


@app.route('/api/v1.0/store/<store_id>', methods=['GET'])
@jwt_required
def get_store_by_id(store_id):
    return store_controller.get_store_by_id_controller(store_id)
