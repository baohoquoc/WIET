# -*- coding: utf-8 -*-
# Author: Rowan
from flask_jwt_extended import get_jwt_identity

from project.services import food_service
from project.utils import message_utils
from project.utils.response_utils import make_response_utils


def food_detail_controller(food_id):
    """Food detail controller

    :param food_id: food_id from clients
    :return: response
    """
    if food_service.is_existed_food_id(food_id):
        food = food_service.get_food_detail_service(get_jwt_identity(), food_id)
        list_same_store_food = food_service.get_list_same_store_food_by_food_id_service(food_id, get_jwt_identity())
        if food and list_same_store_food:
            return make_response_utils(status=201, value=food, values=list_same_store_food,
                                       message=message_utils.GET_SUCCESS.format('food')), 201
        return make_response_utils(status=401, success=False,
                                   message=message_utils.NOT_EXIST_FAILURE.format('food')), 401
    else:
        return make_response_utils(status=401, success=False,
                                   message=message_utils.NOT_EXIST_FAILURE.format('food')), 401


def search_limited_food_controller(request):
    """Search limited food controller

    :param request: request from clients
    :return: response
    """
    values = food_service.search_limited_food_service(request.args.get('search'), request.args.get('limit'),
                                                      get_jwt_identity())
    return make_response_utils(status=201, values=values, message=message_utils.SEARCH_SUCCESS.format('food')), 201
