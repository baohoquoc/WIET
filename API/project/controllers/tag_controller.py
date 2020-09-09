# -*- coding: utf-8 -*-
# Author: Rowan
from project.services import tag_service
from project.utils import message_utils
from project.utils.response_utils import make_response_utils


def get_tag_controller(request):
    """Get all tag controller

    :return: All tag in database
    """
    offset = request.args.get('offset')
    limit = request.args.get('limit')
    search = request.args.get('search')
    query_params = tag_service.validate_query_params(offset, limit, search)
    value = tag_service.get_paging_service(query_params['offset'], query_params['limit'], query_params['search'])
    values = tag_service.get_paging_tag_service(query_params['offset'], query_params['limit'], query_params['search'])
    return make_response_utils(status=201, value=value, values=values,
                               message=message_utils.GET_LIST_SUCCESS.format('tags')), 201


def get_tag_by_meta_tag_id_controller(meta_tag_id):
    """Get tag by meta tag id controller

    :param meta_tag_id: meta_tag_id
    :return: tag
    """
    tags = tag_service.get_tag_by_meta_tag_id_service(meta_tag_id)
    if tags:
        return make_response_utils(status=201, values=tags, message=message_utils.GET_LIST_SUCCESS.format('tags')), 201
    return make_response_utils(status=401, success=False, error=message_utils.NOT_EXIST_FAILURE.format('meta-tag')), 401
