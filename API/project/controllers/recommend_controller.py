# -*- coding: utf-8 -*-
# Author: Ned
from flask_jwt_extended import get_jwt_identity

from project.services import recommend_service, als_service
from project.utils import message_utils
from project.utils.response_utils import make_response_utils


def food_recommend_controller():
    """Get params and response recommended food

    :return: list food object status 201 if success
            error status 401 if params is not correct
    """
    #als_service.als_recommend_service.delay()
    uid = get_jwt_identity()
    location = recommend_service.get_location_for_recommend_by_uid_service(uid)
    value = {"limit": 5,
             "offset": 1,
             "total_offset": 1,
             "total_record": 5}
    if location is not None:
        return make_response_utils(status=201, message=message_utils.GET_LIST_SUCCESS.format('recommend'), value=value,
                                   values=recommend_service.get_recommend_food_service(location, uid)), 201
    else:
        return make_response_utils(status=401, success=False,
                                   error=message_utils.NOT_EXIST_FAILURE.format('location')), 401
