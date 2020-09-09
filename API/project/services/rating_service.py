# -*- coding: utf-8 -*-
# Author: Ned
import logging

from cerberus import Validator

from project.dao import rating_dao
from project.utils import schema_utils
from project.services.image_service import update_protocol_for_a_list_of_dict

DEFAULT_TOP = 5
DEFAULT_OFFSET = 1


def rating_service(uid, rating):
    """Rating

    :param uid: user_id
    :param rating: point of rating
    :return: True if success, False if fail
    """
    return rating_dao.rating_dao(uid, rating)


def is_validated_rating_service(request):
    """Validate rating json

    :param request: request from FE
    :return: True if correct, False if incorrect
    """
    schema = schema_utils.get_rating_schema()
    validator = Validator(schema)
    result = validator.validate(request.get_json())
    if validator.errors:
        logging.error(str(validator.errors))
    return result


def get_top_rating_service(top, offset, uid):
    """Get top rating service

    :param uid: uid
    :param offset: offset
    :param top: top
    :return: top rating
    """
    if top is None or not top.isdigit():
        top = DEFAULT_TOP
    if offset is None or not offset.isdigit():
        offset = DEFAULT_OFFSET
    return update_protocol_for_a_list_of_dict('image', rating_dao.get_top_rating_dao(int(top), int(offset)), uid)


def skip_rating_service(uid):
    """Rating

    :param uid: user_id
    :return: True if success, False if fail
    """
    return rating_dao.skip_rating_dao(uid)


def add_rating_service(uid, food_id, rating):
    """Add rating

    :param uid: user_id
    :param food_id: foo_id
    :param rating: rating
    :return: True if success, false if fail
    """
    return rating_dao.add_rating_dao(uid, food_id, rating)


def subtract_rating_service(uid, food_id, rating):
    """Subtract rating

    :param uid: user_id
    :param food_id: foo_id
    :param rating: rating
    :return: True if success, false if fail
    """
    return rating_dao.subtract_rating_dao(uid, food_id, rating)
