# -*- coding: utf-8 -*-
# Author: Rowan

from project.dao import fcm_token_dao


def save_fcm_token_service(fcm_token_obj):
    """Save fcm token

    :param fcm_token_obj: fcm_token_obj is model
    :return: True if save success
    """
    for fcm_token in get_fcm_token_by_uid_service(fcm_token_obj.uid):
        if fcm_token is fcm_token_obj.fcm_token:
            return True
    return fcm_token_dao.save_fcm_token_dao(fcm_token_obj)


def get_fcm_token_by_uid_service(uid):
    """Get fcm token by uid

    :param uid: uid
    :return: a list of tuple fcm_token
    """
    tuple_results = fcm_token_dao.get_distinct_fcm_token_by_uid_dao(uid)
    list_results = []
    for result in tuple_results:
        list_results.append(result[0])
    return list_results
