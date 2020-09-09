# -*- coding: utf-8 -*-
# Author: Rowan
from project.models.Store import Store
from project.utils.object_utils import object_to_dict_utils


def get_store_by_id_dao(store_id, is_dict_result=False):
    """Get store by id

    :param store_id: store_id
    :param is_dict_result: True return dict
    :return: store response
    """
    result = Store.query.filter_by(restaurant_id=store_id).first()
    if not result:
        return None
    if is_dict_result:
        return object_to_dict_utils(result)
    return result
