# -*- coding: utf-8 -*-
# Author: Ned, Rowan
from project import db
from project.dao import allergy_dao
from project.dao import user_dao, city_dao
from project.models.ALSRecommend import ALSRecommend
from project.models.Food import Food
from project.models.FoodTag import FoodTag
from project.models.Store import Store
from project.models.Rating import Rating
from project.models.Tag import Tag
from project.models.User import User
from project.utils.allergy_utils import is_allergy
from project.utils.object_utils import list_object_to_dict_utils

MAX_RECOMMEND = 5


def get_recommend_food(location, uid):
    """Get food with descending rate

    :param location: location of user
    :param uid: user_id
    :return: list food object
    """
    location_format = "%{}".format(location)
    if is_vegan_user(uid):
        data = get_vegetarian_food_by_location_recommend(location, uid)
    else:
        if is_existed_user_in_als(uid):
            foods = db.session.query(Food).join(Store, Store.restaurant_id == Food.restaurant_id) \
                .join(ALSRecommend, Food.food_id == ALSRecommend.food_id) \
                .filter(ALSRecommend.user_id == uid).filter(Store.address.like(location_format))\
                .order_by(ALSRecommend.rating.desc()).all()
        else:
            foods = db.session.query(Food).join(Store, Store.restaurant_id == Food.restaurant_id) \
                .join(Rating, Food.food_id == Rating.food_id) \
                .filter(Rating.user_id == uid).filter(Store.address.like(location_format))\
                .order_by(Rating.rate.desc()).all()
        allergies = allergy_dao.get_list_allergy_by_uid_dao(uid, is_list_result=True)
        results_without_allergy = []
        for food in foods:
            if not is_allergy(food, allergies):
                results_without_allergy.append(food)
            if len(results_without_allergy) == MAX_RECOMMEND:
                break
        data = list_object_to_dict_utils(results_without_allergy)
    return data


def is_existed_user_in_als(uid):
    """Check if user is exist in als_recommend table

    :param uid: user id
    :return:
    """
    user = ALSRecommend.query.filter_by(user_id=uid).first()
    if user:
        return True
    else:
        return False


def check_location_user(location, uid):
    """Check location and user in database

    :param location: location of user
    :param uid: user id
    :return: False if fail, True if success
    """
    user = user_dao.get_user_by_uid_dao(uid)
    location = city_dao.get_city_by_name(location)
    if user and location:
        return True
    else:
        return False


def get_vegetarian_food_by_location_recommend(location, uid):
    """Get vegetarian food by location

    :param location: location
    :param uid: uid
    :return: list food
    """
    location_format = "%{}".format(location)
    get_vegetarian_food_script = db.session.query(Food).join(FoodTag, Food.food_id == FoodTag.food_id) \
            .join(Tag, FoodTag.tag_id == Tag.id) \
            .filter(Tag.name == 'chay')
    get_food_by_location_script = db.session.query(Food).join(Store, Food.restaurant_id == Store.restaurant_id) \
            .filter(Store.address.like(location_format))
    results = get_vegetarian_food_script.intersect(get_food_by_location_script).order_by(db.func.random()).limit(
        MAX_RECOMMEND).all()
    if results:
        foods = list_object_to_dict_utils(results)
        return foods
    else:
        return None


def is_vegan_user(uid):
    """Check if user is vegan

    :param uid: uid
    :return: True false
    """
    is_vegan = db.session.query(User.is_vegetarian).filter_by(uid=uid).one()
    return bool(is_vegan[0])

