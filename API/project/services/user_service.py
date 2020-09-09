# -*- coding: utf-8 -*-
# Author: Rowan
import logging

from cerberus import Validator
from firebase_admin import auth

from project.dao import user_dao
from project.models.FCMToken import FCMToken
from project.models.User import User
from project.services import fcm_token_service
from project.utils import schema_utils
from project.services import city_service


def is_new_user_service(uid):
    """Check exist uid is in database or not

    :param uid: uid of user
    :return: True if user not in database
    """
    user = user_dao.get_user_by_uid_dao(uid)
    return user is None


def is_missing_token_service(request):
    """Check firebase_token is in request body or not

    :param request: Request data from request
    :return: True if firebase_token not in request
    """
    if request.json == {}:
        return True
    schema = schema_utils.get_auth_schema()
    validator = Validator(schema, require_all=True)
    result = validator.validate(request.json)
    if validator.errors:
        logging.error(str(validator.errors))
    return not result


def get_authenticated_user_service(uid):
    """Get authenticated user

    :param uid: uid of user
    :return: authenticated user
    """
    user = auth.get_user(uid)
    if is_new_user_service(uid):
        user_dao.create_new_user_dao(user)
    return user_dao.get_user_by_uid_dao(uid, is_dict_result=True)


def is_not_valid_device(uid):
    """Check valid device

    :param uid: uid of user
    :return: True if device valid
    """
    user = auth.get_user(uid)
    return user.email is None


def save_token_service(fcm_token, uid):
    """Save token to database

    :param fcm_token: fcm_token from clients
    :param uid: uid of user
    :return: True if save success
    """
    fcm = fcm_token_service.save_fcm_token_service(FCMToken(fcm_token=fcm_token, uid=uid))
    return fcm


def is_validated_user_service(request):
    """Check update user is validated or not

    :param request: request from clients
    :return: True if validated
    """
    schema = schema_utils.get_user_info_schema()
    validator = Validator(schema, require_all=True)
    result = validator.validate(request.json)
    if validator.errors:
        logging.error(str(validator.errors))
    return result


def update_user_service(request):
    """Update user service

    :param request: request from clients
    :return: True if update success
    """
    user = User(**request.json)
    result = user_dao.update_user_dao(user)
    return result


def get_all_uid_service():
    """Get all uid service

    :return: A list of tuples uid
    """
    return user_dao.get_all_uid_dao()


def update_location_service(uid, location):
    """Update location service

    :param uid: uid
    :param location: location
    :return: True if success
    """
    return user_dao.update_location_dao(uid, location)


def is_validated_location_service(request_body):
    """Is validated location service

    :param request_body: request body
    :return: True if validated
    """
    schema = schema_utils.get_location_schema()
    validator = Validator(schema, require_all=True)
    result = validator.validate(request_body)
    if validator.errors:
        logging.error(str(validator.errors))
    return result


def get_location_by_uid_service(uid):
    """Get location by uid

    :param uid: uid
    :return: location if exist, None if user or location not exist
    """
    user = user_dao.get_user_by_uid_dao(uid)
    if user is not None:
        location_in_user = user.location
        if location_in_user is not None:
            location_in_city = city_service.get_city_by_name_service(location_in_user)
            if location_in_city is not None:
                return location_in_user
            else:
                return None
        else:
            return None
    else:
        return None
