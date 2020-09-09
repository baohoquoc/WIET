# -*- coding: utf-8 -*-
# Author: Rowan
import logging

from project import db
from project.models.FCMToken import FCMToken


def save_fcm_token_dao(fcm_token_obj):
    """Save fcm token

    :param fcm_token_obj: fcm_token is model
    :return: True if save success
    """
    try:
        db.session.add(fcm_token_obj)
        db.session.commit()
        return True
    except Exception as e:
        db.session.rollback()
        logging.error(str(e))
        return False


def get_distinct_fcm_token_by_uid_dao(uid):
    """Get distinct fcm token by uid

    :param uid: uid
    :return: return a list of tuples
    """
    result = db.session.query(FCMToken.fcm_token).distinct(FCMToken.fcm_token).filter_by(uid=uid).all()
    return result
