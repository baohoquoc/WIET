# -*- coding: utf-8 -*-
# Author: Rowan
from flask_jwt_extended import get_jwt_identity

from project.services import allergy_service
from project.utils import message_utils
from project.utils.response_utils import make_response_utils


def create_allergy_controller(request):
    """Create allergy controller

    :param request: request from client
    :return: response
    """
    if not allergy_service.is_validated_post_service(request.json):
        return make_response_utils(status=401, error=message_utils.NOT_CORRECT_FORMAT_FAILURE.format('request body'),
                                   success=False), 401
    if not allergy_service.is_existed_meta_tag_ids(request.json):
        return make_response_utils(status=402, error=message_utils.NOT_EXIST_FAILURE.format('tag_ids'),
                                   success=False), 402
    if allergy_service.create_allergy_service(get_jwt_identity(), request.json):
        return make_response_utils(status=201, message=message_utils.CREATE_SUCCESS.format('allergy')), 201
    return make_response_utils(status=403, error=message_utils.ERROR, success=False), 403


def get_allergies_controller():
    """Get allergies

    :return: response
    """
    values = allergy_service.get_all_allergy_by_uid_service(get_jwt_identity())
    return make_response_utils(status=201, values=values,
                               message=message_utils.GET_LIST_SUCCESS.format('allergies')), 201


def delete_allergy_controller(allergy_id):
    """Delete allergy

    :param allergy_id: allergy id
    :return: response
    """
    if allergy_service.delete_allergy_by_id_service(get_jwt_identity(), allergy_id):
        return make_response_utils(status=201, message=message_utils.DELETE_SUCCESS.format('allergy')), 201
    return make_response_utils(status=401, error=message_utils.ERROR), 401
