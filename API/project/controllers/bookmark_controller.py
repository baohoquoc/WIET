# -*- coding: utf-8 -*-
# Author: Rowan
import time
from flask_jwt_extended import get_jwt_identity

from project.services import bookmark_service
from project.services import food_service
from project.utils import message_utils
from project.utils.response_utils import make_response_utils


def create_bookmark_controller(request):
    """Create bookmark controller

    :param request: request from clients
    :return: response
    """
    if not bookmark_service.is_validated_bookmark_post_service(request.json):
        return make_response_utils(status=401, error=message_utils.NOT_CORRECT_FORMAT_FAILURE.format('food_id'),
                                   success=False), 401
    if not food_service.is_existed_food_id(request.json['food_id']):
        return make_response_utils(status=402, error=message_utils.NOT_EXIST_FAILURE.format('food_id'),
                                   success=False), 402
    if bookmark_service.create_bookmark_service(get_jwt_identity(), request.json['food_id']):
        return make_response_utils(status=201, message=message_utils.CREATE_SUCCESS.format('bookmark')), 201
    return make_response_utils(status=403, error=message_utils.ERROR, success=False), 403


def delete_bookmark_controller(food_id):
    """Delete bookmark controller

    :param food_id: food_id from clients
    :return: response
    """
    if bookmark_service.delete_bookmark_service(get_jwt_identity(), food_id):
        return make_response_utils(status=201, message=message_utils.DELETE_SUCCESS.format('bookmark')), 201
    return make_response_utils(status=401, error=message_utils.ERROR, success=False), 401


def get_list_bookmarks_controller():
    """Get list bookmarks controller

    :return: response
    """
    values = bookmark_service.get_list_bookmarks_service(get_jwt_identity())
    return make_response_utils(status=201, message=message_utils.GET_LIST_SUCCESS.format('bookmarks'),
                               values=values), 201
