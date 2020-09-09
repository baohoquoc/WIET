# -*- coding: utf-8 -*-
# Author: Rowan
from flask_jwt_extended import get_jwt_identity

from project.services import history_service, food_service
from project.utils import message_utils
from project.utils.response_utils import make_response_utils


def create_history_controller(request):
    """Create history controller

    :param request: request
    :return: response
    """
    if not history_service.is_validated_history_post_service(request.json):
        return make_response_utils(status=401, error=message_utils.NOT_CORRECT_FORMAT_FAILURE.format('history'),
                                   success=False), 401
    if not food_service.is_existed_food_id(request.json['food_id']):
        return make_response_utils(status=402, error=message_utils.NOT_EXIST_FAILURE.format('food_id'),
                                   success=False), 402
    if history_service.create_history_service(get_jwt_identity(), request.json['food_id']):
        return make_response_utils(status=201, message=message_utils.CREATE_SUCCESS.format('history')), 201
    return make_response_utils(status=403, message=message_utils.ERROR, success=False), 403


def get_list_histories_controller():
    """Get list history controller

    :return: response
    """
    return make_response_utils(status=201, message=message_utils.GET_LIST_SUCCESS.format('histories'),
                               values=history_service.get_list_histories_service(get_jwt_identity())), 201


def delete_history_controller(history_id):
    """Delete history controller

    :return: response
    """
    if history_service.delete_history_service(history_id):
        return make_response_utils(status=201, message=message_utils.DELETE_SUCCESS.format('history')), 201
    return make_response_utils(status=401, error=message_utils.ERROR, success=False), 401
