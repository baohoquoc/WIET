# -*- coding: utf-8 -*-
# Author: Rowan
from project.models.MetaTag import MetaTag
from project.models.Tag import Tag
from project.models.TagMetaTag import TagMetaTag
from project.utils.object_utils import list_object_to_dict_utils


def get_all_meta_tag_dao(is_dict_result=False):
    """Get all meta tag in database

    :param is_dict_result: Return dict if True
    :return: All meta tags in database
    """
    meta_tags = MetaTag.query.order_by(MetaTag.v_name).all()
    if is_dict_result:
        return list_object_to_dict_utils(meta_tags)
    return meta_tags


def search_meta_tag_dao(keyword, limit, is_dict_result=False):
    """Get all searched meta tags

    :param limit: limit
    :param keyword: keyword from clients
    :param is_dict_result: Return dict if True
    :return: All meta tags searched
    """
    keyword = '%{}%'.format(keyword.replace('%', '/%').replace('_', '/_'))
    searched_meta_tags = MetaTag.query.order_by(MetaTag.v_name).filter(MetaTag.v_name.ilike(keyword)).limit(limit).all()
    if is_dict_result:
        return list_object_to_dict_utils(searched_meta_tags)
    return searched_meta_tags


def get_meta_tags_by_tag_id_dao(tag_id, is_dict_result):
    """Get meta tags by tag id dao

    :param tag_id: tag_id
    :param is_dict_result: Return dict if True
    :return: list meta-tags
    """
    result = MetaTag.query.filter(MetaTag.id == TagMetaTag.meta_tag_id).filter(TagMetaTag.tag_id == Tag.id).filter(
        Tag.id == tag_id).all()
    if is_dict_result:
        return list_object_to_dict_utils(result)
    return result


def is_existed_meta_tag(meta_tag_id):
    """Is existed meta tag

    :param meta_tag_id: meta tag id
    :return: True if existed
    """
    result = MetaTag.query.filter_by(id=meta_tag_id).first()
    return result is not None
