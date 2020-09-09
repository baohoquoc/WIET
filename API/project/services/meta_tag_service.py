# -*- coding: utf-8 -*-
# Author: Rowan
from project.dao import meta_tag_dao

DEFAULT_META_TAG_LIMIT = 10


def get_all_meta_tag_service():
    """Get all meta tags

    :return: All meta tags
    """
    return meta_tag_dao.get_all_meta_tag_dao(is_dict_result=True)


def search_meta_tag_service(keyword, limit):
    """Search meta tag service

    :param keyword: keyword
    :param limit: limit
    :return: searched meta tag
    """
    if keyword is None:
        keyword = ""
    if limit is None or not limit.isdigit():
        limit = DEFAULT_META_TAG_LIMIT
    return meta_tag_dao.search_meta_tag_dao(keyword, limit, is_dict_result=True)


def get_meta_tags_by_tag_id_service(tag_id):
    """Get meta tags by tag id service

    :param tag_id: tag id
    :return: meta tags
    """
    if not tag_id.isdigit():
        return None
    return meta_tag_dao.get_meta_tags_by_tag_id_dao(tag_id, is_dict_result=True)


def is_existed_tag_service(meta_tag_id):
    """Is existed tag service

    :param meta_tag_id: meta tag id
    :return: True if existed
    """
    return meta_tag_dao.is_existed_meta_tag(meta_tag_id)
