# -*- coding: utf-8 -*-
# Author: Rowan
from project.dao import survey_dao
from project.services import tag_service, meta_tag_service


def get_survey_service(uid):
    """Get survey service

    :return: survey
    """
    return survey_dao.get_survey_dict_result(uid)


def search_survey_service(offset, limit, search):
    """Search survey service

    :param offset: offset
    :param limit: limit
    :param search: search keyword
    :return: value and values
    """
    query_params = tag_service.validate_query_params(offset, limit, search)
    value = tag_service.get_paging_service(query_params['offset'], query_params['limit'], query_params['search'])
    values = tag_service.get_paging_tag_service(query_params['offset'], query_params['limit'], query_params['search'])
    for item in values:
        item['imageURL'] = ''
        item['meta_tags'] = meta_tag_service.get_meta_tags_by_tag_id_service(str(item['id']))
    return value, values
