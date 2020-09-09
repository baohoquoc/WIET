# -*- coding: utf-8 -*-
# Author: Rowan
import logging

from project import db
from project.models.Allergy import Allergy
from project.models.MetaTag import MetaTag
from project.utils.object_utils import object_to_dict_utils


def create_allergy_dao(uid, meta_tag_id):
    """Create allergy dao

    :param uid: uid of user
    :param meta_tag_id: tag_id of tag
    :return: True if created success
    """
    try:
        db.session.add(Allergy(uid=uid, meta_tag_id=meta_tag_id))
        db.session.commit()
        return True
    except Exception as e:
        db.session.rollback()
        logging.error(str(e))
        return False


def is_existed_allergy_dao(uid, tag_id):
    """Is existed allergy dao

    :param uid: uid of user
    :param tag_id: tag_id of tag
    :return: True if existed
    """
    return Allergy.query.filter_by(uid=uid, meta_tag_id=tag_id).first() is not None


def get_list_allergy_by_uid_dao(uid, is_list_result=False, is_dict_result=False):
    """Get all allergy by uid

    :param is_dict_result:
    :param is_list_result: True return list
    :param uid: uid
    :return: allergy
    """
    if is_dict_result and is_list_result:
        return []
    results = db.session.query(Allergy, MetaTag.v_name).join(MetaTag, MetaTag.id == Allergy.meta_tag_id).filter(
        Allergy.uid == uid).all()
    if is_list_result:
        result_list = []
        for result in results:
            result_list.append(result[1])
        return result_list
    if is_dict_result:
        result_dict = []
        for result in results:
            data_dict = object_to_dict_utils(result[0])
            data_dict['name'] = result[1]
            result_dict.append(data_dict)
        return result_dict
    return results


def get_allergy_by_id(uid, allergy_id):
    """Get allergy by id

    :param uid: uid
    :param allergy_id: allergy id
    :return: allergy
    """
    return Allergy.query.filter_by(uid=uid, id=allergy_id).one()


def delete_allergy_dao(uid, allergy_id):
    """Delete allergy

    :param uid: uid
    :param allergy_id: allergy id
    :return: True if success
    """
    try:
        db.session.delete(get_allergy_by_id(uid, allergy_id))
        db.session.commit()
        return True
    except Exception as e:
        db.session.rollback()
        logging.error(str(e))
        return False
