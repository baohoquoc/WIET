# -*- coding: utf-8 -*-
# Author: Rowan
import logging

from firebase_admin import auth
from flask_jwt_extended import create_access_token, get_jwt_identity

from project.services import user_service
from project.utils import message_utils
from project.utils.response_utils import make_response_utils


def auth_user_controller(request):
    """Authentication user controller

    :param request: Request from clients
    :return: Response for clients
    """
    # handle missing firebase_token
    if user_service.is_missing_token_service(request):
        return make_response_utils(status=401, error=message_utils.AUTH_MISSING_TOKEN_IN_RB, success=False), 401
    else:
        firebase_token = request.get_json()['firebase_token']

    # handle verify firebase_token
    try:
        decoded_token = auth.verify_id_token(firebase_token)
    except Exception as e:
        logging.error(str(e))
        return make_response_utils(status=402, error=message_utils.AUTH_TOKEN_NOT_WORKING, success=False), 402

    # handle virtual machine login
    if user_service.is_not_valid_device(decoded_token['uid']):
        return make_response_utils(status=403, error=message_utils.AUTH_CANNOT_GET_EMAIL, success=False), 403

    # handle authentication success
    user_service.save_token_service(request.json['fcm_token'], decoded_token['uid'])
    value = {
        "access_token": create_access_token(identity=decoded_token['uid'], expires_delta=False),
        "user": user_service.get_authenticated_user_service(decoded_token['uid'])
    }
    return make_response_utils(status=201, message=message_utils.AUTH_SUCCESS, value=value), 201


def update_user_controller(request):
    """Update user information controller

    :param request: Request from clients
    :return: Response for clients
    """
    if not user_service.is_validated_user_service(request):
        return make_response_utils(status=401, error=message_utils.NOT_CORRECT_FORMAT_FAILURE.format('request body'),
                                   success=False), 401
    if user_service.is_new_user_service(request.json['uid']):
        return make_response_utils(status=402, error=message_utils.NOT_EXIST_FAILURE.format('user'), success=False), 402
    user_service.update_user_service(request)
    return make_response_utils(status=201, message=message_utils.UPDATE_SUCCESS.format('user')), 201


def last_location_controller(request_body):
    """Save the last location of user

    :param request_body: request body include location
    :return: response
    """
    if not user_service.is_validated_location_service(request_body.json):
        return make_response_utils(status=401, error=message_utils.NOT_CORRECT_FORMAT_FAILURE.format('location')), 401
    if user_service.update_location_service(get_jwt_identity(), request_body.json['location']):
        return make_response_utils(status=201, message=message_utils.UPDATE_SUCCESS.format('location')), 201
    return make_response_utils(status=402, error=message_utils.ERROR), 402
