# -*- coding: utf-8 -*-
# Author: Rowan
import math

from project.dao import tag_dao

DEFAULT_OFFSET = 1
DEFAULT_LIMIT = 200


def validate_query_params(offset, limit, search):
    """Validate query params service

    :param offset: offset
    :param limit: limit
    :param search: keyword
    :return: validated params
    """
    if offset is None or not offset.isdigit():
        offset = DEFAULT_OFFSET
    if limit is None or not limit.isdigit():
        limit = DEFAULT_LIMIT
    if search is None:
        search = ''
    params = get_paging_service(int(offset), int(limit), str(search))
    return {'offset': params['offset'], 'limit': params['limit'], 'search': str(search)}


def get_paging_tag_service(offset, limit, search):
    """Get tags data service

    :param offset: offset
    :param limit: limit
    :param search: keyword
    :return: Tags in database
    """
    return tag_dao.get_paging_tag_dao(offset, limit, search, is_dict_result=True)


def get_paging_service(offset, limit, search):
    """Get paging service

    :param offset: offset
    :param limit: limit
    :param search: keyword
    :return: Paging information
    """
    total_record_of_tag = tag_dao.get_total_record_of_tag(search)
    total_offset = math.ceil(total_record_of_tag / limit)
    if total_offset == 0:
        total_offset = 1
    if offset <= 0:
        offset = DEFAULT_OFFSET
    if offset > total_offset:
        offset = total_offset
    if limit > total_record_of_tag:
        limit = total_record_of_tag
    if limit <= 0:
        limit = DEFAULT_LIMIT
    return {'offset': offset, 'limit': limit, 'total_offset': total_offset, 'total_record': total_record_of_tag}


def get_tag_by_meta_tag_id_service(meta_tag_id):
    """

    :param meta_tag_id:
    :return:
    """
    if not meta_tag_id.isdigit():
        return None
    return tag_dao.get_tag_by_meta_tag_id_dao(meta_tag_id, is_dict_result=True)


def is_exist_tag_id(tag_id):
    """

    :param tag_id:
    :return:
    """
    return tag_dao.is_exist_tag_id_dao(tag_id)
