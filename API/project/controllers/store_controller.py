# -*- coding: utf-8 -*-
# Author: Rowan
from project.services import store_service
from project.utils import message_utils
from project.utils.response_utils import make_response_utils


def get_store_by_id_controller(store_id):
    """Get store by id controller

    :param store_id: store_id from clients
    :return: response
    """
    store = store_service.get_store_by_id_service(store_id)
    if store:
        return make_response_utils(status=201, value=store, message=message_utils.GET_SUCCESS.format('store')), 201
    return make_response_utils(status=401, success=False, error=message_utils.NOT_EXIST_FAILURE.format('store')), 401
