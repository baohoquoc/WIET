# -*- coding: utf-8 -*-
# Author: Rowan
from flask_jwt_extended import get_jwt_identity

from project.services import survey_service
from project.utils import message_utils
from project.utils.response_utils import make_response_utils


def get_survey_controller():
    """Get survey controller

    :return: response
    """
    return make_response_utils(status=201, values=survey_service.get_survey_service(get_jwt_identity()),
                               message=message_utils.GET_LIST_SUCCESS.format('survey')), 201


def search_survey_controller(request):
    """Search survey controller

    :param request: request from clients
    :return: response
    """
    offset = request.args.get('offset')
    limit = request.args.get('limit')
    search = request.args.get('search')
    value, values = survey_service.search_survey_service(offset, limit, search)
    return make_response_utils(status=201, value=value, values=values,
                               message=message_utils.SEARCH_SUCCESS.format('survey')), 201
