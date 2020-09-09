# -*- coding: utf-8 -*-
# Author: Rowan
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import bookmark_controller


@app.route('/api/v1.0/bookmark', methods=['POST'])
@jwt_required
def create_bookmark():
    return bookmark_controller.create_bookmark_controller(request)


@app.route('/api/v1.0/bookmark/<food_id>', methods=['DELETE'])
@jwt_required
def delete_bookmark(food_id):
    return bookmark_controller.delete_bookmark_controller(food_id)


@app.route('/api/v1.0/bookmarks', methods=['GET'])
@jwt_required
def get_list_bookmarks():
    return bookmark_controller.get_list_bookmarks_controller()
