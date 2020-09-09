# -*- coding: utf-8 -*-
# Author: Rowan
import logging

from project import db
from project.models.User import User
from project.utils.object_utils import object_to_dict_utils


def create_new_user_dao(user):
    """Create new user in database

    :param user: firebase user object
    :return: True if create success
    """
    try:
        new_user = User(uid=user.uid, avatar=user.photo_url, email=user.email, fullname=user.display_name)
        db.session.add(new_user)
        db.session.commit()
        return True
    except Exception as e:
        db.session.rollback()
        logging.error(str(e))
        return False


def get_user_by_uid_dao(uid, is_dict_result=False):
    """Get user by uid

    :param uid: uid of user
    :param is_dict_result: True return dict, False return object
    :return: user information
    """
    user = User.query.filter_by(uid=uid).first()
    if is_dict_result:
        return object_to_dict_utils(user)
    return user


def update_user_dao(user_obj):
    """Update user

    :param user_obj: user object
    :return: True if update success
    """
    try:
        User.query.filter_by(uid=user_obj.uid).update(object_to_dict_utils(user_obj))
        db.session.commit()
    except Exception as e:
        db.session.rollback()
        logging.error(str(e))
        return False


def get_all_uid_dao():
    """Get all uid

    :return: A list of tuples uid
    """
    return db.session.query(User.uid).all()


def update_location_dao(uid, location):
    """Update location

    :param uid: uid
    :param location: location
    :return: True if success
    """
    try:
        user = get_user_by_uid_dao(uid, is_dict_result=False)
        user.location = location
        db.session.commit()
        return True
    except Exception as e:
        db.session.rollback()
        logging.error(str(e))
        return False
