# -*- coding: utf-8 -*-
# Author: Rowan
from project.services import meta_tag_service
from project.utils import message_utils
from project.utils.response_utils import make_response_utils


def get_meta_tag_controller(request):
    """Get meta tags controller

    :param request: request from clients
    :return: response
    """
    keyword = request.args.get('search')
    limit = request.args.get('limit')
    values = meta_tag_service.search_meta_tag_service(keyword, limit)
    return make_response_utils(status=201, message=message_utils.GET_LIST_SUCCESS.format('meta-tags'),
                               values=values), 201


def get_meta_tags_by_tag_id_controller(tag_id):
    """Get meta_tags by tag_id controller

    :param tag_id: tag_id from clients
    :return: response
    """
    meta_tags = meta_tag_service.get_meta_tags_by_tag_id_service(tag_id)
    if meta_tags:
        return make_response_utils(status=201, values=meta_tags,
                                   message=message_utils.GET_LIST_SUCCESS.format('meta-tags')), 201
    return make_response_utils(status=401, success=False, error=message_utils.NOT_EXIST_FAILURE.format('tag_id')), 401
