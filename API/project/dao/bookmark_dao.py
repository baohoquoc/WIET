# -*- coding: utf-8 -*-
# Author: Rowan
import logging

from project import db
from project.models.Bookmark import Bookmark
from project.models.Food import Food
from project.models.Store import Store
from project.utils.object_utils import object_to_dict_utils


def get_bookmark_dao(uid, food_id):
    """Get a bookmark by uid and food_id

    :param uid: uid
    :param food_id: food_id
    :return: bookmark
    """
    return Bookmark.query.filter_by(uid=uid, food_id=food_id).one()


def create_bookmark_dao(uid, food_id):
    """Create bookmark

    :param uid: uid
    :param food_id: food_id
    :return: True if success
    """
    try:
        db.session.add(Bookmark(uid=uid, food_id=food_id))
        db.session.commit()
        return True
    except Exception as e:
        db.session.rollback()
        logging.error(str(e))
        return False


def delete_bookmark_dao(uid, food_id):
    """Delete bookmark

    :param uid: uid
    :param food_id: food_id
    :return: True if success
    """
    try:
        db.session.delete(get_bookmark_dao(uid=uid, food_id=food_id))
        db.session.commit()
        return True
    except Exception as e:
        db.session.rollback()
        logging.error(str(e))
        return False


def is_existed_bookmark_dao(uid, food_id):
    """Is existed bookmark

    :param uid: uid
    :param food_id: food_id
    :return: True if existed
    """
    return Bookmark.query.filter_by(uid=uid, food_id=food_id).first() is not None


def get_list_bookmarks_dao(uid, is_dict_result=False):
    """Get list bookmarks

    :param uid: uid
    :param is_dict_result: True return dict result
    :return: list bookmarks
    """
    results = db.session.query(Food, Store.address) \
        .join(Bookmark, Food.id == Bookmark.food_id) \
        .join(Store, Food.restaurant_id == Store.restaurant_id) \
        .filter(Bookmark.uid == uid).all()
    if is_dict_result:
        results_list = []
        for result in results:
            results_dict = object_to_dict_utils(result[0])
            results_dict['address'] = result[1]
            results_list.append(results_dict)
        return results_list
    return results
