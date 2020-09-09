# -*- coding: utf-8 -*-
# Author: Rowan
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import allergy_controller


@app.route('/api/v1.0/allergy', methods=['POST'])
@jwt_required
def create_allergy():
    return allergy_controller.create_allergy_controller(request)


@app.route('/api/v1.0/allergies', methods=['GET'])
@jwt_required
def get_allergies():
    return allergy_controller.get_allergies_controller()


@app.route('/api/v1.0/allergy/<allergy_id>', methods=['DELETE'])
@jwt_required
def delete_allergy(allergy_id):
    return allergy_controller.delete_allergy_controller(allergy_id)
