# -*- coding: utf-8 -*-
# Author: Ned
from flask_jwt_extended import get_jwt_identity
from project.services import mealtoday_service
from project.utils.response_utils import make_response_utils
from project.utils import message_utils


def mealtoday_controller(request):
    """Meal today controller

    :param request: request from FE
    :return: response
    """
    uid = get_jwt_identity()
    temperature = request.args.get('temperature')
    if temperature is not None:
        is_valid_temp = mealtoday_service.check_temperature_service(temperature)
        location = mealtoday_service.get_location_for_mealtoday_by_uid_service(uid)
        if is_valid_temp and location is not None:
            return make_response_utils(status=200, message=message_utils.GET_LIST_SUCCESS.format('meal today'),
                                       value=mealtoday_service.mealtoday_service(uid, location, temperature)), 200
        elif location is not None and is_valid_temp is False:
            return make_response_utils(status=400, success=False,
                                       error=message_utils.NOT_CORRECT_FORMAT_FAILURE.format('params')), 400
        elif location is None:
            return make_response_utils(status=402, success=False,
                                       error=message_utils.NOT_EXIST_FAILURE.format('location')), 402
    else:
        return make_response_utils(status=401, success=False, error=message_utils.MISSING_FAILURE.format('params')), 401
