# -*- coding: utf-8 -*-
# Author: Rowan
from project import db
from project.dao import recommend_dao
from project.models.MetaTag import MetaTag
from project.models.Survey import Survey
from project.models.Tag import Tag


def get_survey_dict_result(uid):
    """Get survey dict result

    :return: survey dict
    """
    if recommend_dao.is_vegan_user(uid):
        list_meta_tag_tuple = db.session.query(Survey.meta_tag_id, MetaTag.v_name, MetaTag.e_name).filter(
            Survey.meta_tag_id == MetaTag.id, MetaTag.v_name == 'Chay').group_by(Survey.meta_tag_id, MetaTag.v_name,
                                                                                 MetaTag.e_name).all()
    else:
        list_meta_tag_tuple = db.session.query(Survey.meta_tag_id, MetaTag.v_name, MetaTag.e_name).filter(
            Survey.meta_tag_id == MetaTag.id).group_by(Survey.meta_tag_id, MetaTag.v_name, MetaTag.e_name).all()
    result = []
    for meta_tag_tuple in list_meta_tag_tuple:
        meta_tag_dict = {'id': meta_tag_tuple[0], 'v_name': meta_tag_tuple[1], 'e_name': meta_tag_tuple[2]}
        list_tag_tuple = db.session.query(Survey.tag_id, Survey.image, Tag.name).filter(Tag.id == Survey.tag_id).filter(
            Survey.meta_tag_id == meta_tag_dict['id']).all()
        tag_dict_result = []
        for tag_tuple in list_tag_tuple:
            tag_dict = {'id': tag_tuple[0], 'imageURL': tag_tuple[1], 'name': tag_tuple[2]}
            tag_dict_result.append(tag_dict)
        meta_tag_dict['tags'] = tag_dict_result
        result.append(meta_tag_dict)
    return result
