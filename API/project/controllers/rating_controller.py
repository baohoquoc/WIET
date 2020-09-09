# -*- coding: utf-8 -*-
# Author: Ned
from flask_jwt_extended import get_jwt_identity

from project.services import rating_service
from project.utils import message_utils
from project.utils.response_utils import make_response_utils


def rating_controller(request):
    """Rating controller

    :param request: request from FE
    :return: response
    """
    data = request.get_json()
    uid = get_jwt_identity()
    if not data['rating']:
        if rating_service.skip_rating_service(uid):
            return make_response_utils(status=202, message=message_utils.SKIP_RATING_SUCCESS), 202
        else:
            return make_response_utils(status=403, error=message_utils.SKIP_RATING_FAILURE, success=False), 403
    else:
        if rating_service.is_validated_rating_service(request) and rating_service.rating_service(uid, data['rating']):
            return make_response_utils(status=201, message=message_utils.CREATE_SUCCESS.format('rating')), 201
        else:
            return make_response_utils(status=402, error=message_utils.CREATE_FAILURE.format('rating'),
                                       success=False), 402


def get_top_rating_controller(request):
    """Get top rating controller

    :param request: request from clients
    :return: response
    """
    values = rating_service.get_top_rating_service(request.args.get('top'), request.args.get('offset'),
                                                   get_jwt_identity())
    return make_response_utils(status=201, message=message_utils.GET_LIST_SUCCESS.format('top'), values=values), 201
