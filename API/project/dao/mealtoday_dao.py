# -*- coding: utf-8 -*-
# Author: Ned
import logging

from project import db
from project.models.Store import Store
from project.models.Food import Food
from project.models.Tag import Tag
from project.models.FoodTag import FoodTag
from project.utils.object_utils import object_to_dict_utils


def get_lat_long_from_food_id(food_id):
    """Get latitude and longitude from food id

    :param food_id:
    :return: latitude and longitude
    """
    try:
        lat_long = db.session.query(Store.latitude, Store.longitude)\
            .join(Food, Food.restaurant_id == Store.restaurant_id)\
            .filter(Food.food_id == food_id).first()
        return lat_long
    except Exception as e:
        logging.error(str(e))
        return None


def get_vegetarian_food_by_location_mealtoday(location, uid):
    """Get vegetarian food by location

    :param location: location
    :param uid: uid
    :return: food
    """
    location_format = "%{}".format(location)
    get_vegetarian_food_script = db.session.query(Food).join(FoodTag, Food.food_id == FoodTag.food_id) \
            .join(Tag, FoodTag.tag_id == Tag.id) \
            .filter(Tag.name == 'chay')
    get_food_by_location_script = db.session.query(Food).join(Store, Food.restaurant_id == Store.restaurant_id) \
        .filter(Store.address.like(location_format))
    results = get_vegetarian_food_script.intersect(get_food_by_location_script).order_by(db.func.random()).first()
    if results:
        foods = object_to_dict_utils(results)
        return foods
    else:
        return None
