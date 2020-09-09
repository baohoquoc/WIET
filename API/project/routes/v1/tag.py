# -*- coding: utf-8 -*-
# Author: Rowan
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import tag_controller


@app.route('/api/v1.0/tags', methods=['GET'])
@jwt_required
def get_tag():
    return tag_controller.get_tag_controller(request)


@app.route('/api/v1.0/tags/<meta_tag_id>', methods=['GET'])
@jwt_required
def get_tag_by_meta_tag_id(meta_tag_id):
    return tag_controller.get_tag_by_meta_tag_id_controller(meta_tag_id)
