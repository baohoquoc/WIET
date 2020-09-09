# -*- coding: utf-8 -*-
# Author: Rowan
import logging

from cerberus import Validator

from project.dao import history_dao
from project.utils import schema_utils
from project.services.image_service import update_protocol_for_a_list_of_dict


def create_history_service(uid, food_id):
    """Create history

    :param uid: uid
    :param food_id: food_id
    :return: True if success
    """
    return history_dao.create_history_dao(uid, food_id)


def is_validated_history_post_service(request_body):
    """Is validated history post

    :param request_body: request_body
    :return: True if validated
    """
    if request_body == {}:
        return False
    schema = schema_utils.get_history_schema()
    validator = Validator(schema, require_all=True)
    result = validator.validate(request_body)
    if validator.errors:
        logging.error(str(validator.errors))
    return result


def get_list_histories_service(uid):
    """Get list history service

    :param uid: uid
    :return: list history
    """
    return update_protocol_for_a_list_of_dict('image', history_dao.get_list_histories_dao(uid, is_dict_result=True),
                                              uid)


def delete_history_service(history_id):
    """

    :param history_id:
    :return:
    """
    return history_dao.delete_history_dao(history_id)
