# -*- coding: utf-8 -*-
# Author: Rowan
import logging

from cerberus import Validator

from project.dao import allergy_dao
from project.services import meta_tag_service
from project.utils import schema_utils


def is_existed_meta_tag_ids(request_body):
    """Is existed tag_ids service

    :param request_body: request body
    :return: True if all existed
    """
    for meta_tag_id in request_body['meta_tag_ids']:
        if not meta_tag_service.is_existed_tag_service(meta_tag_id):
            return False
    return True


def is_validated_post_service(request_body):
    """Is validated post service

    :param request_body: request body
    :return: True if validated
    """
    if request_body == {}:
        return False
    schema = schema_utils.get_allergy_schema()
    validator = Validator(schema, require_all=True)
    result = validator.validate(request_body)
    if validator.errors:
        logging.error(str(validator.errors))
    return result


def create_allergy_service(uid, request_body):
    """Create allergy service

    :param uid: uid
    :param request_body: request body
    :return: True if created success
    """
    for meta_tag_id in request_body['meta_tag_ids']:
        if not is_existed_allergy_service(uid, meta_tag_id):
            if not allergy_dao.create_allergy_dao(uid, meta_tag_id):
                return False
    return True


def is_existed_allergy_service(uid, tag_id):
    """Is existed allergy service

    :param uid: uid
    :param tag_id: tag_id
    :return: True if existed
    """
    return allergy_dao.is_existed_allergy_dao(uid, tag_id)


def get_all_allergy_by_uid_service(uid):
    """Get all allergy by uid

    :param uid: uid
    :return: allergy
    """
    allergies = allergy_dao.get_list_allergy_by_uid_dao(uid, is_dict_result=True)
    for allergy in allergies:
        del allergy['uid']
    return allergies


def get_list_allergy_by_uid_service(uid):
    """Get list allergy by uid

    :param uid: uid
    :return: allergy
    """
    return allergy_dao.get_list_allergy_by_uid_dao(uid, is_list_result=True)


def delete_allergy_by_id_service(uid, allergy_id):
    """Delete allergy by id

    :param uid: uid
    :param allergy_id: allergy id
    :return: True if success
    """
    return allergy_dao.delete_allergy_dao(uid, allergy_id)
