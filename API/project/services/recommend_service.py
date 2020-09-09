# -*- coding: utf-8 -*-
# Author: Ned
from project.dao import recommend_dao
from project.services import mealtoday_service, user_service
from project.services.image_service import update_protocol_for_a_list_of_dict


def get_recommend_food_service(location, uid):
    """Get food with descending rate

    :param location: location of user
    :param uid: user_id
    :return: list food object
    """
    recommend_foods = recommend_dao.get_recommend_food(location, uid)
    if recommend_foods:
        for food in recommend_foods:
            food['latitude'], food['longitude'] = mealtoday_service.get_lat_long_from_food_id(food['food_id'])
        return update_protocol_for_a_list_of_dict('image', recommend_foods, uid)
    else:
        return None


def get_location_for_recommend_by_uid_service(uid):
    """Get location by uid

    :param uid: uid
    :return: location if exist, None if user or location not exist
    """
    return user_service.get_location_by_uid_service(uid)


def check_location_and_user(location, uid):
    """Check location and user in database

    :param location: location of user
    :param uid: user id
    :return: False if fail, True if success
    """
    return recommend_dao.check_location_user(location, uid)


def is_vegan_user_service(uid):
    """Check if user is vegan

    :param uid: uid
    :return: True false
    """
    return recommend_dao.is_vegan_user(uid)
