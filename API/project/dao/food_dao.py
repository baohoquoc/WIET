# -*- coding: utf-8 -*-
# Author: Ned, Rowan
from project import db
from project.models.Food import Food
from project.models.FoodTag import FoodTag
from project.models.MetaTag import MetaTag
from project.models.MetaTagSuperTag import MetaTagSuperTag
from project.models.Store import Store
from project.models.SuperTag import SuperTag
from project.models.Tag import Tag
from project.models.TagMetaTag import TagMetaTag
from project.utils.object_utils import list_object_to_dict_utils, object_to_dict_utils


def get_food_by_location(location, is_dict_result=False):
    """Get food by location

    :param location: city that user is in
    :param is_dict_result: Return dict if True
    :return: List food corresponding of location
    """
    result = db.session.query(Food).join(FoodTag, Food.food_id == FoodTag.food_id) \
        .join(Tag, FoodTag.tag_id == Tag.id) \
        .filter(Tag.name == location).all()
    if is_dict_result:
        return list_object_to_dict_utils(result)
    return result


def get_food_from_meta_tag(id_meta_tag, is_dict_result=False):
    """Get food by meta_tag

    :param id_meta_tag: id of meta tag
    :param is_dict_result: Return dict if True
    :return: List food corresponding of meta tag
    """
    result = db.session.query(Food).join(FoodTag, Food.food_id == FoodTag.food_id) \
        .join(Tag, FoodTag.tag_id == Tag.id) \
        .join(TagMetaTag, TagMetaTag.tag_id == Tag.id).join(MetaTag, MetaTag.id == TagMetaTag.meta_tag_id) \
        .filter(TagMetaTag.id == id_meta_tag).all()
    if is_dict_result:
        return list_object_to_dict_utils(result)
    return result


def get_food_from_tag(tag_id, is_dict_result=False):
    """Get food by tag

    :param tag_id: id of tag
    :param is_dict_result: Return dict if True
    :return: List food corresponding of tag
    """
    result = db.session.query(Food).join(FoodTag, Food.food_id == FoodTag.food_id) \
        .join(Tag, FoodTag.tag_id == Tag.id) \
        .filter(Tag.id == tag_id).all()
    if is_dict_result:
        return list_object_to_dict_utils(result)
    return result


def get_food_and_store_by_id(food_id, is_dict_result=False):
    """Get food and store by id

    :param is_dict_result: True return dict
    :param food_id: food_id
    :return: food
    """
    result = db.session.query(Food, Store).join(Store, Food.restaurant_id == Store.restaurant_id) \
        .filter(Food.id == food_id).first()
    food, store = result
    if is_dict_result:
        return object_to_dict_utils(food), object_to_dict_utils(store)
    return result


def get_food_by_id(food_id, is_dict_result=False):
    """Get food by id

    :param is_dict_result: True return dict
    :param food_id: food_id
    :return: food
    """
    result = db.session.query(Food).filter(Food.id == food_id).first()
    if is_dict_result:
        return object_to_dict_utils(result)
    return result


def search_limited_food_dao(keyword, limit, is_dict_result=False):
    """Search limited food dao

    :param keyword: keyword
    :param limit: limit
    :param is_dict_result: True return dict
    :return: limited foods
    """
    escaped_keyword = '%{}%'.format(keyword.replace('%', '/%').replace('_', '/_'))
    result_foods = db.session.query(Food, Store.food_name) \
        .join(Store, Store.restaurant_id == Food.restaurant_id) \
        .order_by(Food.name).filter(Food.name.ilike(escaped_keyword)).limit(limit).all()
    if is_dict_result:
        result_foods_list = []
        for result_food in result_foods:
            result_foods_dict = object_to_dict_utils(result_food[0])
            result_foods_dict['description'] = result_food[1]
            result_foods_list.append(result_foods_dict)
        return result_foods_list
    return result_foods


def get_food_from_super_tag_name_and_location(super_tag_name, location):
    """Get food by super_tag

    @param super_tag_name: name of meta tag
    @param location: location of user
    @return: List food corresponding of super tag
    """
    location_format = "%{}".format(location)
    get_food_by_super_tag_script = db.session.query(Food).join(FoodTag, Food.food_id == FoodTag.food_id) \
        .join(Tag, Tag.id == FoodTag.tag_id) \
        .join(TagMetaTag, Tag.id == TagMetaTag.tag_id) \
        .join(MetaTag, MetaTag.id == TagMetaTag.meta_tag_id) \
        .join(MetaTagSuperTag, MetaTag.id == MetaTagSuperTag.meta_tag_id) \
        .join(SuperTag, SuperTag.id == MetaTagSuperTag.super_tag_id) \
        .filter(SuperTag.name == super_tag_name)

    get_food_by_location_script = db.session.query(Food).join(Store, Food.restaurant_id == Store.restaurant_id) \
        .filter(Store.address.like(location_format))
    results = get_food_by_super_tag_script.intersect(get_food_by_location_script).order_by(db.func.random()).first()
    if results is not None:
        food = object_to_dict_utils(results)
        return food
    return None


def get_food_id_by_id(id_food):
    """Get food_id from id

    :param id_food: id
    :return: food_id
    """
    food_id = db.session.query(Food.food_id).filter(Food.id == id_food).first()
    return food_id


def get_list_same_store_food_by_food_id_dao(food_id, is_dict_result=False):
    """Get list same store food by food id

    :param food_id: food id
    :param is_dict_result: If True return dict
    :return: list same store food
    """
    get_store_query = db.session.query(Food.restaurant_id).filter(Food.id == food_id)
    result = db.session.query(Food).filter(Food.restaurant_id == get_store_query)
    if is_dict_result:
        return list_object_to_dict_utils(result)
    return result


def get_food_store_by_location_webhook(location, limit):
    """Get food and store

    @param location: location
    @param limit: limit
    @return:
    """
    results = db.session.query(Food, Store).join(Store, Food.restaurant_id == Store.restaurant_id).filter(
        Store.address.like("%{}".format(location))).order_by(db.func.random()).limit(limit).all()
    if results is not None:
        list_food_store = []
        for result in results:
            food, store = result
            dict_food = food.__dict__
            del dict_food['_sa_instance_state']
            dict_store = store.__dict__
            dict_food['store_name'] = dict_store['food_name']
            dict_food['address'] = dict_store['address']
            list_food_store.append(dict_food)
        return list_food_store
    else:
        return None
