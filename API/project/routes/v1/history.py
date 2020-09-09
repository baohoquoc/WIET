# -*- coding: utf-8 -*-
# Author: Rowan
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import history_controller


@app.route('/api/v1.0/history', methods=['POST'])
@jwt_required
def create_history():
    return history_controller.create_history_controller(request)


@app.route('/api/v1.0/histories', methods=['GET'])
@jwt_required
def get_list_histories():
    return history_controller.get_list_histories_controller()


@app.route('/api/v1.0/history/<history_id>', methods=['DELETE'])
@jwt_required
def delete_history(history_id):
    return history_controller.delete_history_controller(history_id)
