# -*- coding: utf-8 -*-
# Author: Ned
import logging

from project import db
from project.models.Food import Food
from project.models.Rating import Rating
from project.models.Store import Store
from project.models.Tag import Tag
from project.models.User import User
from project.utils.object_utils import object_to_dict_utils


def rating_dao(uid, rating):
    """Plus point for user's rating

    :param uid: user_id
    :param rating: rating object include meta tag and tag
    :return True if success, False if fail
    """
    try:
        for obj in rating:
            db.session.query(db.func.insert_into_rating_table_by_meta_tag_id(uid, obj['meta_tag_id'], 3)).all()
            for tag_id in obj['tag_ids']:
                db.session.query(db.func.insert_into_rating_table_by_tag_id(uid, int(tag_id), 2)).all()
        # Update first login
        user = User.query.filter_by(uid=uid).first()
        user.is_first_login = False
        db.session.commit()
        return True
    except Exception as e:
        db.session.rollback()
        logging.error(str(e))
        return False


def get_top_rating_dao(top, offset):
    """Get top rating dao

    :param offset: offset
    :param top: limit top foods
    :return: top food dict
    """
    results = db.session.query(Food, db.func.sum(Rating.rate), Store.food_name) \
        .join(Rating, Food.food_id == Rating.food_id) \
        .join(Store, Food.restaurant_id == Store.restaurant_id) \
        .group_by(Food.id, Store.food_name).order_by(db.func.sum(Rating.rate).desc()).offset(
        (offset - 1) * top).limit(top).all()
    result_list = []
    rank = 0
    for result in results:
        rank += 1
        result_dict = object_to_dict_utils(result[0])
        result_dict['rate'] = result[1]
        result_dict['description'] = result[2]
        result_dict['rank'] = rank
        result_list.append(result_dict)
    return result_list


def skip_rating_dao(uid):
    """Skip rating

    :param uid: uid
    :return: True if success
    """
    try:
        rice_tag_id = db.session.query(Tag.id).filter(Tag.name.ilike('Cơm')).first()
        db.session.query(db.func.insert_into_rating_table_by_tag_id(uid, rice_tag_id, 1)).all()
        user = User.query.filter_by(uid=uid).first()
        user.is_first_login = False
        db.session.commit()
        return True
    except Exception as e:
        db.session.rollback()
        logging.error(str(e))
        return False


def add_rating_dao(uid, food_id, rating):
    """

    :param uid: user_id
    :param food_id: foo_id
    :param rating: rating
    :return: True if success, false if fail
    """
    try:
        db.session.query(db.func.insert_into_rating_table(uid, food_id, rating)).one()
        db.session.commit()
        return True
    except Exception as e:
        logging.error(str(e))
        return False


def subtract_rating_dao(uid, food_id, rating):
    """

    :param uid: user_id
    :param food_id: foo_id
    :param rating: rating
    :return: True if success, false if fail
    """
    try:
        db.session.query(db.func.subtract_rating(uid, food_id, rating)).one()
        db.session.commit()
        return True
    except Exception as e:
        logging.error(str(e))
        return False
