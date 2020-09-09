# -*- coding: utf-8 -*-
# Author: Rowan
from flask import request
from flask_jwt_extended import jwt_required

from project import app
from project.controllers import survey_controller


@app.route('/api/v1.0/survey', methods=['GET'])
@jwt_required
def get_survey():
    return survey_controller.get_survey_controller()


@app.route('/api/v1.0/survey/search', methods=['GET'])
@jwt_required
def search_survey():
    return survey_controller.search_survey_controller(request)
