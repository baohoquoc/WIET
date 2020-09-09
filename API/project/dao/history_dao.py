# -*- coding: utf-8 -*-
# Author: Rowan
import logging

from project import db
from project.models.Food import Food
from project.models.History import History
from project.utils.object_utils import object_to_dict_utils


def create_history_dao(uid, food_id):
    """Create history

    :param uid: uid
    :param food_id: food_id
    :return: True if success
    """
    try:
        db.session.add(History(uid=uid, food_id=food_id))
        db.session.commit()
        return True
    except Exception as e:
        db.session.rollback()
        logging.error(str(e))
        return False


def get_history_dao(uid, food_id):
    """Get history

    :param uid: uid
    :param food_id: food_id
    :return: history
    """
    return History.query.filter_by(uid=uid, food_id=food_id).one()


def get_history_by_id_dao(history_id):
    """Get history by id

    :param history_id: history_id
    :return: history
    """
    return History.query.filter_by(id=history_id).one()


def delete_history_dao(history_id):
    """Delete history

    :param history_id: history_id
    :return: True if success
    """
    try:
        db.session.delete(get_history_by_id_dao(history_id))
        db.session.commit()
        return True
    except Exception as e:
        db.session.rollback()
        logging.error(str(e))
        return False


def get_list_histories_dao(uid, is_dict_result=False):
    """Get list history

    :param uid: uid
    :param is_dict_result:
    :return:
    """
    result_foods = db.session.query(History, Food.image, Food.name) \
        .join(Food, Food.id == History.food_id).filter(History.uid == uid).all()
    if is_dict_result:
        list_result = []
        for food in result_foods:
            dict_result = object_to_dict_utils(food[0])
            dict_result['image'] = food[1]
            dict_result['food_name'] = food[2]
            list_result.append(dict_result)
        return list_result
    return result_foods
