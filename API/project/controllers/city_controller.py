# -*- coding: utf-8 -*-
# Author: Rowan
from project.services import city_service
from project.utils import message_utils
from project.utils.response_utils import make_response_utils


def get_city_controller(request):
    """Get cities controller

    :param request: request from clients
    :return: response
    """
    keyword = request.args.get('search')
    return make_response_utils(message=message_utils.SEARCH_SUCCESS.format('cities'), status=201,
                               values=city_service.search_city_service(keyword)), 201
