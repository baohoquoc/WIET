# -*- coding: utf-8 -*-
# Author: Rowan
from project.models.FoodTag import FoodTag
from project.models.MetaTag import MetaTag
from project.models.Tag import Tag
from project.models.TagMetaTag import TagMetaTag
from project.utils.object_utils import list_object_to_dict_utils


def get_paging_tag_dao(offset, limit, keyword, is_dict_result=False):
    """Paging tags data

    :param offset: offset
    :param limit: limit
    :param keyword: keyword
    :param is_dict_result: if true return dict
    :return:
    """
    escaped_keyword = '%{}%'.format(keyword.replace('%', '/%').replace('_', '/_'))
    result_tag = Tag.query.order_by(Tag.name).filter(Tag.name.ilike(escaped_keyword)).offset(
        (offset - 1) * limit).limit(limit).all()
    if is_dict_result:
        return list_object_to_dict_utils(result_tag)
    return result_tag


def get_total_record_of_tag(keyword):
    """Total record of tags

    :param keyword: keyword
    :return: number of record
    """
    escaped_keyword = '%{}%'.format(keyword.replace('%', '/%').replace('_', '/_'))
    return len(Tag.query.filter(Tag.name.ilike(escaped_keyword)).all())


def get_tag_by_meta_tag_id_dao(meta_tag_id, is_dict_result=False):
    """Get meta-tags by meta-tag_id

    :param meta_tag_id: meta_tag_id
    :param is_dict_result: tag dict
    :return:
    """
    result = Tag.query.filter(Tag.id == TagMetaTag.tag_id).filter(TagMetaTag.meta_tag_id == MetaTag.id).filter(
        MetaTag.id == meta_tag_id).all()
    if is_dict_result:
        return list_object_to_dict_utils(result)
    return result


def is_exist_tag_id_dao(tag_id):
    """Is exist tag-id dao

    :param tag_id: tag_id
    :return: True if existed
    """
    result = Tag.query.filter_by(id=tag_id).first()
    return result is not None


def get_list_tags_by_food_id_dao(food_id):
    """Get list tags by food_id

    :param food_id: food_id
    :return: list tags
    """
    results = FoodTag.query.filter_by(food_id=food_id).all()
    result_list = []
    for result in results:
        result_list.append(result.tag_id)
    return result_list
