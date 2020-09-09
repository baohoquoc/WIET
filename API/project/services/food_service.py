# -*- coding: utf-8 -*-
# Author: Rowan, Ned
from project.dao import bookmark_dao
from project.dao import food_dao
from project.services import rating_service, recommend_service
from project.services.image_service import update_protocol_for_a_dict, update_protocol_for_a_list_of_dict

DEFAULT_LIMIT = 5


def get_food_detail_service(uid, id_food):
    """Get food detail service

    :param uid: uid
    :param id_food: id_food
    :return: food detail
    """
    if not id_food.isdigit():
        return None
    food, store = food_dao.get_food_and_store_by_id(id_food, is_dict_result=True)
    food['id_food'] = food.pop('id')
    store['id_store'] = store.pop('id')
    store['store_name'] = store.pop('food_name')
    food.update(store)
    result = food
    if result is not None:
        result['is_bookmark'] = bookmark_dao.is_existed_bookmark_dao(uid, id_food)
    food_id = get_food_id_by_id(id_food)
    rating_service.add_rating_service(uid, food_id, 1)
    return update_protocol_for_a_dict('image', result, uid)


def is_existed_food_id(food_id):
    """Is existed food_id

    :param food_id: food_id
    :return: True if existed
    """
    return food_dao.get_food_by_id(food_id) is not None


def search_limited_food_service(keyword, limit, uid):
    """Search limited food service

    :param uid: uid
    :param keyword: keyword
    :param limit: limit
    :return: limited foods
    """
    if limit is None or not limit.isdigit():
        limit = DEFAULT_LIMIT
    if keyword is None:
        keyword = ''
    return update_protocol_for_a_list_of_dict('image',
                                              food_dao.search_limited_food_dao(keyword, limit, is_dict_result=True),
                                              uid)


def get_food_from_super_tag_name_and_location_service(super_tag_name, location):
    """Get food by super_tag

    @param super_tag_name: name of meta tag
    @param location: location of user
    @return: List food corresponding of super tag
    """
    return food_dao.get_food_from_super_tag_name_and_location(super_tag_name, location)


def get_notification_meal_service(uid, meal_in, location):
    """Get notification meal

    :param uid: uid
    :param meal_in: meal_in
    :param location: location
    :return: food
    """
    if recommend_service.check_location_and_user(location, uid):
        return food_dao.get_food_from_super_tag_name_and_location(meal_in, location)
    return None


def get_food_id_by_id(id_food):
    """Get food_id from id

    :param id_food: id
    :return: food_id
    """
    return food_dao.get_food_id_by_id(id_food)


def get_list_same_store_food_by_food_id_service(small_food_id, uid):
    """Get list same store food by food id

    :param uid: uid
    :param small_food_id: small food id
    :return: list same store food
    """
    results = food_dao.get_list_same_store_food_by_food_id_dao(small_food_id, is_dict_result=True)
    return update_protocol_for_a_list_of_dict('image', results, uid)
