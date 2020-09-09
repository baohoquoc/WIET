# -*- coding: utf-8 -*-
# Author: Rowan
from project.dao import store_dao


def get_store_by_id_service(store_id):
    """Get store by id service

    :param store_id: store_id
    :return: store
    """
    if not store_id.isdigit():
        return None
    return store_dao.get_store_by_id_dao(store_id, is_dict_result=True)
