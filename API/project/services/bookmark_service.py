# -*- coding: utf-8 -*-
# Author: Rowan, Ned
import logging

from cerberus import Validator

from project.dao import bookmark_dao
from project.services import rating_service, food_service
from project.utils import schema_utils
from project.services.image_service import update_protocol_for_a_list_of_dict


def create_bookmark_service(uid, id_food):
    """Create bookmark service

    :param uid: uid
    :param id_food: id_food
    :return: True if created_success
    """
    if is_existed_bookmark_service(uid, id_food):
        return True
    else:
        food_id = food_service.get_food_id_by_id(id_food)
        rating_service.add_rating_service(uid, food_id, 2)
        return bookmark_dao.create_bookmark_dao(uid, id_food)


def is_validated_bookmark_post_service(request_body):
    """is validated bookmark post service

    :param request_body: request body
    :return: True if validated
    """
    if request_body == {}:
        return False
    schema = schema_utils.get_bookmark_schema()
    validator = Validator(schema, require_all=True)
    result = validator.validate(request_body)
    if validator.errors:
        logging.error(str(validator.errors))
    return result


def delete_bookmark_service(uid, id_food):
    """Delete bookmark service

    :param uid: uid
    :param id_food: food_id
    :return: True if delete success
    """
    food_id = food_service.get_food_id_by_id(id_food)
    rating_service.subtract_rating_service(uid, food_id, 2)
    return bookmark_dao.delete_bookmark_dao(uid, id_food)


def is_existed_bookmark_service(uid, food_id):
    """Is existed bookmark service

    :param uid: uid
    :param food_id: food_id
    :return:
    """
    return bookmark_dao.is_existed_bookmark_dao(uid, food_id)


def get_list_bookmarks_service(uid):
    """Get list bookmarks service

    :param uid: uid
    :return: list bookmarks
    """
    results = bookmark_dao.get_list_bookmarks_dao(uid, is_dict_result=True)
    for result in results:
        result['is_bookmarked'] = True
    return update_protocol_for_a_list_of_dict('image', results, uid)
