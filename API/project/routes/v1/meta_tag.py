# -*- coding: utf-8 -*-
# Author: Rowan
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import meta_tag_controller


@app.route('/api/v1.0/meta_tags', methods=['GET'])
@jwt_required
def get_meta_tag():
    return meta_tag_controller.get_meta_tag_controller(request)


@app.route('/api/v1.0/meta_tags/<tag_id>', methods=['GET'])
@jwt_required
def get_meta_tag_by_tag_id(tag_id):
    return meta_tag_controller.get_meta_tags_by_tag_id_controller(tag_id)
